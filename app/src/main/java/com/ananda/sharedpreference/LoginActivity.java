package com.ananda.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ananda.sharedpreference.model.UserModel;
import com.ananda.sharedpreference.utils.Preferences;
/*
Tanggal Pengerjaan      : Jumat, 1 Mei 2020
Deskripsi Pengerjaan    : Membuat Login Activity
NIM                     : Ananda Marwanaya Putra
Nama                    : 10117157
Kelas                   : IF-4

 */

public class LoginActivity extends AppCompatActivity {

    private TextView textMasuk;
    private TextView textRegister;
    private EditText editUsername;
    private EditText editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        declareView();

        textMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiLogin();
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Preferences.getLoggedInStatus(getBaseContext())) {
            startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }
    }

    private void declareView() {

        textRegister = findViewById(R.id.tv_login_register);
        textMasuk = findViewById(R.id.tv_login_masuk);
        editUsername = findViewById(R.id.edt_login_username);
        editPassword = findViewById(R.id.edt_login_password);

    }

    private void validasiLogin() {

        editUsername.setError(null);
        editPassword.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = editUsername.getText().toString();
        String password = editPassword.getText().toString();


        if (TextUtils.isEmpty(userName)) {
            editUsername.setError("Harus diisi");
            fokus = editUsername;
            cancel = true;
        } else if (!cekUser(userName)) {
            editUsername.setError("Username Tidak Ditemukan");
            fokus = editUsername;
            cancel = true;
        }

        if (TextUtils.isEmpty(password)) {
            editPassword.setError("Harus Diisi");
            fokus = editPassword;
            cancel = true;
        } else if (!cekPassword(password)) {
            editPassword.setError("Data yang dimasukkan tidak sesuai");
            fokus = editPassword;
            cancel = true;
        }

        if (cancel) {
            fokus.requestFocus();
        } else {
            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);
            Preferences.setUserPreferences(getBaseContext(), userModel);
            Preferences.setLoggedInStatus(getBaseContext(), true);
           startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }

    }

    // True jika parameter user sama dengan data user yang terdaftar dari Preferences */
    private boolean cekUser(String user) {
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    // True jika parameter password sama dengan parameter repassword */
    private boolean cekPassword(String password) {
        return password.equals(Preferences.getRegisteredPassword(getBaseContext()));
    }
}
