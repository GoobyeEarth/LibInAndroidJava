package library.widget;

public interface DaoInterface {
	public static final int TYPE_INT = 0;
	public static final int TYPE_DOUBLE = 1;
	public static final int TYPE_STRING = 2;
	public static final int TYPE_BOOLEAN = 3;
	public static final int TYPE_LONG = 4;
	public static final int TYPE_FLOAT = 5;
	
	/**
	 * integer primary key
	 */
	public static final int TYPE_PRIMARY = 6;
	
	/**
	 * unique string not not implemented
	 */
	public static final int TYPE_STRING_UNIQUE = 7;
	/*
	 * DATABASE_NAME
	 */
	public static final String NAME_ADDITIONAL_PARAMETERS = "additional_parameters";
	public static final String NAME_VIEW = "view";

}
