package com.example.basicboardv2.service;

import com.example.basicboardv2.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;


}
