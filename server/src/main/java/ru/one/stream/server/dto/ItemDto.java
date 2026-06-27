package ru.one.stream.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private int position;

    private String title;

    private String url;

    private String duration;

    private Boolean isNeedProxy;

}
