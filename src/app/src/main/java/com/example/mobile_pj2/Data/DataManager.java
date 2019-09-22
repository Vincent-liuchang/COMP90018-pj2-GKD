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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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


public class DataManager {

    String TAG = getClass().getName();

    private FirebaseFirestore db;
    private CollectionReference buildingsRef;


    public  DataManager(){
        // Access a Cloud Firestore instance from your Activity
        this.db = FirebaseFirestore.getInstance();
        this.buildingsRef = db.collection("buildings");
    }


    public void ListenPeopleInside(final Building building, final FireBaseUpdateCallback callback){

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

}
