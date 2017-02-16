package org.after90.repository;

import com.google.common.base.Splitter;
import org.elasticsearch.action.admin.indices.template.put.PutIndexTemplateRequest;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.collect.MapBuilder;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.ByteSizeUnit;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.common.unit.TimeValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaogj on 03/11/2016.
 */
@Repository
public class ESRepository {
    public Client client = null;
    public BulkProcessor bulkProcessor = null;

    @Value("${es.strClusterName}")
    private String strClusterName;

    @Value("${es.strTransportHostNames}")
    private String strTransportHostNames;

    @Value("${es.strConcurrentRequests}")
    private String strConcurrentRequests;

    @Value("${es.index.strTemplateNamePrefixs}")
    private String strTemplateNamePrefixs;

    private Splitter splitter = Splitter.on(",").trimResults();

    public void init() throws Exception {
        buildClient();
        bulidBulkProcessor();
        buildTemplate();
    }

    private void buildClient() throws Exception {
        Iterable<String> itTransportHostName = splitter.split(strTransportHostNames);
        Settings settings = Settings.settingsBuilder().put("cluster.name", strClusterName)
                .put("client.transport.sniff", true).build();
        client = TransportClient.builder().settings(settings).build();
        for (String strTransportHostName : itTransportHostName) {
            ((TransportClient) client).addTransportAddress(
                    new InetSocketTransportAddress(InetAddress.getByName(strTransportHostName), 9300));
        }
    }

    private void bulidBulkProcessor() throws Exception {
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
                .setFlushInterval(new TimeValue(1000L * 5L)).setConcurrentRequests(Integer.parseInt(strConcurrentRequests)).build();
    }

    // 创建模版
    public void buildTemplate() throws Exception {
        Iterable<String> itTemplateNamePrefix = splitter.split(strTemplateNamePrefixs);
        IndicesAdminClient iac = client.admin().indices();
        for (String strTemplateNamePrefix : itTemplateNamePrefix) {
            PutIndexTemplateRequest pitr = new PutIndexTemplateRequest(strTemplateNamePrefix)
                    .template(strTemplateNamePrefix + "*");
            //number_of_shards 机器数减一,number_of_replicas 备份1份就是两份
            //如果你用单机测试，这段需要注释掉
//            pitr.settings(new MapBuilder<String, Object>().put("number_of_shards", 4).put("number_of_replicas", 1)
//                    .put("refresh_interval", "1s").map());
            Map<String, Object> defaultMapping = new HashMap<String, Object>();
            // 关闭_all
            defaultMapping.put("_all", new MapBuilder<String, Object>().put("enabled", false).map());
            defaultMapping.put("numeric_detection", false);
            defaultMapping.put("dynamic_templates",
                    new Object[]{
                            new MapBuilder<String, Object>().put("date_tpl",
                                    new MapBuilder<String, Object>().put("match", "dt*")
                                            .put("mapping",
                                                    new MapBuilder<String, Object>().put("type", "date")
                                                            .put("index", "not_analyzed").put("doc_values", true)
                                                            .map())
                                            .map())
                                    .map(),
                            new MapBuilder<String, Object>().put("geo_point_tpl",
                                    new MapBuilder<String, Object>().put("match", "geop*")
                                            .put("mapping",
                                                    new MapBuilder<String, Object>().put("type", "geo_point")
                                                            .put("index", "not_analyzed").put("doc_values", true)
                                                            .map())
                                            .map())
                                    .map(),
                            new MapBuilder<String, Object>().put("all_tpl",
                                    new MapBuilder<String, Object>().put("match", "*").put("mapping",
                                            new MapBuilder<String, Object>().put("type", "{dynamic_type}")
                                                    .put("index", "not_analyzed").put("doc_values", true).map())
                                            .map())
                                    .map()});
            pitr.mapping("_default_", defaultMapping);
            iac.putTemplate(pitr);
        }
    }

}
