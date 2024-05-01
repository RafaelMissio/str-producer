package br.com.missio.strproducer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@RequiredArgsConstructor
@Configuration
public class StringProducerFactoryConfig {

    private final KafkaProperties properties;

    // fábrica de produtores
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        var configs = new HashMap<String, Object>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers()); // pega o valor das propiedades do kafka
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // serializa a chave
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // serializa o valor
        return new DefaultKafkaProducerFactory<>(configs); // cria uma fábrica de produtores
    }

    // template para envio de mensagens
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String,String> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }


}
