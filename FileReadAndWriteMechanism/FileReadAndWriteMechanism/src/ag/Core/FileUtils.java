package ag.Core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtils {

	public String filePath = null;
	public FileUtils(String filePath){
		this.filePath=filePath;
	}
	
	public void writeFile(String strContains){
		
		File ofile = new File(filePath);
		FileWriter fw = null;
		try {
			fw = new FileWriter(ofile, true);
		} catch (IOException e) {
			System.out.println("File not found! Unable to write the contains into file.");
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(strContains);
		pw.close();
		System.out.println("File write Successful");
	}
	
	public ArrayList<String> readFile(){
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filePath));
		} catch (FileNotFoundException e) {
			System.out.println("File not found! Unable to write the contains into file.");
		}
		
		String line = null;
		ArrayList<String> oList = new ArrayList<>();
		while (scan.hasNext()) {
			line = scan.nextLine();
			oList.add(line);
		}
		return oList;
	}
}
