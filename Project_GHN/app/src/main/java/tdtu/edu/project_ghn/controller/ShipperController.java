package tdtu.edu.project_ghn.controller;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Map;

import tdtu.edu.project_ghn.controller.service.OnAddReceivedOrderListener;
import tdtu.edu.project_ghn.controller.service.OnGetShipperListener;
import tdtu.edu.project_ghn.entity.Shipper;

public class ShipperController {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Map<String, Object> deliverOrderMap;
    public void getShipperByEmail(String email, OnGetShipperListener listener) {
        DocumentReference documentReference = db.collection("shippers").document(email);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Shipper result = documentSnapshot.toObject(Shipper.class);
                listener.onSuccess(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFailure(e.getMessage());
            }
        });
    }

    public void addReceivedOrderByShipperUserName(String emailDoc, Map<String, Object> orderMap, OnAddReceivedOrderListener listener) {
        db.collection("received_deliver_orders").document(emailDoc)
                .set(orderMap, SetOptions.merge())
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


}
