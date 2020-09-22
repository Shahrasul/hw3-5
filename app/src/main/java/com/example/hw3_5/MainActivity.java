package com.example.hw3_5;

import  androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    public RecyclerView recyclerView;
    public List<Title> list;
    public MainAdapter adapter;
    public Button btnOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MainAdapter(list, this,this);
        recyclerView.setAdapter(adapter);
        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityDataPerson.class);
                startActivityForResult(intent, ActivityDataPerson.REQUEST_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ActivityDataPerson.REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            Title title = (Title) data.getSerializableExtra(ActivityDataPerson.KEY);
            adapter.addApplication(title);

        }
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra("key", list.get(position));
        startActivity(intent);
    }


}