package library.widget;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class LibAndroidClass {
	/**
	 * 別のアクティビティのみに対して遷移します。
	 * @param activity
	 * @param cls
	 */
	public static void callActivity(Activity activity, Class<?> cls){
		String thisClass =  ("" + cls).substring(7-1);
//		Toast.makeText(activity, thisClass, Toast.LENGTH_SHORT).show();
//		Toast.makeText(activity, "" + activity, Toast.LENGTH_SHORT).show();
		if(!("" + activity).startsWith(thisClass)){
			Intent intent = new Intent(activity, cls);
			activity.startActivity(intent);
			
		}
		else{
			
		}
	}

}
