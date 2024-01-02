package tdtu.edu.project_ghn;

import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.controller.CustomerController;
import tdtu.edu.project_ghn.controller.DeliverOrderController;
import tdtu.edu.project_ghn.controller.service.OnGetAllDocumentDeliverOrderListener;
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
    private DeliverOrderController deliverOrderController = new DeliverOrderController();

    Toolbar toolbar;
    private RecyclerView rcvListOrder;
    ProgressBar progressBar;
    private ListOrderAdapter orderAdapter;
    private List<OrderDTO> allOrders = new ArrayList<>();
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_main);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        getCurrentLocation();


        initUI();
        buildNavigation();
        initListener();

        getDataListOrder();

        rcvListOrder.setLayoutManager(new LinearLayoutManager(this));

        navigationView.getMenu().findItem(R.id.nav_list_current_order).setChecked(true);



        //Testing data:
        LocalDateTime dateTime1 = LocalDateTime.now();
        OrderDTO order1 = new OrderDTO("China", "2023", "Thời trang", "0343373617", "waiting");
        allOrders.add(order1);

        LocalDateTime dateTime2 = dateTime1.plusDays(1);
        OrderDTO order2 = new OrderDTO("Đồng Tháp", "2023", "Điện tử", "0194891030", "waiting");
        allOrders.add(order2);

        LocalDateTime dateTime3 = dateTime2.plusDays(1);
        OrderDTO order3 = new OrderDTO("USA", "2023", "Đồ ăn", "9837291033", "waiting");
        allOrders.add(order3);

        orderAdapter = new ListOrderAdapter(this, allOrders);
        rcvListOrder.setAdapter(orderAdapter);

        orderAdapter.setOnItemClickListener(new ListOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(OrderDTO orderDTO) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(ShipperMainActivity.this, ShipperOrderDetailActivity.class);
                intent.putExtra("state", orderDTO.getState());
                startActivity(intent);
            }
        });
    }

    private void getDataListOrder() {
        deliverOrderController.getAllDocumentDeliverOrder(new OnGetAllDocumentDeliverOrderListener() {

            @Override
            public void onSuccess(List<Map<String, Object>> collectionOrders) {
                for (Map<String, Object> objectMap : collectionOrders) {
                    for (Map.Entry<String, Object> entry : objectMap.entrySet()) {

                        String key = entry.getKey();
                        Map<String, Object>  value = (Map<String, Object>) entry.getValue();

                        Long statusOrder = (Long) value.get("status");



                        Log.d("du lieu", key);
                        Log.d("du lieu", value.toString());
                        Log.d("du lieu", statusOrder+"");

                    }
                }
            }

            @Override
            public void onFailure(String err) {

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
        progressBar = findViewById(R.id.progressBar);
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

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(20 * 1000);

            fusedLocationClient.requestLocationUpdates(locationRequest, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }
                    for (Location location : locationResult.getLocations()) {
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            sortOrdersByDistance(latitude, longitude);
                        }
                    }
                }
            }, Looper.getMainLooper());
        }
    }

    private void sortOrdersByDistance(double currentLat, double currentLong) {
        Collections.sort(allOrders, new Comparator<OrderDTO>() {
            @Override
            public int compare(OrderDTO o1, OrderDTO o2) {
                Geocoder geocoder = new Geocoder(ShipperMainActivity.this, Locale.getDefault());
                try {
                    List<Address> addresses1 = geocoder.getFromLocationName(o1.getAddress(), 1);
                    List<Address> addresses2 = geocoder.getFromLocationName(o2.getAddress(), 1);

                    if (addresses1.size() > 0 && addresses2.size() > 0) {
                        Address location1 = addresses1.get(0);
                        Address location2 = addresses2.get(0);

                        float[] results1 = new float[1];
                        float[] results2 = new float[1];

                        Location.distanceBetween(currentLat, currentLong, location1.getLatitude(), location1.getLongitude(), results1);
                        Location.distanceBetween(currentLat, currentLong, location2.getLatitude(), location2.getLongitude(), results2);

                        return Float.compare(results1[0], results2[0]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return 0;
            }
        });

        orderAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}

