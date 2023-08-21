package com.example.passwordsaver.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;

public class BanksFragment extends MyFragment {

    public BanksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragBanksView = inflater.inflate(R.layout.fragment_banks, container, false);

        setFragmentView(fragBanksView, "Banks");
        setBtnAddPasswordAction(fragBanksView, new BanksFragment());
        setRecyclerView(fragBanksView, getMainActivity().getDatabaseHelper().getPasswords("Banks"));

        return fragBanksView;
    }
}