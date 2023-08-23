package com.example.passwordsaver.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordsaver.MainActivity;
import com.example.passwordsaver.R;
import com.example.passwordsaver.RecycledPasswordActivity;
import com.example.passwordsaver.SavedPasswordActivity;
import com.example.passwordsaver.database.Password;

import java.util.ArrayList;

public class RecyclerViewRecycledPasswordAdapter extends RecyclerView.Adapter<RecyclerViewRecycledPasswordAdapter.PasswordViewHolder>{

    private Context context;
    private ArrayList<Password> passwordsList;

    private MainActivity mainActivity;

    public RecyclerViewRecycledPasswordAdapter(Context context, ArrayList<Password> passwordsList, MainActivity mainActivity) {
        this.context = context;
        this.passwordsList = passwordsList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public PasswordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_password_recycler_view, parent, false);
        return new PasswordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordViewHolder holder, int position) {

        holder.txtTitle.setText(passwordsList.get(position).getTitle());
        holder.txtAccount.setText("Account: " + passwordsList.get(position).getAccount());
        holder.txtUsername.setText("Username: " + passwordsList.get(position).getUsername());
        holder.txtImageIconPassword.setText(String.valueOf(passwordsList.get(position).getTitle().charAt(0)));

        holder.linearLayoutRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentRecycledPassword = new Intent(mainActivity.getApplicationContext(), RecycledPasswordActivity.class);
                intentRecycledPassword.putExtra("passwordID", passwordsList.get(position).getId());
                mainActivity.startActivity(intentRecycledPassword);
            }
        });
    }

    @Override
    public int getItemCount() {
        return passwordsList.size();
    }


    public static class PasswordViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle, txtAccount, txtUsername, txtImageIconPassword;
        LinearLayout linearLayoutRow;

        public PasswordViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtAccount = itemView.findViewById(R.id.txtAccount);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtImageIconPassword = itemView.findViewById(R.id.txtImageIconPassword);
            linearLayoutRow = itemView.findViewById(R.id.linearLayoutRow);
        }
    }
}
