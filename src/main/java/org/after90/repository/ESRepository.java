package org.after90.repository;

import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;

/**
 * Created by zhaogj on 16/02/2017.
 */
@Repository
@Slf4j
public class ESRepository {
    public TransportClient client = null;
    public BulkProcessor bulkProcessor = null;

    @Value("${es.strClusterName}")
    private String strClusterName;

    @Value("${es.strTransportHostNames}")
    private String strTransportHostNames;

    private Splitter splitter = Splitter.on(",").trimResults();

    public void buildClient() throws Exception {
        log.info("strClusterName:{}, strTransportHostNames:{}", strClusterName, strTransportHostNames);

        Settings settings = Settings.builder()
                .put("cluster.name", strClusterName).put("client.transport.sniff", true).build();
//        Iterable<String> itTransportHostName = splitter.split(strTransportHostNames);
//        client = new PreBuiltTransportClient(settings).addTransportAddress(
//                new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//        for (String strTransportHostName : itTransportHostName) {
//            client.addTransportAddress(
//                    new InetSocketTransportAddress(InetAddress.getByName(strTransportHostName), 9300));
//        }

         client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));

    }

    public void bulidBulkProcessor() throws Exception {
        bulkProcessor = BulkProcessor.builder(client, new BulkProcessor.Listener() {
            @Override
            public void beforeBulk(long executionId, BulkRequest request) {
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, BulkResponse response) {
            }

            @Override
            public void afterBulk(long executionId, BulkRequest request, Throwable failure) {
            }
        }).setBulkActions(5000).setBulkSize(new ByteSizeValue(10, ByteSizeUnit.MB))
                .setFlushInterval(new TimeValue(1000L * 5L)).setConcurrentRequests(1).build();
    }

    public void closeClient() {
        if (client != null) {
            client.close();
        }
    }

    public void closeBulkProcessor() {
        if (bulkProcessor != null) {
            bulkProcessor.close();
        }
    }
}
