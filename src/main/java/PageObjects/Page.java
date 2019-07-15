package PageObjects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import Context.TestContext;
import Utility.Wait;

import static org.awaitility.Awaitility.*;

public abstract class Page {
	
	WebDriver driver;
	TestContext testcontext=new TestContext();	
	
	public abstract boolean isAt();
	
	public boolean isAt(long timeout, TimeUnit timeunit){
        try{
        	Wait
        	.untilPageLoadComplete(driver);
            await().atMost(timeout, timeunit)
                   .ignoreExceptions()
                   .until(() -> isAt());
            return true;        
        }catch(Exception e){
            return false;
        }
    }
	


}

