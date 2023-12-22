package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tdtu.edu.project_ghn.R;

public class ShipperOrderDetailActivity extends AppCompatActivity {

    TextView txtWaitForShipper, txtBeingShipped, txtFinishedShipping, txtIsPaid, txtFinishedDate;
    Button btnUpdateOrderState;
    //start test update orderdetail UI corresponds to data.
    boolean waitForShipper = false, beingShipped = false, FinishedShipping = false, IsPaid = true;

    private void updateOrderState() {
        if(waitForShipper) {
            txtWaitForShipper.setTextColor(Color.GREEN);
            btnUpdateOrderState.setText("Đã nhận hàng");
        }
        if(beingShipped) {
            txtBeingShipped.setTextColor(Color.GREEN);
            if (IsPaid) btnUpdateOrderState.setText("Hoàn thành vận chuyển");
            else {
                btnUpdateOrderState.setText("Hoàn thành thanh toán");
            }
        }
        if(FinishedShipping) {
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
}