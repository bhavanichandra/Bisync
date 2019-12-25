package com.bhavanichandra.bisync.util;

import com.bhavanichandra.bisync.model.Field;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.integration.http.support.DefaultHttpHeaderMapper;
import org.springframework.integration.mapping.HeaderMapper;
import org.springframework.messaging.MessageHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bhavanichandra.bisync.util.TypeConstants.MARKDOWN;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class Util {


    @Autowired
    @Lazy
    private static ObjectMapper objectMapper;

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

}
