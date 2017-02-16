package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by zhaogj on 04/11/2016.
 */
@Service
@Slf4j
public class ESService {
    @Autowired
    private ESRepository es;


    /**
     * 检查es连接是否正常，如果不正常就尝试重新连接
     */
    public void keepAlive() {
        while (true) {
            if (es.client == null) {
                esInit();
            }
            try {
                ClusterHealthResponse healths = es.client.admin().cluster().prepareHealth().get();
                ClusterHealthStatus status = healths.getStatus();
                log.info("es status:{}", status.name());
            } catch (Exception e) {
                log.error("es status err", e);
                esInit();
            }
            try {
                Thread.sleep(1000 * 3);
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    /**
     * es连接初始化
     */
    public void esInit() {
        log.info("start es init");
        try {
            es.init();
        } catch (Exception e) {
            log.error("es init err", e);
        }
        log.info("end es init");
    }

}
