package com.sparsh.trackichallengescreen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    ArrayList<Model> personInfo;
    Context context;
    int posCreator, winnerStepsInt;



    public CustomAdapter(Context context, ArrayList personInfo) {
        this.context = context;
        this.personInfo = personInfo;
    }

    public void setPersonInfo(ArrayList<Model> personInfo) {
        this.personInfo = personInfo;
        notifyDataSetChanged();
    }

    public  void setPosCreator(int posCreator)
    {
        this.posCreator=posCreator;

    }

    public void setWinnerStepsInt(int steps)
    {
        this.winnerStepsInt = steps;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        int pos = position;
        // set the data in items
       if (position != posCreator)
       {
           holder.creatorTv.setVisibility(View.GONE);
       }

        holder.nameTv.setText(personInfo.get(position).getName());
        holder.stepsTv.setText(personInfo.get(position).getSteps() + " Steps");
        holder.posTv.setText(Integer.toString(pos+2));
        holder.posTv.bringToFront();
        Picasso.get().load(personInfo.get(position).getImage()).into(holder.imageIv);

        int stepsInt;
        stepsInt = Integer.parseInt(personInfo.get(position).getSteps());
        float fractionBarWidth = (float)stepsInt/(float)winnerStepsInt;

        ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) holder.barTv.getLayoutParams();
        params.width = Math.round(fractionBarWidth*650);
        holder.barTv.setLayoutParams(params);
     //   holder.barTv.setText(personInfo.get(position).getSteps() + " Steps");




        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                Toast.makeText(context, personInfo.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return personInfo.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;// init the item view's
        TextView stepsTv;
        TextView creatorTv;
        ImageView imageIv;
        TextView posTv;
         TextView barTv;

        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            nameTv = (TextView) itemView.findViewById(R.id.name);
            stepsTv = itemView.findViewById(R.id.steps);
            imageIv = itemView.findViewById(R.id.image);
            creatorTv = itemView.findViewById(R.id.creator);
            posTv = itemView.findViewById(R.id.pos);
            barTv = itemView.findViewById(R.id.bar);
        }
    }
}
