package com.example.diary;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Password extends Activity{
	 SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.passwordhome);
		db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
		ImageButton go=(ImageButton)findViewById(R.id.imageButton1);
		final EditText newpas=(EditText)findViewById(R.id.editText1);
		final EditText oldpas=(EditText)findViewById(R.id.editText2);
		
		
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String s=oldpas.getText().toString();
				String s1=newpas.getText().toString();
		     	Cursor c=db.rawQuery("select pass from password1", null);
		     	if(c.moveToFirst()){
		     		
		     	if(c!=null){
		     		String ss=c.getString(c.getColumnIndex("pass"));
		     	System.out.println("checking    "  +ss);
		     		if(ss.equals(s)){
		     			System.out.println("checking    "  +s);
		     			db.execSQL("update password1 set pass='"+s1+"' where pass='"+ss+"'");
		     			
		     			Toast.makeText(getApplicationContext(),s1, 100).show();
		     			Intent it=new Intent(Password.this, MainActivity.class);
		     			startActivity(it);
		     			finish();
		     		}
		     		}
		     		
		     	}
			
				}});
		
		
	}
	

}
