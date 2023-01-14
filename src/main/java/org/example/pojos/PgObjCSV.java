package org.example.pojos;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"elementName", "type", "locatorValue"})
public class PgObjCSV {

    private final String elementName;
    private final String type;
    private final String locatorValue;

}
