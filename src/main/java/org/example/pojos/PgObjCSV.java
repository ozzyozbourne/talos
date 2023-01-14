package org.example.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PgObjCSV {

    @JsonProperty(value = "ELEMENT_NAME")
    private String elementName;
    @JsonProperty(value = "TYPE")
    private String type;
    @JsonProperty(value = "LOCATOR_VALUE")
    private String locatorValue;

}
