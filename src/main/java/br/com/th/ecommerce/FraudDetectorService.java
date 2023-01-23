package br.com.th.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class FraudDetectorService {
    public static void main(String[] args) throws InterruptedException {
        var fraudDetectorService = new FraudDetectorService();
        try(var kafkaService = new KafkaService<>(FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER", fraudDetectorService::parse, Order.class, new HashMap<>())){
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> order) throws InterruptedException {
        System.out.println("Processing new order, checking for fraud: "
                + order.offset()
                +" \nkey: " + order.key() + "\n"
                +"value: " + order.value());
        Thread.sleep(5000);
    }

}
