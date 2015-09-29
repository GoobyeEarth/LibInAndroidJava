package library.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.Button;
import android.widget.ImageButton;

public class ImageButtonExtendedClass extends ImageButton{
	private GradientDrawable pressedDrawable;
	private GradientDrawable unpressedDrawable;
	private boolean isDrawable = false;
	
	public ImageButtonExtendedClass(Context context) {
		super(context);
		setDrawable();
	}
	
	private void setDrawable(){
		StateListDrawable stateListDrawable = new StateListDrawable();
		setBackground(stateListDrawable);
		pressedDrawable = new GradientDrawable();
		unpressedDrawable = new GradientDrawable();
		stateListDrawable.addState( new int[]{ android.R.attr.state_pressed }, pressedDrawable );
		stateListDrawable.addState( new int[]{ -android.R.attr.state_pressed }, unpressedDrawable );
	}
	/**ex)
	 * GradientDrawable.OVAL
	 * @param shape
	 */
	public void setShape(int shape){
		pressedDrawable.setShape(shape);
		unpressedDrawable.setShape(shape);
	}
	
	public void setColor(int color){
		pressedDrawable.setColor(colorDarker(color, 0.6));
		unpressedDrawable.setColor(color);
	}
	private int colorDarker(int color, double rate){
		int red = Color.red(color);
		int blue = Color.blue(color);
		int green = Color.green(color);
		red = (int) (red * rate);
		blue = (int) (blue * rate);
		green = (int) (green * rate);
		return Color.rgb(red, green, blue);
	}

}
