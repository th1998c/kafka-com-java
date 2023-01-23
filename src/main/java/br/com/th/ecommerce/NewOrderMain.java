package br.com.th.ecommerce;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try(var dispatcher = new KafkaDispatcher()){
            for(int count = 0; count< 10; count++){
                var key = UUID.randomUUID().toString();
                var value = key +", 6485, 5000.0";

                dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);

                var email = "Welcome! We are processing your order.";
                dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }
}
