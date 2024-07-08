package ru.one.stream.client.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
public class ThymData {

    private String value1;
    private String value2;
    private String value3;
    private String value4;
    private String value5;


    public ThymData() {
    }
}
