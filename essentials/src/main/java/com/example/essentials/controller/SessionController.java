package com.example.essentials.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

    @GetMapping("/login")
    public String login(
            HttpSession session,
            Model model
    ) {
        String username = (String)session.getAttribute("username");
        if(username != null) {
            model.addAttribute("username", username);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginExc(
            @RequestParam String username,
            HttpSession session
    ) {
        session.setAttribute("username", username);
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logoutExc(HttpSession session) {
        // model 안지워도 지워짐
        session.invalidate();
        return "redirect:login";
    }

}
