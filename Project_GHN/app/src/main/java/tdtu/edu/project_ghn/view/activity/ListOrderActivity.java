package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;

import tdtu.edu.project_ghn.R;

public class ListOrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

        setSupportActionBar(toolbar);

        initUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_order, menu);
        return  true;
    }

    private void initUI() {
        toolbar = findViewById(R.id.toolbar);

    }
}