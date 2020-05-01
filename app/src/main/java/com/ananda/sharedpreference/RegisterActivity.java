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
Deskripsi Pengerjaan    : Membuat Register Activity
NIM                     : Ananda Marwanaya Putra
Nama                    : 10117157
Kelas                   : IF-4

 */


public class RegisterActivity extends AppCompatActivity {
    private TextView textMasuk;
    private EditText editUserName;
    private EditText editPassWord;
    private EditText editRePassWord;
    private EditText editPhoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        declareView();
        textMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiRegister();
            }
        });

    }

    private void declareView() {
        textMasuk = findViewById(R.id.tv_reg_masuk);
        editUserName = findViewById(R.id.edt_reg_username);
        editPassWord = findViewById(R.id.edt_reg_password);
        editRePassWord = findViewById(R.id.edt_reg_password_confirm);
        editPhoneNumber = findViewById(R.id.edt_reg_phone);
    }

    private void validasiRegister(){

        editUserName.setError(null);
        editPassWord.setError(null);
        editRePassWord.setError(null);
        View fokus = null;
        boolean cancel = false;

        String userName = editUserName.getText().toString();
        String password = editPassWord.getText().toString();
        String rePassword = editRePassWord.getText().toString();
        String phoneNumber = editPhoneNumber.getText().toString();


        if (TextUtils.isEmpty(userName)){
            editUserName.setError("Harus diisi");
            fokus = editUserName;
            cancel = true;
        }else if(cekUser(userName)){
            editUserName.setError("Username sudah terdaftar");
            fokus = editUserName;
            cancel = true;
        }

       if (TextUtils.isEmpty(password)){
            editPassWord.setError("Harus Diisi");
            fokus = editPassWord;
            cancel = true;
        }else if (!cekPassword(password,rePassword)){
            editPassWord.setError("Password yang dimasukkan tidak sesuai");
            fokus = editPassWord;
            cancel = true;
        }

       if (cancel){
            fokus.requestFocus();
        }else{
            UserModel userModel = new UserModel();
            userModel.setUsername(userName);
            userModel.setPassword(password);
            userModel.setPhone(phoneNumber);
            Preferences.setUserPreferences(getBaseContext(),userModel);
            Preferences.setLoggedInStatus(getBaseContext(),true);
           startActivity(new Intent(getBaseContext(), HomeActivity.class));
            finish();
        }

    }


    private boolean cekUser(String user){
        return user.equals(Preferences.getRegisteredUser(getBaseContext()));
    }

    private boolean cekPassword(String password, String repassword){
        return password.equals(repassword);
    }

}
