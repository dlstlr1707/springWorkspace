package com.example.orderservice.order.domain;

import com.example.orderservice.book.Book;
import com.example.orderservice.book.BookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.example.orderservice.order.domain.OrderStatus.ACCEPTED;
import static com.example.orderservice.order.domain.OrderStatus.REJECTED;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookClient bookClient;

    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    public Mono<Order> submitOrder(String isbn, int quantity) {
        return bookClient.getBookByIsbn(isbn)
                // Book객체를 Order객체로 변환
                .map(book -> buildAcceptedOrder(book, quantity))
                .defaultIfEmpty(buildRejectedOrder(isbn,quantity))
                // Mono<Mono<Order>>를 Mono<Order>로 여러개의 Mono를 합쳐 준다.
                .flatMap(orderRepository::save)
                ;
    }
    
    private static Order buildAcceptedOrder(Book book, int quantity) {
        return Order.builder()
                .bookIsbn(book.isbn())
                .bookName(book.title() + "-" + book.author())
                .bookPrice(book.price())
                .quantity(quantity)
                .status(ACCEPTED)
                .build();
    }

    private static Order buildRejectedOrder(String isbn, int quantity) {
        // 요청받은 책 없을때
        return Order.builder()
                .bookIsbn(isbn)
                .quantity(quantity)
                .status(REJECTED)
                .build();
    }
}
