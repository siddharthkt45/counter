package com.e.incubationcell.medicaps.ac.countit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextTextPersonName;
    TextView count , subject , gitlink;
    Button create , view , update;
    String subjectName;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
        count = (TextView)findViewById(R.id.textView2);
        gitlink = (TextView)findViewById(R.id.textView);
        subject = (TextView)findViewById(R.id.textView3);
        create = (Button)findViewById(R.id.button);
        view = (Button)findViewById(R.id.button2);
        update = (Button)findViewById(R.id.button3);
        final MyHelper myHelper = new MyHelper(this);
        gitlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/shubhansu31/counter_app"));
                startActivity(myIntent);
                //webView.loadUrl("https://github.com/shubhansu31/counter_app");
                Toast.makeText(MainActivity.this,"Github : shubhansu31" , Toast.LENGTH_LONG).show();
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectName = editTextTextPersonName.getText().toString();
                boolean f = myHelper.InsertIt(subjectName);
                if(f)
                    Toast.makeText(MainActivity.this,"Done!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectName = editTextTextPersonName.getText().toString();
                Cursor cursor = myHelper.Viewit(subjectName);
                cursor.moveToNext();
                count.setText(cursor.getString(2));
                subject.setText(cursor.getString(1));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectName = editTextTextPersonName.getText().toString();
                boolean f = myHelper.UpdateIt(subjectName);
                if(f)
                    Toast.makeText(MainActivity.this,"Done!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Error!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}