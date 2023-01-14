package org.example.pojos;

import com.poiji.annotation.ExcelCellName;
import lombok.Data;

@Data
public class PgOjbXLSX {

    @ExcelCellName("ELEMENT_NAME")
    private String elementName;

    @ExcelCellName("TYPE")
    private String type;

    @ExcelCellName("LOCATOR_VALUE")
    private String locatorValue;

}
