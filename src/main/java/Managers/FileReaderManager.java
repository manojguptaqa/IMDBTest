package Managers;

import DataProviders.ConfigFileReader;
import DataProviders.JsonDataReader;

public class FileReaderManager {
	 
	 private static FileReaderManager fileReaderManager = new FileReaderManager();
	 private static ConfigFileReader configFileReader;
	 private static JsonDataReader jsonFileReader;
	 
	 private FileReaderManager() {
	 }
	 
	 public static FileReaderManager getInstance( ) {
	       return fileReaderManager;
	 }
	 
	 public ConfigFileReader getConfigReader() {
	 return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	 }
	 
	 public JsonDataReader getJSONFileReader() {
		 return (jsonFileReader == null) ? new JsonDataReader() : jsonFileReader;
		 }
	}