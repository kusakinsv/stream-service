package ru.one.stream.internetsercher.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResult {
    private String name;
    private String url;
    private boolean isValid;
    private boolean isNeedProxy;

    public ValidationResult(String name, String url, boolean isValid, boolean isNeedProxy) {
        this.name = name;
        this.url = url;
        this.isValid = isValid;
        this.isNeedProxy = isNeedProxy;
    }

    public ValidationResult(String originalUrl, boolean isValid) {
        this.url = originalUrl;
        this.isValid = isValid;
    }

    public static ValidationResult invalid(String url) {
        return new ValidationResult(url, false);
    }

}


