package DataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import Managers.FileReaderManager;
import ObjectClasses.Movie;

public class JsonDataReader {
	 private final String MoviesTestDataPath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath();
	 private List<Movie> moviesList;
	 
	 public JsonDataReader(){
		 moviesList = getMoviesTestData();
	 }
	 
	 private List<Movie> getMoviesTestData() {
	 Gson gson = new Gson();
	 BufferedReader bufferReader = null;
	 try {
	 bufferReader = new BufferedReader(new FileReader(MoviesTestDataPath));
	 Movie[] customers = gson.fromJson(bufferReader, Movie[].class);
	 return Arrays.asList(customers);
	 }catch(FileNotFoundException e) {
	 throw new RuntimeException("Json file not found at path : " + MoviesTestDataPath);
	 }finally {
	 try { if(bufferReader != null) bufferReader.close();}
	 catch (IOException ignore) {}
	 }
	 }
	 
	 public final List<Movie> getJSONTestData(){
	 return moviesList;
	 }
	 
	 
	}