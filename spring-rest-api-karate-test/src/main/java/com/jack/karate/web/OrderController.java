package com.jack.karate.web;

import com.jack.karate.model.Order;
import com.jack.karate.service.OrderConsumer;
import com.jack.karate.service.OrderProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final AtomicLong counter = new AtomicLong(10000);

    private final OrderProducer orderProducer;
    private final OrderConsumer orderConsumer;

    @Autowired
    public OrderController(OrderProducer orderProducer, OrderConsumer orderConsumer) {
        this.orderProducer = orderProducer;
        this.orderConsumer = orderConsumer;
    }

    @PostMapping
    public Long create(final @RequestBody Order order) {
        Long id = counter.incrementAndGet();
        order.setId(id);
        new Thread(() -> {
            try {
                logger.info("sleep 10s to send the order to the queue.");
                Thread.sleep(10000L);
                orderProducer.send(order);
            } catch (InterruptedException e) {

            }
        }).start();
        return id;
    }

    @GetMapping
    public Collection<Order> list() {
        return orderConsumer.getAll();
    }

    @GetMapping("/{id:.+}")
    public Order get(@PathVariable long id) {
        return orderConsumer.getById(id);
    }

}
