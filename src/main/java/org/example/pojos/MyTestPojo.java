package org.example.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.annotations.ColumnNumber;

@Data
@AllArgsConstructor
public class MyTestPojo {

    private int rowNumber;
    @ColumnNumber(number = 0)
    private String one;
    @ColumnNumber(number = 1)
    private String two;
    @ColumnNumber(number = 2)
    private String three;

}
