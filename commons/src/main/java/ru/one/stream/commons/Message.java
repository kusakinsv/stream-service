package ru.one.stream.commons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message { //todo заменить на билдер
    @JsonProperty("id")
    private String id;
    @JsonProperty("headers")
    private Map<String, String> headers = new HashMap<>();
    @JsonProperty("payload")
    private Object payload;

    public Message(Map<String, String> headers, Object payload) {
        this.headers = headers;
        this.payload = payload;
    }

    public Message(String id, Object payload) {
        this.id = id;
        this.payload = payload;
    }

    public Message(Object payload) {
        this.payload = payload;
    }

    public void setHeader(String key, String value) {
        headers.put(key, value);
    }
}


