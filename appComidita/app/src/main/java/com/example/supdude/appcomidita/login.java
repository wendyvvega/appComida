package com.example.supdude.appcomidita;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class login extends AppCompatActivity implements View.OnClickListener{

    private EditText correo, contraseña;
    private TextView forgPass;
    private Button iniciar,mapa;
    private FirebaseAuth mAuth;
    private ImageView showPass,atr_log;
     String email,pass;
    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        atr_log=findViewById(R.id.atras_login);
        atr_log.setOnClickListener(this);
         correo= findViewById(R.id.txtUsuario);
        contraseña = findViewById(R.id.txtContra);
        iniciar = findViewById(R.id.btnIniciarSesion);
        forgPass= findViewById(R.id.forgPass);
        mapa=findViewById(R.id.iniciaMapa);
        iniciar.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnIniciarSesion:
                email=correo.getText().toString();
                pass=contraseña.getText().toString().trim();
                verificaciones(email,pass);
                autenticar(email,pass);

                break;
            case R.id.forgPass:
                startActivity(new Intent (login.this,resetPass.class) );
                break;
            case R.id.iniciaMapa:
                if(isServicesOK()) {
                    startActivity(new Intent(login.this, mapaLocales.class));
                }
                break;
            case    R.id.atras_login:
                startActivity(new Intent(login.this,MainActivity.class));
                break;


        }
    }
    public boolean isServicesOK(){


        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests

            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void verificaciones(String mail, String pass){
        mail= correo.getText().toString();

        if(!validarEmail(mail)){
            Toast.makeText(this, "Por favor introduce un email válido", Toast.LENGTH_SHORT).show();
            iniciar.setEnabled(false);
        }
        if(TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Introduce una contraseña", Toast.LENGTH_SHORT).show();
            iniciar.setEnabled(false);
        }

    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
    private void autenticar(String mail, final String passw){
        mAuth.signInWithEmailAndPassword(mail,passw)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // there was an error
                            if (passw.length() < 6) {
                                contraseña.setError("Contraseña muy corta,introduzca por lo menos 8 caracteres");
                                return;
                            } else {
                                Toast.makeText(login.this, "Autenticación fallida, verifique que el correo o la contraseña sean correctos", Toast.LENGTH_LONG).show();
                                return;
                            }
                        } else {
                            Intent intent = new Intent(login.this, tiposComida.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
