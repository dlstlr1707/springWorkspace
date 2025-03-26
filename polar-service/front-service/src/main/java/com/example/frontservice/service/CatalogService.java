package com.example.frontservice.service;

import com.example.frontservice.client.CatalogClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient catalogClient;

}
