package frameworktests;

import lombok.val;
import org.example.DaggerCarComponent;
import org.testng.annotations.Test;

@Test
public class DaggerDITests {

    public void createCar(){
        System.out.println("Hello world!");
        val component = DaggerCarComponent.create();
        val car = component.getCar();
        System.out.println(car);
    }
}
