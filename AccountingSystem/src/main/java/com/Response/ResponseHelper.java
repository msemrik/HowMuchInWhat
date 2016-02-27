package com.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResponseHelper {

    public static String createResponseEntity(Object object, HttpStatus httpStatus) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RESTResponse restResponse = new RESTResponse(object);
        String returnObject = mapper.writeValueAsString(restResponse);
        ResponseEntity responseEntity = new ResponseEntity<String>(returnObject, httpStatus);
        return mapper.writeValueAsString(responseEntity);

    }

    public static String createErrorResponseEntity(String string, Exception e) throws JsonProcessingException {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        ObjectMapper mapper = new ObjectMapper();
        RESTResponse restResponse = new RESTResponse(string, exceptionAsString);
        String returnObject = mapper.writeValueAsString(restResponse);
        ResponseEntity responseEntity = new ResponseEntity<String>(returnObject, HttpStatus.BAD_REQUEST);
        return mapper.writeValueAsString(responseEntity);
    }

    public static String createErrorResponseEntity(String string) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RESTResponse restResponse = new RESTResponse(string, "");
        String returnObject = mapper.writeValueAsString(restResponse);
        ResponseEntity responseEntity = new ResponseEntity<String>(returnObject, HttpStatus.BAD_REQUEST);
        return mapper.writeValueAsString(responseEntity);
    }

}
