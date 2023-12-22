package tdtu.edu.project_ghn.controller;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import tdtu.edu.project_ghn.entity.Customer;

public class CustomerController {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Customer customer;
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
    public Customer getByEmail(String email) {
        DocumentReference docRef = db.collection("customers").document(email);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                customer = documentSnapshot.toObject(Customer.class);
            }
        });
        return customer;
    }
}
