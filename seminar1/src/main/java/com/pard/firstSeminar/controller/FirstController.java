package com.pard.firstSeminar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller//controller로 쓸거임! 아래 코드
public class FirstController {

    @GetMapping("/hi")//hi로 끝나는 애가 왔을 때
    public String niceToMeetYou(Model model){
        model.addAttribute("username", "하민");
        return "greetings"; //greetings 파일을 찾아줌
    }

    @GetMapping("/bye") //endpoint
    public String seeYouNext(Model model){
        model.addAttribute("nickname","전추현"); //nickname 이라는 key 값에 전추현
        return "goodbye";
    }

}
