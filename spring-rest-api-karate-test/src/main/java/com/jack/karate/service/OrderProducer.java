package com.jack.karate.service;

import javax.jms.Queue;

import com.jack.karate.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

	private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired
	private Queue queue;


	public void send(Order order) {
	    logger.info("prepare to send message [{}].", order.toString());
		this.jmsMessagingTemplate.convertAndSend(this.queue, order);
	}

}