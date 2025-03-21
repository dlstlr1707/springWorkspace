package com.example.orderservice.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class BookClient {
    private static final String BOOKS_ROOT_API = "/books/";
    private final WebClient webClient;

    // Mono가 서빙하는 역할 ->하나만 서빙가능
    // Flux도 서빙하는 역할인데 여러개 서빙가능
    public Mono<Book> getBookByIsbn(String isbn) {
        System.out.println("isbn: " + isbn);
        return webClient.get()  // post,get
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .retrieve() // 요청을 보내고 응답을 기다린다.
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3)) // 3초동안 응답 없으면 연결 끊음.
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }
}
