package com.inti.student.myfitness.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.inti.student.myfitness.Interface.ItemClickListener;
import com.inti.student.myfitness.Model.Exercise;
import com.inti.student.myfitness.R;
import com.inti.student.myfitness.ViewExercise;

import java.util.List;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public ImageView image;
    public TextView text;

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.ex_img);
        text = (TextView) itemView.findViewById(R.id.ex_name);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition());
    }
}
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

    private List<Exercise> exerciseList;
    private Context contect;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context contect) {
        this.exerciseList = exerciseList;
        this.contect = contect;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_exercise,parent,false);

        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( RecyclerViewHolder recyclerViewHolder, int position) {

        recyclerViewHolder.image.setImageResource(exerciseList.get(position).getImage_id());
        recyclerViewHolder.text.setText(exerciseList.get(position).getName());

        recyclerViewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //Call to new Activity
                Intent intent = new Intent(contect, ViewExercise.class);
                intent.putExtra("image_id",exerciseList.get(position).getImage_id());
                intent.putExtra("name",exerciseList.get(position).getName());
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                contect.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
