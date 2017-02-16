package test.org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.service.ReadEsService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ReadEsService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Feb 16, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class ReadEsServiceTest {

    @Autowired
    private ReadEsService readES;
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: readES()
     */
    @Test
    public void testReadES() throws Exception {
        log.info("start ReadEsServiceTest");
        readES.readES();
        log.info("end ReadEsServiceTest");
    }


} 
