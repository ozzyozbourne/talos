package frameworktests;

import lombok.val;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.example.utils.Interpolation.*;

@Test
public final class InterpolationTests {

    public void replaceOneTestNone(){
        val input = "replacement none";
        val replacement = "test";
        val out = replaceOne(input, replacement);
        System.out.println(out);
        Assert.assertEquals(out, "replacement none");
    }

    public void replaceOneTestA(){
        val input = "replacement one ${0}";
        val replacement = "test";
        val out = replaceOne(input, replacement);
        System.out.println(out);
        Assert.assertEquals(out, "replacement one test");
    }

    public void replaceOneTestB(){
        val input = "replacement one ${0}, '${0}' | ${0}";
        val replacement = "test";
        val out = replaceOne(input, replacement);
        System.out.println(out);
        Assert.assertEquals(out, "replacement one test, 'test' | test");
    }

    public void replaceOneTestC(){
        val input = "replacement one ${0}${0}${0}";
        val replacement = "test";
        val out = replaceOne(input, replacement);
        System.out.println(out);
        Assert.assertEquals(out, "replacement one testtesttest");
    }

    public void replaceTwoTestA(){
        val input = "replacement ${0} ${1}";
        val replacement1 = "ONE";
        val replacement2 = "TWO";
        val out = replaceTwo(input, replacement1, replacement2);
        System.out.println(out);
        Assert.assertEquals(out, "replacement ONE TWO");
    }

    public void replaceTwoTestB(){
        val input = "replacement ${0} ${1} '${0} ${1}' | ${0} ${1}";
        val replacement1 = "ONE";
        val replacement2 = "TWO";
        val out = replaceTwo(input, replacement1, replacement2);
        System.out.println(out);
        Assert.assertEquals(out, "replacement ONE TWO 'ONE TWO' | ONE TWO");
    }

    public void replaceTwoTestC(){
        val input = "replacement ${0}${1}${0}${1}${0}${1}";
        val replacement1 = "ONE";
        val replacement2 = "TWO";
        val out = replaceTwo(input, replacement1, replacement2);
        System.out.println(out);
        Assert.assertEquals(out, "replacement ONETWOONETWOONETWO");
    }

    public void replaceThreeTestA(){
        val input = "replacement ${0} ${1} ${2}";
        val replacement1 = "ONE";
        val replacement2 = "TWO";
        val replacement3 = "THREE";
        val out = replaceThree(input, replacement1, replacement2, replacement3);
        System.out.println(out);
        Assert.assertEquals(out, "replacement ONE TWO THREE");
    }

    public void replaceThreeTestB(){
        val input = "replacement ${0} ${1} ${2} '${0} ${1} ${2}' | ${0} ${1} ${2}";
        val replacement1 = "ONE";
        val replacement2 = "TWO";
        val replacement3 = "THREE";
        val out = replaceThree(input, replacement1, replacement2, replacement3);
        System.out.println(out);
        Assert.assertEquals(out, "replacement ONE TWO THREE 'ONE TWO THREE' | ONE TWO THREE");
    }

    public void replaceThreeTestC(){
        val input = "replacement ${0}${1}${2}${0}${1}${2}${0}${1}${2}";
        val replacement1 = "ONE";
        val replacement2 = "TWO";
        val replacement3 = "THREE";
        val out = replaceThree(input, replacement1, replacement2, replacement3);
        System.out.println(out);
        Assert.assertEquals(out, "replacement ONETWOTHREEONETWOTHREEONETWOTHREE");
    }
}
