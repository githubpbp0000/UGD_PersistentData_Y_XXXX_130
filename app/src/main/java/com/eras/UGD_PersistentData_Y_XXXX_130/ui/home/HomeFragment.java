package com.eras.UGD_PersistentData_Y_XXXX_130.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.eras.UGD_PersistentData_Y_XXXX_130.Model.User;
import com.eras.UGD_PersistentData_Y_XXXX_130.Preferences.UserPreferences;
import com.eras.UGD_PersistentData_Y_XXXX_130.R;
import com.eras.UGD_PersistentData_Y_XXXX_130.ui.auth.LoginActivity;
import com.google.android.material.button.MaterialButton;

public class HomeFragment extends Fragment {
    private TextView tvWelcome;
    private MaterialButton btnLogout;
    private User user;
    private UserPreferences userPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        userPreferences = new UserPreferences(getContext());
        tvWelcome = root.findViewById(R.id.tvWelcome);
        btnLogout = root.findViewById(R.id.btnLogout);

        user = userPreferences.getUserLogin();

        checkLogin();

        tvWelcome.setText("Selamat datang, "+user.getName());

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPreferences.logout();
                Toast.makeText(getContext(), "Baiii baiii", Toast.LENGTH_SHORT).show();
                checkLogin();
            }
        });

        return root;
    }

    private void checkLogin(){
        /* this function will check if user login , akan memunculkan toast jika tidak redirect ke login activity */
        if(!userPreferences.checkLogin()){
            startActivity(new Intent(getContext(), LoginActivity.class));
            getActivity().finish();
        }else {
            Toast.makeText(getContext(), "Welcome back !", Toast.LENGTH_SHORT).show();
        }
    }
}