package com.example.diary;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Search extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
	final EditText hint=(EditText)findViewById(R.id.editText1);
		Button search=(Button)findViewById(R.id.button1);
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent it=new Intent(Search.this,searchResult.class);
				String s=hint.getText().toString();
				it.putExtra("hint", s);
				startActivity(it);
				
			}
		});
		}

}
