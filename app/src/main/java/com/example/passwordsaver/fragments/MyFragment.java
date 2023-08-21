package com.example.passwordsaver.fragments;

import android.app.Dialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;
import com.example.passwordsaver.adapters.RecyclerViewPasswordAdapter;
import com.example.passwordsaver.database.Password;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyFragment extends Fragment {
    private MainActivity mainActivity;

    protected void setFragmentView(View fragmentView, String toolbarSubTitle){

        mainActivity = ( (MainActivity) getActivity() );

        setToolbarSubTitle(toolbarSubTitle);
    }

    private void setToolbarSubTitle(String subTitle){

        mainActivity.getToolbar().setSubtitle(subTitle);
    }

    protected void setBtnAddPasswordAction(View fragmentView, Fragment currentFragment){

        FloatingActionButton btnAddPassword = fragmentView.findViewById(R.id.btnAddPassword);

        btnAddPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.layout_add_password_dialog);
                setPasswordTypeSpinner(dialog); //set password type spinner
                dialog.show();

                Button savePasswordButton = dialog.findViewById(R.id.savePasswordBtn);
                savePasswordButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText edtTitle = dialog.findViewById(R.id.edtTitle);
                        EditText edtAccount = dialog.findViewById(R.id.edtAccount);
                        EditText edtUsername = dialog.findViewById(R.id.edtUsername);
                        EditText edtPassword = dialog.findViewById(R.id.edtPassword);
                        Spinner passwordTypeSpinner = dialog.findViewById(R.id.passwordTypeSpinner);

                        String title = edtTitle.getText().toString();
                        String account = edtAccount.getText().toString();
                        String username = edtUsername.getText().toString();
                        String password = edtPassword.getText().toString();
                        String passwordType = passwordTypeSpinner.getSelectedItem().toString();

                        mainActivity.getDatabaseHelper().addPassword(title, account, username, password, passwordType);
                        Toast.makeText(mainActivity.getApplicationContext(), "Password Saved Successfully!", Toast.LENGTH_SHORT).show();

                        dialog.dismiss();

                        mainActivity.loadFragment(currentFragment, false);
                    }
                });
            }
        });
    }

    private void setPasswordTypeSpinner(Dialog dialog){

        ArrayList<String> passwordTypeList = new ArrayList<>();
        passwordTypeList.add("Banks");
        passwordTypeList.add("Banking Apps");
        passwordTypeList.add("Social");
        passwordTypeList.add("Emails");
        passwordTypeList.add("Work");

        Spinner passwordTypeSpinner = dialog.findViewById(R.id.passwordTypeSpinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, passwordTypeList);
        passwordTypeSpinner.setAdapter(spinnerAdapter);
    }

    protected void setRecyclerView(View fragmentView, ArrayList<Password> passwordsList){

        RecyclerView recyclerViewPassword = fragmentView.findViewById(R.id.recyclerViewPassword);
        recyclerViewPassword.setLayoutManager(new LinearLayoutManager(mainActivity.getApplicationContext()));

        RecyclerViewPasswordAdapter recyclerViewPasswordAdapter = new RecyclerViewPasswordAdapter(mainActivity.getApplicationContext(), passwordsList, mainActivity);
        recyclerViewPassword.setAdapter(recyclerViewPasswordAdapter);
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }
}
