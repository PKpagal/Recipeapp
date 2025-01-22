package com.example.recipeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword;
    Button loginbutton;
    FirebaseAuth auth;
    TextView textView;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        // Bind UI components
        textView=findViewById(R.id.ragisternow);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        loginbutton = findViewById(R.id.btn);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), login.class);
                startActivity(intent);
                finish();
            }
        });
       loginbutton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = editTextEmail.getText().toString().trim();
               String password = editTextPassword.getText().toString().trim();

               // Validate email and password fields
               if (TextUtils.isEmpty(email)) {
                   Toast.makeText(login.this, "Please enter email", Toast.LENGTH_SHORT).show();
                   return;
               }

               if (TextUtils.isEmpty(password)) {
                   Toast.makeText(login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                   return;
               }
               auth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   // Sign in success, update UI with the signed-in user's information
                                   Toast.makeText(login.this, "Login sucess", Toast.LENGTH_SHORT).show();
                                 Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                 startActivity(intent);
                                 finish();
                                   FirebaseUser user = auth.getCurrentUser();

                               } else {
                                   // If sign in fails, display a message to the user.

                                   Toast.makeText(login.this, "Authentication failed.",
                                           Toast.LENGTH_SHORT).show();

                               }
                           }
                       });
           }
       });
    }
}