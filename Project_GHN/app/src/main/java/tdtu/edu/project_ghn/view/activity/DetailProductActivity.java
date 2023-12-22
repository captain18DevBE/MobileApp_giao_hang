package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.entity.DeliverOrder;

public class DetailProductActivity extends AppCompatActivity {

    Button btnContinue, btnChose_imgProduct;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        initUI();
        initListener();
        buildMenu();

        DeliverOrder deliverOrder = (DeliverOrder) getIntent().getSerializableExtra("deliverOrder");
        Log.d("testdata", deliverOrder.getService());
    }

    private void buildMenu() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông tin gói hàng");
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
        getMenuInflater().inflate(R.menu.menu_detail_product, menu);
        return true;
    }

    private void initListener() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = getIntent().getStringExtra("address");
                Intent intent = new Intent(DetailProductActivity.this, CustomerCreateOrderActivity3.class);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        });

        btnChose_imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProductActivity.this, CameraXActivity.class);

                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            String imgPath = data.getStringExtra("imgPath");
//            /storage/emulated/0/Android/data/tdtu.edu.project_ghn/files/1703058173162.jpg
            Log.d("image_path", imgPath);
        }
    }

    private void initUI() {
        btnContinue = findViewById(R.id.btnContinue);
        btnChose_imgProduct = findViewById(R.id.chose_imgProduct);
        toolbar = findViewById(R.id.toolbar);
    }
}