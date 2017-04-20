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
        long lStart = System.currentTimeMillis();
        for (int j = 0; j < 200; j++) {
            for (int i = 0; i < 2000000000; i++) {
                Map<String, Object> json = new HashMap<String, Object>();
                json.put("strName", "张三" + i);
                json.put("strID", "1302222012020600" + i);
                json.put("adstrID", "1302222012020600" + i);
                json.put("dtCreate", new Date());
                es.bulkProcessor.add(new IndexRequest("zhaogj_test01",
                        "type_test",
                        "id_" + j + "_" + i).source(json));
                if (i % 1000000 == 0) {
                    log.info("speed: {}/s", 1000000 * 1000 / (System.currentTimeMillis() - lStart));
                    lStart = System.currentTimeMillis();
                }
            }
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
