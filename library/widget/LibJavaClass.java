package library.widget;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LibJavaClass {
	/**
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * 　replacement を返すメゾッドを代入してください。
	 * @return
	 */
	public static String replaceAll(String text ,String regex, GetStringSetStringInterface replacement){
		Matcher matcher = Pattern.compile(regex).matcher(text);
		
		matcher.reset();
        boolean result = matcher.find();
        if (result) {
            StringBuffer sb = new StringBuffer();
            do {
            	
            	matcher.appendReplacement(sb, replacement.setProcess(matcher.group()) );
                result = matcher.find();
            } while (result);
            matcher.appendTail(sb);
            return sb.toString();
        }
        return text;
	}
	/**
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * 　replacement を返すメゾッドを代入してください。
	 * @return
	 */
	public static String replace(String text ,String regex, GetStringSetStringInterface replacement){
		Matcher matcher = Pattern.compile(regex).matcher(text);
		
		matcher.reset();
        boolean result = matcher.find();
        if(result){
        	StringBuffer sb = new StringBuffer();
        	matcher.appendReplacement(sb, replacement.setProcess(matcher.group()) );
        	return sb.toString();
        }
        else{
        	return text;
        }
	}
	/**
	 * 
	 * @param text
	 * @param regex
	 * @param replacement
	 * 　replacement を返すメゾッドを代入してください。
	 * @return
	 */
	public static String replace(String text ,String regex, int ordinal, GetStringSetStringInterface replacement){
		Matcher matcher = Pattern.compile(regex).matcher(text);
		
		matcher.reset();
        boolean result;
        
        for(int i = 0; i < ordinal; i++) {
        	result = matcher.find();
        	if(!result){
        		return text;
        	}
        }
        
        StringBuffer sb = new StringBuffer();
    	matcher.appendReplacement(sb, replacement.setProcess(matcher.group()) );
    	return sb.toString();
	}
	
	
	/**
	 *  cutは　正規表現に合致するものを一つ取り出します。
	 *  base には取り出されたあとの文章を取り出します。
	 *  
	 * @param text
	 * @param regex
	 * @return
	 */
	public static TextData divide(String text, String regex){
		Matcher matcher = Pattern.compile(regex).matcher(text);
		matcher.reset();
        boolean result = matcher.find();
        if (result) {
        	StringBuffer sb = new StringBuffer();
        	matcher.appendReplacement(sb, "" );
        	
        	matcher.appendTail(sb);
        	TextData data = new TextData();
        	data.base = sb.toString();
        	data.cut = matcher.group();
            return data;
        }
        else{
        	TextData data = new TextData();
        	data.base = text;
        	data.cut ="";
        	return data;
        }
        
	}
	
	public static String date(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String strDate = sdf.format(cal.getTime());
        return strDate;
	}
	
	public static long max(long[] data){
		long max = data[0];
		for(int i = 1; i < data.length; i++) {
			if(max < data[i]){
				max = data[i];
			}
		}
		return max;
		
	}
	
	public static class TimeAsSec{
		public static int timeToSec(int hour, int min, int second){
			int sec = timeToSecRow(hour, min, second);
			return sec;
		}
		
		private static int timeToSecRow(int hour, int min, int second){
			min += hour * 60;
			second += min * 60;
			return second;
		}
		
		public static int timeToInDay(int sec){
			if(sec < 0) sec = 0;
			
			if(timeToSecRow(24, 0, 0) <= sec){
				sec = sec - timeToSecRow(24, 0, 0);
			}
			
			return sec;
		}
		
		public static Result secToTime(int sec){
			
			Result result = new Result();
			
			result.hour = sec / (60 * 60);
			result.min = (sec % (60 * 60) ) / 60;
			result.second = (sec % (60 * 60) ) % 60;
			
			return result;
			
		}
		
		public static class Result{
			public int hour;
			public int min;
			public int second;
			public int timeToSec(){
				int sec = TimeAsSec.timeToSec(hour, min, second);
				return sec;
			}
			
			public void timeToInDay(){
				if(hour == 24) hour = 0;
			}
			
			public Result(){
				
			}
			
			public Result(int sec){
				Result result = secToTime(sec);
				hour = result.hour;
				min = result.min;
				second = result.second;
				
			}
		}
	}
	
	public static class TextData{
		public String base;
		public String cut;
	}
	
	public interface GetStringSetStringInterface{
		public String setProcess(String str);
	}
	
}
