package test.org.after90.service;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.service.ESClientAdminService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * ESClientAdminService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Feb 16, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class ESClientAdminServiceTest {
    @Autowired
    private ESClientAdminService esClientAdmin;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createIndex(String strIndex)
     */
    @Test
    public void testCreateIndex() throws Exception {
        esClientAdmin.createIndex("zhaogj");
    }

    /**
     * Method: clusterHealth()
     */
    @Test
    public void testClusterHealth() throws Exception {
        esClientAdmin.clusterHealth();

    }


} 
