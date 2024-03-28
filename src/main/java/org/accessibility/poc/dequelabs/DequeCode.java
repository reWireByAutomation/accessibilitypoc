package org.accessibility.poc.dequelabs;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DequeCode {

        public static void main(String[] args) {

            AxeBuilder axeBuilder = new AxeBuilder();
            WebDriver driver = new ChromeDriver();

            driver.get("https://dequeuniversity.com/demo/mars/");

            try {
                Results results = axeBuilder.analyze(driver);
                results.getViolations().forEach(violation -> System.out.println(violation.getId()));
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                driver.close();
            }
        }

}
