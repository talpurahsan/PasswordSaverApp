package com.example.passwordsaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.passwordsaver.database.Password;
import com.example.passwordsaver.fragments.AllFragment;

import java.util.ArrayList;

public class SavedPasswordActivity extends PasswordActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_password);

        //finding IDs
        findViewIDs();

        //getting activity intent
        intent = getIntent();
        passwordID = intent.getIntExtra("passwordID", 0);

        Password foundPassword = MainActivity.getDatabaseHelper().getPassword(passwordID, "password");
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        new MenuInflater(this).inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int itemID = item.getItemId();

        if(itemID == R.id.optionEdit){

            Intent intentEditPassword = new Intent(SavedPasswordActivity.this, EditPasswordActivity.class);
            intentEditPassword.putExtra("passwordID", passwordID);
            intentEditPassword.putExtra("title", title);
            intentEditPassword.putExtra("account", account);
            intentEditPassword.putExtra("username", username);
            intentEditPassword.putExtra("password", password);
            intentEditPassword.putExtra("passwordType", passwordType);
            startActivity(intentEditPassword);
        }
        if(itemID == R.id.optionDelete){

            MainActivity.getDatabaseHelper().deletePassword(passwordID, "password");
            Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
            Intent intentMainActivity = new Intent(SavedPasswordActivity.this, MainActivity.class);
            intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);   // clearing the activity stacktrace
            startActivity(intentMainActivity);
        }

        return super.onOptionsItemSelected(item);
    }
}