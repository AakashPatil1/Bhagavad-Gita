package com.example.bhagavadgita.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bhagavadgita.Models.Translation;
import com.example.bhagavadgita.R;

import java.util.ArrayList;

public class TranslationAdapter extends RecyclerView.Adapter<TranslationAdapter.ViewHolder> {

    ArrayList<Translation> translations;

    Context context;



    public TranslationAdapter(ArrayList<Translation> translations, Context context) {
        this.translations = translations;
        this.context = context;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_single_verses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.tvDesc.setText(translations.get(position).getDescription());
            holder.tvLanguage.setText(translations.get(position).getLanguage().toUpperCase());
            holder.tvAuthor.setText(translations.get(position).getAuthor_name());

    }

    @Override
    public int getItemCount() {
            return translations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDesc;
        TextView tvAuthor;
        TextView tvLanguage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDesc = itemView.findViewById(R.id.tvDesc);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvLanguage = itemView.findViewById(R.id.tvLanguage);
        }
    }
}
