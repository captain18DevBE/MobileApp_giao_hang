package tdtu.edu.project_ghn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdtu.edu.project_ghn.controller.CustomerController;
import tdtu.edu.project_ghn.entity.Customer;
import tdtu.edu.project_ghn.view.activity.ChosseAccountActivity;
import tdtu.edu.project_ghn.view.activity.CustomerCreateOrderActivity3;
import tdtu.edu.project_ghn.view.activity.CustomerProfileActivity;
import tdtu.edu.project_ghn.view.activity.ListOrderActivity;
import tdtu.edu.project_ghn.view.fragment.CreateOrderFragment;
import tdtu.edu.project_ghn.view.fragment.ListOrderFragment;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_LIST_ORDER = 0;
    //test
    private int mCurrentFragment = FRAGMENT_LIST_ORDER;
    //test
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    TextView txtNav_UserName, txtNav_UserEmail;
    Button btnNextToProfileCustomer;
    View header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        buildNavigation();
        initListener();

        updateUI();

        navigationView.getMenu().findItem(R.id.nav_list_current_order).setChecked(true);
    }

    private void updateUI() {
        String email = user.getEmail();

        txtNav_UserEmail.setText(email);
    }

    private void buildNavigation() {

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(MainActivity.this);
    }

    private void initListener() {
        btnNextToProfileCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initUI() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_viewMainActivity);

        header = navigationView.getHeaderView(0);

        txtNav_UserEmail =  header.findViewById(R.id.txtNav_UserEmail);
        txtNav_UserName = header.findViewById(R.id.txtNav_UserName);

        btnNextToProfileCustomer = header.findViewById(R.id.btnNextToProfileCustomer);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_signOut) {
            Intent intent = new Intent(MainActivity.this, ChosseAccountActivity.class);
            startActivity(intent);

            navigationView.getMenu().findItem(R.id.nav_signOut).setChecked(true);
            FirebaseAuth.getInstance().signOut();
            finishAffinity();
        } else if (id == R.id.nav_create_order) {
            replaceFragment(new CreateOrderFragment());
            navigationView.getMenu().findItem(R.id.nav_create_order).setChecked(true);
        } else if (id == R.id.nav_list_current_order) {
            Intent intent = new Intent(MainActivity.this, ListOrderActivity.class);
            startActivity(intent);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.replaceFragment, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}