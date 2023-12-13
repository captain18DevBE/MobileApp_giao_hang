package tdtu.edu.project_ghn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import tdtu.edu.project_ghn.view.ChosseAccountActivity;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth user;
    private Button btnSignOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initListener();
    }

    private void initListener() {
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this, ChosseAccountActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }

    private void initUI() {
        btnSignOut = findViewById(R.id.btnCustomerSignOut);
    }
}