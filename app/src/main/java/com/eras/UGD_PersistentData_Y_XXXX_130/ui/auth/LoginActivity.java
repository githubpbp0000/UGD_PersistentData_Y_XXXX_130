package com.eras.UGD_PersistentData_Y_XXXX_130.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eras.UGD_PersistentData_Y_XXXX_130.Database.DatabaseClient;
import com.eras.UGD_PersistentData_Y_XXXX_130.MainActivity;
import com.eras.UGD_PersistentData_Y_XXXX_130.Model.Todo;
import com.eras.UGD_PersistentData_Y_XXXX_130.Model.User;
import com.eras.UGD_PersistentData_Y_XXXX_130.Preferences.UserPreferences;
import com.eras.UGD_PersistentData_Y_XXXX_130.R;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private MaterialButton btnClear, btnLogin, btnRegister;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(LoginActivity.this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        btnClear = findViewById(R.id.btnClear);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        /* Apps will check the login first from shared preferences */
        checkLogin();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        /* to clear the field just set text to "" */
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etUsername.setText("");
                etPassword.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    attemptLogin(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                }
            }
        });

    }


    private void attemptLogin(String username, String password){

        class AttemptLogin extends AsyncTask<Void, Void, User> {
            @Override
            protected User doInBackground(Void... voids) {

                User user = DatabaseClient.getInstance(LoginActivity.this)
                        .getDatabase()
                        .userDao()
                        .attemptLogin(username,password);

                return user;
            }

            @Override
            protected void onPostExecute(User user) {
                super.onPostExecute(user);
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Username atau password salah", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Berhasil login", Toast.LENGTH_SHORT).show();
                    userPreferences.setUser(user.getId(),user.getName(), user.getUsername(),user.getPassword());
                }
                checkLogin();

            }

        }

        AttemptLogin attemptLogin = new AttemptLogin();
        attemptLogin.execute();
    }

    private boolean validateForm(){
        /* Check username & password is empty or not */
        if(etUsername.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty()){
            Toast.makeText(LoginActivity.this,"Username Atau Password Kosong",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkLogin(){
        if(userPreferences.checkLogin()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

}