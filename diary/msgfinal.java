package com.example.diary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class msgfinal extends Activity implements OnInitListener{
	int MY_DATA_CHECK_CODE = 0;
    TextToSpeech t;
    String message;
    String ivimage;
   ImageView iv;
  SQLiteDatabase db;
    String s1;
    private static final int SELECT_PICTURE = 1;
    String selectedImagePath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finalview);
		t = new TextToSpeech(this, this);
		  db=openOrCreateDatabase("diary", MODE_PRIVATE, null);
	iv=(ImageView)findViewById(R.id.imageView1);
		    final TextView msg=(TextView)findViewById(R.id.textView1);
		    Button speech=(Button)findViewById(R.id.button1);
		    Button home=(Button)findViewById(R.id.button2);
		    Button edit=(Button)findViewById(R.id.edit);
		    Button del=(Button)findViewById(R.id.buttonb);
		    System.out.println("$#$#$#$#$#$ viewmsg class");
		    final Intent intent = getIntent();
		    message = intent.getExtras().getString("messg");
	       final String res=intent.getExtras().getString("date");
		    s1=res;
		    msg.setText(message);
		    Cursor c=db.rawQuery("select * from diarytab1 where date='"+res+"'",null);
			c.moveToFirst();
			if(c!=null)
				do{
					  String i=c.getString(c.getColumnIndex("image"));
					  Uri imgUri=Uri.parse(i);
			iv.setImageURI(imgUri);
				}while(c.moveToNext());
		    speech.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					speakOut();
				}
});
		    home.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent it=new Intent(msgfinal.this,second.class);
					startActivity(it);
					finish();
				}
			});
		    edit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent it=new Intent(msgfinal.this,edit.class);
					it.putExtra("date",res);
					startActivity(it);
					finish();
				}
			});
		    del.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AlertDialog.Builder buld=new AlertDialog.Builder(msgfinal.this);
					buld.setTitle("delete  Text");
				
					buld.setPositiveButton("yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							String s2="";
							System.out.println("deleted");
							db.execSQL("delete from diarytab1 where date='"+s1+"'");
							Intent it2=new Intent(msgfinal.this,second.class);
							startActivity(it2);
							finish();
						}
					}
				);
					 
		buld.setNegativeButton("no", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							
						}
					});
					buld.show();
					// TODO Auto-generated method stub
				
				}
			});
		    
	}
	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		
	}
	private void speakOut() {
		// TODO Auto-generated method stub
		
		t.speak(message, TextToSpeech.QUEUE_FLUSH, null);
		Toast.makeText(getApplicationContext(), "Speaking..", 100).show();
	}
	
}

