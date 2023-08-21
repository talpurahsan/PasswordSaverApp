package com.example.passwordsaver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.passwordsaver.fragments.AllFragment;

import java.util.ArrayList;

public class EditPasswordActivity extends PasswordActivity {

    private Button savePasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        //finding IDs
        findViewIDs();
        savePasswordBtn = findViewById(R.id.savePasswordBtn);

        //getting activity intent
        intent = getIntent();
        passwordID = intent.getIntExtra("passwordID", 0);
        title = intent.getStringExtra("title");
        account = intent.getStringExtra("account");
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");
        passwordType = intent.getStringExtra("passwordType");

        //set toolbar on saved password activity
        setToolbar(toolbar, "Edit Password");
        getSupportActionBar().setSubtitle(title);

        //set text on EditText views
        edtTitle.setText(title);
        edtAccount.setText(account);
        edtUsername.setText(username);
        edtPassword.setText(password);

        //set showPasswordCheckBox
        setShowPasswordCheckBox(showPasswordCheckBox, edtPassword);

        //set passwordTypeSpinner
        passwordTypeSpinner = findViewById(R.id.passwordTypeSpinner);
        setPasswordTypeSpinner(passwordTypeSpinner, passwordType);

        savePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = edtTitle.getText().toString();
                String account = edtAccount.getText().toString();
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                String passwordType = passwordTypeSpinner.getSelectedItem().toString();

                MainActivity.getDatabaseHelper().updatePassword(passwordID, title, account, username, password, passwordType);
                Toast.makeText(EditPasswordActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intentMainActivity = new Intent(EditPasswordActivity.this, MainActivity.class);
                intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   // clearing the activity stacktrace
                startActivity(intentMainActivity);
            }
        });
    }
}