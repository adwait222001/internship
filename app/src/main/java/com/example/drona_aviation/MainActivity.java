package com.example.drona_aviation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final int gallery_req_code = 100000;
    private final int camera_req_code = 100;
    ImageView imggallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imggallery = findViewById(R.id.imggallery);
        Button btngallery = findViewById(R.id.btngallery);
        Button button2 = findViewById(R.id.button2);
        TextView hello = findViewById(R.id.hello);
        Toast.makeText(this, "hello this is the assignment code of Adwait", Toast.LENGTH_SHORT).show();
        btngallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery, gallery_req_code);
                //btngallery.setVisibility(View.INVISIBLE);
                //button2.setVisibility(View.INVISIBLE);
                //hello.setVisibility(View.INVISIBLE);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icamera,camera_req_code);
                //btngallery.setVisibility(View.INVISIBLE);
                //button2.setVisibility(View.INVISIBLE);
                hello.setVisibility(View.INVISIBLE);
            }
        });



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == gallery_req_code && data != null) {
            imggallery.setImageURI(data.getData());
            imggallery.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imggallery.getLayoutParams().height = 500;
            imggallery.getLayoutParams().width = 500;
        }
        if (resultCode == RESULT_OK && requestCode==camera_req_code && data != null){
            if(requestCode==camera_req_code)
            {
                Bitmap img = (Bitmap)data.getExtras().get("data");
                imggallery.setImageBitmap(img);
                imggallery.getLayoutParams().height= 500;
                imggallery.getLayoutParams().width= 1000;


            }

        }

    }
}