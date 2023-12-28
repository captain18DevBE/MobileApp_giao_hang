package tdtu.edu.project_ghn.controller;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.net.Uri;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

import tdtu.edu.project_ghn.controller.service.OnGetCustomerByEmail;
import tdtu.edu.project_ghn.entity.Customer;
import tdtu.edu.project_ghn.view.activity.CustomerProfileActivity;

public class CustomerController {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();

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
        String imgLocalPath = resp.getImgPath();
        if (!imgLocalPath.isEmpty()) {
            Uri imgFile = Uri.fromFile(new File(resp.getImgPath()));
            StorageReference userImageProfile = storageRef.child("images/avatar_users/"+imgFile.getLastPathSegment());
            resp.setImgPath(imgFile.getLastPathSegment());
            UploadTask uploadTask = userImageProfile.putFile(imgFile);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("firebase", "upload image is failed");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("firebase", "upload image is success");
                }
            });

        }

        documentReference
                .update(
                    "fullName", resp.getFullName(),
                        "address", resp.getAddress(),
                        "phoneNumber", resp.getPhoneNumber(),
                        "imgPath", resp.getImgPath()
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
