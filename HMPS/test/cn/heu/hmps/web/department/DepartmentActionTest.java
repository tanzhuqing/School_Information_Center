package cn.heu.hmps.web.department;

import cn.heu.hmps.service.department.IDepartmentService;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * User: Yingtao.Q
 * Date: 12-4-8
 * Time: 下午12:15
 */
@ContextConfiguration(locations = { "classpath*:resources/spring/applicationContext*.xml" })
public class DepartmentActionTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private IDepartmentService departmentService;
    @Test
    public void testListMainDepartmentJson() {
        List list = this.departmentService.getMainDepartment("直属部门");
        int i = 0;
        assertEquals(0, i);
    }
}
