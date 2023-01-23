package br.com.th.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {
    public static void main(String[] args) throws InterruptedException {
        var fraudDetectorService = new FraudDetectorService();
        try(var kafkaService = new KafkaService(FraudDetectorService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER", fraudDetectorService::parse)){
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, String> order) throws InterruptedException {
        System.out.println("Processing new order, checking for fraud: "
                + order.offset()
                +" \nkey: " + order.key() + "\n"
                +"value: " + order.value());
        Thread.sleep(5000);
    }

}
