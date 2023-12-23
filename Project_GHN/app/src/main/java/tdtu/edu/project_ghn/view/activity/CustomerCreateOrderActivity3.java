package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;

import java.util.zip.Inflater;

import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.entity.DeliverOrder;
import tdtu.edu.project_ghn.entity.Product;
import tdtu.edu.project_ghn.entity.Receiver;

public class CustomerCreateOrderActivity3 extends AppCompatActivity {


    ImageButton btnMap;
    TextInputEditText txtAddress, edtAddressDetail, edt_ReceiverName, edt_ReceiverPhoneNumber, edt_ReceiverNotes;
    CheckBox checkBox;
    LinearLayout cashOnDelivery;
    Toolbar toolbar;

    Button btnFinishOrder;

    Product productDetail;
    DeliverOrder deliverOrder;

    Receiver receiver = new Receiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_create_order_3);

        initUI();
        initListener();
        buildMenu();

        String address = getIntent().getStringExtra("address");
        txtAddress.setText(address);

        deliverOrder = (DeliverOrder) getIntent().getSerializableExtra("deliverOrder");
        productDetail = deliverOrder.getProduct();

        Log.d("du lieu intent", productDetail.getProductType());

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
                    receiver.setPaid(true);
                    cashOnDelivery.setVisibility(View.VISIBLE);
                }
                else {
                    receiver.setPaid(false);
                    cashOnDelivery.setVisibility(View.GONE);
                }
            }
        });

        btnFinishOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiver.setAddress(txtAddress.getText().toString().trim());
                receiver.setDetailLocal(edtAddressDetail.getText().toString().trim());
                receiver.setFullName(edt_ReceiverName.getText().toString().trim());
                receiver.setPhoneNumber(edt_ReceiverPhoneNumber.getText().toString().trim());
                receiver.setNotes(edt_ReceiverNotes.getText().toString().trim());

            }
        });
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar2);
        btnMap = findViewById(R.id.btnMap);
        txtAddress = findViewById(R.id.txtAddress);
        checkBox = findViewById(R.id.checkBox);
        cashOnDelivery = findViewById(R.id.cashOnDelivery);

        btnFinishOrder = findViewById(R.id.btnFinishOrder);

        edt_ReceiverName = findViewById(R.id.edt_ReceiverName);
        edt_ReceiverNotes = findViewById(R.id.edt_ReceiverNotes);
        edt_ReceiverPhoneNumber = findViewById(R.id.edt_ReceiverPhoneNumber);

    }
}