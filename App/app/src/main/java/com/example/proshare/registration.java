package com.example.proshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

class details{
    String firstname, lastname, Email;

    public details(String firstname1, String lastname1, String Email1) {
        firstname = firstname1;
        lastname = lastname1;
        Email = Email1;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

public class registration extends AppCompatActivity {

    private FirebaseAuth mAuth;
    TextInputLayout Firstname, lastname, email, password, confirmpassword;
    String Email, Password;
    Button signup;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Firstname = (TextInputLayout) findViewById(R.id.FirstnameLayout);
        lastname = (TextInputLayout) findViewById(R.id.SecondNameLayout);
        email = (TextInputLayout) findViewById(R.id.EmailLayout);
        password = (TextInputLayout) findViewById(R.id.PasswordLayout);
        confirmpassword = (TextInputLayout) findViewById(R.id.ConfirmPasswordLayout);
        signup = (Button) findViewById(R.id.RegisterButton);


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        final int[] flag = {1};

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                Email = email.getEditText().getText().toString();
                Password = password.getEditText().getText().toString();
                String firstname = Firstname.getEditText().getText().toString();
                String Lastname = lastname.getEditText().getText().toString();
                String Confirmpassword = confirmpassword.getEditText().getText().toString();

                if (firstname.isEmpty()) {
                    flag[0] = 0;
                    Firstname.setError("First name cannot be empty");
                }
                if (Lastname.isEmpty()) {
                    flag[0] = 0;
                    lastname.setError("Last name cannot be empty");
                }
//                if (Password.isEmpty()) {
//                    flag[0] = 0;
//                    password.setError("Password cannot be empty");
//                }
//                if(!password.equals(Confirmpassword)){
//          0.
//          flag[0] = 0;
//                    confirmpassword.setError("Enter the same password as above");
//                }

                if (flag[0] == 1) {
                    mAuth.createUserWithEmailAndPassword(Email, Password)
                            .addOnCompleteListener(registration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Successfully Added", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(registration.this, HomePage.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Invalid email/password", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                if (flag[0] == 1) {
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            details Details = new details(firstname, Lastname, Email);
                            databaseReference.push().setValue(Details);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


    }
}