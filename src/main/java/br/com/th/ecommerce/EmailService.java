package br.com.th.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class EmailService {
    public static void main(String[] args) throws InterruptedException {
        var emailService = new EmailService();
        try(var kafkaservice = new KafkaService(EmailService.class.getSimpleName(),
                              "ECOMMERCE_SEND_EMAIL",
                                    emailService::parse,
                                    String.class, new HashMap<>())) {
            kafkaservice.run();
        }
    }

    private void parse(ConsumerRecord<String, String> order) throws InterruptedException {
         System.out.println("Send email!"
                +" \nkey: " + order.key() + "\n"
                +"value: " + order.value());
         Thread.sleep(1000);

        System.out.println("Email sent");
    }

}
