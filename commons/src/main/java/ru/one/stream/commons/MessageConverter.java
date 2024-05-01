package ru.one.stream.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



public class MessageConverter {


    ObjectMapper objectMapper;

    @SneakyThrows
    public String toString(Message message) {
        return objectMapper.writeValueAsString(message);
    }

    @SneakyThrows
    public Message toMessage(String string) {
        return objectMapper.readValue(string, Message.class);
    }

}
