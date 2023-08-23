package com.example.passwordsaver.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;

public class EmailsFragment extends MyFragment {

    public EmailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragEmailsView = inflater.inflate(R.layout.fragment_emails, container, false);

        setFragmentView(fragEmailsView, "Emails");
        setBtnAddPasswordAction(fragEmailsView, new EmailsFragment());
        setRecyclerView(fragEmailsView, MainActivity.getDatabaseHelper().getPasswords("Emails", "password"));

        return fragEmailsView;
    }
}