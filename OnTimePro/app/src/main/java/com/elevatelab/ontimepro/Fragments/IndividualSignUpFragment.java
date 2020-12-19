package com.elevatelab.ontimepro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.elevatelab.ontimepro.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class IndividualSignUpFragment extends Fragment {
    private View rootView;
    private TextInputEditText nameEdt, emailEdt, passwordEdt, confirmPasswordEdt;
    private Spinner organizationSpinner;
    private MaterialButton individualSignUpBtn;
    private TextInputLayout nameTextInputLayout, emailTextInputLayout, passwordTextInputLayout, cnfPasswordTextInputLayout;

    public IndividualSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_individual_sign_up, container, false);
        initViews();

        individualSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {

                }
            }
        });

        return rootView;
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

    private void initViews() {
        nameEdt = rootView.findViewById(R.id.name_edit_text);
        emailEdt = rootView.findViewById(R.id.email_edit_text);
        passwordEdt = rootView.findViewById(R.id.password_edit_text);
        confirmPasswordEdt = rootView.findViewById(R.id.confirm_password_edit_text);
        organizationSpinner = rootView.findViewById(R.id.registered_organization_spinner);
        individualSignUpBtn = rootView.findViewById(R.id.individual_sign_up_btn);
        nameTextInputLayout = rootView.findViewById(R.id.name_txt_input_layout);
        emailTextInputLayout = rootView.findViewById(R.id.email_txt_input_layout);
        passwordTextInputLayout = rootView.findViewById(R.id.password_text_input_layout);
        cnfPasswordTextInputLayout = rootView.findViewById(R.id.cnf_password_text_input_layout);
    }
}