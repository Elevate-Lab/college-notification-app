package com.elevatelab.ontimepro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.elevatelab.ontimepro.R;
import com.google.android.material.textfield.TextInputEditText;


public class OrganizationSignUpFragment extends Fragment {

    private View rootView;
    private TextInputEditText nameEdt, emailEdt, domainEdt, passwordEdt, confirmPasswordEdt;

    public OrganizationSignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_organization_signup, container, false);
        initViews();
        return rootView;
    }

    private void initViews() {
        nameEdt = rootView.findViewById(R.id.name_edit_text);
        emailEdt = rootView.findViewById(R.id.email_edit_text);
        domainEdt = rootView.findViewById(R.id.domain_edit_text);
        passwordEdt = rootView.findViewById(R.id.password_edit_text);
        confirmPasswordEdt = rootView.findViewById(R.id.confirm_password_edit_text);
    }
}