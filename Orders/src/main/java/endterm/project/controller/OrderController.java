package endterm.project.controller;

import endterm.project.entity.Order;
import endterm.project.messaging.OrderProducer;
import endterm.project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor //
public class OrderController {
    private final OrderService orderService;  // Зависимость будет внедрена
    private final OrderProducer orderProducer;

    @PostMapping

    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order savedOrder = orderService.saveOrder(order);
        orderProducer.sendOrder(savedOrder);
        return ResponseEntity.ok(savedOrder);
    }
}
