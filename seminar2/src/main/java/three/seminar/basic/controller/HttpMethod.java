package three.seminar.basic.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users") //아래에 하나하나 써주기 싫으니까
public class HttpMethod {
    @GetMapping
    public String userAll(){
        return "get users";
    }

    @PostMapping
    public String addUser(){
        return "post user";
    }

    @GetMapping("/{userId}") //URL : /mapping/users/{userId}
    public String findUser(@PathVariable String userId){
        return "get user id " + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId) {
        return "update user id " + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId) {
        return "delete user id " + userId;
    }
}
