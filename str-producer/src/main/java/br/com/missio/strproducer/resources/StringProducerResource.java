package br.com.missio.strproducer.resources;

import br.com.missio.strproducer.services.StringProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/producer")
public class StringProducerResource {

    private final StringProducerService producerService;

    // Envia uma mensagem para o tópico Kafka
    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message){
        producerService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
