package tdtu.edu.project_ghn.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdtu.edu.project_ghn.MainActivity;
import tdtu.edu.project_ghn.R;

public class CustomerLoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    Button btnCustomerSignUp, btnCustomerLogin;
    EditText edtUserName, edtPassWord;
    TextView tvAlertLogin;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        initFindView();
        buildMenuAction(toolbar);

        initListener();

    }

    private void initListener() {
        btnCustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthentication();
            }
        });

        btnCustomerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerLoginActivity.this, CustomerSignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void checkAuthentication() {

        mAuth = FirebaseAuth.getInstance();
        String email = edtUserName.getText().toString().trim();
        String password = edtPassWord.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
//                    updateUI()
                    Intent intent = new Intent(CustomerLoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                } else {
                    tvAlertLogin.setText("Sai tên đăng nhập hoặc mật khẩu!");
                }
            }
        });
    }


    private void buildMenuAction(Toolbar toolbar) {
        toolbar.setTitle("ĐĂNG NHẬP");
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        toolbar.inflateMenu(R.menu.menu_login);

    }

    private void initFindView() {
        btnCustomerSignUp = findViewById(R.id.btnSignUp);
        btnCustomerLogin = findViewById(R.id.btnLogin);
        toolbar = findViewById(R.id.toolbar);
        edtUserName = findViewById(R.id.edtUserNameCustomer);
        edtPassWord = findViewById(R.id.edtPassCustomer);
        tvAlertLogin = findViewById(R.id.tvAlertLogin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

//  private void updateUI(FirebaseUser user) {
//        hideProgressDialog();
//        if (user != null) {
//            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
//            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));
//
//            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
//        } else {
//            mStatusTextView.setText(R.string.signed_out);
//            mDetailTextView.setText(null);
//
//            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
//            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
//        }
//    }

}