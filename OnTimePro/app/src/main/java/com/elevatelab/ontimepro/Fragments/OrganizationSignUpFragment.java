package com.elevatelab.ontimepro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.elevatelab.ontimepro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class OrganizationSignUpFragment extends Fragment {

    private View rootView;
    private TextInputEditText nameEdt, emailEdt, domainEdt, passwordEdt, confirmPasswordEdt;
    private MaterialButton orgSignUpBtn;
    private TextInputLayout nameTextInputLayout, emailTextInputLayout, domainTextInputLayout, passwordTextInputLayout, cnfPasswordTextInputLayout;

    public OrganizationSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_organization_signup, container, false);
        initViews();

        orgSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {

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
        if (nameEdt.getText().toString().isEmpty()) {
            nameTextInputLayout.setError("Name is required !");
            returnCheck = false;
        }
        if (emailEdt.getText().toString().isEmpty()) {
            emailTextInputLayout.setError("Email is required !");
            returnCheck = false;
        }
        if (domainEdt.getText().toString().isEmpty()) {
            domainTextInputLayout.setError("Domain is required !");
            returnCheck = false;
        }
        if (passwordEdt.getText().toString().isEmpty()) {
            passwordTextInputLayout.setError("Password is required !");
            returnCheck = false;
        }
        if (confirmPasswordEdt.getText().toString().isEmpty()) {
            cnfPasswordTextInputLayout.setError("Confirm Password is required !");
            returnCheck = false;
        }
        if (!confirmPasswordEdt.getText().toString().equals(passwordEdt.getText().toString())) {
            cnfPasswordTextInputLayout.setError("Confirm Password didn't match !");
            returnCheck = false;
        }

        return returnCheck;
    }
}