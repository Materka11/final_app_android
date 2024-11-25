package com.example.zad4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        GridView _grid = findViewById(R.id.GridView);
        _grid.setAdapter(new ImageAdapter(MainActivity.this));

        _grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                ImageAdapter adapter = (ImageAdapter) _grid.getAdapter();
                int[] imageResources = adapter.getImageResources();

                intent.putExtra("selectedPosition", i);
                intent.putExtra("imageResources", imageResources);

                startActivity(intent);
            }
        });

    }
}