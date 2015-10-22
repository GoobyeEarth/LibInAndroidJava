package library.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;

public class FragmentDialogUpperClass extends DialogFragment{
	protected String title;
	protected View view;
	private boolean okButtonExists = false;
	private boolean titleExists = false;
	private Activity activity;
	private OnClickListener okClick;
	public FragmentDialogUpperClass(String title, View view, Activity activity) {
		super();
		this.title = title;
		this.view = view;
		this.activity = activity;
		titleExists = true;
	}
	public FragmentDialogUpperClass(View view, Activity activity) {
		super();
		this.view = view;
		this.activity = activity;
	}


	

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {


		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		if(titleExists){
			builder.setMessage(title);
		}
		
		
		builder.setView(view);
		if(okButtonExists){
			builder.setPositiveButton("はい", okClick)
			.setNegativeButton("キャンセル",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int id) {
							
						}
					});
		}
		

		return builder.create();
	}
	
	 public void show(String tag) {
		super.show(activity.getFragmentManager(), tag);
	}
	 
	 public void show(String tag, DialogInterface.OnClickListener okClick){
		 this.okClick = okClick;
		 okButtonExists = true;
		 super.show(activity.getFragmentManager(), tag);
		 
	 }
	
	
}
