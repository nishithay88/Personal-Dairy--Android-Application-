package com.example.diary;


import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class view extends Activity{
	SQLiteDatabase db;
    ImageButton b1;
	ArrayList<String> result=new ArrayList<String>();
	ArrayList<String> al1=new ArrayList<String>();
	ListView lv;
	static final int DATE_DIALOG_ID1 = 0;
	static final int DATE_DIALOG_ID2 = 1;
	String s11,s2;
	 private int mYear1;
 	private int mMonth1;
 	private int mDay1;
 	
  	ArrayList<String> result1=new ArrayList<String>();
 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_diary);
		lv=(ListView)findViewById(R.id.listView1);
		db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
		b1=(ImageButton)findViewById(R.id.imageButton1);
	
		 b1.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
		            showDialog(DATE_DIALOG_ID1);
		        }
		    });
		
		 final Calendar c1 = Calendar.getInstance();
		    mYear1 = c1.get(Calendar.YEAR);
		    mMonth1 = c1.get(Calendar.MONTH);
		    mDay1 = c1.get(Calendar.DAY_OF_MONTH);
		    int mmonth1=mMonth1;
		    s11=mDay1+"-"+mmonth1+"-"+mYear1;
		    
		  try{
		Cursor c=db.rawQuery("select * from diarytab1 order by date ASC", null);
		c.moveToFirst();
		if(c!=null){
			do{
				
				String date=c.getString(c.getColumnIndex("date")).toString();
				 String message=c.getString(c.getColumnIndex("message")).toString();
				
				 String msg=message.substring(0,10);
					String res=date+"     "+msg+"...";
					result.add(res);				
					System.out.println("#$#$#$#$#"+res);
				
			}while(c.moveToNext());
			
		}
		}
		catch (Exception e) {
			Toast.makeText(getApplicationContext(), "No Diary Found", 100).show();
		}
			
		
		final Spinner sp=(Spinner)findViewById(R.id.spinnertheme);
		final LinearLayout ll=(LinearLayout)findViewById(R.id.ll1);
	sp.setBackgroundColor(Color.TRANSPARENT);
		al1.add("theme1");
		al1.add("theme2");
		al1.add("theme3");
		sp.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,al1));
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String si=sp.getItemAtPosition(arg2).toString();
				if(si=="theme1")
				{
					ll.setBackgroundResource(R.drawable.theme1);
				}
				 if(si=="theme2")
				{
					ll.setBackgroundResource(R.drawable.theme2);
				}
				 if(si=="theme3")
				{
					ll.setBackgroundResource(R.drawable.theme3);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
			Button b=(Button)findViewById(R.id.Buttonhome);
			b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(view.this,second.class);
					startActivity(i);
					finish();
				}
			});
			System.out.println("clear");
			final Button ok=(Button)findViewById(R.id.buttonok);
			ok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					
					// TODO Auto-generated method stub
					 int mmonth1=mMonth1+1;
					 if(mDay1<=9 && mmonth1<=9)
		        	 	    s11="0"+mDay1+"-"+"0"+mmonth1+"-"+mYear1;	
		        	 	    else
		        	 	    	{if(mDay1<=9 && mmonth1>9)
		        	 	    	s11="0"+mDay1+"-"+mmonth1+"-"+mYear1;
		        	 	    else if(mDay1>9 && mmonth1<=9)
		        	 	    	s11=mDay1+"-"+"0"+mmonth1+"-"+mYear1;
		        	 	    else  s11=mDay1+"-"+mmonth1+"-"+mYear1;
		        	 	    	}
		        	 System.out.println(s11);
		        	
		        Calendar cal = Calendar.getInstance();
		             String[] s12=s11.split("-");
		             int y = Integer.parseInt(s12[2].toString());
		             int m = Integer.parseInt(s12[1].toString());
		             int d = Integer.parseInt(s12[0].toString());
		           cal.set(y,m,d);
		          
					 try{
		        		
		        			Cursor c1=db.rawQuery("select * from diarytab1", null);
		        			c1.moveToFirst();
		        			if(c1!=null){
		        				do{
		        					
		        					String date=c1.getString(c1.getColumnIndex("date")).toString();
		        					 String message=c1.getString(c1.getColumnIndex("message")).toString();
		        					 Calendar cal1 = Calendar.getInstance();
		        		             String[] s22=date.split("-");
		        		             int y1 = Integer.parseInt(s22[2].toString());
		        		             int m1 = Integer.parseInt(s22[1].toString());
		        		             int d2 = Integer.parseInt(s22[0].toString());
		        		             
		        		             cal1.set(y1,m1,d2);
		        				
		        				 if(cal1.after(cal))
		        					 {
		        						 System.out.println("ok clicked");
		        					 String msg=message.substring(0,10);
		        						String res=date+"     "+msg+"...";
		        						result1.add(res);				
		        						System.out.println("#$#$#$#$#"+res);
		        					 }
		        				}while(c1.moveToNext());
		        				
		        			}
		        			}
		        			catch (Exception e) {
		        				Toast.makeText(getApplicationContext(), "No Diary Found", 100).show();
		        			}
		        	 lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,result1));
					ok.setEnabled(false);
				}
			});
		
		lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,result));
	    lv.setOnItemClickListener(new OnItemClickListener() {
	    
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			
			String val=lv.getItemAtPosition(position).toString();
			 String result=val.substring(0,10);
			System.out.println("#@#@###"+result);
			
			Cursor c=db.rawQuery("select * from diarytab1 where date='"+result+"'", null);
		c.moveToFirst();
		if(c!=null){
			do{
			String message=c.getString(c.getColumnIndex("message")).toString(); 
			   String date=c.getString(c.getColumnIndex("date")).toString();
			 Intent i= new Intent(view.this, msgfinal.class);
			//System.out.println("1$$#$#$#$#$#$#$#"+message);
				i.putExtra("messg", message);
				i.putExtra("date", result);
			//	System.out.println("2$$#$#$#$#$#$#$#"+img);
				startActivity(i);
				finish();
			  }while(c.moveToNext());
		}
			}
			
	});
	   
	}	
	 private DatePickerDialog.OnDateSetListener m1DateSetListener =
			    new DatePickerDialog.OnDateSetListener() {
			        public void onDateSet(DatePicker view, int year, 
			                              int monthOfYear, int dayOfMonth) {
			            mYear1 = year;
			            mMonth1 = monthOfYear;
			            mDay1 = dayOfMonth;
			          
			            s11=mDay1+"-"+(mMonth1+1)+"-"+mYear1;
			           System.out.println(s11);
			        }
			    };
			   
			    protected Dialog onCreateDialog(int id) {
			    	   switch (id) {
			    	   case DATE_DIALOG_ID1:
			    	      return new DatePickerDialog(this,
			    	                m1DateSetListener,
			    	                mYear1, mMonth1, mDay1);
			    	   
			    	   }
			    	   return null;
			    	}
			   
	}
	



