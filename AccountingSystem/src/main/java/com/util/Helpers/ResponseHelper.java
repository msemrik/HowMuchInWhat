package com.util.Helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.RESTResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by semri on 1/31/2016.
 */
public class ResponseHelper {

    public static String createResponseEntity(Object object, HttpStatus httpStatus) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RESTResponse restResponse = new RESTResponse(object);
        String returnObject = mapper.writeValueAsString(restResponse);
        ResponseEntity responseEntity = new ResponseEntity<String>(returnObject, httpStatus);
        return mapper.writeValueAsString(responseEntity);

    }

    public static String createResponseEntity(String string, HttpStatus httpStatus) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RESTResponse restResponse = new RESTResponse(string);
        String returnObject = mapper.writeValueAsString(restResponse);
        ResponseEntity responseEntity = new ResponseEntity<String>(returnObject, httpStatus);
        return mapper.writeValueAsString(responseEntity);
    }

    public static String createResponseEntity(Long id, String string, HttpStatus httpStatus) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        RESTResponse restResponse = new RESTResponse(id, string);
        String returnObject = mapper.writeValueAsString(restResponse);
        ResponseEntity responseEntity = new ResponseEntity<String>(returnObject, httpStatus);
        return mapper.writeValueAsString(responseEntity);
    }

}
