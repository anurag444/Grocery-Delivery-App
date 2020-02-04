package com.anurag.grocerydelivery;

//RecyclerView.Adapter
//RecyclerView.ViewHolder

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>{

    private Context mctx;
    private List<Grocery> groceryList;
    private GroceryAdapter.OnItemClickListener onItemClickListener;

    public GroceryAdapter(Context mctx, List<Grocery> groceryList) {
        this.mctx = mctx;
        this.groceryList = groceryList;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(GroceryAdapter.OnItemClickListener listener){
        onItemClickListener=listener;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mctx);
        View view=inflater.inflate(R.layout.list_layout,null);
        GroceryViewHolder holder= new GroceryViewHolder(view,onItemClickListener );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int position) {

        Grocery grocery=groceryList.get(position);
        holder.textViewTitle.setText(grocery.getTitle());
        holder.textViewQuuatity.setText(grocery.getQuantity());
        holder.textViewPrice.setText(grocery.getPrice());
        holder.imageView.setImageDrawable(mctx.getResources().getDrawable(grocery.getImage()));


    }

    @Override
    public int getItemCount() {
        return groceryList.size();
    }

    class GroceryViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle, textViewQuuatity, textViewPrice;

        public GroceryViewHolder(@NonNull View itemView,final OnItemClickListener listener) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textViewPrice= itemView.findViewById(R.id.textViewPrice);
            textViewQuuatity=itemView.findViewById(R.id.textViewQuantity);
            textViewTitle=itemView.findViewById(R.id.textViewTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}