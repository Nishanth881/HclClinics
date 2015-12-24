package com.hclavitas.database;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class FetachDataByDataBase {
	static MyDataBaseManager_ dbhelper;

	public ArrayList<Model_bean> FEtchdatafrom_db(Context cn) {
		ArrayList<Model_bean> modelList = new ArrayList<Model_bean>();
		dbhelper = new MyDataBaseManager_(cn);
		Model_bean cus = null;
		try {
			Cursor show = dbhelper.get_all_data();

			if (show.getCount() > 0) {
				for (show.moveToFirst(); !show.isAfterLast(); show.moveToNext()) {

					cus = new Model_bean();

					cus.setUser_name(show.getString(show
							.getColumnIndex("user_name")));
					cus.setAns_five(show.getString(show
							.getColumnIndex("ans_five")));
					cus.setAns_four(show.getString(show
							.getColumnIndex("ans_four")));
					cus.setAns_one(show.getString(show
							.getColumnIndex("ans_one")));
					cus.setAns_three(show.getString(show
							.getColumnIndex("ans_three")));
					cus.setAns_two(show.getString(show
							.getColumnIndex("ans_two")));
					cus.setFacility_type(show.getString(show
							.getColumnIndex("facility_type")));
					cus.setPhone_nu(show.getString(show
							.getColumnIndex("phone_nu")));
					cus.setQues_one(show.getString(show
							.getColumnIndex("ques_one")));
					cus.setQuest_two(show.getString(show
							.getColumnIndex("ques_two")));
					cus.setQust_four(show.getString(show
							.getColumnIndex("ques_four")));
					cus.setQust_three(show.getString(show
							.getColumnIndex("ques_three")));
					cus.setQustn_five(show.getString(show
							.getColumnIndex("ques_five")));
					cus.setQues_six(show.getString(show
							.getColumnIndex("ques_six")));
					cus.setQues_seven(show.getString(show
							.getColumnIndex("ques_seven")));
					cus.setAns_six(show.getString(show
							.getColumnIndex("ans_six")));
					cus.setAns_seven(show.getString(show
							.getColumnIndex("ans_seven")));
					cus.setAns_eight(show.getString(show
							.getColumnIndex("ans_eight")));
					cus.setAns_nine(show.getString(show
							.getColumnIndex("ans_nine")));
					cus.setAns_ten(show.getString(show
							.getColumnIndex("ans_ten")));
					cus.setManagerName(show.getString(show.getColumnIndex("manager_name")));
					cus.setQues_eight(show.getString(show
							.getColumnIndex("ques_eight")));
					cus.setQues_nine(show.getString(show
							.getColumnIndex("ques_nine")));
					cus.setQues_ten(show.getString(show
							.getColumnIndex("ques_ten")));
					cus.setClusterMobileNumber(show.getString(show
							.getColumnIndex("facility_number")));
					modelList.add(cus);

				}
				show.close();
			}

		} catch (NullPointerException e) {

			// TODO: handle exception
			Log.e("Exception ", e.getMessage());
			//return null;
		} catch (Exception e) {
			Log.w("saved error", e.getMessage());
			//return null;
		}
		return modelList;
	}

}
