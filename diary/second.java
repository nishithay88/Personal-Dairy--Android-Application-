package com.example.diary;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class second extends Activity {
String npasswd,cpasswd;
SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		Button add=(Button)findViewById(R.id.button1);
		Button view=(Button)findViewById(R.id.button2);
		Button search=(Button)findViewById(R.id.button3);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it=new Intent(second.this,diary.class);
				startActivity(it);
				finish();
				}
		
		});
		
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it=new Intent(second.this,view.class);
				startActivity(it);
				finish();
			}
		});
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it=new Intent(second.this,Search.class);
				startActivity(it);
				finish();
			}
		});
		
		
		
		
				}
	}


