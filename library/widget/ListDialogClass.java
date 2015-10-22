package library.widget;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;

public class ListDialogClass {

	private List<String> itemList;
	private Activity activity;
	private List<ClickListener> itemFuncList;
	private String title="";
	private ClickListener interfaceForward = null;
	private Builder listDlg;


	public ListDialogClass(Activity activity){
		this.activity = activity;
		itemList = new ArrayList<String>();
		itemFuncList = new ArrayList<ClickListener>();
	}
	
	public ListDialogClass(Activity activity, String title){
		this.activity = activity;
		itemList = new ArrayList<String>();
		itemFuncList = new ArrayList<ClickListener>();
		this.title = title;
	}

	public void functionBeforeShow(final ClickListener interfaceForward){
		this.interfaceForward = interfaceForward;
	}
	/**
	 * you can modify more efficient code
	 * "listDlg.setItems("items" ,new d...);
	 * @param interfaceBackward
	 */
	public void show(final ClickListener interfaceBackward){

		listDlg = new AlertDialog.Builder(activity);
        
        listDlg.setTitle(title);
        listDlg.setItems(
            itemList.toArray(new String[0]),
            new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					if(interfaceForward != null) interfaceForward.setProcess(which, itemList.get(which));
					if(itemFuncList.get(which) != null) itemFuncList.get(which).setProcess(which, itemList.get(which));
					if(interfaceBackward != null) interfaceBackward.setProcess(which, itemList.get(which));
				}
            });
        listDlg.create().show();
    }
	
	
	

	public void addItem(String item, ClickListener itemSelectInterface){
		itemList.add(item);
		itemFuncList.add(itemSelectInterface);
	}
	
	
	
	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
	public interface ClickListener{
		public void setProcess(int num, String str);
	}

}
