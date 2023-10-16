package com.example.bhagavadgita.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bhagavadgita.Models.Verses;
import com.example.bhagavadgita.R;
import com.example.bhagavadgita.SingleVersesActivity;

import java.util.List;

public class AllVersesAdapter extends RecyclerView.Adapter<AllVersesAdapter.ViewHolder> {


    List<Verses> list;

    Context vContext;

    public AllVersesAdapter(List<Verses> list, Context vContext) {
        this.list = list;
        this.vContext = vContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_verses,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvText.setText(list.get(position).getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vContext, SingleVersesActivity.class);
                intent.putExtra("chapterNumber",list.get(position).getChapter_number());
                intent.putExtra("versesNumber",list.get(position).getVerse_number());
                vContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvText = itemView.findViewById(R.id.tvText);
        }
    }
}
