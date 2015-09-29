package library.widget;


import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
/*
 *
 * Daoclassを導入する場合これらをコピペ、編集すること。
 *
 */
//import java.util.ArrayList;
//import java.util.List;
//import android.content.ContentValues;
//import android.database.Cursor;

public abstract class DaoAbstractClass implements DaoInterface {

	protected SQLiteDatabase db;
	protected Activity activity;

	protected String dataBaseName;
	protected String tableName;
	protected String[] column;
	protected int[] types ;


	/**
	 * Daoclassを導入する場合これらをコピペ、編集すること。
	 *
	 */
//	private static final String DATABASE_NAME = "";
//	private static final String TABLE_NAME = "";
//
//	private static final String COLUMN_VIEW_ID = "viewid";
//
//	private static final int NUM_VIEW_ID = 0;
//
//	private static final String[] COLUMN = new String[]{
//		COLUMN_VIEW_ID
//	};
//
//	private static final int[] TYPES = new int[]{
//		TYPE_INT
//	};

	/**
	 * @param activity
	 * @param dataBaseName
	 * @param tableName
	 * @param column
	 * @param types
	 */
	public DaoAbstractClass(Activity activity, String dataBaseName, String tableName, String[] column, int[] types){
		this.activity = activity;

		this.dataBaseName = dataBaseName;
		this.tableName = tableName;
		this.column = column;
		this.types = types;

		MyDBHelperClass mainHelper = new MyDBHelperClass(activity, dataBaseName, null, 1);
		db = mainHelper.getReadableDatabase();
		mainHelper.createTable(db, tableName, column, types);


	}

	/**
	 * Daoclassを導入する場合これらをコピペ、編集すること。
	 *
	 */

//	/**
//	 * 全データの取得 ----------------①
//	 *
//	 * @return
//	 */
//	public List<DATAClass> findAll(){
//		List<DATAClass> entityList = new ArrayList<DATAClass>();
//
//		Cursor cursor = db.query(tableName, column, null, null, null, null, COLUMN_VIEW_ID);
//
//		while (cursor.moveToNext()) {
//			entityList.add(getDataClass(cursor) );
//		}
//
//		return entityList;
//	}
//
//	/**
//	 * 特定IDのデータを取得 ----------------②
//	 *
//	 * @param rowId
//	 * @return
//	 */
//	public DATAClass findById(int rowId){
//		String selection = COLUMN_VIEW_ID + "=" + rowId;
//		Cursor cursor = db.query(tableName, column, selection, null, null,
//				null, null);
//
//		/*
//		 * to start id with 1.
//		 */
//		if(cursor.moveToNext() ){
//			return getDataClass(cursor);
//		}
//		else{
//			DATAClass entity = new DATAClass();
//			entity.id = -1;
//			return entity;
//		}
//
//	}
//
//	private DATAClass getDataClass(Cursor cursor){
//		DATAClass entity = new DATAClass();
//		entity.id = cursor.getInt(NUM_VIEW_ID);
//		entity.data = cursor.getString(NUM_DATA);
//
//
//		return entity;
//	}
//
//	/**
//	 * データの登録 ----------------③
//	 *
//	 * @param data
//	 * @return
//	 */
//	public long insert(DATAClass entity){
//
//		db.insert(tableName, null, getValues(entity) );
//		return entity.id;
//	}
//	
//	/**
//	 * データの登録
//	 * 複数
//	 * @param entityList
//	 */
//	public void insert(List<DATAClass> entityList){
//		for(int i=0; i < entityList.size(); i++){
//				insert( entityList.get(i) );
//		}
//
//	}
//
//	/**
//	 * データの更新 ----------------④
//	 *
//	 * @param rowid
//	 * @param date
//	 * @return
//	 */
//	public int update(DATAClass entity) {
//		String whereClause = COLUMN_VIEW_ID + "=" + entity.id;
//		return db.update(tableName, getValues(entity), whereClause, null);
//	}
//
//	private ContentValues getValues(DATAClass entity){
//		ContentValues values = new ContentValues();
//
//		values.put(COLUMN_VIEW_ID, entity.id);
//		values.put(COLUMN_DATA, entity.data);
//
//		return values;
//
//	}
//
//	/**
//	 * データの削除 ----------------⑤
//	 *
//	 * @param id
//	 * @return
//	 */
//	public int delete(int id) {
//		String whereClause = COLUMN_VIEW_ID + "=" + id;
//		return db.delete(tableName, whereClause, null);
//	}
//
//
//	/**
//	 * データ数
//	 * @return
//	 */
//   public long getRecordCount() {
//       String sql = "select count(*) from " + tableName;
//       Cursor c = db.rawQuery(sql, null);
//       c.moveToLast();
//       long count = c.getLong(0);
//       c.close();
//       return count;
//   }
//
//	/**
//	 * 特定のidのデータを取得
//	 * @param id
//	 * @return
//	 */
//	public List<DATAClass> getDatasByViewId(int id){
//		String selection = COLUMN_VIEW_ID + "=" + id;
//		List<DATAClass> entityList = new ArrayList<DATAClass>();
//		Cursor cursor = db.query(tableName, column, selection, null, null,
//				null, null);
//
//		while (cursor.moveToNext()) {
//			entityList.add(getDataClass(cursor) );
//		}
//
//		return entityList;
//	}

	

	protected boolean toBoolean(int trueOrFalse){
		if(trueOrFalse == 1) return true;
		else return false;
	}

	protected int toInt(boolean trueOrFalse){
		if(trueOrFalse) return 1;
		return 0;
	}

	private class DATAClass{
		public int id;
		public String data;
	}


}
