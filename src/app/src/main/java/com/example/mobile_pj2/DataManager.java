package com.example.mobile_pj2;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private FirebaseFirestore db;
    private CollectionReference buildingsRef;


    public  DataManager(){
        // Access a Cloud Firestore instance from your Activity
        this.db = FirebaseFirestore.getInstance();
        this.buildingsRef = db.collection("buildings");
    }



    protected void ListenPeopleInside(final Building building, final FireBaseUpdateCallback callback){

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
                        System.out.println("Status Now: " + building.getPeopleInside());
                    }
                });
    }

}
