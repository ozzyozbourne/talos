package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.annotations.ColumnNumber;

@Data
public class DemoPojoThree {

    private int rowNumber;
    @ColumnNumber(number = 0)
    private String firstName;
    @ColumnNumber(number = 1)
    private String lastName;
    @ColumnNumber(number = 2)
    private String email;
    @ColumnNumber(number = 3)
    private String phoneNumber;
    @ColumnNumber(number = 4)
    private String location;
    @ColumnNumber(number = 5)
    private String town;
}
