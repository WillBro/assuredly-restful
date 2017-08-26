package uk.co.trycatchfinallysoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {
        "uk.co.trycatchfinallysoftware"
})
public class TestApplication {
    public void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
