package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.window.OnBackInvokedDispatcher;

import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tdtu.edu.project_ghn.MainActivity;
import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.controller.DeliverOrderController;
import tdtu.edu.project_ghn.controller.service.OnGetAllDocumentDeliverOrderListener;
import tdtu.edu.project_ghn.controller.service.OnGetDeliverOrderByEmailUserListener;
import tdtu.edu.project_ghn.entity.DeliverOrder;
import tdtu.edu.project_ghn.model.OrderDTO;
import tdtu.edu.project_ghn.view.adapter.ListOrderAdapter;

public class ListOrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private ListOrderAdapter listOrderAdapter;
    private List<OrderDTO> orderDTOList = new ArrayList<>();
    private DeliverOrderController deliverOrderController = new DeliverOrderController();
    private Map<String, Object> orderMap;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        initUI();
        setSupportActionBar(toolbar);



        //test get api
//        deliverOrderController.getAllDocumentDeliverOrder(new OnGetAllDocumentDeliverOrderListener() {
//            @Override
//            public void onSuccess(Map<String, Map<String, DeliverOrder>> collectionOrders) {
//                Log.d("lay danh sach du lieu", "abcssad");
//            }
//
//            @Override
//            public void onFailure(String err) {
//
//            }
//        });

        deliverOrderController.getListDeliverOrdersByEmailUser(new OnGetDeliverOrderByEmailUserListener() {
            @Override
            public void onSuccess(Map<String, Object> values) {
                orderMap = values;
                for (Map.Entry<String, Object> entry : values.entrySet()) {
                    OrderDTO orderDTO = new OrderDTO();
                    String key = entry.getKey();
                    Map<String, Object> value = (Map<String, Object>) entry.getValue();
//                    DeliverOrder value = (DeliverOrder) entry.getValue();
                    String address = (String) value.get("receiverAddress");
                    Map<String, Object> dateTime = (Map<String, Object>) value.get("dateTime");

                    Long year = (Long) dateTime.get("year");
                    Long month = (Long) dateTime.get("monthValue");
                    Long dayOfMonth = (Long) dateTime.get("dayOfMonth");
                    Long timeOfDay = (Long) dateTime.get("hour");
                    Long minute = (Long) dateTime.get("minute");
                    Long second = (Long) dateTime.get("second");

                    String strDateTime = ""+year+"-"+month+"-"+dayOfMonth+"  "+timeOfDay+":"+minute+":"+second;

                    Map<String, Object> productMap = (Map<String, Object>) value.get("product");
                    String productType = (String) productMap.get("productType");

                    Map<String, Object> receiverMap = (Map<String, Object>) value.get("receiver");
                    String phoneNumber = (String) receiverMap.get("phoneNumber");

                    orderDTO.setPhoneNumber(phoneNumber);
                    orderDTO.setAddress(address);
                    orderDTO.setDateTime(strDateTime);
                    orderDTO.setType(productType);


                    orderDTOList.add(orderDTO);
//                    Log.d("lay danh sach du lieu"+key, orderDTO.getDateTime());
                    initRCV();
                }
            }

            @Override
            public void onFailure(String err) {
                Log.d("lay danh sach du lieu", "that bai");
            }
        });

    }

    private void initRCV() {

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
        Intent intent = new Intent(ListOrderActivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
        return super.getOnBackInvokedDispatcher();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ListOrderActivity.this, MainActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}