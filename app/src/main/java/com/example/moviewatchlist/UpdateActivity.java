package com.example.moviewatchlist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText name_input, director_input, category_input;
    Button update_button, delete_button;
    String id, name, director, category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name_input = findViewById(R.id.name_input2);
        director_input = findViewById(R.id.director_input2);
        category_input = findViewById(R.id.category_input2);

        update_button = findViewById(R.id.update_button);

        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null)
            ab.setTitle(name);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                director = director_input.getText().toString().trim();
                category = category_input.getText().toString().trim();
                db.updateData(id, name, director, category);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });






    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("director") && getIntent().hasExtra("category")){

           // get data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            director = getIntent().getStringExtra("director");
            category = getIntent().getStringExtra("category");

            // set data
            name_input.setText(name);
            director_input.setText(director);
            category_input.setText(category);

        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + " ?");
        builder.setMessage("Are you sure you want to delete? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Database db = new Database(UpdateActivity.this);
                db.deleteOneRow(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}