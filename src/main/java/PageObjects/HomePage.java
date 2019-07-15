package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.Wait;

public class HomePage extends Page
{
	
	private Actions action;
	
	 public HomePage(WebDriver driver) {
		 this.driver=driver;
	     PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(xpath= "//div[@class='subNavListContainer']//a[text()='Top Rated Movies']") 
	 private WebElement TopRatedMoviesMenu_Link;
	 
	 @FindBy(xpath = "//*[@id='navTitleMenu']/span") 
	 private WebElement TitleTopMenu_Link;

	@Override
	public boolean isAt() {
		return this.TitleTopMenu_Link.isDisplayed();
	}
	 

	public void goTo(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public TopRatedMoviesPage goToTopRatedMovies()
	{
		action=new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='navTitleMenu']/span")))
		      .perform();
		
		try {
			Wait.untilElementIsVisible(driver, TopRatedMoviesMenu_Link);
			TopRatedMoviesMenu_Link.click();
			return new TopRatedMoviesPage(driver);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}

