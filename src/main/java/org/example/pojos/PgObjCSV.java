package org.example.pojos;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"elementName", "type", "locatorValue"})
public class PgObjCSV {

    private String elementName;
    private String type;
    private String locatorValue;

}
