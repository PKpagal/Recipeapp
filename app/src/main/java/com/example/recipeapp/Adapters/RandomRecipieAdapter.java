package com.example.recipeapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipeapp.Models.Recipe;
import com.example.recipeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipieAdapter extends RecyclerView.Adapter<RandomRecipieViewHolder> {
    Context context;
    List<Recipe> list;

    public RandomRecipieAdapter(List<Recipe> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RandomRecipieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_random_recipie, parent, false);
        return new RandomRecipieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipieViewHolder holder, int position) {
        Recipe recipe = list.get(position);

        holder.textview_title.setText(recipe.title);
        holder.textview_servings.setText(recipe.servings + " Servings");
        holder.textview_likes.setText("Likes: " + recipe.aggregateLikes);
        holder.textview_time.setText(recipe.readyInMinutes + " mins");
        Picasso.get().load(recipe.image).into(holder.imageview_food);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // Method to add new recipes to the existing list (for pagination)
    public void addRecipes(List<Recipe> newRecipes) {
        list.addAll(newRecipes);
        notifyDataSetChanged();
    }
}

class RandomRecipieViewHolder extends RecyclerView.ViewHolder {
    CardView random_list_container;
    TextView textview_title, textview_servings, textview_likes, textview_time;
    ImageView imageview_food;

    public RandomRecipieViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textview_title = itemView.findViewById(R.id.textview_title);
        textview_servings = itemView.findViewById(R.id.textview_servings);
        textview_likes = itemView.findViewById(R.id.textview_likes);
        textview_time = itemView.findViewById(R.id.textview_time);
        imageview_food = itemView.findViewById(R.id.imageview_food);
    }
}
