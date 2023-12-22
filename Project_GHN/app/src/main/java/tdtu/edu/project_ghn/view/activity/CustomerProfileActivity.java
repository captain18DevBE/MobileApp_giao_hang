package tdtu.edu.project_ghn.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import tdtu.edu.project_ghn.R;
import tdtu.edu.project_ghn.controller.CustomerController;
import tdtu.edu.project_ghn.controller.service.OnGetCustomerByEmail;
import tdtu.edu.project_ghn.entity.Customer;

public class CustomerProfileActivity extends AppCompatActivity {
    TextInputEditText edt_CustomerFullNameUpdate, edt_CustomerAddressUpdate, edt_CustomerPhoneNumberUpdate;
    Button btnUpdateCustomerInf;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    CustomerController customerController = new CustomerController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        
        initUI();
        updateUI();
        updateCustomerInf();
    }

    private void updateUI() {
        customerController.getByEmail(user.getEmail(), new OnGetCustomerByEmail() {
            @Override
            public void onSuccess(Customer customer) {
                edt_CustomerFullNameUpdate.setText(customer.getFullName());
                edt_CustomerAddressUpdate.setText(customer.getAddress());
                edt_CustomerPhoneNumberUpdate.setText(customer.getPhoneNumber());

                //get image

            }
            @Override
            public void onFailure(String msgErr) {
                Toast.makeText(CustomerProfileActivity.this, "Đã có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initUI() {
        btnUpdateCustomerInf = findViewById(R.id.btnUpdateCustomerInf);
        edt_CustomerPhoneNumberUpdate = findViewById(R.id.edt_CustomerPhoneNumberUpdate);
        edt_CustomerAddressUpdate = findViewById(R.id.edt_CustomerAddressUpdate);
        edt_CustomerFullNameUpdate = findViewById(R.id.edt_CustomerFullNameUpdate);
    }

    private void updateCustomerInf() {
        btnUpdateCustomerInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                customer.setEmail(user.getEmail());
                customer.setFullName(edt_CustomerFullNameUpdate.getText().toString().trim());
                customer.setAddress(edt_CustomerAddressUpdate.getText().toString().trim());
                customer.setPhoneNumber(edt_CustomerPhoneNumberUpdate.getText().toString().trim());
                customerController.updateCustomerInf(customer);
                finish();
            }
        });
    }
}