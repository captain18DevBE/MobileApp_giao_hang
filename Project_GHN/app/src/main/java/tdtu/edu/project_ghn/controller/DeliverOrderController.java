package tdtu.edu.project_ghn.controller;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import tdtu.edu.project_ghn.controller.service.OnAddDeliverOrderListener;
import tdtu.edu.project_ghn.entity.DeliverOrder;

public class DeliverOrderController {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

}
