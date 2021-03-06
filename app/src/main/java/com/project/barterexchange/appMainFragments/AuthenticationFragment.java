package com.project.barterexchange.appMainFragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.barterexchange.R;
import com.project.barterexchange.systemScripts.MainActivity;

import java.sql.SQLOutput;
import java.util.concurrent.Executor;

import javax.net.ssl.SSLContext;

public class AuthenticationFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button registerButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check exist user
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    // If the user exist...

                }
                else{
                    // Else...

                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_authentication, container, false);

        emailEditText = view.findViewById(R.id.editEmailAddress);
        passwordEditText = view.findViewById(R.id.editPassword);
        loginButton = view.findViewById(R.id.login);
        registerButton = view.findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();

//        FirebaseUser user = mAuth.getCurrentUser();
//        if(user != null){
//            Intent intent = new Intent(view.getContext(), AfterRegister.class);
//            startActivity(intent);
//        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if(email.length() > 0 && password.length() > 0){
                    login(email, password, view);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_authenticationFragment_to_registrationFragment);
            }
        });

        return view;
    }

    private void registration(String email, String password, View view){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(view.getContext(), "?????????????????????? ???????????? ??????????????!", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_authenticationFragment_to_fragmentSwitcher);
                }
                else{
                    Toast.makeText(view.getContext(), "?????????????????????? ??????????????????!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(String email, String password, View view){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((Activity) view.getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(view.getContext(), "?????????????????????? ???????????? ??????????????!", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_authenticationFragment_to_fragmentSwitcher);
                }
                else{
                    Toast.makeText(view.getContext(), "?????????????????????? ??????????????????!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
