package com.example.passwordsaver.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllFragment extends MyFragment {

    public AllFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragAllView = inflater.inflate(R.layout.fragment_all, container, false);

        setFragmentView(fragAllView, "All");
        setBtnAddPasswordAction(fragAllView, new AllFragment());
        setRecyclerView(fragAllView, getMainActivity().getDatabaseHelper().getAllPasswords());

        return fragAllView;
    }
}