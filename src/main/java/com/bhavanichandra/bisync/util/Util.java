package com.bhavanichandra.bisync.util;

import com.bhavanichandra.bisync.model.Field;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bhavanichandra.bisync.util.TypeConstants.MARKDOWN;

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
                field.setText("*" + entry.getKey() + ":*\n " + entry.getValue());
            }
            fields.add(field);
        }
        return fields;
    }

}
