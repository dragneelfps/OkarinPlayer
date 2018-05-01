package com.example.sourabh.okarin;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class AbsPermissionActivity extends AppCompatActivity {

    static int STORAGE_READ_PERMISSION = 123;

    Boolean requestStoragePermission(){
        if(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    STORAGE_READ_PERMISSION);
            return false;
        }
        return true;

    }
}
