package com.example.passwordsaver.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;
import com.example.passwordsaver.adapters.RecyclerViewPasswordAdapter;
import com.example.passwordsaver.adapters.RecyclerViewRecycledPasswordAdapter;
import com.example.passwordsaver.database.Password;

import java.util.ArrayList;

public class RecycleBinFragment extends MyFragment {

    public RecycleBinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragRecyclebinView =  inflater.inflate(R.layout.fragment_recyclebin, container, false);

        setFragmentView(fragRecyclebinView, "Recycle Bin");
        setRecyclerView1(fragRecyclebinView, MainActivity.getDatabaseHelper().getAllPasswords("recyclebin"));

        return fragRecyclebinView;
    }

    private void setRecyclerView1(View fragmentView, ArrayList<Password> passwordsList) {

        RecyclerView recyclerViewPassword = fragmentView.findViewById(R.id.recyclerViewPassword);
        recyclerViewPassword.setLayoutManager(new LinearLayoutManager(getMainActivity().getApplicationContext()));

        RecyclerViewRecycledPasswordAdapter recyclerViewRecycledPasswordAdapter = new RecyclerViewRecycledPasswordAdapter(getMainActivity().getApplicationContext(), passwordsList, getMainActivity());
        recyclerViewPassword.setAdapter(recyclerViewRecycledPasswordAdapter);
    }
}