package com.example.essentials.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CookieController {

    @GetMapping("/set-cookie")
    public String setCookie(){
        return "set-cookie";
    }
    @GetMapping("/get-cookie")
    public String getCookieExc(
            HttpServletRequest request,
            Model model
    ){
        Cookie[] cookies = request.getCookies();
        String username = null;

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("username")) {
                    username = cookie.getValue();
                    break;
                }
            }
        }
        if(username != null) {
            model.addAttribute("username", username);
            model.addAttribute("message","쿠키에서 사용자 정보를 읽없습니다.");
        }else{
            model.addAttribute("message","쿠키가 없습니다.");
        }
        return "result-cookie";
    }

    @PostMapping("/set-cookie")
    public String setCookieExc(
            @RequestParam String username,
            HttpServletResponse response,
            Model model
    ){
        Cookie cookie = new Cookie("username",username);
        cookie.setMaxAge(7*24*60*60); // 1주일
        cookie.setPath("/");
        cookie.setHttpOnly(true); // true면 js로 제어 안되게 한다

        response.addCookie(cookie);

        model.addAttribute("message", "쿠키가 설정되었습니다.");
        model.addAttribute("username", username);

        return "result-cookie";
    }

    @GetMapping("/delete-cookie")
    public String deleteCookie(
            HttpServletResponse response,
            Model model
    ){
        Cookie cookie = new Cookie("username","");
        cookie.setMaxAge(0); // 덮어 씌우던 기존꺼 가져오던 생존 주기만 0으로 만들면 됨
        cookie.setPath("/");

        response.addCookie(cookie);

        model.addAttribute("message","쿠키가 삭제 되었습니다.");

        return "result-cookie";
    }
}
