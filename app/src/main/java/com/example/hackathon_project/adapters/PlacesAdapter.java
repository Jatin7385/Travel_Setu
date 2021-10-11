package com.example.hackathon_project.adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hackathon_project.MapsActivity;
import com.example.hackathon_project.R;
import com.example.hackathon_project.models.PlaceModel;
import com.google.firebase.database.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<ViewHolderPlaces> {
    @NonNull
    List<PlaceModel> list;
    Context context;

    public PlacesAdapter(@NotNull Context context, List<PlaceModel> list) {
        this.list = list;
        this.context = context;
    }

    public ViewHolderPlaces onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.explore_cardview,parent,false);
        return new ViewHolderPlaces(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderPlaces holder, int position) {
        holder.text.setText(list.get(position).getName().toString().trim());

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("PlaceModel",list.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("Size : " + list.size());
        return list.size();
    }
}

class ViewHolderPlaces extends RecyclerView.ViewHolder {
    TextView text;
    Button search;
    CardView card;

    public ViewHolderPlaces(@NonNull @NotNull View itemView) {
        super(itemView);
        card = itemView.findViewById(R.id.explore_card);
        text = itemView.findViewById(R.id.explore_textofPlace);
        search = itemView.findViewById(R.id.explore_search_cv);
    }
}


