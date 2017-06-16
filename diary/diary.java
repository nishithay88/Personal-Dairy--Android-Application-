package com.example.diary;



import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.R.string;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class diary extends Activity implements OnClickListener {
        private EditText msg;
        SQLiteDatabase db;
        SQLiteDatabase myDb;
        int i=0;
	    private Button save,cancel;
	    private  String selectedImagePath;
	    private static final int SELECT_PICTURE = 1;
	    String d;
	    String message;
        private String ss;
        private int mYear;
    	private int mMonth;
    	private int mDay;
    	
Button get_image;
ImageView iv;
Uri selectedImageUri;
    	private TextView mDateDisplay;
    	private ImageButton mPickDate;

    	static final int DATE_DIALOG_ID = 0;
        
	    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_diary);
		  // Set the views
		msg=(EditText)findViewById(R.id.editTextfile);
		db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
		save=(Button)findViewById(R.id.save);
        cancel=(Button)findViewById(R.id.Cancel);
        
        save.setOnClickListener(this);  
        cancel.setOnClickListener(this);  
        mDateDisplay = (TextView) findViewById(R.id.showMyDate);        
	    mPickDate = (ImageButton) findViewById(R.id.myDatePickerButton);
        get_image=(Button)findViewById(R.id.get_image);
        get_image.setOnClickListener(this);
        iv=(ImageView)findViewById(R.id.imageView1);
	    mPickDate.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	            showDialog(DATE_DIALOG_ID);
	        }
	    });
	    // get the current date
	    final Calendar c1 = Calendar.getInstance();
	    mYear = c1.get(Calendar.YEAR);
	    mMonth = c1.get(Calendar.MONTH);
	    mDay = c1.get(Calendar.DAY_OF_MONTH);
	   
	 ss=mDay+"-"+mMonth+"-"+mYear;
	    // display the current date
	    updateDisplay();
	   
	   
	 }
	    
    
	    private void updateDisplay() {
		    this.mDateDisplay.setText(
		        new StringBuilder()
		                // Month is 0 based so add 1
		                 .append(mDay).append("-")
		                 .append(mMonth+1).append("-")
		                 .append(mYear).append(" "));
		  
		}
		private DatePickerDialog.OnDateSetListener mDateSetListener =
			    new DatePickerDialog.OnDateSetListener() {
			        public void onDateSet(DatePicker view, int year, 
			                              int monthOfYear, int dayOfMonth) {
			            mYear = year;
			            mMonth = monthOfYear;
			            mDay = dayOfMonth;
			            updateDisplay();
			        }
			    };
			    protected Dialog onCreateDialog(int id) {
			    	   switch (id) {
			    	   case DATE_DIALOG_ID:
			    	      return new DatePickerDialog(this,
			    	                mDateSetListener,
			    	                mYear, mMonth, mDay);
			    	   }
			    	   return null;
			    	}
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.save:
        	 int mmonth=mMonth+1;
        	 if(mDay<=9 && mmonth<=9)
        	 	    ss="0"+mDay+"-"+"0"+mmonth+"-"+mYear;	
        	 	    else
        	 	    	{if(mDay<=9 && mmonth>9)
        	 	    	ss="0"+mDay+"-"+mmonth+"-"+mYear;
        	 	    else if(mDay>9 && mmonth<=9)
        	 	    	ss=mDay+"-"+"0"+mmonth+"-"+mYear;
        	 	    else  ss=mDay+"-"+mmonth+"-"+mYear;
        	 	    	}
        	 System.out.println(ss);
        	 
        	 
        	db.execSQL("create table if not exists diarytab1(date varchar,message varchar,image varchar)");
        	
		    message=msg.getText().toString();
		 
			if(message.length()==0){
				
			   Toast.makeText(getApplicationContext(), "Please Enter Message", Toast.LENGTH_SHORT).show();
			
			             }
			if(message.length()<15){
			Toast.makeText(getApplicationContext(), "Please Enter Some more text", Toast.LENGTH_SHORT).show();
			}
					
				
				else{
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					Cursor cc=null;
					cc=db.rawQuery("select * from diarytab1 where date='"+ss+"' ", null);
					
					if(cc.moveToFirst()){
						     String dt=cc.getString(cc.getColumnIndex("date"));
						     String m=cc.getString(cc.getColumnIndex("message"));
						     String i=cc.getString(cc.getColumnIndex("image"));
						        System.out.println("#####dbdate#####"+dt);
						     System.out.println("#######mmmm#######"+m);
					              if(dt.equalsIgnoreCase(ss)){
							      message=m+". "+message;
							      System.out.println("######updated msg####"+message);
							      db.execSQL("update diarytab1 set message='"+message+"' ,image='"+selectedImageUri+"' where date='"+ss+"'");
							      Toast.makeText(getApplicationContext(), "Inserted Message", Toast.LENGTH_SHORT).show();
						          Intent it=new Intent(diary.this,second.class);
						          startActivity(it);
						          finish();
						          }
						
					}else {
						
						 db.execSQL("insert into diarytab1 values('"+ss+"','"+message+"','"+selectedImageUri+"')");
						 Toast.makeText(getApplicationContext(), "Inserted message"+selectedImageUri, Toast.LENGTH_SHORT).show();
				     
				       Intent it=new Intent(diary.this,second.class);
				          startActivity(it);
				          finish();
					}
            }
			break;
        case R.id.Cancel:
        	Intent it=new Intent(diary.this,second.class);
            startActivity(it);
            finish();
        	break;
        case R.id.get_image:
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    SELECT_PICTURE);
            break;
      

     
        }
    }
    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
               
                selectedImagePath = getPath(selectedImageUri);
                System.out.println("Image Path : " + selectedImagePath);
                iv.setVisibility(View.VISIBLE);
                iv.setImageURI(selectedImageUri);
            }
        }
    }
}
    	
