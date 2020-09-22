package com.example.hw3_5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Objects;

public class ActivityDataPerson extends AppCompatActivity {
    public static int REQUEST_CODE = 100;
    public static String KEY = "key";
    private EditText etName;
    private EditText etLastName;
    private EditText etAge;
    private EditText etGroup;
    private ImageView imageView;
    private Uri imageData;
    private Title title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_person);
        init();
    }

    private void init() {
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLName);
        etAge = findViewById(R.id.etAge);
        etGroup = findViewById(R.id.etGroup);
        imageView = findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Choose image"), 200);
    }

    public void sendResult(View view) {

        String titleName = etName.getText().toString();
        String titleLastName = etLastName.getText().toString();
        String titleAge = etAge.getText().toString();
        String titleGroup = etGroup.getText().toString();
        String image = imageData.toString();

        Intent intent = new Intent();
        title = new Title();
        title.setName(titleName);
        title.setLastName(titleLastName);
        title.setAge(titleAge);
        title.setGroup(titleGroup);
        title.setImageView(image);
        intent.putExtra(KEY, title);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK) {
            if (data != null) {
                imageData = data.getData();
                imageView.setImageURI(imageData);
                title.setImageView(Objects.requireNonNull(data.getData().toString()));
            }

        }
    }
}