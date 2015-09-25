package library.widget;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceClass {
	SharedPreferences pref;
	public PreferenceClass(Context context, String fileName){
		pref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE );
	}
	
	
	public void saveString(String key, String data){
		Editor editor = pref.edit();
        editor.putString( stringKey(key), data );
        editor.commit();
	}
	
	public String loadString(String key){
		return pref.getString(stringKey(key), "");
	}
	
/////////////////////////////key//////////////////////////////////////////////////

private String intKey(String key){
return "Int_" + key;
}

private String floatKey(String key){
return "Float_" + key;
}

private String booleanKey(String key) {
return "Boolean_" + key;
}


private String stringKey(String key){
return "String_" + key;

}


private String arrayKey(String key, int element){
return key + "_" + String.valueOf(element);
}
private String arrayLengthKey(String key){
return key + "_count";
}

}
