package org.example.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.annotations.ColumnNumber;

@Data
@AllArgsConstructor
public class MyTestPojoTwo {

    private int rowNumber;
    @ColumnNumber(number = 10)
    private String one;
    @ColumnNumber(number = 11)
    private String two;
    @ColumnNumber(number = 12)
    private String three;
}
