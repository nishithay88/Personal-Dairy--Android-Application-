package com.example.diary;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class searchResult extends Activity{
	SQLiteDatabase db;
	ArrayList<String> result=new ArrayList<String>();
	String date,hint;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresult);
		  Intent intent = getIntent();
		    hint = intent.getExtras().getString("hint");
		db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
	    final ListView lv=(ListView)findViewById(R.id.listView1);
		try{
			Cursor c=db.rawQuery("select * from diarytab1 where message like '%"+hint+"%' ", null);
			c.moveToFirst();
			if(c!=null){
				do{
					 date=c.getString(c.getColumnIndex("date")).toString();
					 String message=c.getString(c.getColumnIndex("message")).toString();
					 
			
						String msg=	message.substring(0,10);
						String res=date+"     "+msg+"...";
						result.add(res);				
						System.out.println("#$#$#$#$#"+res);
					
				}while(c.moveToNext());
			}
			}
			catch (Exception e) {
				Toast.makeText(getApplicationContext(), "No Diary Found", 100).show();
			}
			lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,result));	
		    lv.setOnItemClickListener(new OnItemClickListener() {
			    
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position,
						long arg3) {
					
					String val=lv.getItemAtPosition(position).toString();
					String result=val.substring(0,10);
					System.out.println("#@#@###"+result);
					Cursor c=db.rawQuery("select * from diarytab1 where date='"+date+"'", null);
					c.moveToFirst();
					if(c!=null){
						
						String message=c.getString(c.getColumnIndex("message")).toString();
					    
						Intent i= new Intent(searchResult.this, msgfinal.class);
					//System.out.println("1$$#$#$#$#$#$#$#"+message);
						i.putExtra("messg", message);
						
					//	System.out.println("2$$#$#$#$#$#$#$#"+img);
						startActivity(i);
						
					}
					
				}
			});
	}
	

}