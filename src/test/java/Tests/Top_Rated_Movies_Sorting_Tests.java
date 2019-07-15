package Tests;

import org.testng.annotations.Test;

import Enum.Sort;
import Managers.ExtentManager;
import Managers.FileReaderManager;
import ObjectClasses.Movie;
import Utility.ListsMethods;

import org.testng.annotations.Listeners;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class Top_Rated_Movies_Sorting_Tests extends BaseTest {

	
 @Test(description="Top Rated Movies Sort By Number Of Ratings Asc order")
  public void Top_Rated_Movies_SortBy_Number_Of_Ratings_Asc() {
	  

	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByNumberofRatings, Sort.Asc);
	
	//ListsMethods.AssertionsForListsComparison(FileReaderManager.getInstance().getJSONFileReader().getJSONTestData(), Actual, Sort.SortByRank, Sort.Desc);
	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByNumberofRatings, Sort.Asc);
	  
	  
  }
 
 
 @Test(description="Top Rated Movies Sort By Number Of Ratings Desc order")
 public void Top_Rated_Movies_SortBy_Number_Of_Ratings_Desc() 
 {
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByNumberofRatings, Sort.Desc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByNumberofRatings, Sort.Desc); 
	  
 }
 
 @Test(description="Top Rated Movies Sort By Ratings Asc order")
 public void Top_Rated_Movies_SortBy_Ratings_Asc() 
 {	 
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByRatings, Sort.Asc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByRatings, Sort.Asc); 	  
 }


@Test(description="Top Rated Movies Sort By Ratings Desc order")
public void Top_Rated_Movies_SortBy_Ratings_Desc() 
{
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByRatings, Sort.Desc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByRatings, Sort.Desc);	  
	  
}

@Test(description="Top Rated Movies Sort By Release Date Asc order")
public void Top_Rated_Movies_SortBy_Date_Asc() 
{
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByDate, Sort.Asc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByDate, Sort.Asc);	  
	  
}

@Test(description="Top Rated Movies Sort By Release Date Desc order")
public void Top_Rated_Movies_SortBy_Date_Desc() 
{
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByDate, Sort.Desc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByDate, Sort.Desc);	  
	  
}

@Test(description="Top Rated Movies Sort By Movie Rank Asc order")
public void Top_Rated_Movies_SortBy_Rank_Asc() 
{
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByRank, Sort.Asc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByRank, Sort.Desc);	// Sort.Desc because Rank 250 is considered lowest rank
	  
}

@Test(description="Top Rated Movies Sort By Movie Rank Desc order")
public void Top_Rated_Movies_SortBy_Rank_Desc() 
{
	List<Movie>ActualData=topRatedMoviesPage.Filter(Sort.SortByRank, Sort.Desc);	
	ListsMethods.AssertionsForListsComparison(MasterData, ActualData, Sort.SortByRank, Sort.Asc);	// Sort.Desc because Rank 1 is considered highest rank  
	  
}
 

}
