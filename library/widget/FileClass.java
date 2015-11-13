package library.widget;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileClass {
	
	public static String[][] loadAsCsv(String filename){
		String[] row = load(filename);
		String[][] result = new String[row.length][];
		for(int i = 0; i < row.length; i++) {
			result[i] = row[i].split(",");
		}
		return result;
	}

	public static String[] load(String filename) {
		ArrayList<String> data = new ArrayList<String>();
		
		try {
			File file = new File(filename);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			while(str != null){
				data.add(str);
				str = br.readLine();
			}

			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
		return data.toArray(new String[0]);
	}
	
	public static void save(String[] data, String filename){
		try{
			  File file = new File(filename);
			  FileWriter filewriter = new FileWriter(file);
			  
			  for (String string : data) {
				filewriter.append(string + "\n");
			  }
			  filewriter.close();
			}catch(IOException e){
			  System.out.println(e);
			}
	}
	
	public static void append(String[] data, String filename){
		try{
			  File file = new File(filename);
			  FileWriter filewriter = new FileWriter(file, true);
			  
			  for (String string : data) {
				filewriter.append(string + "\n");
			  }
			  filewriter.close();
			}catch(IOException e){
			  System.out.println(e);
			}
	}
	
	public static void saveAsCsv(String[][] data, String filename){
		String[] newLine = new String[data.length];
		for(int lineNum = 0; lineNum < data.length; lineNum++) {
			newLine[lineNum] = "";
			if(1 <= data[lineNum].length) newLine[lineNum] = data[lineNum][0];
			for(int columnNum = 1; columnNum < data[lineNum].length; columnNum++) {
				newLine[lineNum] += "," + data[lineNum][columnNum];
			}
		}
		save(newLine, filename);
		
	}
	
}
