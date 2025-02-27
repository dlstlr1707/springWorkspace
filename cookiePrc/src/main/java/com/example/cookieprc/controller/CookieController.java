package com.example.cookieprc.controller;

import com.example.cookieprc.dto.UserDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CookieController {

    private final List<UserDTO> users;

    @GetMapping("/")
    public String renderMain(
            HttpServletRequest request,
            Model model
    ) {
        Cookie[] cookies = request.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if(username != null) {
            model.addAttribute("username", username);
            model.addAttribute("message","쿠키에서 사용자 정보를 읽었습니다.");
        }else{
            model.addAttribute("message","쿠키가 없습니다.");
        }
        return "main";
    }
    @GetMapping("/login")
    public String renderLogin() {
        return "login";
    }
    @GetMapping("/join")
    public String renderJoin() {
        return "join";
    }

    @PostMapping("/login")
    public String loginExc(
            @RequestParam String userid,
            @RequestParam String password,
            HttpServletResponse response,
            Model model
    ){
        UserDTO foundUser = null;
        for(UserDTO user : users) {
            if (user.getUserid().equals(userid)&&user.getPassword().equals(password)) {
                foundUser = user;
            }
        }
        if(foundUser != null) {
            Cookie cookie = new Cookie("username", foundUser.getUsername());
            cookie.setPath("/");
            cookie.setMaxAge(7*24*60*60);
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
            return "redirect:/";
        }else{
            return "redirect:/login";
        }
    }
    @PostMapping("/join")
    public String joinExc(
            @RequestParam String userid,
            @RequestParam String password,
            @RequestParam String username,
            HttpServletResponse response,
            Model model
    ){
        UserDTO newUser = UserDTO.builder()
                .userid(userid)
                .password(password)
                .username(username)
                .build();
        users.add(newUser);
        Cookie cookie = new Cookie("username", username);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7*24*60*60);

        response.addCookie(cookie);

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logoutExc(
            HttpServletResponse response,
            Model model
    ) {
        Cookie cookie = new Cookie("username","");
        cookie.setMaxAge(0); // 덮어 씌우던 기존꺼 가져오던 생존 주기만 0으로 만들면 됨
        cookie.setPath("/");

        response.addCookie(cookie);

        model.addAttribute("message","쿠키가 삭제 되었습니다.");

        return "redirect:/";
    }
}
