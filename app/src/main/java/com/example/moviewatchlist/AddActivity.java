package com.example.moviewatchlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText title_input, director_input, category_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_input = findViewById(R.id.title_input);
        director_input = findViewById(R.id.director_input);
        category_input = findViewById(R.id.category_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database db = new Database(AddActivity.this);
                db.addBook(title_input.getText().toString().trim(),
                        director_input.getText().toString().trim(),
                        category_input.getText().toString().trim());
            }
        });
    }
}