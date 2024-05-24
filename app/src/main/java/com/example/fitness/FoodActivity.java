package com.example.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class FoodActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        String[] tstory = getResources().getStringArray(R.array.title_story);
        final String[] dstory = getResources().getStringArray(R.array.details_story);

        listView = findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row, R.id.rowtxt, tstory);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String t = dstory[position];
                Intent intent = new Intent(FoodActivity.this, FoodActivityDetails.class);
                intent.putExtra("story", t);
                startActivity(intent);
            }
        });

    }

    public void foodGoBack(View view) {
        Intent intent = new Intent(FoodActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FoodActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}