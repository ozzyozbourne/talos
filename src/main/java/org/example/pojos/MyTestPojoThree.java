package org.example.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.annotations.ColumnNumber;

@Data
@AllArgsConstructor
public class MyTestPojoThree {

    private int rowNumber;
    @ColumnNumber(number = 110)
    private String one;
    @ColumnNumber(number = 111)
    private String two;
    @ColumnNumber(number = 112)
    private String three;
}
