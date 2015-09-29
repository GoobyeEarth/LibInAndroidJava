package library.widget;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileClass {

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
	
	public static boolean save(String filename, String data){
		 try {
	            //出力先を作成する
	            FileWriter fw = new FileWriter("filename", false);  //※１
	            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

	            //内容を指定する
	            pw.println(data);
	           

	            //ファイルに書き出す
	            pw.close();

	            //終了メッセージを画面に出力する
	            System.out.println("出力が完了しました。");

	        } catch (IOException ex) {
	            //例外時処理
	            ex.printStackTrace();
	        }
		return true;
	}
	
}
