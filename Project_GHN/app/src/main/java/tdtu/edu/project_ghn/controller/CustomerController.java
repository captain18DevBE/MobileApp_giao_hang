package tdtu.edu.project_ghn.controller;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import tdtu.edu.project_ghn.controller.service.OnGetCustomerByEmail;
import tdtu.edu.project_ghn.entity.Customer;
import tdtu.edu.project_ghn.view.activity.CustomerProfileActivity;

public class CustomerController {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public boolean signUp(Customer customer) {
        boolean result = false;

        db.collection("customers").document(customer.getEmail()).set(customer);
        return true;
    }

    public boolean signIn(String email) {
        boolean result = false;

        return result;
    }

    //get customer by email
    public void getByEmail(String email, final OnGetCustomerByEmail listener) {
        DocumentReference docRef = db.collection("customers").document(email);

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Customer customer = documentSnapshot.toObject(Customer.class);
                Log.d("firebase", "lay du lieu thanh cong");
                listener.onSuccess(customer);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFailure(e.getMessage());
            }
        });
    }

    //update detail information of customer
    public void updateCustomerInf(Customer resp) {
        DocumentReference documentReference = db.collection("customers").document(resp.getEmail());

        documentReference
                .update(
                    "fullName", resp.getFullName(),
                        "address", resp.getAddress(),
                        "phoneNumber", resp.getPhoneNumber()
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("cap nhat thong tin", "thanh cong");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("cap nhat thong tin", "that bai");
                    }
                });
    }
}
