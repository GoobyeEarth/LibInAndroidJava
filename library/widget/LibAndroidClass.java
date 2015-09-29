package library.widget;

import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class LibAndroidClass {
	/**
	 * 別のアクティビティのみに対して遷移します。
	 * @param activity
	 * @param cls
	 */
	public static void callActivity(Activity activity, Class<?> cls){
		String thisClass =  ("" + cls).substring(7-1);
		if(!("" + activity).startsWith(thisClass)){
			Intent intent = new Intent(activity, cls);
			activity.startActivity(intent);
			
		}
		else{
			
		}
	}
	
	public static float dpi(Activity activity){
		DisplayMetrics metrics = new DisplayMetrics();  
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);  
		return metrics.density;
	}

}
