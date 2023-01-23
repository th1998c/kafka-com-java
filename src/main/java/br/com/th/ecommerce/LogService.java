package br.com.th.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.regex.Pattern;

public class LogService {
    public static void main(String[] args) throws InterruptedException {
        var logService = new LogService();

        try(var kafkaService = new KafkaService(LogService.class.getSimpleName(), Pattern.compile("ECOMMERCE.*"),
                logService::parse)){
            kafkaService.run();
        }
    }

    private void parse(ConsumerRecord<String, String> order) {
        System.out.println("-----------------------------");
        System.out.println("LOG "+ order.topic()
                +" \nkey: " + order.key() + "\n"
                +"value: " + order.value() + "\n");
    }

}
