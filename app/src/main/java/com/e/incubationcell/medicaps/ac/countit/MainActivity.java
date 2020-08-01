package com.e.incubationcell.medicaps.ac.countit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextTextPersonName;
    TextView count;
    Button create , view , update;
    String subjectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextTextPersonName = (EditText)findViewById(R.id.editTextTextPersonName);
        count = (TextView)findViewById(R.id.textView2);
        create = (Button)findViewById(R.id.button);
        view = (Button)findViewById(R.id.button2);
        update = (Button)findViewById(R.id.button3);
        final MyHelper myHelper = new MyHelper(this);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectName = editTextTextPersonName.getText().toString();
                boolean f = myHelper.InsertIt(subjectName);
                if(f)
                    Toast.makeText(MainActivity.this,"Done!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectName = editTextTextPersonName.getText().toString();
                Cursor cursor = myHelper.Viewit(subjectName);
                count.setText(cursor.getString(1));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subjectName = editTextTextPersonName.getText().toString();
                if(myHelper.UpdateIt(subjectName))
                    Toast.makeText(MainActivity.this,"Done!",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Error!",Toast.LENGTH_LONG).show();
            }
        });
    }
}