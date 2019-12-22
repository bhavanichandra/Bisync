package com.bhavanichandra.bisync.util;

import com.bhavanichandra.bisync.model.Field;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bhavanichandra.bisync.util.TypeConstants.MARKDOWN;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;

public class Util {


    private Util() {
    }

    public static <T extends Enum<T>> String getLowerCaseValue(Enum<T> type) {
        return type.toString().toLowerCase();
    }

    public static List<Field> modelToMap(Object object) throws JsonProcessingException {
        List<Field> fields = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();


        Map<String, Object> mapToConvert = objectMapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
        for (Map.Entry<String, Object> entry : mapToConvert.entrySet()) {
            Field field = new Field();
            field.setType(MARKDOWN);
            if (entry.getValue() != null) {
                if (entry.getKey().equalsIgnoreCase("type")) {
                    String type = (String) entry.getValue();
                    String replaced = type.replaceAll("sf:", "");
                    field.setText("*" + capitalize(entry.getKey()) + "*: " + replaced);
                } else {
                    field.setText("*" + capitalize(entry.getKey()) + "*: " + entry.getValue());
                }

            }
            fields.add(field);
        }
        return fields;
    }

    public static HeaderMapper<HttpHeaders> httpHeadersMapper() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HeaderMapper<HttpHeaders> headerMapper = new DefaultHttpHeaderMapper();
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
        MessageHeaders messageHeaders = new MessageHeaders(headerMap);
        headerMapper.fromHeaders(messageHeaders, headers);
        return headerMapper;
    }

}
