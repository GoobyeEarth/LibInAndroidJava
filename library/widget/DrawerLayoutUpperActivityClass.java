package library.widget;

import com.example.t15sep24.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
/**
 * スライド式のサイドメニューを作るためのActivityです。
 * setMainViewで普段表示させるviewを
 * setSideViewで横に表示されるviewを
 * 指定します。
 * themeとsideIconでコードを変更できます。
 * @author 凜太郎
 *
 */
public class DrawerLayoutUpperActivityClass extends Activity{

	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle actionBarDrawerToggle;
	protected int theme = android.R.style.Theme_Holo_Light;
	protected int sideIcon = R.drawable.abc_ab_share_pack_mtrl_alpha;
	private InputMethodManager inputMethodManager;
	
	private View mainView;
	private View sideView;
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		setTheme(theme);
		drawerLayout = new DrawerLayout(this);
		
//		drawerLayout.setFocusableInTouchMode(true);
//		drawerLayout.setFocusable(true);
		
		setContentView(drawerLayout);

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, sideIcon, 0) {
	        @Override
	        public void onDrawerClosed(View drawerView) {
	        }
	        @Override
	        public void onDrawerOpened(View drawerView) {
	        }
	        @Override
	        public void onDrawerSlide(View drawerView, float slideOffset) {
	            // ActionBarDrawerToggleクラス内の同メソッドにてアイコンのアニメーションの処理をしている。
	            // overrideするときは気を付けること。
	            super.onDrawerSlide(drawerView, slideOffset);
	        }
	        @Override
	        public void onDrawerStateChanged(int newState) {
	            // 表示済み、閉じ済みの状態：0
	            // ドラッグ中状態:1
	            // ドラッグを放した後のアニメーション中：2
	        }
	    };

	    drawerLayout.setDrawerListener(actionBarDrawerToggle);

	    // UpNavigationアイコン(アイコン横の<の部分)を有効に
	    // NavigationDrawerではR.drawable.drawerで上書き
	    getActionBar().setDisplayHomeAsUpEnabled(true);
	    // UpNavigationを有効に
	    getActionBar().setHomeButtonEnabled(true);





	}

	protected void setMainView(View view){
		drawerLayout.addView(view);
		mainView = view;
		view.setFocusableInTouchMode(true);
		view.setFocusable(true);
		
	}

	protected void setSideView(View view, int width, int height){
		DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
				width,
				height);
		params.gravity = Gravity.LEFT;

		drawerLayout.addView(view, params);
	}

	protected void setSideView(View view, int width){
		DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
				width,
				LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.LEFT;

		drawerLayout.addView(view, params);
		sideView = sideView;
	}

	protected void setSideView(View view){
		DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT,
				LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.START;

		drawerLayout.addView(view, params);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // ActionBarDrawerToggleにandroid.id.home(up ナビゲーション)を渡す。
	    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
	        return true;
	    }

	    return super.onOptionsItemSelected(item);
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if(keyCode != KeyEvent.KEYCODE_BACK){
    		return super.onKeyDown(keyCode, event);
    	}else{

    		return false;
    	}
    }

}
