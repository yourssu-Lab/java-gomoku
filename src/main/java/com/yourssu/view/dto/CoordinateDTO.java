package com.yourssu.view.dto;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yourssu.model.Coordinate;

import java.io.IOException;

public record CoordinateDTO(
        int row,
        @JsonSerialize(using = ColumnSerializer.class)
        @JsonDeserialize(using = ColumnDeserializer.class)
        int column
) {
    public static Coordinate toDomain(CoordinateDTO dto) {
        return new Coordinate(dto.row(), dto.column());
    }

    public static CoordinateDTO from(Coordinate coordinate) {
        return new CoordinateDTO(coordinate.row(), coordinate.column());
    }
}

class ColumnSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        gen.writeNumber(convertToColumnNumber(value));
    }

    private int convertToColumnNumber(String column) {
        int result = 0;
        for (int i = 0; i < column.length(); i++) {
            result = result * 26 + (column.charAt(i) - 'A' + 1);
        }
        return result;
    }
}

class ColumnDeserializer extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        int columnNumber = p.getIntValue();
        return convertToColumnLabel(columnNumber);
    }

    private String convertToColumnLabel(int columnNumber) {
        StringBuilder result = new StringBuilder();

        while (columnNumber > 0) {
            columnNumber--;
            result.insert(0, (char)('A' + columnNumber % 26));
            columnNumber /= 26;
        }

        return result.toString();
    }
}
