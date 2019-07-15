package Managers;

import org.openqa.selenium.WebDriver;

import PageObjects.HomePage;
import PageObjects.TopRatedMoviesPage;

public class PageObjectManager {

	private WebDriver driver;

	private HomePage homePage;

	private TopRatedMoviesPage topRatedMoviesPage;

	public PageObjectManager(WebDriver driver) {

		this.driver = driver;

	}

	public HomePage getHomePage() {

		return (homePage == null) ? homePage = new HomePage(driver) : homePage;

	}

	public TopRatedMoviesPage getTopRatedMoviesPage() {

		return (topRatedMoviesPage == null) ? topRatedMoviesPage = new TopRatedMoviesPage(driver) : topRatedMoviesPage;

	}
}