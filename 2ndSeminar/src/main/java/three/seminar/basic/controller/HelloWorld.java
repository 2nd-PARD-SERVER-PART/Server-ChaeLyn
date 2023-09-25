package three.seminar.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloWorld {
    @RequestMapping({"/hello-world","hello"}) //둘 다 쳐도 됨
    public String helloWorld() {
        log.info("hello-world");
        return "hello world"; //얘가 뜸(사이트에)
    }

}
