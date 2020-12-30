package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextView mSignUp;
    private TextInputEditText email, password;
    private Button googleLoginBtn, signInBtn;
    private TextInputLayout emailTxt, passwordTxt;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private String InstiCodeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });


        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    String userEmail = Objects.requireNonNull(email.getText()).toString().trim();
                    String userPassword = Objects.requireNonNull(password.getText()).toString().trim();
                    mFirebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Signed In, successfully !!", Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mFirebaseAuth.getCurrentUser();
                                String userID = user.getUid();
                                firebaseFirestore.collection("Users")
                                        .document(userID)
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                InstiCodeUser = (String) documentSnapshot.get("instiID");
                                                Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                                mainIntent.putExtra("instiCode",InstiCodeUser);
                                                startActivity(mainIntent);
                                                finish();
                                            }
                                        });

                            } else {
                                Log.e("SignInError :", "" + task.getException());
                                Toast.makeText(LoginActivity.this, "Error in Signing In !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }

    private boolean validateFields() {
        boolean validCheck = true;
        if (email.getText().toString().trim().isEmpty()) {
            emailTxt.setError("Email is required !");
            validCheck = false;
        }
        if (password.getText().toString().trim().isEmpty()) {
            passwordTxt.setError("Password is required !");
            validCheck = false;
        }
        return validCheck;
    }

    private void initViews() {
        mSignUp = findViewById(R.id.signUp_txt);
        email = findViewById(R.id.email_edit_text_login);
        password = findViewById(R.id.pass_edit_text_login);
        googleLoginBtn = findViewById(R.id.google_login_btn);
        signInBtn = findViewById(R.id.sign_in_btn);
        emailTxt = findViewById(R.id.email_id);
        passwordTxt = findViewById(R.id.password);
    }
}