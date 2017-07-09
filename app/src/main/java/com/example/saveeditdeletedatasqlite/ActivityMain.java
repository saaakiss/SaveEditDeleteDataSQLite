package com.example.saveeditdeletedatasqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityMain extends AppCompatActivity {

    private static final String TAG = "ActivityMain";

    DatabaseHelper mDatabaseHelper;

    @BindView(R.id.btnAdd)
    Button btnAdd;

    @BindView(R.id.btnView)
    Button btnView;

    @BindView(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mDatabaseHelper = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0){
                    addData(newEntry);
                    editText.setText("");
                }
                else {
                    Toast.makeText(ActivityMain.this, "You must put sth in the text field", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMain.this, ActivityRecycler.class);
                startActivity(intent);
            }
        });
    }

    public void addData(String newEntry){
        boolean instertData = mDatabaseHelper.addDataToDB(newEntry);

        if (instertData){
            Toast.makeText(ActivityMain.this, "Data inserted with success", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ActivityMain.this, "Error inserting data", Toast.LENGTH_SHORT).show();
        }
    }
}
