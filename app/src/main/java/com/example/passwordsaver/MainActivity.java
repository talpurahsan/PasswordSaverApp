package com.example.passwordsaver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.passwordsaver.database.DatabaseHelper;
import com.example.passwordsaver.fragments.AllFragment;
import com.example.passwordsaver.fragments.BankingAppsFragment;
import com.example.passwordsaver.fragments.BanksFragment;
import com.example.passwordsaver.fragments.EmailsFragment;
import com.example.passwordsaver.fragments.SocialFragment;
import com.example.passwordsaver.fragments.WorkFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    //database
    private static DatabaseHelper databaseHelper;

    private static final String ROOT_FRAGMENT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        //set toolbar
        setSupportActionBar(toolbar);

        //set navigation drawer
        setNavigationDrawer();

        //set default fragment
        loadFragment(new AllFragment(), true);

        //creating database
        databaseHelper = new DatabaseHelper(this);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    private void setNavigationDrawer(){

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemID = item.getItemId();

                if(itemID == R.id.all){ loadFragment(new AllFragment(), true); }
                else if(itemID == R.id.banks){ loadFragment(new BanksFragment(), false); }
                else if(itemID == R.id.bankingApps){ loadFragment(new BankingAppsFragment(), false); }
                else if(itemID == R.id.social){ loadFragment(new SocialFragment(), false); }
                else if(itemID == R.id.emails){ loadFragment(new EmailsFragment(), false); }
                else if (itemID == R.id.work){ loadFragment(new WorkFragment(), false); }

                drawerLayout.closeDrawer(GravityCompat.START);  //closing the drawer on the item's click

                return true;
            }
        });
    }

    public void loadFragment(Fragment fragment, boolean defaultFragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if(defaultFragment) {
            fragmentTransaction.add(R.id.container, fragment);
            fragmentManager.popBackStack(ROOT_FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            fragmentTransaction.addToBackStack(ROOT_FRAGMENT_TAG);
        }
        else {
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    //getters and setters
    public Toolbar getToolbar() {
        return toolbar;
    }

    public static DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public static MainActivity getMainActivityInstance(){

        return new MainActivity();
    }
}