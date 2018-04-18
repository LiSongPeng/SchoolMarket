package com.solar;

/**
 * @author LiHuiBo
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/spring.xml", "classpath*:/spring-servlet.xml"})
public class BaseJunitTest {

}
