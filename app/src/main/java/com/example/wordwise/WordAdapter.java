package com.example.wordwise;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewHolder> {

    Context context;
    Cursor cursor;
    DatabaseHelper databaseHelper;

    public WordAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursor = cursor;
        databaseHelper = new DatabaseHelper(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtWord, txtMeaning, txtStatus;
        Button btnLearned;

        public ViewHolder(View itemView){
            super(itemView);

            txtWord = itemView.findViewById(R.id.txtWord);
            txtMeaning = itemView.findViewById(R.id.txtMeaning);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            btnLearned = itemView.findViewById(R.id.btnLearned);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_word,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){

        if(cursor.moveToPosition(position)){

            int id = cursor.getInt(0);
            String word = cursor.getString(1);
            String meaning = cursor.getString(2);
            int learned = cursor.getInt(3);

            holder.txtWord.setText(word);
            holder.txtMeaning.setText(meaning);

            if(learned == 1){
                holder.txtStatus.setText("Status: Learned ✓");
            }
            else{
                holder.txtStatus.setText("Status: Not Learned");
            }

            holder.btnLearned.setOnClickListener(v ->{
                databaseHelper.markAsLearned(id);
            });
        }
    }

    @Override
    public int getItemCount(){
        return cursor.getCount();
    }
}