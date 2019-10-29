package com.example.fragmentssqlite;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentssqlite.Database.Model.Hobby;

import java.text.SimpleDateFormat;
import java.util.List;

public class HobbiesAdapter extends RecyclerView.Adapter<HobbiesAdapter.MyViewHolder> {
    private Context context;
    private List<Hobby> hobbiesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView hobby;

        public MyViewHolder(View view) {
            super(view);
            hobby = view.findViewById(R.id.tv_hobby);
        }
    }

    public HobbiesAdapter(Context context, List<Hobby> hobbiesList) {
        this.context = context;
        this.hobbiesList = hobbiesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_element, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Hobby hobby = hobbiesList.get(position);

        holder.hobby.setText(hobby.getHobby());
    }

    @Override
    public int getItemCount() {
        return hobbiesList.size();
    }
}
