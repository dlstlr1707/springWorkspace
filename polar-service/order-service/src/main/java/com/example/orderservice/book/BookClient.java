package com.example.orderservice.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class BookClient {
    private static final String BOOKS_ROOT_API = "/books";
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

    public Flux<Book> getBooks(){
        return webClient.get()
                .uri(BOOKS_ROOT_API)
                .retrieve()
                .bodyToFlux(Book.class)
                .timeout(Duration.ofSeconds(3)) // 3초동안 응답 없으면 연결 끊음.
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Flux.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Flux.empty());
    }

    public Mono<Book> enrollBook(Book book){
        return webClient.post()
                .uri(BOOKS_ROOT_API)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3)) // 3초동안 응답 없으면 연결 끊음.
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }

    public Mono<Book> updateBook(String isbn, Book book){
        return webClient.put()
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .bodyValue(book)
                .retrieve()
                .bodyToMono(Book.class)
                .timeout(Duration.ofSeconds(3)) // 3초동안 응답 없으면 연결 끊음.
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }

    public Mono<Void> deleteBook(String isbn){
        return webClient.delete()
                .uri(BOOKS_ROOT_API + "/" + isbn)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(3)) // 3초동안 응답 없으면 연결 끊음.
                .onErrorResume(WebClientResponseException.NotFound.class, exception -> Mono.empty())
                .retryWhen(
                        Retry.backoff(3, Duration.ofMillis(100))
                )
                .onErrorResume(Exception.class, exception -> Mono.empty());
    }
}
