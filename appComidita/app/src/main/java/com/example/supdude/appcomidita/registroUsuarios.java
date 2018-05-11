package com.example.supdude.appcomidita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;

public class registroUsuarios extends AppCompatActivity {
    private EditText nomb,apell,corr,contra,tel;
    private RadioButton homb,muj,otro;
    private ImageView showPass;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

    }
}
