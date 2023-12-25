package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import tdtu.edu.project_ghn.R;

public class ShipperOrderDetailActivity extends AppCompatActivity {

    TextView txtWaitForShipper, txtBeingShipped, txtFinishedShipping, txtIsPaid, txtFinishedDate;
    Button btnUpdateOrderState;
    Toolbar toolbar;
    ImageView imgProduct;
    //start test update orderdetail UI corresponds to data.
    boolean waitForShipper = false, beingShipped = false, FinishedShipping = false, IsPaid = false;

    private void updateOrderState() {
        if(waitForShipper) {
            txtWaitForShipper.setTextColor(Color.GREEN);
            btnUpdateOrderState.setText("Đã nhận hàng");
        }
        if(beingShipped) {
            waitForShipper = true;
            txtWaitForShipper.setTextColor(Color.GREEN);
            txtBeingShipped.setTextColor(Color.GREEN);
            if (IsPaid) btnUpdateOrderState.setText("Hoàn thành vận chuyển");
            else {
                btnUpdateOrderState.setText("Hoàn thành thanh toán");
            }
        }
        if(FinishedShipping) {
            waitForShipper = true; txtWaitForShipper.setTextColor(Color.GREEN);
            beingShipped = true; txtBeingShipped.setTextColor(Color.GREEN);
            if (!IsPaid) {
                IsPaid = true;
            }
            txtFinishedShipping.setTextColor(Color.GREEN);
            txtFinishedDate.setText("Hoàn thành vào ngày: XX - XX - XXXX");
            txtFinishedDate.setVisibility(View.VISIBLE);
            btnUpdateOrderState.setVisibility(View.GONE);
        }
        if(IsPaid) {
            txtIsPaid.setTextColor(Color.GREEN);
        } else {
            txtIsPaid.setText("4. Chưa thanh toán: XXX VND");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_order_detail);

        txtWaitForShipper = findViewById(R.id.txtWaitForShipper);
        txtBeingShipped = findViewById(R.id.txtBeingShipped);
        txtFinishedShipping = findViewById(R.id.txtFinishedShipping);
        txtIsPaid = findViewById(R.id.txtIsPaid);
        btnUpdateOrderState = findViewById(R.id.btnUpdateOrderState);
        txtFinishedDate = findViewById(R.id.txtFinishedDate2);
        toolbar = findViewById(R.id.toolbar4);

        buildMenuAction(toolbar);

        //testing saving and loading image on local storage
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String imagePath = sharedPreferences.getString("imgPath", "");

        imgProduct = findViewById(R.id.imgProduct);
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        imgProduct.setImageBitmap(bitmap);

        String state = getIntent().getStringExtra("state");
        switch (state) {
            case "delivering": beingShipped = true; break;
            case "delivered": FinishedShipping = true; break;
            case "waiting": waitForShipper = true; break;
        }
        updateOrderState();

        btnUpdateOrderState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!waitForShipper) {
                    waitForShipper = true;
                } else if (!beingShipped) {
                    beingShipped = true;
                } else if (!FinishedShipping) {
                        FinishedShipping = true;
                }
                updateOrderState();
            }
        });
    }

    private void buildMenuAction(androidx.appcompat.widget.Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin đơn hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}