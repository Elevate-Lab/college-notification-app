package com.elevatelab.ontimepro.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.elevatelab.ontimepro.R;
import com.elevatelab.ontimepro.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class IndividualSignUpFragment extends Fragment {
    private ArrayList<String> collegeOptions;
    private FirebaseFirestore db;
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
        collegeOptions = new ArrayList<>();
        collegeOptions.add("Choose Institution");
        getCollegeOptions();

        individualSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {

                }
            }
        });

        organizationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), collegeOptions.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private void getCollegeOptions()
    {
        db = FirebaseFirestore.getInstance();

        db.collection("Colleges")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null && task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot s : task.getResult())
                            {
                                System.out.println(s.get("name"));
                                collegeOptions.add(s.get("name").toString());
                                setSpinnerDetails();
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Some Issue Occurred", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setSpinnerDetails(){
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, collegeOptions);
        organizationSpinner.setAdapter(adapter);
    }
}
