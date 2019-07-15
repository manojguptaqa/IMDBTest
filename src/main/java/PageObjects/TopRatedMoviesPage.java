package PageObjects;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Enum.Sort;
import ObjectClasses.Movie;
import Utility.Wait;

public class TopRatedMoviesPage extends Page
{
	
	public TopRatedMoviesPage(WebDriver driver) {
		 this.driver=driver;
	     PageFactory.initElements(driver, this);
	 }
	 
	 @FindAll(@FindBy(xpath= "//tbody[@class='lister-list']/tr")) 
	 private List<WebElement> TopRatedMoviesRows;
	 
	 @FindBy(id= "lister-sort-by-options")
	 private WebElement Sort_DD;
	 
	 @FindBy(xpath= "//span[contains(@class,'global-sprite lister-sort-reverse')]")
	 private WebElement Sort_Toggle_Btn;
	 
//	 @FindBy(xpath= "//span[contains(@class,'global-sprite lister-sort-reverse') and (@title='Ascending order')]")
//	 private WebElement Ascending_Order_Toggle_Btn;	 
//	 
//	 @FindBy(xpath= "//span[contains(@class,'global-sprite lister-sort-reverse') and (@title='Descending order')]")
//	 private WebElement Descending_Order_Toggle_Btn;	
	 
	 @Override
	public boolean isAt() {
		return this.Sort_DD.isDisplayed();
	}
	 
	
	public void sortAscending()
	{
		while (!"Descending order".equals(Sort_Toggle_Btn.getAttribute("title")))
		{
			Sort_Toggle_Btn.click();
		}
		Wait.untilJqueryIsDone(driver);
		
	}
  
	public void sortDescending()
	{
		while (!"Ascending order".equals(Sort_Toggle_Btn.getAttribute("title")))
		{
			Sort_Toggle_Btn.click();
			
		}
		Wait.untilJqueryIsDone(driver);
	}
	
	public void selectDDValue(String option)
	{
		driver.findElement(By.xpath("//option[contains(text(),'"+option+"')]")).click();
		Wait.untilJqueryIsDone(driver);
		
	}
	
	public List<Movie> Filter(Sort filterType,Sort sortType )
	{
		switch (filterType) {
		case SortByRank:
			selectDDValue("Ranking");
			break;
		case SortByDate:
			selectDDValue("Release Date");
			break;
		case SortByNumberofRatings:
			selectDDValue("Number of Ratings");
			break;
		case SortByRatings:
			selectDDValue("IMDb Rating");
			break;
		default:
			throw new RuntimeException("No valid Sorting option found.");
		}	
		
		
		if(sortType==Sort.Asc)
		{
			sortAscending();
		}
		else if(sortType==Sort.Desc)
		{
			sortDescending();
		}
		
		return createListofMovies();
		
	}
	
	
	

	
	
public List<Movie> createListofMovies()
	{
		ArrayList<Movie> list = new ArrayList<>();
		
		for(WebElement m:TopRatedMoviesRows)
		{
			String poster_src=m.findElement(By.xpath("td[@class='posterColumn'][1]/a/img")).getAttribute("src").trim();
			
			
//	       // Charset utf8 = Charset.forName("UTF-8");
//	        Charset def = Charset.defaultCharset();

	       String Title_raw=m.findElement(By.xpath("td[@class='titleColumn'][1]")).getText();
	       
	       String Title="";
		try {
			byte[] bytes = Title_raw.getBytes("UTF-8");
			    Title = new String(bytes , "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       
	        
			
			int rank=Integer.parseInt(Title.substring(0,Title.indexOf(".")).trim());
			
			String movie_Title_Unicode=Title.substring(Title.indexOf(".")+1,Title.lastIndexOf("(")).trim();
			
			String movie_Title="";
			try {
				byte[] bytes = movie_Title_Unicode.getBytes("UTF-8");
				movie_Title = new String(bytes , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	System.out.println(movie_Title);
			
			int movie_Year=Integer.parseInt(Title.substring(Title.lastIndexOf(")")-4,Title.lastIndexOf(")")));
			
			String Total_Votes_Title=m.findElement(By.xpath("td[@class='ratingColumn imdbRating'][1]/strong")).getAttribute("title");
			
			String Total_Votes=Total_Votes_Title.substring(Total_Votes_Title.indexOf("n")+1, Total_Votes_Title.indexOf("user")).trim(); //8.6 based on 465,882 user ratings
			String Total_Votes_String=Total_Votes.replaceAll("[^0-9]", "");
			
			int movie_Total_Votes=Integer.parseInt(Total_Votes_String);
			
			String Rating=Total_Votes_Title.substring(0,Total_Votes_Title.indexOf("b")).trim();
			Float movie_Rating=Float.valueOf(Rating);
			
			list.add(new Movie(rank, poster_src, movie_Title, movie_Year, movie_Total_Votes, movie_Rating));
		}
		
		return list;
	}
}

