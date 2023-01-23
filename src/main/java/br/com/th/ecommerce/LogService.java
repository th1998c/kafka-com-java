package br.com.th.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;

public class LogService {
    public static void main(String[] args) throws InterruptedException {
        var logService = new LogService();

        try(var kafkaService = new KafkaService(LogService.class.getSimpleName(),
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))){
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
