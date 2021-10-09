package com.example.hackathon_project.adapters;


public class NearbyAdapter extends RecyclerView.Adapter<NearbyAdapter.RecyclerViewHolder>{

    List<PlaceModel> list;
    Context context;

    public NearbyAdapter(List<PlaceModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.nearby_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{

        public RecyclerViewHolder(@NonNull View view) {
            super(view);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        PlaceModel place = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateAdapter(List<PlaceModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}