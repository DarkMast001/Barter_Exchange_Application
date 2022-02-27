package com.project.barterexchange.systemScripts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.barterexchange.R;
import com.project.barterexchange.appMainFragments.ChatFragment;
import com.project.barterexchange.appMainFragments.ProfileFragment;
import com.project.barterexchange.appMainFragments.ListFragment;

public class fragmentSwitcher extends Fragment {
    private BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ListFragment()).commit();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_switcher, container, false);

        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Name", String.valueOf(item.getItemId()));
                switch (item.getItemId()) {
                    case R.id.list:
                        Log.d("Cor", "Correct!");
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ListFragment()).commit();
                        break;
                    case R.id.chat:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ChatFragment()).commit();
                        break;
                    case R.id.profile:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new ProfileFragment()).commit();
                        break;
                }
                return true;
            }
        });

        return view;
    }
}
