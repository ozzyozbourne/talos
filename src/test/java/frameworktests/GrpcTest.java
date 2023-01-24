package frameworktests;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import lombok.val;
import org.example.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.example.framework.Constants.PATH_TEST_RC;
import static org.example.utils.Grpc.*;

@Test
public class GrpcTest {


    public static Dummy newDummy(int id, String name) {
        return Dummy.newBuilder().setId(id).setName(name).build();
    }

    public static IdWrapper newIdWrapper(int id) {return IdWrapper.newBuilder().setId(id).build();}

    public void simpleTest(){
        Simple message = Simple.newBuilder().setId(10).setIsSimple(true).setName("qwe")
                .addSampleLists(1).addSampleLists(2).addSampleLists(3).build();

        System.out.println(message);
    }

    public void ComplexTest(){
        val dummies =  Complex.newBuilder().setOneDummy(newDummy(10, "OneD"))
                .addAllDummies(List.of(newDummy(11, "TwoD"),
                        newDummy(12, "ThreeD"), newDummy(13, "FourD"))).build();
        System.out.println(dummies);
    }

    public void enumTest(){
        val s =  Enumerations.newBuilder().setEyeColor(EyeColor.EYE_COLOR_BLUE).build();
        System.out.println(s);
    }

    public void mapsTest(){
        val s =  MapExample.newBuilder().putAllIds(Map.of("One", newIdWrapper(10), "Two", newIdWrapper(20))).build();
        System.out.println(s);
    }

    public void oneOfsTest(){
        val s = result.newBuilder().setId(10).build();
        System.out.println(s);

        val sp = result.newBuilder(s).setMessage("10nner").build();
        System.out.println(sp);
    }

    public void testToBinOne() throws IOException {
        var dummies =  Complex.newBuilder().setOneDummy(newDummy(10, "OneD"))
                .addAllDummies(List.of(newDummy(11, "TwoD"),
                        newDummy(12, "ThreeD"), newDummy(13, "FourD"))).build();
        System.out.println(dummies);

        val path = PATH_TEST_RC + "grpcbinaryout"+ File.separator +"complex.bin";
        writeToBin(dummies, path);
        dummies = readFromBin(Complex::parseFrom, path);
        System.out.println(dummies);
    }

    public void testToBinTwo() throws IOException {

        Simple message = Simple.newBuilder().setId(10).setIsSimple(true).setName("qwe")
                .addSampleLists(1).addSampleLists(2).addSampleLists(3).build();
        System.out.println(message);
        val path = PATH_TEST_RC + "grpcbinaryout"+ File.separator +"Simple.bin";
        writeToBin(message, path);
        message = readFromBin(Simple::parseFrom, path);
        System.out.println(message);

    }

    public void testToAndFromJson() throws InvalidProtocolBufferException {
        Simple message = Simple.newBuilder().setId(10).setIsSimple(true).setName("qwe")
                .addSampleLists(1).addSampleLists(2).addSampleLists(3).build();
        val s = convertToJson(message);
        System.out.println(s);
        ConvertMessage<Simple.Builder, Simple> d = m -> {JsonFormat.parser().merge(s, m); return m.build();};
        message = d.apply(Simple.newBuilder());
        System.out.println(message);
    }
}
