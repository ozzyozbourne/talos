package org.example.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"ELEMENT_NAME", "TYPE", "LOCATOR_VALUE"})
public class PgObjCSV {

    @JsonProperty(value = "ELEMENT_NAME")
    private String elementName;
    @JsonProperty(value = "TYPE")
    private String type;
    @JsonProperty(value = "LOCATOR_VALUE")
    private String locatorValue;

}
