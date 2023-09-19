package com.pard.hw1st.controller;


// TODO: MainController를 만들어주세요.

import com.pard.hw1st.dto.MyInfoDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MainController {

    @GetMapping("")
    public String startPage() {
        return "start";
    }

    @GetMapping("/submitInfo")
    public String showForm() {

        return "submitInfo";
    }

    @PostMapping("/showInfo")
    public String newInfoForm(MyInfoDto myInfoDto, Model model) {
        model.addAttribute("major", myInfoDto.getMajor());
        model.addAttribute("grade", myInfoDto.getGrade());
        model.addAttribute("age", myInfoDto.getAge());
        model.addAttribute("hobby", myInfoDto.getHobby());
        model.addAttribute("name", myInfoDto.getName());
        model.addAttribute("hometown", myInfoDto.getHometown());
        model.addAttribute("introduction", myInfoDto.getIntroduction());
        log.info(myInfoDto.toString());
        return "showInfo";
    }

}
