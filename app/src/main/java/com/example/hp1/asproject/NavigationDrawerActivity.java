package com.example.hp1.asproject;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.hp1.asproject.CameraGalleryActivity.CAMERA_REQUEST;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int WRITE_REQUEST_CODE = 2;
    DrawerLayout drawer;
    ImageView profilepicture;
    Bitmap bitmap;
    private static final int CAMERA_REQUEST = 0;
    private static final int SELECT_IMAGE = 1;
    private static final int NOTIFICATION_REMINDER_NIGHT = 2;
    private int requestCode;
    private int grantResults[];
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        if (ContextCompat.checkSelfPermission(this, String.valueOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)) == PackageManager.PERMISSION_DENIED) {
            //if you dont have required permissions ask for it (only required for API 23+)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View i = navigationView.getHeaderView(0);
        profilepicture = i.findViewById(R.id.profilepicture);
        SharedPreferences pref = getSharedPreferences("Profile" ,MODE_PRIVATE);
        String name = pref.getString("image","");
        if (!name.equals("")){
            Bitmap myBitmap = BitmapFactory.decodeFile(name);
            profilepicture.setImageBitmap(myBitmap);
        }

        profilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,CAMERA_REQUEST);
            }
        });
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new FragmentMain()).commit();
//            navigationView.setCheckedItem(R.id.Main);
//
//        }

    }

    @Override // android recommended class to handle permissions
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("","granted");
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.uujm
                    Toast.makeText(NavigationDrawerActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();

                    //app cannot function without this permission for now so close it...
                    onDestroy();
                }
                return;
            }

            // other 'case' line to check fosr other
            // permissions this app might request
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){


        if(requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profilepicture.setImageBitmap(photo);
            String file = saveImage(photo);

            SharedPreferences pref = getSharedPreferences("Profile",MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("image",file);
            editor.commit();
        }
        else{
            if(requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK){
                Uri targetUri = data.getData();
                try{
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    profilepicture.setImageBitmap(bitmap);
                    String file = saveImage(bitmap);

                    SharedPreferences pref = getSharedPreferences("Profile",MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("image",file);
                    editor.commit();
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
    }
    private void openGallery(){
        Intent gallary = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallary, PICK_IMAGE);
    }

    public String saveImage(Bitmap bitmap) {

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
            Log.d("FILE",file.toString());
            Toast.makeText(this, file + " Failed to save image", Toast.LENGTH_LONG).show();
            Log.d("IMAGE SAVE",e.toString());
        }
        return filePath;

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure");
            builder.setCancelable(true);
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    NavigationDrawerActivity.super.onBackPressed();
                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog=builder.create();

            dialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Intent goToNextActivity;

        switch (item.getItemId()){
            case R.id.about:
                goToNextActivity = new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(goToNextActivity);
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.Main:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new FragmentMain()).commit();
                Intent mainPage = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainPage);
                break;

            case R.id.Sign_up:
                Intent signUpPage = new Intent(getApplicationContext(), signup.class);
                startActivity(signUpPage);
                break;

            case R.id.Sign_in:
                Intent signInPage = new Intent(getApplicationContext(), signin.class);
                startActivity(signInPage);
                break;

            case R.id.wishList:
                Intent wishList = new Intent(getApplicationContext(), signin.class);
                startActivity(wishList);
                break;


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}