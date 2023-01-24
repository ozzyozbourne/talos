package pojos;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import lombok.Data;

@Data
public class DemoPojoTwo {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("One")
    private String one;

    @ExcelCellName("Two")
    private String two;

    @ExcelCellName("Three")
    private String three;
}
