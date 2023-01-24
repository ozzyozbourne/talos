package pojos;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelRow;
import lombok.Data;

@Data
public class DemoPojoOne {

    @ExcelRow
    private int rowIndex;

    @ExcelCellName("ELEMENT_NAME")
    private String elementName;

    @ExcelCellName("TYPE")
    private String type;

    @ExcelCellName("LOCATOR_VALUE")
    private String locatorValue;

}
