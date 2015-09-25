package library.widget;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataPreference {
	SharedPreferences pref;
	/**
	 * DATA_OF_ACTIVITY1<br>
	 * @param activity
	 * @param fileName
	 */
	public DataPreference(Activity activity, String fileName){
		pref = activity.getSharedPreferences(fileName, Context.MODE_PRIVATE );
	}

	public DataPreference(Context context, String fileName){
		pref = context.getSharedPreferences(fileName, Context.MODE_PRIVATE );
	}
	///////////////////////int//////////////////////////////////////////
	public void intSave(String key, int num){

        Editor editor = pref.edit();
        editor.putInt( intKey(key), num );
        editor.commit();
	}
	/**
	 * CURRENT_EDIT_RULE_ID<br>
	 * @param key
	 * @return
	 */
	public int intLoad(String key) {
		return pref.getInt(intKey(key), -1);
	}

	public void intArraySave(String key, int[] intDataArray){
		Editor editor = pref.edit();

		editor.putInt(arrayLengthKey(key), intDataArray.length);

		for(int i=0; i<intDataArray.length; i++){

			editor.putInt(arrayKey(intKey(key), i), intDataArray[i]);
		}
		editor.commit();
	}

	public int[] intArrayLoad(String key){
		int arrayLength = pref.getInt(arrayLengthKey(key), 0);
		int[] intArray;
		if(arrayLength !=0){
			intArray = new int[arrayLength];
			for(int i=0; i<arrayLength; i++){
				intArray[i] =pref.getInt( arrayKey(intKey(key), i), -1 );
			}

		}else{
			intArray = new int[1];
			intArray[0] = -1;
		}
		return intArray;
	}

	public int intArray3dElementLoad(String key,int element1, int element2, int element3){
		return pref.getInt(arrayKey(arrayKey(arrayKey(intKey(key), element1), element2), element3), -1);
	}

	public void intArray3dElementSave(String key,int element1, int element2, int element3, int intData){
		Editor editor = pref.edit();
		editor.putInt(arrayKey(arrayKey(arrayKey(intKey(key), element1), element2), element3), intData);
		editor.commit();
	}

	public void intArray2dElementSave(String key,int element1, int element2, int intData){
		Editor editor = pref.edit();
		editor.putInt(arrayKey(arrayKey(intKey(key), element1), element2), intData);
		editor.commit();
	}
	public int intArray2dElementLoad(String key,int element1, int element2){
		return pref.getInt(arrayKey(arrayKey(intKey(key), element1), element2), -1);
	}

	public void intArrayElementSave(String key,int element, int intData){
		Editor editor = pref.edit();
		editor.putInt(arrayKey(intKey(key), element), intData);
		editor.commit();
	}

	public int intArrayElementLoad(String key, int element){
		return pref.getInt( arrayKey(intKey(key), element), -1 );
	}

	////////////////////////////float//////////////////////////////////////////////////////////

	public void floatArrayElementSave(String key,int element, float floatData){
		Editor editor = pref.edit();
		editor.putFloat(arrayKey(floatKey(key), element), floatData);
		editor.commit();
	}

	public float floatArrayElementLoad(String key, int element){
		return pref.getFloat( arrayKey(floatKey(key), element), -1 );
	}

	//////////////////////////boolean//////////////////////////////

	public void booleanSave(String key, boolean truth){

        Editor editor = pref.edit();
        editor.putBoolean( booleanKey(key), truth);
        editor.commit();
	}
	public boolean booleanLoad(String key) {
		return pref.getBoolean(booleanKey(key), false);
	}

	public void booleanArrayElementSave(String key,int element, boolean booleanData){
		Editor editor = pref.edit();
		editor.putBoolean(arrayKey(booleanKey(key), element), booleanData);
		editor.commit();
	}


	public boolean booleanArrayElementLoad(String key, int element){
		return pref.getBoolean( arrayKey(booleanKey(key), element), false);
	}

	public boolean[] booleanArrayLoad(String key) {
		int arrayLength = pref.getInt(arrayLengthKey(key), 0);
		boolean[] booleanArray;
		if(arrayLength !=0){
			booleanArray = new boolean[arrayLength];
			for(int i=0; i<arrayLength; i++){
				booleanArray[i] =pref.getBoolean( arrayKey(booleanKey(key), i), false );
			}

		}else{
			booleanArray = new boolean[1];
			booleanArray[0] = false;
		}
		return booleanArray;
	}

	public boolean booleanArray2dElementLoad(String key, int element1, int element2) {
		return pref.getBoolean(arrayKey(arrayKey(booleanKey(key), element1), element2), false);
	}

	public void booleanArray2dElementSave(String key, int element1, int element2, boolean booleanData){
		Editor editor = pref.edit();
		editor.putBoolean(arrayKey(arrayKey(booleanKey(key), element1), element2), booleanData);
		editor.commit();
	}

	public boolean booleanArray3dElementLoad(String key, int element1, int element2, int element3) {
		return pref.getBoolean(arrayKey(arrayKey(arrayKey(booleanKey(key), element1), element2), element3), false);
	}

	public void booleanArray3dElementSave(String key, int element1, int element2, int element3, boolean booleanData){
		Editor editor = pref.edit();
		editor.putBoolean(arrayKey(arrayKey(arrayKey(booleanKey(key), element1), element2), element3), booleanData);
		editor.commit();
	}

	////////////////////String///////////////////////////////////
	
	
	public void stringArrayElementSave(String key,int element, String stringData){
		Editor editor = pref.edit();
		editor.putString(arrayKey(stringKey(key), element), stringData);
		editor.commit();
	}

	public String stringArrayElementLoad(String key, int element){
		return pref.getString( arrayKey(stringKey(key), element), "");
	}

	public void stringArray2dElementSave(String key,int element1, int element2, String intData){
		Editor editor = pref.edit();
		editor.putString(arrayKey(arrayKey(stringKey(key), element1), element2), intData);
		editor.commit();
	}
	public String stringArray2dElementLoad(String key,int element1, int element2){
		return pref.getString(arrayKey(arrayKey(stringKey(key), element1), element2), "");
	}

	public void stringArray3dElementSave(String key,int element1, int element2, int element3, String intData){
		Editor editor = pref.edit();
		editor.putString(arrayKey(arrayKey(arrayKey(stringKey(key), element1), element2), element3), intData);
		editor.commit();
	}
	public String stringArray3dElementLoad(String key,int element1, int element2, int element3){
		return pref.getString(arrayKey(arrayKey(arrayKey(stringKey(key), element1), element2), element3), "");
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
