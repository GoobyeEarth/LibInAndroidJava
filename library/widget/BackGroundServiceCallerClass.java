package library.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

public class BackGroundServiceCallerClass {
	private static void callPeriodically(Context context, Class<? extends Service> service, long intervalMillis){
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		
		
		Intent intent = new Intent(context, service);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, intervalMillis, intervalMillis, pendingIntent);
		
		
	}
	
	public static void callPeriodicallySec(Context context, Class<? extends Service> service, int sec){
		callPeriodically(context, service, sec * 1000);
	}
	public static void callPeriodicallyMin(Context context, Class<? extends Service> service, int min){
		callPeriodically(context, service, min * 60 * 1000);
	}
	
	public static void callPeriodicallyDay(Context context, Class<? extends Service> service, int day){
		callPeriodically(context, service, day * 24 * 60 * 60 * 1000);
	}
	
	public static void stop(Context context, Class<? extends Service> service){
		
		Intent intent = new Intent(context, service);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		pendingIntent.cancel();
	}
	
	public static void hoge(Class<? extends Service> service, Context context){
		
		AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		
		
		Intent intent = new Intent(context, service);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		long triggerAtMillis = 10 * 1000;
		
		long intervalMillis = 10 * 1000;
		
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtMillis, intervalMillis, pendingIntent);
		
	}
}
