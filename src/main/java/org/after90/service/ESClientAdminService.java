package org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhaogj on 16/02/2017.
 */
@Service
@Slf4j
public class ESClientAdminService {
    @Autowired
    private ESRepository es;

    public void createIndex(String strIndex) {
        es.client.admin().indices().prepareCreate(strIndex);
    }

    public void clusterHealth() {
        ClusterHealthResponse healths = es.client.admin().cluster().prepareHealth().get();
        String clusterName = healths.getClusterName();
        int numberOfDataNodes = healths.getNumberOfDataNodes();
        int numberOfNodes = healths.getNumberOfNodes();
        log.info("getClusterName:{}, getNumberOfDataNodes:{}, getNumberOfNodes:{}", clusterName, numberOfDataNodes, numberOfNodes);

//        for (ClusterIndexHealth health : healths) {
//            String index = health.getIndex();
//            int numberOfShards = health.getNumberOfShards();
//            int numberOfReplicas = health.getNumberOfReplicas();
//            ClusterHealthStatus status = health.getStatus();
//
//        }

    }
}
