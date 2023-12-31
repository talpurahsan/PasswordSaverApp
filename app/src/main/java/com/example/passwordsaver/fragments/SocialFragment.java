package com.example.passwordsaver.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;

public class SocialFragment extends MyFragment {

    public SocialFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View fragSocialView = inflater.inflate(R.layout.fragment_social, container, false);

        setFragmentView(fragSocialView, "Social");
        setBtnAddPasswordAction(fragSocialView, new SocialFragment());
        setRecyclerView(fragSocialView, MainActivity.getDatabaseHelper().getPasswords("Social", "password"));

        return fragSocialView;
    }
}