package com.example.frontservice.controller;

import com.example.frontservice.dto.CreateCatalogRequestDTO;
import com.example.frontservice.dto.CreateCatalogResponseDTO;
import com.example.frontservice.dto.DisplayCatalogListResponseDTO;
import com.example.frontservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webs/api/catalog")
public class HomeApiController {

    private final CatalogService catalogService;

    @GetMapping
    public DisplayCatalogListResponseDTO displayCatalogList(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String accessToken
    ){
        return catalogService.displayCatalogList(accessToken);
    }

    @PostMapping
    public CreateCatalogResponseDTO createCatalog(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String accessToken,
            @RequestBody CreateCatalogRequestDTO createCatalogRequestDTO
    ){
        return catalogService.createCatalog(accessToken, createCatalogRequestDTO);
    }
}
