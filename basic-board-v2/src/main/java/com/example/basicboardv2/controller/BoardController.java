package com.example.basicboardv2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {
    @GetMapping("/")
    public String boardList(){
        return "board-list";
    }

    @GetMapping("/write")
    public String boardWrite(){
        return "board-write";
    }

    @GetMapping("/detail")
    public String boardDetail(@RequestParam("id") Long id, Model model){
        model.addAttribute("id",id);
        return "board-detail";
    }
    /*
    // 내가만든코드
    @GetMapping("/update")
    public String boardUpdate(
            @RequestParam("id") Long id,
            @RequestParam("userId") String userId,
            @RequestParam("userName") String userName,
            Model model
    ){
        model.addAttribute("id",id);
        model.addAttribute("userId",userId);
        model.addAttribute("userName",userName);
        return "board-update";
    }
     */

    // 강사님 코드
    @GetMapping("/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("id",id);
        return "board-update";
    }

}
