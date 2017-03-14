package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaogj on 12/03/2017.
 * 给es施加数据压力
 */
@Service
@Slf4j
public class PressESService {
    @Autowired
    private ESRepository es;

    public void data2ES() {
        for (int i = 0; i < 2000000000; i++) {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("strName", "张三" + i);
            json.put("strID", "1302222012020600" + i);
            json.put("dtCreate", new Date());
            es.bulkProcessor.add(new IndexRequest("zhaogj_test24",
                    "type_test",
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
