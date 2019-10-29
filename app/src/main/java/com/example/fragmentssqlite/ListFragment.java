package com.example.fragmentssqlite;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragmentssqlite.Database.DatabaseHelper;
import com.example.fragmentssqlite.Database.Model.Hobby;

import java.util.List;

public class ListFragment extends Fragment {

    public static final String TAG = "LIST_FRAG";


    private View view;
    private RecyclerView myRecyclerView;
    private List<Hobby> hobbies;
    private Context context;
    private DatabaseHelper db;
    private HobbiesAdapter myAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hobby_list,container,false);
        myRecyclerView = view.findViewById(R.id.my_recycler_view);
        db = new DatabaseHelper(getContext());
        context = getContext();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hobbies = db.getAllHobbies();
        myRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        myAdapter = new HobbiesAdapter(context, hobbies);
        myRecyclerView.setAdapter(myAdapter);
    }
}
