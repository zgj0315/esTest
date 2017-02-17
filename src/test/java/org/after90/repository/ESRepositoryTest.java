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
 * @since <pre>Feb 16, 2017</pre>
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
    }

    /**
     * Method: bulidBulkProcessor()
     */
    @Test
    public void testBulidBulkProcessor() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ESRepository.getClass().getMethod("bulidBulkProcessor"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: closeClient()
     */
    @Test
    public void testCloseClient() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ESRepository.getClass().getMethod("closeClient"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: closeBulkProcessor()
     */
    @Test
    public void testCloseBulkProcessor() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = ESRepository.getClass().getMethod("closeBulkProcessor"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
