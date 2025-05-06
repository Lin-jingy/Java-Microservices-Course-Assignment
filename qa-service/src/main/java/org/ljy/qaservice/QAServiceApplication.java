package org.ljy.qaservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
    "org.ljy.qaservice",
    "org.ljy.userservice"
})
@EnableDubbo
public class QAServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QAServiceApplication.class, args);
    }
}

