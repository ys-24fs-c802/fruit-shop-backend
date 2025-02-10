package kr.co.cofile.fruitshop.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class DemoTests {

    @Test
    void uuidDemoTest() {
        String uuid = UUID.randomUUID().toString() + "cat.jpg";
        System.out.println(uuid);
    }

}
