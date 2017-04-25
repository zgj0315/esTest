package test.org.after90.service.mapping;

import lombok.extern.slf4j.Slf4j;
import org.after90.Application;
import org.after90.service.mapping.FieldDatatypesService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * FieldDatatypesService Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Apr 25, 2017</pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Slf4j
public class FieldDatatypesServiceTest {
    @Autowired
    private FieldDatatypesService fieldDatatypes;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testArrayDatatype() throws Exception {
        fieldDatatypes.arrayDatatype();
    }

    @Test
    public void testIpDatatype() throws Exception {
        fieldDatatypes.ipDatatype();
    }


} 
