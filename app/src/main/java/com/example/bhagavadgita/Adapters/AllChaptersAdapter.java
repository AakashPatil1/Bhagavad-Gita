package com.example.bhagavadgita.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bhagavadgita.Models.Chapters;
import com.example.bhagavadgita.R;
import com.example.bhagavadgita.SingleChapterActivity;

import java.util.List;

public class AllChaptersAdapter extends RecyclerView.Adapter<AllChaptersAdapter.ViewHolder> {

    List<Chapters> list;
    Context cContext;

    public AllChaptersAdapter(List<Chapters> list, Context cContext) {
        this.list = list;
        this.cContext = cContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chapters, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(list.get(position).getName());
        holder.tvNameTranslated.setText(list.get(position).getNameTranslated());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cContext, SingleChapterActivity.class);
                intent.putExtra("chapterNumber", list.get(position).getChapterNumber());
                cContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvNameTranslated;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNameTranslated = itemView.findViewById(R.id.tvNameTranslated);
        }
    }
}
