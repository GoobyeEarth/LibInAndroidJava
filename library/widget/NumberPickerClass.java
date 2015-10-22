package library.widget;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.NumberPicker;

public class NumberPickerClass extends NumberPicker{
	
	public NumberPickerClass(Context context, int max, int min){
		super(context);
		
		setMaxValue(max);
		setMinValue(min);
	}
	
}
