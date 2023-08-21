package com.example.passwordsaver;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class PasswordActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected EditText edtTitle, edtAccount, edtUsername, edtPassword;
    protected CheckBox showPasswordCheckBox;
    protected Spinner passwordTypeSpinner;
    protected Intent intent;
    protected int passwordID;
    protected String title, account, username, password, passwordType;

    protected void findViewIDs(){

        toolbar = findViewById(R.id.toolbar);
        edtTitle = findViewById(R.id.edtTitle);
        edtAccount = findViewById(R.id.edtAccount);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        showPasswordCheckBox = findViewById(R.id.showPasswordCheckBox);
    }

    protected void setToolbar(Toolbar toolbar, String titleToolbar){

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(titleToolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    protected void setShowPasswordCheckBox(CheckBox showPasswordCheckBox, EditText edtPassword){

        showPasswordCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked)
                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                else
                    edtPassword.setInputType(129);  // 129 hides the password
            }
        });
    }

    protected void setPasswordTypeSpinner(Spinner passwordTypeSpinner, String passwordType){

        ArrayList<String> passwordTypeList = new ArrayList<>();
        passwordTypeList.add("Banks");
        passwordTypeList.add("Banking Apps");
        passwordTypeList.add("Social");
        passwordTypeList.add("Emails");
        passwordTypeList.add("Work");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, passwordTypeList);
        passwordTypeSpinner.setAdapter(spinnerAdapter);

        //setting the password type on spinner
        for (int i = 0; i < passwordTypeSpinner.getAdapter().getCount(); i++)
            if (passwordTypeSpinner.getAdapter().getItem(i).toString().equals(passwordType))
                passwordTypeSpinner.setSelection(i);
    }
}
