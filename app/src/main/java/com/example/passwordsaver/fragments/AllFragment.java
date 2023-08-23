package com.example.passwordsaver.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.R;

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
        setRecyclerView(fragAllView, getMainActivity().getDatabaseHelper().getAllPasswords("password"));

        return fragAllView;
    }
}