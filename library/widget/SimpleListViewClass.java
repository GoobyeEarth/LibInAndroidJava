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
	private ChildViewListener childView;
	private ItemClickListener beforeListner;

	private MyAdapter myadapter;

	public SimpleListViewClass(Activity activity) {
		super(activity);
		this.activity = activity;
		data = new ArrayList<ForListViewData>();
	}
	/**
	 * 
	 * @param context
	 * @param beforeListner this process is used as 
	 */
	public SimpleListViewClass(Activity context, ItemClickListener beforeListner) {
		super(context);
		this.activity = context;
		data = new ArrayList<ForListViewData>();
		this.beforeListner = beforeListner;
	}
	
	public void setChildView(ChildViewListener childView){
		this.childView = childView;
		
	}

	public void add(String[] items, ItemClickListener process) {
		ForListViewData listViewData = new ForListViewData(items, process);
		data.add(listViewData);
	}
	/**
	 * if you don't use setChildView,
	 * childView is one textView;
	 */
	public void setData(){
		if(childView == null) {
			childView = new ChildViewListener() {
				
				@Override
				public View setView() {
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

				if(beforeListner != null) beforeListner.onClick(position, selectedData.items);
				
				if(selectedData.eachListner != null) selectedData.eachListner.onClick(position, selectedData.items);

				if(forAllInterface != null) forAllInterface.onItemClick(parent, view, position, id);
				
			}
		});
	}
	
	@Override
	public void setOnItemLongClickListener( final OnItemLongClickListener forAllInterface){
		super.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				ForListViewData selectedData = data.get(position);

				if(beforeListner != null) beforeListner.onLongClick(position, selectedData.items);
				
				if(selectedData.eachListner != null) selectedData.eachListner.onLongClick(position, selectedData.items);
				
				if(forAllInterface != null) forAllInterface.onItemLongClick(parent, view, position, id);
				
				
				
				return true;
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
            	convertView = childView.setView();
            	
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
        private ItemClickListener eachListner;
        public ForListViewData(String[] text, ItemClickListener eacheListner){
        	this.items = text;
        	this.eachListner = eacheListner;
        }

    }
    
    public interface ChildViewListener{
    	public View setView();
    }
    
    public interface ItemClickListener{
    	public void onClick(int num, String[] textArray);
    	public void onLongClick(int num, String[] textArray);
    }


}


