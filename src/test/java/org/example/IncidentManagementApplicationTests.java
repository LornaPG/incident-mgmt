package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IncidentManagementApplicationTests {

    @Test
    public void contextLoads() {
        // 验证 ApplicationContext 是否成功加载
        assertThat(true).isTrue();
    }

    @Test
    public void testApplicationStartup() {
        // 你可以在这里添加更多的启动验证逻辑
        SpringApplication application = new SpringApplication(IncidentManagementApplication.class);
        var context = application.run();

        // 检查上下文是否包含某些 Bean
        assertThat(context).isNotNull();
        assertThat(context.containsBean("IncidentManagementApplication")).isTrue();
    }
}
