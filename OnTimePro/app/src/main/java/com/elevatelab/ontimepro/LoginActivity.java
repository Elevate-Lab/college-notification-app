package com.elevatelab.ontimepro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private TextView mSignUp;
    private TextInputEditText email, password;
    private Button googleLoginBtn, signInBtn;
    private TextInputLayout emailTxt, passwordTxt;
    private FirebaseAuth mFirebaseAuth;
    private Spinner organizationSpinner;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> collegeOptions;
    private ArrayList<String> collegeUserUID;
    private ArrayList<String> collegeDomain;
    private String collegeNameSel;
    private String collegeUidSel;
    private String collegeDomainSel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        mFirebaseAuth = FirebaseAuth.getInstance();

        collegeOptions = new ArrayList<>();
        collegeUserUID = new ArrayList<>();
        collegeDomain = new ArrayList<>();
        collegeOptions.add("Select Organization");
        collegeUserUID.add("-1");
        collegeDomain.add("-1");
        getCollegeOptions();

        collegeNameSel = collegeOptions.get(0);
        collegeUidSel = collegeUserUID.get(0);
        collegeDomainSel = collegeDomain.get(0);

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
                    if (checkSpinner()) {
                        String userEmail = Objects.requireNonNull(email.getText()).toString().trim();
                        String userPassword = Objects.requireNonNull(password.getText()).toString().trim();
                        mFirebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Signed In, successfully !!", Toast.LENGTH_SHORT).show();
                                    Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(mainIntent);
                                    finish();
                                } else {
                                    Log.e("SignInError :", "" + task.getException());
                                    Toast.makeText(LoginActivity.this, "Error in Signing In !!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });

        organizationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collegeNameSel = collegeOptions.get(position);
                collegeUidSel = collegeUserUID.get(position);
                collegeDomainSel = collegeDomain.get(position);
                Toast.makeText(LoginActivity.this, collegeNameSel + " : " + collegeUidSel + " : " + collegeDomainSel, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
        organizationSpinner = findViewById(R.id.organization_spinner);
    }

    private void getCollegeOptions() {
        firebaseFirestore = FirebaseFirestore.getInstance();

        firebaseFirestore.collection("organizations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult() != null && task.isSuccessful()) {
                            for (QueryDocumentSnapshot s : task.getResult()) {
                                System.out.println(s.get("orgName"));
                                collegeOptions.add(s.get("orgName").toString());
                                collegeUserUID.add(s.get("userId").toString());
                                collegeDomain.add(s.get("orgDomain").toString());
                                Log.i("><><><<><", s.get("userId").toString());
                                System.out.println(s.get("orgDomain"));
                                setSpinnerDetails();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, "Some Issue Occurred", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setSpinnerDetails() {
        ArrayAdapter adapter = new ArrayAdapter<String>(LoginActivity.this, R.layout.support_simple_spinner_dropdown_item, collegeOptions);
        organizationSpinner.setAdapter(adapter);
    }

    private boolean checkSpinner() {
        if (collegeUidSel.equals("-1") && collegeDomainSel.equals("-1")) {
            Toast.makeText(LoginActivity.this, "Select Organization", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}