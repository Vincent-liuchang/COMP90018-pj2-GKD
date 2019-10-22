/*
 * Connect with Database
 * Make Transactions
 *
 */

package com.example.mobile_pj2.Data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobile_pj2.Data.Model.Building;
import com.example.mobile_pj2.UI.Bottole.ReceiveCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class DataManager {

    String TAG = getClass().getName();

    private FirebaseFirestore db;
    private CollectionReference buildingsRef;
    private int messageNum = 0;
    private Map messagereceive;


    public  DataManager(){
        // Access a Cloud Firestore instance from your Activity
        this.db = FirebaseFirestore.getInstance();
        this.buildingsRef = db.collection("buildings");
    }


    public void ListenPeopleInside(final Building building, final UpdateCallback callback){

        Query query = buildingsRef.whereEqualTo("BuildingName", building.getBuildingName());

        query.addSnapshotListener(new EventListener<QuerySnapshot>() {

                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            System.out.println("Listen Failed");
                            return;
                        }
                        for (QueryDocumentSnapshot doc : value) {
                            building.setPeopleInside(Integer.parseInt(doc.getData().get("PeopleInside").toString()));
                        }
                        callback.update();
                        Log.d("Status Now: ",String.valueOf(building.getPeopleInside()));
                    }
        });
    }

    public void ModifyPeopleInside(final String buildingName, final int i){
        final DocumentReference documentRef = buildingsRef.document(buildingName);

        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(documentRef);
                // Note: this could be done without a transaction
                //       by updating the population using FieldValue.increment()
                int peopleInside = Integer.parseInt(snapshot.get("PeopleInside").toString()) + i;
                transaction.update(documentRef, "PeopleInside", peopleInside);
                // Success

                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "Transaction success!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Transaction failure.", e);
                    }
                });
    }

    public void AddMessageToDatabase(final String userName, final String content, final String buildingName){
        db.collection("bottleMessage").document("beach").collection("messages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            messageNum = task.getResult().size();
                            RealUpdate(userName,content,buildingName);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void RealUpdate(final String userName, final String content, final String buildingName){
        messageNum = messageNum + 1;
        String messageName = "message"+messageNum;
        final DocumentReference documentRef = db.collection("bottleMessage").
                document("beach").collection("messages").document(messageName);
        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                DocumentSnapshot snapshot = transaction.get(documentRef);
                Map<String, Object> message = new HashMap<>();
                message.put("content", content);
                message.put("buildingName", buildingName);
                message.put("read",false);
                message.put("userName",userName);
                message.put("dateTime",new Date());
                transaction.set(documentRef,message);
                // Success
                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "BottleMessage Transaction success!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "BottleMessage Transaction failure.", e);
                    }
                });
    }

    public void GetAnUnreadBottle(final String buildingName, final ReceiveCallBack callback){
        CollectionReference collectionReference =  db.collection("bottleMessage").document("beach").collection("messages");
        Query query = collectionReference.whereEqualTo("read",false).whereEqualTo("buildingName",buildingName);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(task.getResult().size()>0) {
                        int random_index = (int) (Math.random() * task.getResult().size());
                        DocumentSnapshot collected_bottole = task.getResult().getDocuments().get(random_index);
                        messagereceive = collected_bottole.getData();
                        callback.update(messagereceive);
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }

}
