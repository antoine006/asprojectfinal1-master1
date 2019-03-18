package com.example.hp1.asproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraGalleryActivity extends AppCompatActivity implements View.OnClickListener {
    Button camera, gallery;
    ImageView imageView2;
    public static final int CAMERA_REQUEST = 0;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);
        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(this);
        gallery = (Button) findViewById(R.id.gallery);
        gallery.setOnClickListener(this);
        imageView2 = (ImageView) findViewById(R.id.cameraImageView);
    }

    @Override
    public void onClick(View view) {
        if (view == camera) {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);

        } else {

            //later
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //      if (resultCode== CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        imageView2.setImageBitmap(photo);
        Toast.makeText(this, "inside set photo", Toast.LENGTH_LONG).show();
        String imagePath = saveimage(bitmap);
        //     }
    }

    public String saveimage(Bitmap bitmap) {
        File root = Environment.getExternalStorageDirectory();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = root.getAbsolutePath() + "/DCIM/Camera/IMG_" + timeStamp + ".jpg";
        File file = new File(filePath);

        try {
            file.createNewFile();
            FileOutputStream ostream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
        return filePath;

    }

}

