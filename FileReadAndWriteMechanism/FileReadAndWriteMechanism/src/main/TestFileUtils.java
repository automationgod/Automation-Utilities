package main;

import java.util.ArrayList;

import ag.Core.FileUtils;

public class TestFileUtils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileUtils fileUtils = new FileUtils(System.getProperty("user.dir")+"/Doc/SampleFile.req");
		
		//Write into any file
		fileUtils.writeFile("My 3rd commit into the file.");
		
		
		// Read any file		
		ArrayList<String> oList = fileUtils.readFile();
		for(String items : oList){
			System.out.println(items);
		}
	}

}
