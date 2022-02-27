package com.project.barterexchange.appMainFragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.barterexchange.R;
import com.project.barterexchange.User;

public class RegistrationFragment extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference mDataBase;

    private Button registerButton;
    private EditText editEmail;
    private EditText editPassword;
    private EditText editName;
    private EditText editCity;
    private EditText editArea;
    private EditText editRailwayStation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        mAuth = FirebaseAuth.getInstance();
        mDataBase = FirebaseDatabase.getInstance().getReference();

        registerButton = view.findViewById(R.id.registerBtn);
        editEmail = view.findViewById(R.id.editRegistrationEmailAddress);
        editPassword = view.findViewById(R.id.editRegistrationPassword);
        editName = view.findViewById(R.id.editName);
        editCity = view.findViewById(R.id.editCity);
        editArea = view.findViewById(R.id.editArea);
        editRailwayStation = view.findViewById(R.id.editRailwayStation);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();
                String name = editName.getText().toString();
                String city = editCity.getText().toString();
                String area = editArea.getText().toString();
                String railwayStation = editRailwayStation.getText().toString();

                if(email.length() != 0 && password.length() != 0){
                    // Make registration
                    registration(email, password, name, city, area, railwayStation, view);
                }
            }
        });

        return view;
    }

    private void registration(String email, String password, String name, String city, String area, String railwayStation, View view){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(view.getContext(), "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();

                    String id = mDataBase.getKey();
                    User newUser = new User(id, email, name, city, area, railwayStation);
                    mDataBase.push().setValue(newUser);

                    Navigation.findNavController(getView()).navigate(R.id.action_registrationFragment_to_fragmentSwitcher);
                }
                else{
                    Toast.makeText(view.getContext(), "Регистрация провалена!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}