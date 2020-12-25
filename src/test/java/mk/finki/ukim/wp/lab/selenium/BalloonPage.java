package mk.finki.ukim.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage {

    @FindBy(css = ".add")
    private List<WebElement> adds;

    @FindBy(css = ".edit")
    private List<WebElement> edits;

    @FindBy(css = ".delete")
    private List<WebElement> deletes;

    public BalloonPage(WebDriver driver){
        super(driver);
    }

    public static BalloonPage to(WebDriver driver)
    {
        get(driver,"/");
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public void assertElements(int edits,int deletes,int adds)
    {
        Assert.assertEquals("Edit buttons dont match",edits,this.edits.size());
        Assert.assertEquals("Delete buttons dont match",deletes,this.deletes.size());
        Assert.assertEquals("Add buttons dont match",adds,this.adds.size());
    }
}
