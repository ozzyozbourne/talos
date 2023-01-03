package sample;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.Test;

@Test
public class SampleTest {

    public void testA(){
        System.out.println("Hello Gradle Testng");
        System.out.println(System.getProperty("tier"));
        System.out.println(System.getProperty("browser"));
        System.out.println(System.getProperty("product"));

    }

    public void playwright(){
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch();
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            System.out.println(page.title());
        }
    }
}
