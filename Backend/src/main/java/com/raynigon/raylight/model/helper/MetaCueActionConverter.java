package com.raynigon.raylight.model.helper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynigon.raylight.model.CueAction;

import lombok.SneakyThrows;

import org.springframework.stereotype.Component;

@Component
public class MetaCueActionConverter implements AttributeConverter<List<CueAction>, String> {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(List<CueAction> attribute) {
        return mapper.writeValueAsString(attribute);
    }

    @Override
    @SneakyThrows
    public List<CueAction> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return Collections.emptyList();
        }
        CueAction[] cueActions = mapper.readValue(dbData, CueAction[].class);
        if (cueActions == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(cueActions);
    }
}
