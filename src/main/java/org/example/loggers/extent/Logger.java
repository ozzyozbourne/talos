package org.example.loggers.extent;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.example.enums.LogType;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.example.selenium.Utils.getBase64;

public final class Logger {

    public static final Consumer<WebDriver> TAKE_SNAP = driver -> Manager.getExtentTest().info("TAKING SNAP ->",
            MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());

    private Logger() {
    }

    private static final Consumer<String> TESTNG_PASS    = msg -> Reporter.log("[PASS] " + msg, true);
    private static final Consumer<String> TESTNG_FAIL    = msg -> Reporter.log("[FAIL] " + msg, true);
    private static final Consumer<String> TESTNG_INFO    = msg -> Reporter.log("[INFO] " + msg, true);
    private static final Consumer<String> TESTNG_SKIP    = msg -> Reporter.log("[SKIP] " + msg, true);
    private static final Consumer<String> TESTNG_WARNING = msg -> Reporter.log("[WARNING] " + msg, true);

    private static final Consumer<String> PASS_SPARK_INNER      = msg -> Manager.getExtentInnerTest().pass(msg);
    private static final Consumer<String> INFO_SPARK_INNER      = msg -> Manager.getExtentInnerTest().info(msg);
    private static final Consumer<String> FAIL_SPARK_INNER      = msg -> Manager.getExtentInnerTest().fail(msg);
    private static final Consumer<String> WARNING_SPARK_INNER   = msg -> Manager.getExtentInnerTest().warning(msg);
    private static final Consumer<String> SKIP_SPARK_INNER      = msg -> Manager.getExtentInnerTest().skip(msg);

    private static final Consumer<String> INFO_SPARK_OUTER     = msg -> Manager.getExtentTest().info(msg);

    private static final Map<LogType, Consumer<String>> MAP_INNER = new EnumMap<>(LogType.class);


    static {
        MAP_INNER.put(LogType.PASS,    PASS_SPARK_INNER.andThen(TESTNG_PASS));
        MAP_INNER.put(LogType.FAIL,    FAIL_SPARK_INNER.andThen(TESTNG_FAIL));
        MAP_INNER.put(LogType.INFO,    INFO_SPARK_INNER.andThen(TESTNG_INFO));
        MAP_INNER.put(LogType.SKIP,    SKIP_SPARK_INNER.andThen(TESTNG_SKIP));
        MAP_INNER.put(LogType.WARNING, WARNING_SPARK_INNER.andThen(TESTNG_WARNING));
    }

    public static void log(LogType status, String msg){
        MAP_INNER.get(status).accept(msg);
    }

    public static void logToSpark(LogType status, String msg){
        switch (status){
            case PASS ->    PASS_SPARK_INNER.accept(msg);
            case FAIL ->    FAIL_SPARK_INNER.accept(msg);
            case INFO ->    INFO_SPARK_INNER.accept(msg);
            case SKIP ->    SKIP_SPARK_INNER.accept(msg);
            case WARNING -> WARNING_SPARK_INNER.accept(msg);
            default      -> throw new IllegalArgumentException("Enum not in switch" + status);
        }
    }

    public static void logTestTagResult(String msg){
        INFO_SPARK_OUTER.accept(msg);
    }
}
