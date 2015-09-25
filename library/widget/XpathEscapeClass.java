package library.widget;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.widget.Toast;
import library.my_interface.GetStringSetStringInterface;

public class XpathEscapeClass {
	public static String escape(String src){
		
		//fix
		src = deleteSemiColonInTag(src);
		
		//need
		src = addEnd(src, "img");
		src = addEnd(src, "input");
		
		
		
		//delete
		src = delete(src, "meta");
		src = delete(src, "link");
		src = delete(src, "br");
		
		//escape against Html code
		
		//escape
		src = and(src);
		src = greaterInScript(src);
		return src;
	}
	
	private static String and(String src){
		return src.replaceAll("&", "&amp;");
	}
	
	
	private static String delete(String src, String tag){
		return src.replaceAll("<" + tag + "[ /\t/\n]*[^<>]*>", "");//need *
	}
	
	private static String addEnd(String src, final String tag){
		return LibJavaClass.replaceAll(src, "<" + tag + "[ /\t/\n]*[^<>]*>", new GetStringSetStringInterface() {//need *
			
			@Override
			public String setProcess(String str) {
				str = str.replace(">", "/>");
				
				return str;
			}
		});
	}
	
	private static String deleteSemiColonInTag(String src){
		src = LibJavaClass.replaceAll(src, "<[^<>]*;[^<>]*>", new GetStringSetStringInterface() {
			
			@Override
			public String setProcess(String str) {
				
				str = LibJavaClass.replaceAll(str, "\"[ /\t/\n]*;", new GetStringSetStringInterface() {
					
					@Override
					public String setProcess(String str) {
						str = str.replace(";", "");
						return str;
					}
				});
				
				return str;
			}
		});
		
		return src;
		
	}
	

	
	
	private static String greaterInScript(String src){
		CustumReplaceAllClass replace = new CustumReplaceAllClass();
		do {
			src = replace.replaceAllCustom(src, "<script[ /\t/\n]*[^<>]*>[^<]*<[^/]", new GetStringSetStringInterface() {
				
				@Override
				public String setProcess(String str) {
					str = LibJavaClass.replace(str, "<", 2, new GetStringSetStringInterface() {
						
						@Override
						public String setProcess(String str) {
							return "&lt;";
						}
					});
					return str;
				}
			});
		} while (replace.find());
		return src;
	}
	/**
	 * ReplaceAllにあったかどうかを実装したクラスです。
	 * @author aberintaro
	 *
	 */
	public static class CustumReplaceAllClass{
		private boolean find = true;
		
		
		public CustumReplaceAllClass(){
		}
		public String replaceAllCustom(String text ,String regex, GetStringSetStringInterface replacement){
			Matcher matcher = Pattern.compile(regex).matcher(text);
			
			matcher.reset();
	        boolean result = matcher.find();
	        find = result;
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
		public boolean find(){
			return find;
		}
		
	}
	
	
	
	
}
