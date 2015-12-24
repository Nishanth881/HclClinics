package com.hclavitas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseManager_ extends SQLiteOpenHelper
{

	SQLiteDatabase sqLiteDatabase;

	public MyDataBaseManager_(Context context)
	{
		super(context, DataBaseValues.DB_NAME, null, DataBaseValues.DB_VERSION);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		// TODO Auto-generated method stub

		String tab_list = "create table " + DataBaseValues.MAIN_TABLE + " ( " + DataBaseValues.MAIN_TABLE_ID + " integer primary key autoincrement not null, " + DataBaseValues.USER_NAME + " text, " + DataBaseValues.PHONE_NU + " text, " + DataBaseValues.MANAGERNAME + " text, "+ DataBaseValues.FACILITY_TYPE + " text, " + DataBaseValues.FACILITY_NUMBER + " text, "+ DataBaseValues.QUES_ONE + " text, " + DataBaseValues.ANS_ONE + " text, " + DataBaseValues.QUES_TWO + " text, " + DataBaseValues.ANS_TWO + " text, " + DataBaseValues.QUES_THREE + " text, " + DataBaseValues.ANS_THREE + " text, " + DataBaseValues.QUES_FOUR + " text, " + DataBaseValues.ANS_FOUR + " text , " + DataBaseValues.QUES_FIVE + " text , "

		+ DataBaseValues.ANS_FIVE+" text, "+ DataBaseValues.QUES_SIX + " text, " + DataBaseValues.ANS_SIX + " text, " + DataBaseValues.QUES_SEVEN + " text, "+ DataBaseValues.ANS_SEVEN+ " text, " + DataBaseValues.QUES_EIGHT + " text, "+ DataBaseValues.ANS_EIGHT+ " text, " + DataBaseValues.QUES_NINE + " text, "+ DataBaseValues.ANS_NINE+ " text, " + DataBaseValues.QUES_TEN + " text, "+ DataBaseValues.ANS_TEN+ " text " + ")";
		try
		{

			db.execSQL(tab_list);

		}
		catch (Exception e)
		{

		}

	}
	
	
   public void	deleteTable(){
	   sqLiteDatabase = this.getWritableDatabase();
		sqLiteDatabase.execSQL("delete from " +  DataBaseValues.MAIN_TABLE);
		sqLiteDatabase.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub

	}

	public long Insert_Info(Model_bean list)
	{
		sqLiteDatabase = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(DataBaseValues.USER_NAME, list.getUser_name());
		cv.put(DataBaseValues.PHONE_NU, list.getPhone_nu());
		cv.put(DataBaseValues.FACILITY_TYPE, list.getFacility_type());
		cv.put(DataBaseValues.QUES_ONE, list.getQues_one());
		cv.put(DataBaseValues.ANS_ONE, list.getAns_one());
		cv.put(DataBaseValues.QUES_TWO, list.getQuest_two());
        cv.put(DataBaseValues.MANAGERNAME, list.getManagerName());
		cv.put(DataBaseValues.ANS_TWO, list.getAns_two());
		cv.put(DataBaseValues.QUES_THREE, list.getQust_three());
		cv.put(DataBaseValues.ANS_THREE, list.getAns_three());
		cv.put(DataBaseValues.QUES_FOUR, list.getQust_four());
		cv.put(DataBaseValues.ANS_FOUR, list.getAns_four());
		cv.put(DataBaseValues.QUES_FIVE, list.getQustn_five());
		cv.put(DataBaseValues.ANS_FIVE, list.getAns_five());
		cv.put(DataBaseValues.QUES_TEN, list.getQues_ten());
		cv.put(DataBaseValues.ANS_TEN, list.getAns_ten());
		cv.put(DataBaseValues.QUES_NINE, list.getQues_nine());
		cv.put(DataBaseValues.ANS_NINE, list.getAns_nine());
		cv.put(DataBaseValues.QUES_EIGHT, list.getQues_eight());
		cv.put(DataBaseValues.ANS_EIGHT, list.getAns_eight());
		cv.put(DataBaseValues.QUES_SEVEN, list.getQues_seven());
		cv.put(DataBaseValues.ANS_SEVEN, list.getAns_seven());
		cv.put(DataBaseValues.QUES_SIX, list.getQues_six());
		cv.put(DataBaseValues.ANS_SIX, list.getAns_six());
        cv.put(DataBaseValues.FACILITY_NUMBER, list.getClusterMobileNumber());
		long i = sqLiteDatabase.insert(DataBaseValues.MAIN_TABLE, null, cv);
		sqLiteDatabase.close();

		return i;
	}

	public Cursor getDataByusername(String user_name)
	{

		sqLiteDatabase = this.getReadableDatabase();
		String str = " select * from " + DataBaseValues.MAIN_TABLE + " where user_name='" + user_name + "'";
		Cursor c = sqLiteDatabase.rawQuery(str, null);
		return c;

	}

	public Cursor get_all_data()
	{
		sqLiteDatabase = this.getReadableDatabase();
		String s = "select * from " + DataBaseValues.MAIN_TABLE;
		Cursor c = sqLiteDatabase.rawQuery(s, null);
		return c;

	}

	
	
}
