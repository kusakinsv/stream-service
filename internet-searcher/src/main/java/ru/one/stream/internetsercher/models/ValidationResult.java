package ru.one.stream.internetsercher.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationResult {
    private String originalUrl;
    private String finalUrl;
    private boolean isValid;
    private boolean needProxy;

    public ValidationResult(String originalUrl, String finalUrl, boolean isValid, boolean needProxy) {
        this.originalUrl = originalUrl;
        this.finalUrl = finalUrl;
        this.isValid = isValid;
        this.needProxy = needProxy;
    }

    public ValidationResult(String originalUrl, boolean isValid) {
        this.originalUrl = originalUrl;
        this.isValid = isValid;
    }

    public static ValidationResult invalid(String url) {
        return new ValidationResult(url, false);
    }

}


