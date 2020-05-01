package com.ananda.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
/*
Tanggal Pengerjaan      : Jumat, 1 Mei 2020
Deskripsi Pengerjaan    : Membuat Home Activity
NIM                     : Ananda Marwanaya Putra
Nama                    : 10117157
Kelas                   : IF-4

 */

import com.ananda.sharedpreference.utils.Preferences;

public class HomeActivity extends AppCompatActivity {
    private TextView textKeluar;
    private TextView textName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        declareView();
        textKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Preferences.setLogout(getBaseContext());

                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });
    }
    private void declareView() {
        textKeluar = findViewById(R.id.tv_logout);
        textName = findViewById(R.id.tvName);

        textName.setText(Preferences.getRegisteredUser(getBaseContext()));
    }
}
