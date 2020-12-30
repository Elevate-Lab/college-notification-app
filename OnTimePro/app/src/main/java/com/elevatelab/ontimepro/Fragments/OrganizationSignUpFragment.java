package com.elevatelab.ontimepro.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.elevatelab.ontimepro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class OrganizationSignUpFragment extends Fragment {

    private View rootView;
    private TextInputEditText nameEdt, emailEdt, domainEdt, passwordEdt, confirmPasswordEdt;
    private MaterialButton orgSignUpBtn;
    private TextInputLayout nameTextInputLayout, emailTextInputLayout, domainTextInputLayout, passwordTextInputLayout, cnfPasswordTextInputLayout;
    private FirebaseAuth orgFirebaseAuth;
    private FirebaseFirestore mFirebaseFireStore;
    private String UserId;

    public OrganizationSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_organization_signup, container, false);
        initViews();
        orgFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseFireStore = FirebaseFirestore.getInstance();
        orgSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    final String email = Objects.requireNonNull(emailEdt.getText()).toString().trim();
                    String password = Objects.requireNonNull(passwordEdt.getText()).toString().trim();
                    final String name = Objects.requireNonNull(nameEdt.getText()).toString();
                    final String domain = Objects.requireNonNull(domainEdt.getText()).toString();
                    orgFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Organization registered, successfully !!", Toast.LENGTH_SHORT).show();
                                UserId = Objects.requireNonNull(orgFirebaseAuth.getCurrentUser()).getUid();
                                DocumentReference mDocumentReference = mFirebaseFireStore.collection("organizations").document(UserId);
                                Map<String, String> orgMap = new HashMap<>();
                                orgMap.put("orgName", name);
                                orgMap.put("orgDomain", domain);
                                orgMap.put("userID",UserId);
                                orgMap.put("email",email);
                                mDocumentReference.set(orgMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("SignUpProfileSetUp : ", "Successful for " + UserId);
                                    }
                                });
                                Intent mainActivityIntent = new Intent(getActivity(), com.elevatelab.ontimepro.MainActivity.class);
                                mainActivityIntent.putExtra("instiCode",UserId);
                                startActivity(mainActivityIntent);
                                Objects.requireNonNull(getActivity()).finish();
                            } else {
                                Log.e("SignUpError :", "" + task.getException());
                                Toast.makeText(getContext(), "Error in Signing Up !!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        return rootView;
    }

    private void initViews() {
        nameEdt = rootView.findViewById(R.id.name_edit_text);
        emailEdt = rootView.findViewById(R.id.email_edit_text);
        domainEdt = rootView.findViewById(R.id.domain_edit_text);
        passwordEdt = rootView.findViewById(R.id.password_edit_text);
        confirmPasswordEdt = rootView.findViewById(R.id.confirm_password_edit_text);
        orgSignUpBtn = rootView.findViewById(R.id.organization_sign_up_btn);
        nameTextInputLayout = rootView.findViewById(R.id.name_txt_input_layout);
        emailTextInputLayout = rootView.findViewById(R.id.email_txt_input_layout);
        domainTextInputLayout = rootView.findViewById(R.id.domain_text_input_layout);
        passwordTextInputLayout = rootView.findViewById(R.id.password_text_input_layout);
        cnfPasswordTextInputLayout = rootView.findViewById(R.id.cnf_text_input_layout);
    }

    private boolean validateFields() {
        boolean returnCheck = true;
        if (Objects.requireNonNull(nameEdt.getText()).toString().trim().isEmpty()) {
            nameTextInputLayout.setError("Name is required !");
            returnCheck = false;
        }
        if (Objects.requireNonNull(emailEdt.getText()).toString().trim().isEmpty()) {
            emailTextInputLayout.setError("Email is required !");
            returnCheck = false;
        }
        if (Objects.requireNonNull(domainEdt.getText()).toString().trim().isEmpty()) {
            domainTextInputLayout.setError("Domain is required !");
            returnCheck = false;
        }
        if (Objects.requireNonNull(passwordEdt.getText()).toString().trim().isEmpty()) {
            passwordTextInputLayout.setError("Password is required !");
            returnCheck = false;
        }
        if (Objects.requireNonNull(confirmPasswordEdt.getText()).toString().trim().isEmpty()) {
            cnfPasswordTextInputLayout.setError("Confirm Password is required !");
            returnCheck = false;
        }
        if (!confirmPasswordEdt.getText().toString().trim().equals(passwordEdt.getText().toString().trim())) {
            cnfPasswordTextInputLayout.setError("Confirm Password didn't match !");
            returnCheck = false;
        }

        return returnCheck;
    }
}