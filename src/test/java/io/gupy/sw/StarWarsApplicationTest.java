package io.gupy.sw;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import io.gupy.sw.StarWarsApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StarWarsApplication.class)
@WebAppConfiguration
public class StarWarsApplicationTest {
    
    @Test
    public void contextLoads() {
    }
}