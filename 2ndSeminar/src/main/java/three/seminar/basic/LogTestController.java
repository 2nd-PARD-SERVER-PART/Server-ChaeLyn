package three.seminar.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    @RequestMapping("/log-test")
        public String logtest(){
            String name = "lyn";
            System.out.println("name = " + name); //메모리 먹음
            log.info("info log = {}" , name);
            log.trace("info log = {}" , name);
            log.debug("info log = {}" , name);
            log.warn("info log = {}" , name);
            log.error("info log = {}" , name); //ㅁㅔ모리 안 잡아먹음
            log.info("Wrong log = " + name); //메모리 와앙

            return name;
        }
}
