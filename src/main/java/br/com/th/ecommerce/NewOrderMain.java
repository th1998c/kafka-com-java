package br.com.th.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var orderDispatcher = new KafkaDispatcher<Order>()){
            try(var dispatcher = new KafkaDispatcher<Email>()){
                for(int count = 0; count< 10; count++){
                    var userId = UUID.randomUUID().toString();
                    var orderId = UUID.randomUUID().toString();
                    var amount = Math.random() * 5000 + 1;
                    var order = new Order(userId, orderId, new BigDecimal(amount));

                    orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);

                    var email = new Email(orderId,"Welcome! We are processing your order.");
                    dispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
                }
            }
            }
        }
}