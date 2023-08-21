package com.example.passwordsaver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;

public class BankingAppsFragment extends MyFragment {

    public BankingAppsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragBankingAppsView =  inflater.inflate(R.layout.fragment_banking_apps, container, false);

        setFragmentView(fragBankingAppsView, "Banking Apps");
        setBtnAddPasswordAction(fragBankingAppsView, new BankingAppsFragment());
        setRecyclerView(fragBankingAppsView, getMainActivity().getDatabaseHelper().getPasswords("Banking Apps"));

        return fragBankingAppsView;
    }
}