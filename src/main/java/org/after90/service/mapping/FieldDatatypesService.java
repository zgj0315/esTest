package org.after90.service.mapping;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;


/**
 * Created by zhaogj on 25/04/2017.
 */
@Service
@Slf4j
public class FieldDatatypesService {
    @Autowired
    private ESRepository es;

    private String strIndex = "my_index1";
    private String strType = "my_type";


    public void arrayDatatype() {
        es.client.admin().indices().prepareDelete(strIndex);

        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("message", "some arrays in this document...")
                    .array("tags", "elasticsearch", "wow")
                    .startArray("lists")
                    .startObject()
                    .field("name", "prog_list")
                    .field("description", "programming list")
                    .endObject()
                    .startObject()
                    .field("name", "cool_list")
                    .field("description", "cool stuff list")
                    .endObject()
                    .endArray()
                    .endObject();
            es.bulkProcessor.add(new IndexRequest(strIndex,
                    strType,
                    "1").source(builder));
            es.bulkProcessor.flush();

            builder = jsonBuilder()
                    .startObject()
                    .field("message", "no arrays in this document...")
                    .array("tags", "elasticsearch")
                    .startArray("lists")
                    .startObject()
                    .field("name", "prog_list")
                    .field("description", "programming list")
                    .endObject()
                    .endArray()
                    .endObject();
            es.bulkProcessor.add(new IndexRequest(strIndex,
                    strType,
                    "2").source(builder));
            es.bulkProcessor.flush();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    public void ipDatatype() {
        String strMapping = "{\n" +
                "      \"properties\": {\n" +
                "        \"ip_addr\": {\n" +
                "          \"type\": \"ip\"\n" +
                "        }\n" +
                "      }\n" +
                "}";
        es.delete(strIndex);
        es.create(strIndex, 18, 1);
        es.putMapping(strIndex, strType, strMapping);
        try {
            XContentBuilder builder = jsonBuilder()
                    .startObject()
                    .field("ip_addr", "192.168.1.1")
                    .endObject();
            es.bulkProcessor.add(new IndexRequest(strIndex,
                    strType,
                    "1").source(builder));
            es.bulkProcessor.flush();
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
