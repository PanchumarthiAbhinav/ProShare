package com.example.proshare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class projectsdisplaycategory extends AppCompatActivity {

    MyAdapter adapter;
    RecyclerView recview;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectsdisplaycategory);

        recview = (RecyclerView) findViewById(R.id.displayprojectsrec);
        searchView = (SearchView) findViewById(R.id.titlesearchview);

        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        Intent intent = getIntent();
        String Category = intent.getStringExtra("Category");

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Projects").orderByChild("category").startAt(Category).endAt(Category+"\uf8ff"), model.class)
                        .build();

        adapter = new MyAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processdata(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processdata(newText);
                return false;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public void processdata(String title){
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("Projects").orderByChild("title").startAt(title).endAt(title+"\uf8ff"), model.class)
                        .build();

        adapter = new MyAdapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }


}