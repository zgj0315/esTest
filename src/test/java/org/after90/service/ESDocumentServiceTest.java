package test.org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.service.ESDocumentService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ESDocumentService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Feb 17, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class ESDocumentServiceTest {
    @Autowired
    private ESDocumentService esDocument;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: index()
     */
    @Test
    public void testIndex() throws Exception {
        esDocument.index();
    }

    /**
     * Method: get()
     */
    @Test
    public void testGet() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: delete()
     */
    @Test
    public void testDelete() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: bulkProcessor()
     */
    @Test
    public void testBulkProcessor() throws Exception {
        esDocument.bulkProcessor();
    }


} 
