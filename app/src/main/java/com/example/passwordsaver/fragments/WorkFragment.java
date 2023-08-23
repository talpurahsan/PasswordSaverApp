package com.example.passwordsaver.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;

public class WorkFragment extends MyFragment {

    public WorkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragWorkView = inflater.inflate(R.layout.fragment_work, container, false);

        setFragmentView(fragWorkView, "Work");
        setBtnAddPasswordAction(fragWorkView, new WorkFragment());
        setRecyclerView(fragWorkView, MainActivity.getDatabaseHelper().getPasswords("Work", "password"));

        return fragWorkView;
    }
}