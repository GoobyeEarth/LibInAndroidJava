package library.widget;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * テーブルの作成用SQL
 * id は　自分でカウントする必要があります。
 */
public class MyDBHelperClass extends SQLiteOpenHelper implements DaoInterface{


    /**
     * コンストラクタ（必須）
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MyDBHelperClass(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
    }
    
    public void createTable(SQLiteDatabase db, String tableName, String[] columns, int[] types){
    	
    	if(tableExists(db, tableName) == false ){
        	String sentence = "";
        	sentence = "create table " + tableName + " (";
        	sentence = sentence + columns[0] + " " + changeToText(types[0]);

        	for(int i=1; i < columns.length; i++){
        		sentence = sentence + ", " + columns[i] + " " + changeToText(types[i]);
        	}
        	
        	sentence = sentence + ")";
        	db.execSQL(sentence);
    	}
    	
    }
    
    private boolean tableExists(SQLiteDatabase db, String tableName){
    	String query = "SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='" + tableName +  "';"; 
    	Cursor c = db.rawQuery(query, null); 
    	c.moveToFirst();
    	int result = c.getInt(0);
    	if(result == 1){
    		Log.d("createTable", tableName + " " + "result == 1" + result);
    		return true;
    		
    	}
    	else{
    		Log.d("createTable", tableName + " " + "result == 0" + result);
    		return false;
    	}
    }
    public void deleteTable(SQLiteDatabase db, String tableName){
    	db.execSQL("drop table if exists " + tableName);
    }

//    /**
//     *
//     * @param tableName
//     * @param columns
//     * @param types
//     */
//    public void setTable(String tableName, String[] columns, int[] types){
//    	String sentence = "";
//    	sentence = "create table " + tableName + " (";
//    	sentence = sentence + columns[0] + " " + changeToText(types[0]);
//
//    	for(int i=1; i < columns.length; i++){
//    		sentence = sentence + ", " + columns[i] + " " + changeToText(types[i]);
//    		Log.d(String.valueOf(i) + columns[i], "tag");
//    	}
//
//    	sentence = sentence + ")";
//
//
//    }

    private String changeToText(int type){

    	switch (type){
    	case TYPE_INT:
    	case TYPE_BOOLEAN:
    		return "integer";
    	case TYPE_STRING:
    		return "text not null";
    	case TYPE_PRIMARY:
    		return "integer primary key";
    	}

    	return null;

    }


    /**
     * テーブルの生成（必須）
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    /**
     * テーブルの再作成（必須）
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}