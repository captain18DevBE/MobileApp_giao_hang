package tdtu.edu.project_ghn.controller;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import tdtu.edu.project_ghn.controller.service.OnAddDeliverOrderListener;
import tdtu.edu.project_ghn.controller.service.OnAddOrderMapListener;
import tdtu.edu.project_ghn.controller.service.OnGetAllDocumentDeliverOrderListener;
import tdtu.edu.project_ghn.entity.DeliverOrder;

public class DeliverOrderController {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, DeliverOrder> deliverOrderMap;
    public void addDeliverOrderByEmail(DeliverOrder deliverOrder, final OnAddDeliverOrderListener listener) {
        db.collection("deliver_orders").document(user.getEmail()).set(deliverOrder)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        listener.onSuccess();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage());
                    }
                });
    }

    public void addDeliverOrderMapByUserEmail(Map<String, DeliverOrder> orderMap, OnAddOrderMapListener listener) {
        db.collection("deliver_orders").document(user.getEmail())
                .set(orderMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        listener.onSuccess();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listener.onFailure(e.getMessage());
                    }
                });
    }


    public void getAllDocumentDeliverOrder(OnGetAllDocumentDeliverOrderListener listener) {
        db.collection("deliver_orders")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("lay danh sach du lieu", document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d("lay danh sach du lieu that bai", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}
