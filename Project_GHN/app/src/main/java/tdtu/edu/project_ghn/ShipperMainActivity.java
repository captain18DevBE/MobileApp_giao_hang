package tdtu.edu.project_ghn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.controller.CustomerController;
import tdtu.edu.project_ghn.entity.Customer;
import tdtu.edu.project_ghn.model.OrderDTO;
import tdtu.edu.project_ghn.view.activity.ChangeCustomerPassActivity;
import tdtu.edu.project_ghn.view.activity.ChosseAccountActivity;
import tdtu.edu.project_ghn.view.activity.ListOrderActivity;
import tdtu.edu.project_ghn.view.activity.ShipperInfoUpdate;
import tdtu.edu.project_ghn.view.activity.ShipperListOrderActivity;
import tdtu.edu.project_ghn.view.activity.ShipperOrderDetailActivity;
import tdtu.edu.project_ghn.view.adapter.ListOrderAdapter;
import tdtu.edu.project_ghn.view.fragment.CreateOrderFragment;

public class ShipperMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    private RecyclerView rcvListOrder;
    private ListOrderAdapter orderAdapter;
    private List<OrderDTO> allOrders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_main);

        initUI();
        buildNavigation();
        initListener();

        rcvListOrder.setLayoutManager(new LinearLayoutManager(this));

        navigationView.getMenu().findItem(R.id.nav_list_current_order).setChecked(true);

        //Testing data:
        LocalDateTime dateTime1 = LocalDateTime.now();
        OrderDTO order1 = new OrderDTO("Đồng Tháp", dateTime1, "Thời trang", "0343373617", "waiting");
        allOrders.add(order1);

        LocalDateTime dateTime2 = dateTime1.plusDays(1);
        OrderDTO order2 = new OrderDTO("China", dateTime2, "Điện tử", "0194891030", "waiting");
        allOrders.add(order2);

        LocalDateTime dateTime3 = dateTime2.plusDays(1);
        OrderDTO order3 = new OrderDTO("USA", dateTime3, "Đồ ăn", "9837291033", "waiting");
        allOrders.add(order3);

        orderAdapter = new ListOrderAdapter(this, allOrders);
        rcvListOrder.setAdapter(orderAdapter);

        orderAdapter.setOnItemClickListener(new ListOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(OrderDTO orderDTO) {
                Intent intent = new Intent(ShipperMainActivity.this, ShipperOrderDetailActivity.class);
                intent.putExtra("state", orderDTO.getState());
                startActivity(intent);
            }
        });
    }
    private void initListener() {

    }
    private void buildNavigation() {

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(ShipperMainActivity.this, mDrawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(ShipperMainActivity.this);
    }

    private void initUI() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_viewShipperMainActivity);
        rcvListOrder = findViewById(R.id.listWaitingOrders);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_signOut) {
            Intent intent = new Intent(ShipperMainActivity.this, ChosseAccountActivity.class);
            startActivity(intent);

            navigationView.getMenu().findItem(R.id.nav_signOut).setChecked(true);
            FirebaseAuth.getInstance().signOut();
            finishAffinity();
        } else if (id == R.id.nav_changeInformation) {
            Intent intent = new Intent(ShipperMainActivity.this, ShipperInfoUpdate.class);
            startActivity(intent);
        } else if (id == R.id.nav_list_current_order) {
            //Testing
            Intent intent = new Intent(ShipperMainActivity.this, ShipperMainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_list_accecpted_order) {
            //Testing
            Intent intent = new Intent(ShipperMainActivity.this, ShipperListOrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_changePass) {
            //Shipper Pass Changing Activity is not created, tested first using customer's
            Intent intent = new Intent(ShipperMainActivity.this, ChangeCustomerPassActivity.class);
            startActivity(intent);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

