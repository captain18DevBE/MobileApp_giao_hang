package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdtu.edu.project_ghn.MainActivity;
import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.ShipperMainActivity;

public class ShipperLoginActivity extends AppCompatActivity {

    Button btnLoginShipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipper_login);

        btnLoginShipper = findViewById(R.id.btnLoginShipper);

        btnLoginShipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShipperLoginActivity.this, ShipperMainActivity.class);
                startActivity(intent);
            }
        });
    }
}