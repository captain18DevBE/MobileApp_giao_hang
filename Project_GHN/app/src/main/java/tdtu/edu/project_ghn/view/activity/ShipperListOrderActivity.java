package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.controller.DeliverOrderController;
import tdtu.edu.project_ghn.controller.service.OnGetAllDocumentDeliverOrderListener;
import tdtu.edu.project_ghn.entity.DeliverOrder;
import tdtu.edu.project_ghn.model.OrderDTO;
import tdtu.edu.project_ghn.view.adapter.ListOrderAdapter;

public class ShipperListOrderActivity extends AppCompatActivity {

    private RecyclerView rcvListOrder;
    private ListOrderAdapter orderAdapter;
    private List<OrderDTO> allOrders = new ArrayList<>();
    private List<OrderDTO> filteredOrders = new ArrayList<>();

    private Map<String, Object> listAllNewOrders;
    ProgressDialog progressDialog;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        toolbar = findViewById(R.id.toolbarListOrder);
        progressBar = findViewById(R.id.progressBar);
        buildMenuAction(toolbar);
        progressDialog = new ProgressDialog(ShipperListOrderActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);

        rcvListOrder = findViewById(R.id.rcv_listOrder);
        rcvListOrder.setLayoutManager(new LinearLayoutManager(this));






        //Testing data:

        LocalDateTime dateTime1 = LocalDateTime.now();
<<<<<<< HEAD
        OrderDTO order1 = new OrderDTO("12", "TP.HCM", "2023", "Thời trang", "0343373617", "delivering");
        allOrders.add(order1);

        LocalDateTime dateTime2 = dateTime1.plusDays(1);
        OrderDTO order2 = new OrderDTO("13", "Hanoi", "2023", "Điện tử", "0194891030", "delivering");
        allOrders.add(order2);

        LocalDateTime dateTime3 = dateTime2.plusDays(1);
        OrderDTO order3 = new OrderDTO("14", "Dak Lak", "2023", "Đồ ăn", "9837291033", "delivered");
=======
        OrderDTO order1 = new OrderDTO("TP.HCM", "2023", "Thời trang", "0343373617", 1l);
        allOrders.add(order1);

        LocalDateTime dateTime2 = dateTime1.plusDays(1);
        OrderDTO order2 = new OrderDTO("Hanoi", "2023", "Điện tử", "0194891030", 1l);
        allOrders.add(order2);

        LocalDateTime dateTime3 = dateTime2.plusDays(1);
        OrderDTO order3 = new OrderDTO("Dak Lak", "2023", "Đồ ăn", "9837291033", 1l);
>>>>>>> 08132d5e39bcb5601e869c3eb674f1ee9c740dbb
        allOrders.add(order3);

        for (OrderDTO order : allOrders) {
            if ("delivering".equals(order.getState())) {
                filteredOrders.add(order);
            }
        }
        orderAdapter = new ListOrderAdapter(this, filteredOrders);
        rcvListOrder.setAdapter(orderAdapter);

        orderAdapter.setOnItemClickListener(new ListOrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(OrderDTO orderDTO) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(ShipperListOrderActivity.this, ShipperOrderDetailActivity.class);
                intent.putExtra("state", orderDTO.getState());
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_shipper_list_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        ActionBar actionBar = getSupportActionBar();
        filteredOrders.clear();

        if (item.getItemId() == R.id.menu_inProgressOrder) {
            progressDialog.show();
            for (OrderDTO order : allOrders) {
                if ("delivering".equals(order.getState())) {
                    filteredOrders.add(order);
                }
            }
            if (actionBar != null) {
                actionBar.setTitle("Đơn đang nhận");
            }
        } else if (item.getItemId() == R.id.menu_completedOrder) {
            progressDialog.show();
            for (OrderDTO order : allOrders) {
                if ("delivered".equals(order.getState())) {
                    filteredOrders.add(order);
                }
            }
            if (actionBar != null) {
                actionBar.setTitle("Đơn đã hoàn thành");
            }
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        orderAdapter.notifyDataSetChanged();
        progressDialog.dismiss();

        return super.onOptionsItemSelected(item);
    }

    private void buildMenuAction(androidx.appcompat.widget.Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đơn đang nhận");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}