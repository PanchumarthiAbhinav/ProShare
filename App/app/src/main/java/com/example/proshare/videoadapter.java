package com.example.proshare;

import android.content.Intent;
import android.media.MediaPlayer;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class videoadapter extends FirebaseRecyclerAdapter<videomodel, videoadapter.myviewholder> {
    int count = 0, flag = 0, count1 = 0;

    public videoadapter(@NonNull FirebaseRecyclerOptions<videomodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull videomodel model) {
        holder.setdata(model);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlereeldesign, parent, false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        VideoView reel;
        TextView title;
        ProgressBar progressBar;
        ImageView like,dislike;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            reel = (VideoView) itemView.findViewById(R.id.reelplay);
            title = (TextView) itemView.findViewById(R.id.reeltitleplay);
            progressBar = (ProgressBar) itemView.findViewById(R.id.reelprogressbar);
            like = (ImageView) itemView.findViewById(R.id.Like);
            dislike = (ImageView) itemView.findViewById(R.id.dislike);
        }

        void setdata(videomodel obj){
            reel.setVideoPath(obj.getReelurl());
            title.setText(obj.getTitle());

            reel.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();
                }
            });
            reel.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });


            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(count==0 && flag==0) {
                        like.setImageResource(R.drawable.likeclicked);
                        count = 1;
                    }
                    else if(flag==0&&count==1){
                        like.setImageResource(R.drawable.like);
                        count = 0;
                        flag = 1;
                    }

                }
            });

            dislike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(flag==1&&count1==0){
                        dislike.setImageResource(R.drawable.thumbsdownclicked);
                        count1=1;
                    }
                    else if(flag==1&&count1==1){
                        dislike.setImageResource(R.drawable.thunbdown);
                        flag = 0;
                        count1=0;
                    }
                }
            });
        }
    }
}
