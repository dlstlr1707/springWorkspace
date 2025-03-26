package com.example.frontservice.client;

import com.example.frontservice.dto.CreateCatalogRequestDTO;
import com.example.frontservice.dto.CreateCatalogResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "catalogClient", url = "${polar.edge-service-url}/books")
public interface CatalogClient {

    @PostMapping
    CreateCatalogResponseDTO createCatalog(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CreateCatalogRequestDTO createCatalogRequestDTO
    );
    
}
