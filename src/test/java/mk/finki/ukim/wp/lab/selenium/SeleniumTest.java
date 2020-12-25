package mk.finki.ukim.wp.lab.selenium;

import mk.finki.ukim.wp.lab.models.Balloon;
import mk.finki.ukim.wp.lab.models.Manufacturer;
import mk.finki.ukim.wp.lab.models.Type;
import mk.finki.ukim.wp.lab.service.BalloonService;
import mk.finki.ukim.wp.lab.service.ManufacturerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTest {

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    BalloonService balloonService;

    private HtmlUnitDriver driver;

    private static Manufacturer m1;
    private static Balloon b1;

    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup() {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() {
        if (!dataInitialized) {

            m1 = manufacturerService.save("m","m","m");
            b1 = balloonService.add("b","b",m1.getId(), Type.B);

            dataInitialized = true;
        }
    }

    @Test
    public void testBalloons() throws Exception{
        BalloonPage balloonPage = BalloonPage.to(driver);
        System.out.println(driver.getPageSource());
        balloonPage.assertElements(0,0,0);

        LoginPage loginPage = LoginPage.toLogin(driver);
        balloonPage = LoginPage.doLogin(driver,loginPage,"admin","admin");
        System.out.println(driver.getPageSource());
        balloonPage.assertElements(1,1,1);
    }
}
