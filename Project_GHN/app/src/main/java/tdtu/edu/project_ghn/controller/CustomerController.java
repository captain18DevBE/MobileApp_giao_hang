package tdtu.edu.project_ghn.controller;

import com.google.firebase.firestore.FirebaseFirestore;

import tdtu.edu.project_ghn.entity.Customer;

public class CustomerController {
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public boolean signUp(Customer customer) {
        boolean result = false;


        //Test
        Customer customer1  = new Customer();
        customer1.setEmail("tranleduy@gmail.com");
        customer1.setRoleId(1);

        db.collection("customers").document("customers").set(customer1);
        return result;
    }

    public boolean signIn(String email) {
        boolean result = false;

        return result;
    }
}
