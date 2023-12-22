package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import java.util.zip.Inflater;

import tdtu.edu.project_ghn.R;

public class CustomerCreateOrderActivity3 extends AppCompatActivity {


    ImageButton btnMap;
    TextInputEditText txtAddress;
    CheckBox checkBox;
    LinearLayout cashOnDelivery;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order_3);

        initUI();

        String address = getIntent().getStringExtra("address");
        txtAddress.setText(address);

        initListener();
        buildMenu();
    }

    private void buildMenu() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin người nhận");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_information_receiver, menu);
        return true;
    }

    private void initListener() {
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerCreateOrderActivity3.this, GoogleMapActivity.class);
                intent.putExtra("address", txtAddress.getText().toString());
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cashOnDelivery.setVisibility(View.VISIBLE);
                }
                else {
                    cashOnDelivery.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar2);
        btnMap = findViewById(R.id.btnMap);
        txtAddress = findViewById(R.id.txtAddress);
        checkBox = findViewById(R.id.checkBox);
        cashOnDelivery = findViewById(R.id.cashOnDelivery);
    }
}