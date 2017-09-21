package com.example.omnip.miwokclear;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class familyActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RecycleViewAdapter mAdapter;
    AudioManager mAudioManager;

    String [] familyMembers = {"father","mother","son","daughter","older brother","younger brother",
            "older sister", "younger sister", "grandmother", "grandfather"};
    String [] miwokMembers = {"әpә","әṭa","angsi","tune","taachi","chalitti","teṭe","kolliti","ama","paapa"};
    int [] iconIDs = {R.drawable.family_father,R.drawable.family_mother,R.drawable.family_son,R.drawable.family_daughter,
            R.drawable.family_older_brother,R.drawable.family_younger_brother,R.drawable.family_older_sister,
            R.drawable.family_younger_sister,R.drawable.family_grandmother,R.drawable.family_grandfather};
    int [] soundIDs = {R.raw.family_father,R.raw.family_mother,R.raw.family_son,R.raw.family_daughter,R.raw.family_older_brother,
            R.raw.family_younger_brother,R.raw.family_older_sister,R.raw.family_younger_sister,R.raw.family_grandmother,R.raw.family_grandfather};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist); //set view to a recyclerView xml



        //create a recyclerView using listLayout
        mRecyclerView = (RecyclerView) findViewById(R.id.listActivityView);
        //set layoutManager
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        //create a dividing line between rows
        Drawable divider = getDrawable(R.drawable.separator);
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(divider);
        mRecyclerView.addItemDecoration(dividerDecoration);

        //add new Words to list
        ArrayList<Words> familyList = new ArrayList<>();
        for (int i = 0; i < familyMembers.length; i++) {
            familyList.add( new Words(miwokMembers[i], familyMembers[i], iconIDs[i],soundIDs[i]) );
        }
        //create a new recycleViewAdapter and set to recyclerView
        mAdapter = new RecycleViewAdapter(familyList, R.color.familyCategory, familyActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mAdapter.mediaPlayer != null) {
            mAdapter.mediaPlayer.release();
            mAdapter.mediaPlayer = null;
            mAdapter.mAudioManager.abandonAudioFocus(mAdapter.focusChangeListener);
        }

    }
}
