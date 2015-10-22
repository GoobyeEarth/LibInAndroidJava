package library.widget;

import android.content.Context;
import android.webkit.WebView;

public class WebViewClass extends WebView{
	private OnLoadBeforeListener onBefore;

	public WebViewClass(Context context) {
		super(context);
		
		
	}
	
	@Override
	public void loadUrl(String url) {
		if(onBefore  != null) onBefore.onLoadBefore(url);
		super.loadUrl(url);
		
		
	}
	
	public void setOnLoadStartListener(OnLoadBeforeListener onLoadBeforeListener){
		onBefore = onLoadBeforeListener;
	}
	
	public interface OnLoadBeforeListener{
		public void onLoadBefore(String url);
	}

}
