package com.sparsh.trackichallengescreen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // ArrayList for person names
    ArrayList<Model> personInfo;

    CustomAdapter customAdapter;
    String winner;
    int winnerStepsInt;
    int posWinner;
    int posCreator;
    int noOfParticipants;
    TextView tvWinner;
    TextView tvSteps;
    TextView tvCreator;
    TextView tvPos;
    TextView tvTitle;
    TextView tvBar;
    ImageView ivImage;
    String winnerImgUrl;
    String winnerStepsStr;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWinner = findViewById(R.id.tvWinner);
        tvSteps = findViewById(R.id.tvSteps);
        ivImage = findViewById(R.id.ivImage);
        tvCreator = findViewById(R.id.tvCreator);
        tvPos = findViewById(R.id.tvPos);
        tvTitle = findViewById(R.id.tvTitle);
        tvBar = findViewById(R.id.tvBar);
        personInfo = new ArrayList<>();

        getData();



        actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));



        tvPos.bringToFront();
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        customAdapter= new CustomAdapter(MainActivity.this, personInfo);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView


    }

    public void getData()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);

        Call<ResponseModel> call = api.getData();

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                List<Model> modelList = response.body().getParticipant();
                winnerStepsInt = 0;


                for (int i=0; i<modelList.size(); i++) {
                    personInfo.add(modelList.get(i));

                    if (Integer.parseInt(modelList.get(i).getSteps())>winnerStepsInt)
                    {
                        winnerStepsInt = Integer.parseInt(modelList.get(i).getSteps());
                        winner = modelList.get(i).getName();
                        winnerStepsStr = modelList.get(i).getSteps();
                        winnerImgUrl = modelList.get(i).getImage();
                        posWinner = i;

                    }

                    tvWinner.setText(winner);
                    tvSteps.setText(winnerStepsStr + " Steps");

                    Picasso.get().load(winnerImgUrl).into(ivImage);


                    if (modelList.get(i).getCreator().equalsIgnoreCase("yes"))
                    {
                        posCreator = i;
                    }

                    if (posWinner == posCreator)
                    {
                        tvCreator.setVisibility(View.VISIBLE);

                        posCreator=-1;
                    }

                     else  if (posWinner>posCreator)
                    {
                        posCreator--;
                    }




                }

                noOfParticipants = personInfo.size();


                ViewGroup.LayoutParams params = (ViewGroup.LayoutParams) tvBar.getLayoutParams();
                params.width = 550;
                tvBar.setLayoutParams(params);

                actionBar.setTitle("Fitness Challenge" + "\t" +"(" + noOfParticipants + " Participants)");


                personInfo.remove(posWinner);

                Collections.sort(personInfo, new Comparator<Model>()
                {
                    public int compare(Model m1,Model m2){
                        if(m1.getSteps()==m2.getSteps())
                            return 0;
                        else if(Integer.parseInt(m1.getSteps())<Integer.parseInt(m2.getSteps()))
                            return 1;
                        else
                            return -1;
                    }
                });
                customAdapter.setPersonInfo(personInfo);
                customAdapter.setPosCreator(posCreator);
                customAdapter.setWinnerStepsInt(winnerStepsInt);

             }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

            }
        });
    }

    }

