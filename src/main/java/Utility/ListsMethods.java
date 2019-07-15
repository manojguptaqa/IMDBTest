package Utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Enum.ScreenshotType;
import Enum.Sort;
import ObjectClasses.Movie;

public class ListsMethods {

//public static List<Movie> compareTwoLists(List<Movie>ActualList, List<Movie> ExpectedList) {
//		
//		List<Movie> difference=new ArrayList<>();
//		List<Movie> sortCheck=new ArrayList<>();
//		
//		if(ActualList.size()!=ExpectedList.size())
//		{
//		difference = ActualList.stream()
//				.filter(A -> (ExpectedList.stream().filter(E -> A.equals(E)).count()) < 1).collect(Collectors.toList());
//		
//		difference = ExpectedList.stream()
//				.filter(E -> (ActualList.stream().filter(A -> E.equals(A)).count()) < 1).collect(Collectors.toList());
//		}
//		else
//		{
//			for(int i=0;i<ActualList.size();i++)
//			{
//				if(!ActualList.get(i).equals(ExpectedList.get(i)))
//				{
//					sortCheck.add(ActualList.get(i));
//				}
//							
//			}
//			
//			difference = ExpectedList.stream()
//					.filter(E -> (ActualList.stream().filter(A -> E.equals(A)).count()) < 1).collect(Collectors.toList());
//		}
//		
//		if(sortCheck.size()>0)
//		{
//			System.out.println("Sorting not as expected.");
//			ListToJSON(sortCheck);
//			
//		}
//		
//		if(difference.size()>0)
//		{
//			System.out.println("Data not as expected.");
//			ListToJSON(difference);
//			
//		}
//		
//		return sortCheck;
//
//	}
	
	public static List<Movie> compareTwoLists(List<Movie>ActualList, List<Movie> ExpectedList) {
		
		List<Movie> diff=new ArrayList<>();
		
		ExtentReportsUtility.printJson(ListsMethods.ListToJSON(ActualList), "Actual Data Displayed in Application (Count="+ActualList.size()+")");
		ExtentReportsUtility.printJson(ListsMethods.ListToJSON(ExpectedList), "Expected Data (Count="+ExpectedList.size()+")");
		
		if(ActualList.size()!=ExpectedList.size())
		{
			throw new RuntimeException("Data Mismatch!");
		}
		
		else
		{
	
			for(int i=0;i<ActualList.size();i++)
			{
				if(!ActualList.get(i).equals(ExpectedList.get(i)))
				{
					diff.add(ActualList.get(i));
				}
							
			}

//			if(diff.size()==ExpectedList.size()-1)
//			{
//				throw new RuntimeException("Data not sorted as expected!");
//			}

		}
		
		
		
		return diff;

	}


	public static List<Movie> sortMoviesByRank(List<Movie> movies, Sort sortType) {
		List<Movie> Mov = movies;

		if (sortType == (Sort.Asc)) {
			Mov.sort(Comparator.comparing(Movie::getId));
		}

		else if (sortType == (Sort.Desc)) {
			Comparator<Movie> comparator = Comparator.comparing(Movie::getId);
			Mov.sort(comparator.reversed());
		}

		return Mov;
	}

	public static List<Movie> sortMoviesByDate(List<Movie> movies, Sort sortType) {
		List<Movie> Mov = movies;

		if (sortType == (Sort.Asc)) {
			Mov.sort(Comparator.comparing(Movie::getYear));
		}

		else if (sortType == (Sort.Desc)) {
			Comparator<Movie> comparator = Comparator.comparing(Movie::getYear);
			Mov.sort(comparator.reversed());
		}

		return Mov;
	}

	public static List<Movie> sortMoviesByNumberofRatings(List<Movie> movies, Sort sortType) {
		List<Movie> Mov = movies;

		if (sortType == (Sort.Asc)) {
			Mov.sort(Comparator.comparing(Movie::getCountOfRatings));
		}

		else if (sortType == (Sort.Desc)) {
			Comparator<Movie> comparator = Comparator.comparing(Movie::getCountOfRatings);
			Mov.sort(comparator.reversed());
		}

		return Mov;
	}

	public static List<Movie> sortMoviesByRatings(List<Movie> movies, Sort sortType) {
		List<Movie> Mov = movies;

		if (sortType == (Sort.Asc)) {
			Mov.sort(Comparator.comparing(Movie::getRating));
		}

		else if (sortType == (Sort.Desc)) {
			Comparator<Movie> comparator = Comparator.comparing(Movie::getRating);
			Mov.sort(comparator.reversed());
		}

		return Mov;
	}
	
	
	public static void AssertionsForListsComparison(List<Movie> expectedList, List<Movie> CurrentDisplayMovieList,Sort FilterBy,Sort sortType)
	{
		List<Movie> sortedExpectedList;

		switch (FilterBy) {
		case SortByRank:
			sortedExpectedList = sortMoviesByRank(expectedList, sortType);
			break;
		case SortByDate:
			sortedExpectedList = sortMoviesByDate(expectedList, sortType);
			break;
		case SortByNumberofRatings:
			sortedExpectedList = sortMoviesByNumberofRatings(expectedList, sortType);
			break;
		case SortByRatings:
			sortedExpectedList = sortMoviesByRatings(expectedList, sortType);
			break;
		default:
			throw new RuntimeException("No valid Sorting option found.");
		}
		
//		System.out.println("+++++++++++Expected List+++++++++++++++++++++++++++++++++++++++++++");
//		ListToJSON(sortedExpectedList);
//		System.out.println("+++++++++++Actual List+++++++++++++++++++++++++++++++++++++++++++");
//		ListToJSON(CurrentDisplayMovieList);
//		System.out.println("++++++++++++++++++END++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		List<Movie> difference = compareTwoLists(CurrentDisplayMovieList,sortedExpectedList);
		System.out.println(difference.size());

		
		if(difference.size()==expectedList.size())
		{
			ExtentReportsUtility.reportStep("sorting not as expected.");
			ExtentReportsUtility.AttachScreenShotToReport(ScreenshotType.partial);
		}
		else if(difference.size()<1)
		{
			ExtentReportsUtility.reportPass("<b>Sorting As expected.</b>");
		}

		else 
		{
			String diffJsonString=ListToJSON(difference);
			ExtentReportsUtility.printJson(diffJsonString, "Data mismatch for below movies (Count="+difference.size()+")");
			ExtentReportsUtility.AttachScreenShotToReport(ScreenshotType.partial);
			

		}


		Assert.assertTrue(difference.size() < 1, "<b>After sorting data not as expected</b>");

	}
	
	
	
	public static String ListToJSON(List<Movie> movies) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(movies);
		return json;
	}
	
	

}
