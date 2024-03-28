package org.accessibility.poc.dequelabs;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class DequeDemoSite {

    public static WebDriver driver = null;

    public static void main(String[] args) throws InterruptedException {

        try {

            ChromeOptions chromeOptions = getChromeOptions();
          //  chromeOptions.setExperimentalOption("excludeSwitches", List.of("disable-popup-blocking"));
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--remote-allow-origins=*");

            AxeBuilder axeBuilder = new AxeBuilder();
            driver = new ChromeDriver(chromeOptions);
            driver.get("https://dequeuniversity.com/demo/mars/");

            Results results = axeBuilder.analyze(driver);
            System.out.println("Total Number of Violations: " + results.getViolations().size());
            results.getViolations().forEach(violation -> System.out.println(violation.getId()));
            System.out.println("Results Error Message: " + results.getErrorMessage());

        } catch (RuntimeException e){
            e.printStackTrace();
        } finally {
            driver.close();
        }


    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBrowserVersion(String.valueOf("123"));
        chromeOptions.setPlatformName("Windows");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setImplicitWaitTimeout(Duration.ofMillis(5000));
        chromeOptions.setPageLoadTimeout(Duration.ofMillis(300000));
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS);
        return chromeOptions;
    }

}