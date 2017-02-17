package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepositoryBack;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaogj on 16/02/2017.
 */
@Service
@Slf4j
public class Data2ESService {
    @Autowired
    private ESRepositoryBack es;

    @Value("${es.index.strGuestIndex}")
    private String strGuestIndex;
    @Value("${es.index.strGuestType}")
    private String strGuestType;

    public void data2ES() {
        log.info("start data2ES");
        log.info("es.client.prepareIndex");
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("strName", "张三");
        json.put("strID", "1302222012020600");
        json.put("dtCreate", new Date());
        es.client.prepareIndex(strGuestIndex,
                strGuestType,
                "id_").setSource(json);
        log.info("es.bulkProcessor.add");
        for (int i = 0; i < 100; i++) {
            json = new HashMap<String, Object>();
            json.put("strName", "张三" + i);
            json.put("strID", "1302222012020600" + i);
            json.put("dtCreate", new Date());
            es.bulkProcessor.add(new IndexRequest(strGuestIndex,
                    strGuestType,
                    "id_" + i).source(json));
        }
        log.info("end data2ES");
    }
}
