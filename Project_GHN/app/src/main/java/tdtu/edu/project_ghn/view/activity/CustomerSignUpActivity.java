package tdtu.edu.project_ghn.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdtu.edu.project_ghn.MainActivity;
import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.controller.CustomerController;
import tdtu.edu.project_ghn.entity.Customer;

public class CustomerSignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    TextView txtNotice;
    EditText edt_CustomerEmailSignup, edt_CustomerPassSignup, edt_CustomerPassConfirmSignup;
    Button btnCustomerSignup;

    CustomerController customerController = new CustomerController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);

        initUI();
        initListener();

        mAuth = FirebaseAuth.getInstance();


    }

    private void initListener() {
        btnCustomerSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_CustomerEmailSignup.getText().toString();
                String password = edt_CustomerPassSignup.getText().toString();
                String confirmPass = edt_CustomerPassConfirmSignup.getText().toString();
                if (email.isEmpty() || !password.equals(confirmPass)) {
                    txtNotice.setText("Vui lòng nhập đúng thông tin!");
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(CustomerSignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        txtNotice.setText("Đăng ký tài khoản thành công!");
                                        Customer newCustomer = new Customer();
                                        newCustomer.setEmail(user.getEmail());
                                        newCustomer.setRole("customer");
                                        customerController.signUp(newCustomer);
                                        Intent intent = new Intent(CustomerSignUpActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
//                                      updateUI(user);
                                    } else {
                                        txtNotice.setText("Đăng ksy tài khoản thất bại!");
                                    }
                                }
                            });
                    }
            }
        });
    }

    private void initUI() {
        edt_CustomerEmailSignup = findViewById(R.id.edt_CustomerEmailSignup);
        edt_CustomerPassSignup = findViewById(R.id.edt_CustomerPassSignup);
        edt_CustomerPassConfirmSignup = findViewById(R.id.edt_CustomerPassConfirmSignup);
        btnCustomerSignup = findViewById(R.id.btnCustomerSignup);
        txtNotice = findViewById(R.id.txtNotice);
    }
}