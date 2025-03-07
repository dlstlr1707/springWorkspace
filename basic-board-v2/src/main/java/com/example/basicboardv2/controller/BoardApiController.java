package com.example.basicboardv2.controller;

import com.example.basicboardv2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

}
