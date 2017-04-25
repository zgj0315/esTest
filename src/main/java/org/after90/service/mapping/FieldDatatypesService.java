package org.after90.service.mapping;

import lombok.extern.slf4j.Slf4j;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
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


    public void arrayDatatype() {
        String strIndex = "my_index1";
        String strType = "my_type";
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
}
