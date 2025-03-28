package com.example.catalogservice.dto;

import com.example.catalogservice.domain.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DisplayCatalogListResponseDTO {
    private Iterable<Book> catalogList;
}
