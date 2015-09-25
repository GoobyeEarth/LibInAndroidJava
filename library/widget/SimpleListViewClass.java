package library.widget;

import java.util.ArrayList;

import android.app.Activity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import library.my_interface.*;

/**
 * this SimpleListViewClass is only used as ListView whose child view is TextView;
 * @author 凜太郎
 *
 */
public class SimpleListViewClass extends ListView{

	private Activity activity;
	private ArrayList<ForListViewData> data;
	private GetViewInterface childViewProcess;
	private SetIntStringArrayInterface beforeAllInterface;

	private MyAdapter myadapter;

	public SimpleListViewClass(Activity context) {
		super(context);
		this.activity = context;
		data = new ArrayList<ForListViewData>();
	}
	/**
	 * 
	 * @param context
	 * @param beforeAllInterface this process is used as 
	 */
	public SimpleListViewClass(Activity context, SetIntStringArrayInterface beforeAllInterface) {
		super(context);
		this.activity = context;
		data = new ArrayList<ForListViewData>();
		this.beforeAllInterface = beforeAllInterface;
	}
	
	public void setChildView(GetViewInterface childView){
		childViewProcess = childView;
		
	}

	public void add(String[] items, SetIntStringArrayInterface process) {
		ForListViewData listViewData = new ForListViewData(items, process);
		data.add(listViewData);
	}
	/**
	 * if you don't use setChildView,
	 * childView is one textView;
	 */
	public void setData(){
		if(childViewProcess == null) {
			childViewProcess = new GetViewInterface() {
				
				@Override
				public View setProcess() {
					LinearLayout linearLayout = new LinearLayout(activity);
					linearLayout.setOrientation(LinearLayout.VERTICAL);
					
					
					TextView textView = new TextView(activity);
					linearLayout.addView(textView);
					textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
					int padding = 25;

					textView.setPadding(padding, padding, padding, padding);
					textView.setTag("0");
					
					
			
					return linearLayout;
				}
			};
		}
		myadapter = new MyAdapter(activity, data);
		setAdapter(myadapter);
		
	}
	
	
	@Override
	public void setOnItemClickListener(final OnItemClickListener forAllInterface){
		
		super.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ForListViewData selectedData = data.get(position);

				if(beforeAllInterface != null) beforeAllInterface.setProcess(position, selectedData.items);
				
				if(selectedData.eachInterface != null) selectedData.eachInterface.setProcess(position, selectedData.items);
//
				if(forAllInterface != null) forAllInterface.onItemClick(parent, view, position, id);
				
			}
		});
	}

	private class MyAdapter extends ArrayAdapter<ForListViewData>{

        public MyAdapter(Activity context, ArrayList<ForListViewData> objects) {
            super(context, 0, objects);
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ForListViewData stringData = this.getItem(position);

            if (convertView == null) {
            	convertView = childViewProcess.setProcess();
            	
            }

    		for(int i=0; i < stringData.items.length; i++){
    			TextView textView = (TextView) convertView.findViewWithTag(String.valueOf(i));
    			textView.setText(stringData.items[i]);
    			
    		}
    		return convertView;
        }

	}

    private class ForListViewData {
        private String[] items;
        private SetIntStringArrayInterface eachInterface;
        public ForListViewData(String[] text, SetIntStringArrayInterface process){
        	this.items = text;
        	this.eachInterface = process;
        }

    }


}


