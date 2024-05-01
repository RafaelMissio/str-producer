package br.com.missio.strproducer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {


    private final KafkaTemplate<String, String> kafkaTemplate;

    // Envia uma mensagem para o tópico Kafka
    public void sendMessage(String message){

        kafkaTemplate.send("str-topic", message).whenComplete(
                (result, ex) -> {
                    if(ex != null){
                        log.error("Erro ao enviar mensagem: {}", ex.getMessage());
                    }else{
                        log.info("Mensagem enviada com sucesso: {}", message);
                        log.info("Partition: {}", result.getRecordMetadata().partition());
                        log.info("Offset: {}", result.getRecordMetadata().offset());
                    }
                }
        );
    }
}
