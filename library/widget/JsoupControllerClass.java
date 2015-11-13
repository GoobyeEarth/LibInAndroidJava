package library.widget;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class JsoupControllerClass {
	
	
	public static String select(String html, String selecta){
		
		if (html != null) {
			Document document = Jsoup.parse(html);
			Elements elements = document.select(selecta);
			String result = "";
			for (Element element : elements) {
				String tagName = element.tagName();
				String innerHtml = element.outerHtml();
				result += innerHtml + "\n";
			}
			return result;
			
		}
		else{
			return null;
		}
	}
	
	public static String select(Document document, String selecta){
		Elements elements = document.select(selecta);
		String result = "";
		for (Element element : elements) {
			String tagName = element.tagName();
			String innerHtml = elements.outerHtml();
			result += innerHtml + "\n";
		}
		return result;
	}
	
	
	
	private static String removeTag(String html, String tag){
		html = html.replaceAll("<"+ tag +"[ /\t/\n]*[^<>]*>", "");
		return html.replaceAll("</"+ tag +">", "");
	}
	
	public static List<String> searchContain(String html, String contain, String parentTag){
		List<String> list = new ArrayList<String>();
		
		Document document = Jsoup.parse(html);
		Elements elements = document.select( parentTag + ":contains(" + contain + ")");
		
		for (Element element : elements) {
			list.add(element.cssSelector());
		}
		
		return list;
	}
	
	public static List<String> searchContain(Document document, String contain){
		List<String> list = new ArrayList<String>();
		Elements elements = document.select( ":contains(" + contain + ")");
		System.out.println("" + document.html());
		
		for (Element element : elements) {
			list.add(element.cssSelector());
		}
		
		return list;
	}
	
	
	public static List<ResultData> selectsGettingData(Document document, String selecta){
		
		Elements elements = document.select(selecta);
		List<ResultData> results = new 	ArrayList<ResultData>();
		for (Element element : elements) {
			
			results.add(new ResultData(element.outerHtml(), element.cssSelector()) );
		}
		return results;
	}
	
	
	public static class ResultData{
		
		public String html;
		public String indivisualSelecta;
		public ResultData(){
			
		}
		
		public ResultData(String html, String selecta){
			this.html = html;
			indivisualSelecta = selecta;
		}
	}
}
