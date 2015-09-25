package library.widget;
import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class ReferenceValueClass {
	public static int getDpi(Activity activity){
		DisplayMetrics metrics = new DisplayMetrics();  
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);  
		int dpi1 = metrics.densityDpi;
		return dpi1;
		 
	}
	
	public static int getDpi(Context context){
		return context.getResources().getDisplayMetrics().densityDpi;
	}
}
