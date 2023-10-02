package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ExceptionResponse;

import java.io.File;
import java.io.IOException;

public class ExceptionWritter {
    public static void sendException(File outputFile, String type, String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(outputFile,new ExceptionResponse(type,message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
