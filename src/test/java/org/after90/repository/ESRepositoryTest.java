package test.org.after90.repository;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.repository.ESRepository;
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


} 
