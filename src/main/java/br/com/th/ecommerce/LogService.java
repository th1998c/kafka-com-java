package br.com.th.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.regex.Pattern;

public class LogService {
    public static void main(String[] args) throws InterruptedException {
        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Pattern.compile("ECOMMERCE.*"));
        while (true){
            var records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()){
                records.forEach(order -> System.out.println("LOG "+ order.topic()
                        +" \nkey: " + order.key() + "\n"
                        +"value: " + order.value() + "\n"));
            }

        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, LogService.class.getSimpleName());
        return properties;
    }
}
