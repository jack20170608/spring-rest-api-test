package com.jack.karate.service;

import com.google.common.collect.Maps;
import com.jack.karate.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Map;

@Component
public class OrderConsumer {

	private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);
	private static Map<Long, Order> orderDB = Maps.newConcurrentMap();

	@PostConstruct
	private void initSomeDummyData(){
		orderDB.put(1L, new Order(1L, "1st order"));
		orderDB.put(2L, new Order(2L, "2nd dummy order"));
	}

	@JmsListener(destination = "sample.queue")
	public void receiveQueue(Order order) {
	    logger.info("Consumer receive [{}].", order.toString());
	    orderDB.put(order.getId(), order);
	}

	public Collection<Order> getAll(){
		return orderDB.values();
	}

	public Order getById(Long id){
		return orderDB.get(id);
	}

	public Order deleteById(Long id){
		return orderDB.remove(id);
	}

}