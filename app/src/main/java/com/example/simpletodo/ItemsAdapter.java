package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//responsible for taking data in a position in putting into ViewHolder
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    //ItemsAdapter constructor
    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    //responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflator to inflate view
        View toDoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside viewholder and return it
        return new ViewHolder(toDoView);
    }
    //responsible for taking data from particular position, and putting into ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item on the position
        String item = items.get(position);
        //bin item to specified viewholder
        holder.bind(item);

    }
    //tells recylcerview how many items are in list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside the view holder with the data of string item
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    //notify listener of which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
