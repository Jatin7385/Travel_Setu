package com.example.hackathon_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hackathon_project.adapters.Explore_Adapter;
import com.example.hackathon_project.adapters.PlacesAdapter;
import com.example.hackathon_project.models.PlaceModel;
import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AllFragment newInstance(String param1, String param2) {
        AllFragment fragment = new AllFragment();
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
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        RecyclerView recyclerViewAll,recommended;

        recyclerViewAll = view.findViewById(R.id.recycler_view_all_places);
        recommended = view.findViewById(R.id.recycler_view_all_recommended_places);

        recyclerViewAll.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recommended.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        List<PlaceModel> list = new ArrayList<>();
        list.add(new PlaceModel("Agra", "drawable/tj.jpg", "27.1767","78.0081"));
        list.add(new PlaceModel("Delhi", "drawable/tj.jpg", "28.7041","77.1025"));
        list.add(new PlaceModel("Mumbai", "drawable/tj.jpg", "19.0760","72.8777"));
        list.add(new PlaceModel("Punjab", "drawable/tj.jpg", "31.1471", "75.3412"));
        recyclerViewAll.setAdapter(new PlacesAdapter(getContext(),list));


        List<PlaceModel> list1 = new ArrayList<>();
        list1.add(new PlaceModel("Taj Mahal", "drawable/tj.jpg", "27.1751","78.0421"));
        list1.add(new PlaceModel("Golden Temple", "drawable/tj.jpg", "31.6200","74.8765"));
        list1.add(new PlaceModel("Red Fort", "drawable/tj.jpg", "28.6562","77.2410"));
        list1.add(new PlaceModel("Amer Fort", "drawable/tj.jpg", "26.9855","75.8513"));
        recommended.setAdapter(new PlacesAdapter(getContext(), list1));

        return view;
    }
}