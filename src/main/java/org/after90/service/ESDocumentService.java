package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by zhaogj on 17/02/2017.
 */
@Service
@Slf4j
public class ESDocumentService {
    @Autowired
    private ESRepository es;
    @Value("${es.index.strGuestIndex}")
    private String strGuestIndex;
    @Value("${es.index.strGuestType}")
    private String strGuestType;

    public void index() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("strName", "张三");
        json.put("strID", "1302222012020600");
        json.put("dtCreate", new Date());
        IndexResponse response = es.client.prepareIndex(strGuestIndex,
                strGuestType,
                "id_").setSource(json).get();
        // Index name
        String _index = response.getIndex();
        // Type name
        String _type = response.getType();
        // Document ID (generated or not)
        String _id = response.getId();
        // Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
        // status has stored current instance statement.
        RestStatus status = response.status();
        log.info("index:{}, type:{}, id:{}, version:{}, status.name:{}", _index, _type, _id, _version, status.name());
    }

    public void get() {
        GetResponse response = es.client.prepareGet(strGuestIndex, strGuestType, "id_").get();

    }

    public void delete() {
        DeleteResponse response = es.client.prepareDelete(strGuestIndex, strGuestType, "id_").get();
    }

    public void bulkProcessor() {
        for (int i = 0; i < 100; i++) {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("strName", "张三" + i);
            json.put("strID", "1302222012020600" + i);
            json.put("dtCreate", new Date());
            es.bulkProcessor.add(new IndexRequest(strGuestIndex,
                    strGuestType,
                    "id_" + i).source(json));
        }
        //这里是个坑！如果你不等会，直接退出会丢数据，或者flush一下
        try {
            //Thread.sleep(1000 * 10);
        } catch (Exception e) {
            log.error("", e);
        }
        es.bulkProcessor.flush();
    }
}
