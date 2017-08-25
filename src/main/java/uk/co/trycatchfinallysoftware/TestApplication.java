package uk.co.trycatchfinallysoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "uk.co.trycatchfinally"
})
public class TestApplication {
    public void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
