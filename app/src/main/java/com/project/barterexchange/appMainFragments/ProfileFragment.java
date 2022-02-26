package com.project.barterexchange.appMainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.project.barterexchange.R;

public class ProfileFragment extends Fragment {
    private Button exitButton;
    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        exitButton = view.findViewById(R.id.exitButton);
        firebaseAuth = FirebaseAuth.getInstance();

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                Navigation.findNavController(getView()).navigate(R.id.action_fragmentSwitcher_to_authenticationFragment);
            }
        });

        return view;
    }
}