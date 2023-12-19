package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;

import tdtu.edu.project_ghn.R;

public class CustomerCreateOrderActivity3 extends AppCompatActivity {


    ImageButton btnMap;
    TextInputEditText txtAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order_3);

        btnMap = findViewById(R.id.btnMap);
        txtAddress = findViewById(R.id.txtAddress);

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerCreateOrderActivity3.this, GoogleMapActivity.class);
                intent.putExtra("address", txtAddress.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}