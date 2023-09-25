package three.seminar.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import three.seminar.basic.dto.HelloDto;

@Slf4j
@RestController
public class Path {
    @GetMapping("mapping/{userId}")
    public String mappingPaht(@PathVariable("userId") String userId) {
        log.info("mapping userId = {}", userId);
        return "userId = " + userId;
    }

    @GetMapping("mapping/users/{userId}/age/{ageage}")
    public String mappingPath(@PathVariable("userId") String userId, @PathVariable(required = false) int ageage) {
        log.info("mapping userId = {}, age = {}", userId, ageage);
        return "userId = " + userId + ", age = " + ageage;
    }

    @GetMapping("requestParam/users")
    public String mappingPath(@RequestParam(defaultValue = "cofls") String name, @RequestParam(required = false) Integer age) {
        log.info("RequestParam name ={}, age ={}", name, age);
        return "name =" + name + ", age = " + age;
    }

    @ResponseStatus(HttpStatus.CREATED) //default = 200
    @PostMapping("/requestBody")
    public String requestBody(@RequestBody HelloDto helloDto){
        log.info("RequestBody name = {}, age = {}", helloDto.getUserName(), helloDto.getAge());
        return "name = " + helloDto.getUserName() + ", age = " + helloDto.getAge();
    }

}