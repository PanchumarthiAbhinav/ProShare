package com.example.proshare;

import android.media.Image;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.w3c.dom.Text;

public class MyAdapter extends FirebaseRecyclerAdapter<model, MyAdapter.myviewholder> {


    public MyAdapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model Model) {

        holder.title.setText(Model.getTitle());
        holder.category.setText(Model.getCategory());
        Glide.with(holder.icon.getContext()).load(Model.getImageurl()).into(holder.icon);

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.body_container,new descriptionfragment(Model.getTitle(),Model.getCategory(),Model.getImageurl(),Model.getDescription())).addToBackStack(null).commit();

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);

        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title, category, description;
        public myviewholder(@NonNull View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.img1);
            title = (TextView) itemView.findViewById(R.id.rec_title);
            category = (TextView) itemView.findViewById(R.id.rec_category);
        }
    }
}
