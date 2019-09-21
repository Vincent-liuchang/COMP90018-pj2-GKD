/*
 * Connect with Database
 *
 */

package com.example.mobile_pj2.Data;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.mobile_pj2.Data.Model.Building;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class DataManager {

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

}
