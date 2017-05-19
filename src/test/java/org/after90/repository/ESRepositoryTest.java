package test.org.after90.repository;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.repository.ESRepository;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ESRepository Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Feb 17, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class ESRepositoryTest {

    @Autowired
    private ESRepository es;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: buildClient()
     */
    @Test
    public void testBuildClient() throws Exception {
        es.buildClient();
        log.info("client is ok");
    }

    /**
     * Method: bulidBulkProcessor()
     */
    @Test
    public void testBulidBulkProcessor() throws Exception {
        es.buildClient();
        log.info("client is ok");
        es.bulidBulkProcessor();
        log.info("bulkProcessor is ok");
    }

    /**
     * Method: closeClient()
     */
    @Test
    public void testCloseClient() throws Exception {
        es.buildClient();
        log.info("client is ok");
        es.bulidBulkProcessor();
        log.info("bulkProcessor is ok");
        es.closeBulkProcessor();
        es.closeClient();

    }

    /**
     * Method: closeBulkProcessor()
     */
    @Test
    public void testCloseBulkProcessor() throws Exception {
        es.buildClient();
        log.info("client is ok");
        es.bulidBulkProcessor();
        log.info("bulkProcessor is ok");
        es.closeBulkProcessor();
        es.closeClient();
    }

    @Test
    public void testPutMapping() throws Exception {
        log.info("start put mapping");
        String strIndex = "index_zhaogj";
        String strType = "type_zhaogj";
        es.buildClient();
        es.delete(strIndex);
        es.create(strIndex, 1, 0);
        PropertiesClass properties = new PropertiesClass(new WebAssetClass());
        String strMapping = JSON.toJSONString(properties);
        log.info("strMapping:{}", strMapping);
        es.putMapping(strIndex, strType, strMapping);
        log.info("end put mapping");
    }

    @Test
    public void testIndex() throws Exception {
        String strIndex = "index_zhaogj";
        String strType = "type_zhaogj";
        es.buildClient();
        es.bulidBulkProcessor();
        es.delete(strIndex);
        es.create(strIndex, 1, 0);
        PropertiesClass properties = new PropertiesClass(new WebAssetClass());
        String strMapping = JSON.toJSONString(properties);
        es.putMapping(strIndex, strType, strMapping);
        for (int i = 0; i < 100; i++) {
            JSONObject object = new JSONObject();
            object.put("url", "www.nsfocus.com" + i);
            object.put("ip", "10.24.0." + i);
            object.put("banner", "this is a banner, No." + i);
            object.put("banner1", "this is a banner, No." + i);
            es.bulkProcessor.add(new IndexRequest(strIndex,
                    strType,
                    "id_" + i).source( object.toJSONString()));
        }
        es.bulkProcessor.flush();
    }
} 
