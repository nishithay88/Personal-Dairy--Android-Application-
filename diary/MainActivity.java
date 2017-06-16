package com.example.diary;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
	// TODO Auto-generated constructor stub
SQLiteDatabase db;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	Button b=(Button)findViewById(R.id.login);
	final EditText et=(EditText)findViewById(R.id.editTextpassword);
	TextView tv=(TextView)findViewById(R.id.textView1);
	db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
	String k="12345";
	db.execSQL("create table if not exists password1(pass varchar)");
	db.execSQL("insert into password1 values ('"+k+"')");
	
	b.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String s=et.getText().toString();
			if(s.equals(""))
			{
				Toast.makeText(getApplicationContext(),"enter password",200).show();
			}
			else
			{
			Cursor c=db.rawQuery("select pass from password1", null);
	     	if(c.moveToFirst()){
	     		
	     	if(c!=null){
	     		String ss=c.getString(c.getColumnIndex("pass"));
	     		if(s.equals(ss))
	     		{
	     			Intent it1=new Intent(MainActivity.this,second.class);
	     			startActivity(it1);
	     			finish();
	     		}
	     		else
	     		{
	     	Toast.makeText(getApplicationContext(),"wrong password",200).show();
	     		
	     		}
	     	}
	     	}
			}
			}
	
	});
	tv.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			
			AlertDialog.Builder buld=new AlertDialog.Builder(MainActivity.this);
			buld.setTitle("Change Password");
			LinearLayout lila1= new LinearLayout(getApplicationContext());
			TextView tv=new TextView(getApplicationContext());
			tv.setText("Default password is 12345");
			lila1.addView(tv);
			buld.setView(lila1);
			buld.setPositiveButton("Change", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
				Intent it1=new Intent(MainActivity.this,Password.class);
					startActivity(it1);
					finish();
				}
			}
		);
			 
buld.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			buld.show();
			
		}
	});
}
}