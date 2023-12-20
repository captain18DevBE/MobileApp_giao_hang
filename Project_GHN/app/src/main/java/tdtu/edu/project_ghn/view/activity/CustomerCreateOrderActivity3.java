package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import tdtu.edu.project_ghn.R;

public class CustomerCreateOrderActivity3 extends AppCompatActivity {


    ImageButton btnMap;
    TextInputEditText txtAddress;
    CheckBox checkBox;
    LinearLayout cashOnDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order_3);

        btnMap = findViewById(R.id.btnMap);
        txtAddress = findViewById(R.id.txtAddress);
        checkBox = findViewById(R.id.checkBox);
        cashOnDelivery = findViewById(R.id.cashOnDelivery);

        String address = getIntent().getStringExtra("address");
        txtAddress.setText(address);

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
}