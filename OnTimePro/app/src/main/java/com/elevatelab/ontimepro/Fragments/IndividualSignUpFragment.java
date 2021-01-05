package com.elevatelab.ontimepro.Fragments;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class IndividualSignUpFragment extends Fragment {
    private ArrayList<String> collegeOptions;
    private ArrayList<String> collegeUserUID;
    private ArrayList<String> collegeDomain;
    private String collegeNameSel;
    private String userId;
    private String collegeUidSel;
    private String collegeDomainSel;
    private FirebaseFirestore db;
    private View rootView;
    private FirebaseAuth auth;
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
        collegeUserUID = new ArrayList<>();
        collegeDomain = new ArrayList<>();
        collegeOptions.add("Select Organization");

        collegeUserUID.add("-1");
        collegeDomain.add("-1");
        getCollegeOptions();

        collegeNameSel = collegeOptions.get(0);
        collegeUidSel = collegeUserUID.get(0);
        collegeDomainSel = collegeDomain.get(0);

        individualSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateFields()) {
                    if(checkSpinner()){
                        if(domainMatch()){
                            String nameInd = nameEdt.getText().toString();
                            String emailInd = emailEdt.getText().toString();
                            String password = passwordEdt.getText().toString();
                            String institutionID = collegeUidSel;
                            String institutionName = collegeNameSel;
                            startSignUpProcess(nameInd,emailInd,password,institutionID,institutionName);
                        }
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
                Toast.makeText(getContext(), collegeNameSel + " : " + collegeUidSel + " : " + collegeDomainSel , Toast.LENGTH_SHORT).show();
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

        if(returnCheck)
        {
            nameTextInputLayout.setError("");
            emailTextInputLayout.setError("");
            passwordTextInputLayout.setError("");
            cnfPasswordTextInputLayout.setError("");
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

        db.collection("organizations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.getResult() != null && task.isSuccessful())
                        {
                            for(QueryDocumentSnapshot s : task.getResult())
                            {
                                System.out.println(s.get("orgName"));
                                collegeOptions.add(s.get("orgName").toString());
                                collegeUserUID.add(s.get("userID").toString());
                                collegeDomain.add(s.get("orgDomain").toString());
                                Log.i("><><><<><",s.get("userID").toString());
                                System.out.println(s.get("orgDomain"));
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

    private boolean checkSpinner()
    {
        if(collegeUidSel.equals("-1") && collegeDomainSel.equals("-1"))
        {
            Toast.makeText(getContext(), "Select Organization", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean domainMatch()
    {
        if(emailEdt.getText().toString().toLowerCase().endsWith(collegeDomainSel))
        {
            emailTextInputLayout.setError("");
            return true;
        }
        else {
            emailTextInputLayout.setError("Domain Not Matched");
            Toast.makeText(getContext(), "Domain Not Matched", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void startSignUpProcess(final String name, final String email, String password, final String idInsti, final String nameInsti)
    {
        final HashMap<String,String> hashMap = new HashMap<>();
        final HashMap<String,String> hashMap1 = new HashMap<>();
        auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getContext(), "User Singed Successfully", Toast.LENGTH_SHORT).show();
                            userId = auth.getCurrentUser().getUid();
                            hashMap.put("indName",name);
                            hashMap.put("indUserId",userId);
                            hashMap.put("indEmail",email);
                            hashMap.put("instiName",nameInsti);
                            hashMap.put("instiID",idInsti);
                            hashMap1.put("indUserId",userId);
                            hashMap1.put("instiID",idInsti);

                            db.collection("Users")
                                    .document(userId)
                                    .set(hashMap)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("Signed Up! : ",userId);
                                        }
                                    });

                            db.collection("organizations")
                                    .document(idInsti)
                                    .collection("Users")
                                    .document(userId)
                                    .set(hashMap1)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Log.d("Added IN Instituition", userId);
                                        }
                                    });
                            Intent mainActivityIntent = new Intent(getActivity(),com.elevatelab.ontimepro.MainActivity.class);
                            mainActivityIntent.putExtra("instiCode",idInsti);
                            startActivity(mainActivityIntent);
                            Objects.requireNonNull(getActivity()).finish();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "Error Signing Up!", Toast.LENGTH_SHORT).show();
                            Log.e("SignUpError :", "" + task.getException());
                        }
                    }
                });

    }

}
