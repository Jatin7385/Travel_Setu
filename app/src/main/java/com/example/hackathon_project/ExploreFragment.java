package com.example.hackathon_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hackathon_project.adapters.Explore_Adapter;
import com.example.hackathon_project.models.PlaceModel;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExploreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText text;

    public ExploreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ExploreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ExploreFragment newInstance(String param1, String param2) {
        ExploreFragment fragment = new ExploreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_explore, container, false);

        Button search;
        text = view.findViewById(R.id.explore_text);
        search = view.findViewById(R.id.explore_search);


        Places.initialize(getContext(),"AIzaSyB6CJ_QKSsN3EeBtV4tm6K78l7kTXfRlqg");

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG,Place.Field.NAME);

                //Create Intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                        fieldList).build(getContext());

                startActivityForResult(intent,100);
            }
        });

        RecyclerView recyclerView;
        RecyclerView recyclerView1;
        recyclerView = view.findViewById(R.id.explore_places_recycler_view);
        recyclerView1 = view.findViewById(R.id.explore_placestovisit_recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        List<PlaceModel> list = new ArrayList<>();
        list.add(new PlaceModel("Agra", "drawable/tj.jpg", "27.1767","78.0081"));
        list.add(new PlaceModel("Delhi", "drawable/tj.jpg", "28.7041","77.1025"));
        list.add(new PlaceModel("Mumbai", "drawable/tj.jpg", "19.0760","72.8777"));
        list.add(new PlaceModel("Punjab", "drawable/tj.jpg", "31.1471", "75.3412"));
        recyclerView.setAdapter(new Explore_Adapter(getContext(), list));


        List<PlaceModel> list1 = new ArrayList<>();
        list1.add(new PlaceModel("Taj Mahal", "drawable/tj.jpg", "27.1751","78.0421"));
        list1.add(new PlaceModel("Golden Temple", "drawable/tj.jpg", "31.6200","74.8765"));
        list1.add(new PlaceModel("Red Fort", "drawable/tj.jpg", "28.6562","77.2410"));
        list1.add(new PlaceModel("Amer Fort", "drawable/tj.jpg", "26.9855","75.8513"));
        recyclerView1.setAdapter(new Explore_Adapter(getContext(), list1));

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == AutocompleteActivity.RESULT_OK)
        {
            Place place = Autocomplete.getPlaceFromIntent(data);

            text.setText(place.getName());
        }
        else if(resultCode == AutocompleteActivity.RESULT_ERROR)
        {
            Status status = Autocomplete.getStatusFromIntent(data);

            Toast.makeText(getContext(),status.getStatusMessage(),Toast.LENGTH_LONG).show();
        }
    }
}