package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class registroLocales extends AppCompatActivity {
    EditText done;
    ImageView atrasLo,img1,img2,img3,showPass;
    EditText nomb,corr,pass,contra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_locales);
    }
}
