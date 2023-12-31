package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.window.OnBackInvokedDispatcher;

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

public class ListOrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private ListOrderAdapter listOrderAdapter;
    private List<OrderDTO> orderDTOList = new ArrayList<>();
    private DeliverOrderController deliverOrderController = new DeliverOrderController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        initUI();
        setSupportActionBar(toolbar);
        initRCV();

        deliverOrderController.getAllDocumentDeliverOrder(new OnGetAllDocumentDeliverOrderListener() {
            @Override
            public void onSuccess(Map<String, Map<String, DeliverOrder>> collectionOrders) {
                Log.d("lay danh sach du lieu", "abcssad");
            }

            @Override
            public void onFailure(String err) {

            }
        });

    }

    private void initRCV() {
        //test
        OrderDTO tmp = new OrderDTO();
        tmp.setAddress("TP.HCM");
        tmp.setPhoneNumber("0343373617");
        tmp.setType("Thời trang");
        LocalDateTime localDateTime = LocalDateTime.now();
        tmp.setDateTime(localDateTime);

        OrderDTO tmp2 = new OrderDTO();
        tmp2.setAddress("TP.HCM");
        tmp2.setPhoneNumber("0343373617");
        tmp2.setType("Thời trang");
        LocalDateTime localDateTime2 = LocalDateTime.now();
        tmp2.setDateTime(localDateTime2);
        orderDTOList.add(tmp);
        orderDTOList.add(tmp2);
        //test

        listOrderAdapter = new ListOrderAdapter(this, orderDTOList);
        recyclerView.setAdapter(listOrderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_order, menu);
        return  true;
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbarListOrder);
        recyclerView = findViewById(R.id.rcv_listOrder);
    }

    @NonNull
    @Override
    public OnBackInvokedDispatcher getOnBackInvokedDispatcher() {

        finish();
        return super.getOnBackInvokedDispatcher();
    }

}