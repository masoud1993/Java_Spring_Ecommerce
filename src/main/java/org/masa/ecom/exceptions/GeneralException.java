package org.masa.ecom.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class GeneralException {

    private List<ExceptionPair> errors;
    private int statusCode;


    @Data
    @AllArgsConstructor
    public static class ExceptionPair {
        private String title;
        private String message;
    }
}