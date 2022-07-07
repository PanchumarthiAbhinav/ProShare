package com.example.proshare;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button signin;
    TextInputLayout email, password;
    String Email, Password;
    TextView newuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        newuser = (TextView) findViewById(R.id.RegisterHere);
        email = (TextInputLayout) findViewById(R.id.UsernameInputLayout);
        password = (TextInputLayout) findViewById(R.id.PasswordInputLayout);
        signin = (Button)findViewById(R.id.Login);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(MainActivity.this, HomePage.class);
            startActivity(intent);
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                Email = email.getEditText().getText().toString();
                Password = password.getEditText().getText().toString();
                mAuth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Toast.makeText(getApplicationContext(),"Successfully Added",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(),"Invalid email/password",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registration.class);
                startActivity(intent);
            }
        });
    }
}