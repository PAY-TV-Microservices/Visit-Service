package br.ada.visitService.queue;

import br.ada.visitService.controller.dto.VisitRequest;
import br.ada.visitService.service.VisitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleVisitConsumer {

    private final ObjectMapper objectMapper;
    private final VisitService visitService;

    @RabbitListener(queues = "schedule_technical_visit")
    public void receive(Message message, Channel channel) throws IOException {
        String request = new String(message.getBody());
        log.info("[VisitConsumer] Message received {}", request);

        try {
            List<VisitRequest> visitRequestList = Arrays.stream(objectMapper.readValue(request, VisitRequest[].class)).toList();
            visitService.execute(visitRequestList);
            log.info("[VisitConsumer] Message consumed.");
        } catch (Exception e) {
            log.info("[VisitConsumer] Message with error.");
        } finally {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
