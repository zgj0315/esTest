package test.org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.service.PressESService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * PressESService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 12, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class PressESServiceTest {
    @Autowired
    private PressESService pressES;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: data2ES()
     */
    @Test
    public void testData2ES() throws Exception {
        pressES.data2ES();
    }


} 
