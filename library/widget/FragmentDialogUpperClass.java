package library.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class FragmentDialogUpperClass extends DialogFragment{
	protected String title;
	protected View view;
	protected OkClickInterface okProcess;
	private boolean okButtonExists = false;
	private boolean titleExists = false;
	public FragmentDialogUpperClass(String title, View view) {
		super();
		this.title = title;
		this.view = view;
		titleExists = true;
	}
	public FragmentDialogUpperClass(View view) {
		super();
		this.view = view;
	}


	public void setOkClickListener(OkClickInterface okProcess){
		this.okProcess = okProcess;
		
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {


		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		if(titleExists){
			builder.setMessage(title);
		}
		
		
		builder.setView(view);
		if(okButtonExists){
			builder.setPositiveButton("はい",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int id) {
							if(okProcess != null) okProcess.onClick();
						}
					})
			.setNegativeButton("キャンセル",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int id) {
							
						}
					});
		}
		

		return builder.create();
	}
	
	public void setOkButton(OkClickInterface okProcess){
		this.okProcess = okProcess;
		okButtonExists = true;
	}
	
	public interface OkClickInterface{
		public void onClick();
	}
	
	
}
