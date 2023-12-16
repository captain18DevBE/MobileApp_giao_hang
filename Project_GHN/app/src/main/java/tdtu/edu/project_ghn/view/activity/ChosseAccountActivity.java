package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdtu.edu.project_ghn.R;

public class ChosseAccountActivity extends AppCompatActivity {

    Button btnCustomerLogin, btnShipperLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosse_account);

        initFindView();

        btnShipperLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosseAccountActivity.this, ShipperLoginActivity.class);
                startActivity(intent);
                finishAfterTransition();
            }
        });

        btnCustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChosseAccountActivity.this, CustomerLoginActivity.class);
                startActivity(intent);

            }
        });

    }

    private void initFindView() {
        btnShipperLogin = findViewById(R.id.btnShipperLogin);
        btnCustomerLogin = findViewById(R.id.btnCustomerLogin);
    }
}