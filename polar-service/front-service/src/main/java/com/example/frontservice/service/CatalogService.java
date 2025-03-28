package com.example.frontservice.service;

import com.example.frontservice.client.CatalogClient;
import com.example.frontservice.dto.CreateCatalogRequestDTO;
import com.example.frontservice.dto.CreateCatalogResponseDTO;
import com.example.frontservice.dto.DisplayCatalogListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient catalogClient;

    public DisplayCatalogListResponseDTO displayCatalogList(String accessToken) {
        return catalogClient.getCatalogList(accessToken);
    }

    public CreateCatalogResponseDTO createCatalog(
            String accessToken,
            CreateCatalogRequestDTO createCatalogRequestDTO
    ) {
        return catalogClient.createCatalog(accessToken, createCatalogRequestDTO);
    }

}
