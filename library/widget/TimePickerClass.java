package library.widget;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class TimePickerClass {
	private NumberPickerClass hourPicker;
	private NumberPickerClass minPicker;
	private NumberPickerClass secondPicker;
	private FragmentDialogClass dialog;

	public TimePickerClass(Activity activity, int hour , int min, int second){
		Constracta(activity, hour, min, second, 23);	
	}
	
	public TimePickerClass(Activity activity, LibJavaClass.TimeAsSec.Result result){
		Constracta(activity, result.hour, result.min, result.second, 23);
	}
	
	public TimePickerClass(Activity activity, int hour , int min, int second, int hourMax){
		Constracta(activity, hour, min, second, hourMax);	
	}
	
	public TimePickerClass(Activity activity, LibJavaClass.TimeAsSec.Result result, int hourMax){
		Constracta(activity, result.hour, result.min, result.second, hourMax);
	}
	
	
	public void Constracta(Activity activity, int hour , int min, int second, int hourMax){
		LinearLayout linearLayout = new LinearLayout(activity);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		
		hourPicker = new NumberPickerClass(activity, hourMax, 0);
		hourPicker.setValue(hour);
		LayoutParams hourLP = hoge();
		linearLayout.addView(hourPicker, hourLP);
		
		minPicker = new NumberPickerClass(activity, 59, 0);
		LayoutParams minLP = hoge();
		linearLayout.addView(minPicker, minLP);
		minPicker.setValue(min);
		
		secondPicker = new NumberPickerClass(activity, 59, 0);
		LayoutParams secondLP = hoge();
		linearLayout.addView(secondPicker, secondLP);
		secondPicker.setValue(second);
		
		dialog= new FragmentDialogClass(linearLayout, activity);
	}
	
	private LayoutParams hoge(){
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		params.weight = 1;
		return params;
	}
	
	
	/**
	 * 
	 * @param tag
	 * @param okProcess setProcess when ok pressed
	 */
	public void show(String tag, OnClickListener okProcess){
		dialog.show(tag, okProcess);
	}
	
	public int getHour(){
		return hourPicker.getValue();
	}
	
	public int getMin(){
		return minPicker.getValue();
	}
	
	public int getSecond(){
		return secondPicker.getValue();
	}
	
	
	
	

}
