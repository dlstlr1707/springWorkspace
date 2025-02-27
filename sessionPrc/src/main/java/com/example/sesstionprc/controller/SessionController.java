package com.example.sesstionprc.controller;

import com.example.sesstionprc.dto.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SessionController {
    private final List<UserDTO> users;

    @GetMapping("/")
    public String renderMain(
            HttpSession session,
            Model model
    ) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("username", username);
        }
        return "main";
    }
    @GetMapping("/login")
    public String renderLogin(){
        return "login";
    }
    @GetMapping("/join")
    public String renderJoin(){
        return "join";
    }

    @PostMapping("/login")
    public String loginExc(
            @RequestParam String userid,
            @RequestParam String password,
            HttpSession session,
            Model model
    ){
        UserDTO foundUser = null;
        for (UserDTO user : users) {
            if (user.getId().equals(userid) && user.getPassword().equals(password)) {
                foundUser = user;
                break;
            }
        }
        if(foundUser != null){
            session.setAttribute("username", foundUser.getUsername());
            model.addAttribute("username", foundUser.getUsername());
            return "redirect:/";
        }else{
            return "redirect:login";
        }

    }
    @PostMapping("/join")
    public String joinExc(
            @RequestParam String userid,
            @RequestParam String password,
            @RequestParam String username,
            HttpSession session,
            Model model
    ){
        UserDTO newUser = UserDTO.builder().id(userid).username(username).password(password).build();
        users.add(newUser);
        session.setAttribute("username", username);
        model.addAttribute("username", username);
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logoutExc(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
