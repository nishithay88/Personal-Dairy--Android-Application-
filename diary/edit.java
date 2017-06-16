package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class edit extends Activity 
{
SQLiteDatabase db;
String date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add2_diary);
		Button save=(Button)findViewById(R.id.save);
		Button cancel=(Button)findViewById(R.id.cancel);
		final EditText  edit1=(EditText)findViewById(R.id.editTexta);
		db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
		Intent it=getIntent();
		final String s1=it.getExtras().getString("date");
	
		Cursor c=db.rawQuery("select * from diarytab1 where date='"+s1+"'", null);
		c.moveToFirst();
		if(c!=null){
			do{
				  String message=c.getString(c.getColumnIndex("message")).toString(); 
				 edit1.setText(message);
			}while(c.moveToNext());
		}
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s2=edit1.getText().toString();
				db.execSQL("update diarytab1 set message='"+s2+"' where date='"+s1+"'");
						 
					Toast.makeText(getApplicationContext(),"successful inserted",100);
				Intent it2=new Intent(edit.this,view.class);
				startActivity(it2);
			
				finish();
			}
			
		});
        cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it1=new Intent(edit.this,second.class);
				startActivity(it1);
				finish();
			}
		});
	}
}


