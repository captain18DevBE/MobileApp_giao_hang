package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdtu.edu.project_ghn.R;

public class DetailProductActivity extends AppCompatActivity {

    Button btnContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        btnContinue = findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = getIntent().getStringExtra("address");
                Intent intent = new Intent(DetailProductActivity.this, CustomerCreateOrderActivity3.class);
                intent.putExtra("address", address);
                startActivity(intent);
            }
        });
    }
}