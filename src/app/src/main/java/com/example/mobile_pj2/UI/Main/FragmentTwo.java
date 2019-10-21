package com.example.mobile_pj2.UI.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import com.example.mobile_pj2.R;
import com.example.mobile_pj2.UI.LibraryInfo.LibInfoActivity;

public class FragmentTwo extends Fragment {

    private BuildingAdapter buildingAdapter;
    private ListView list_main = null;
    private Context context;

    public FragmentTwo(BuildingAdapter buildingAdapter, Context context) {
        this.context = context;
        this.buildingAdapter = buildingAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two,container,false);
        list_main = view.findViewById(R.id.list_main);
        list_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), LibInfoActivity.class);
                String message = buildingAdapter.getItem(i);
                intent.putExtra("buildingName", message);
                startActivity(intent);
            }
        });
        list_main.setAdapter(buildingAdapter);
        return view;
    }
}