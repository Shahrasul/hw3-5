package com.example.hw3_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class InfoActivity extends AppCompatActivity {
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        init();
        Intent intent = getIntent();
        Title model = (Title) intent.getSerializableExtra("key");
        if (model != null) {
            text1.setText(model.name);
            text2.setText(model.lastName);
            text3.setText(model.age);
            text4.setText(model.group);
            Glide.with(this).load(model.getImageView()).into(imageView);
            imageView.setImageURI(Uri.parse(model.getImageView()));

        }

    }

    private void init() {
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        text3 = findViewById(R.id.txt3);
        text4 = findViewById(R.id.txt4);
        imageView = findViewById(R.id.imageView2);

    }
}