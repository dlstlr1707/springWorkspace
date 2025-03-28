package com.example.frontservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayCatalogListResponseDTO {
    private Iterable<?> catalogList;
}
