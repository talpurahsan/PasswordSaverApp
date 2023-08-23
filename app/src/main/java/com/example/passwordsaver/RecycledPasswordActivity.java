package com.example.passwordsaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.passwordsaver.database.Password;
import com.example.passwordsaver.fragments.RecycleBinFragment;

public class RecycledPasswordActivity extends PasswordActivity {

    private Button deleteBtn;
    private Button restoreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycled_password);

        //finding IDs
        findViewIDs();
        deleteBtn = findViewById(R.id.deleteBtn);
        restoreBtn = findViewById(R.id.restoreBtn);

        //getting activity intent
        intent = getIntent();
        passwordID = intent.getIntExtra("passwordID", 0);

        Password foundPassword = MainActivity.getDatabaseHelper().getPassword(passwordID, "recyclebin");
        title = foundPassword.getTitle();
        account = foundPassword.getAccount();
        username = foundPassword.getUsername();
        password = foundPassword.getPassword();
        passwordType = foundPassword.getPasswordType();

        //set toolbar on saved password activity
        setToolbar(toolbar, title);

        //set text on EditText views
        edtTitle.setText(title);
        edtAccount.setText(account);
        edtUsername.setText(username);
        edtPassword.setText(password);

        //making EditText views non-editable
        edtTitle.setEnabled(false);
        edtAccount.setEnabled(false);
        edtUsername.setEnabled(false);
        edtPassword.setEnabled(false);

        //set showPasswordCheckBox at EditText edtPassword
        setShowPasswordCheckBox(showPasswordCheckBox, edtPassword);

        //set passwordTypeSpinner
        passwordTypeSpinner = findViewById(R.id.passwordTypeSpinner);
        setPasswordTypeSpinner(passwordTypeSpinner, passwordType);
        passwordTypeSpinner.setEnabled(false);  //making spinner non-editable

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.getDatabaseHelper().deletePassword(passwordID, "recyclebin");
                Intent intentMainActivity = new Intent(RecycledPasswordActivity.this, MainActivity.class);
                intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   // clearing the activity stacktrace
                startActivity(intentMainActivity);
            }
        });

        restoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.getDatabaseHelper().addPassword(title, account,username, password, passwordType);
                MainActivity.getDatabaseHelper().deletePassword(passwordID, "recyclebin");
                Intent intentMainActivity = new Intent(RecycledPasswordActivity.this, MainActivity.class);
                intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   // clearing the activity stacktrace
                startActivity(intentMainActivity);
            }
        });
    }
}