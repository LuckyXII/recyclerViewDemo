package com.example.omnip.miwokclear;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class colorActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RecycleViewAdapter mAdapter;

    String [] colors = {"red","green","brown","gray","black","white","dusty yellow","mustard yellow"};
    String [] miwokColors = {"weṭeṭṭi","chokokki","ṭakaakki","ṭopoppi","kululli","kelelli","ṭopiisә","chiwiiṭә"};
    int [] iconIDs = {R.drawable.color_red,R.drawable.color_green,R.drawable.color_brown,R.drawable.color_gray,
            R.drawable.color_black,R.drawable.color_white,R.drawable.color_dusty_yellow,R.drawable.color_mustard_yellow};
    int [] soundIDs = {R.raw.color_red,R.raw.color_green,R.raw.color_brown,R.raw.color_gray,R.raw.color_black,
            R.raw.color_white,R.raw.color_dusty_yellow,R.raw.color_mustard_yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
        mRecyclerView = (RecyclerView) findViewById(R.id.listActivityView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        Drawable divider = getDrawable(R.drawable.separator);
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(divider);
        mRecyclerView.addItemDecoration(dividerDecoration);

        ArrayList<Words> colorList = new ArrayList<>();
        for (int i = 0; i < colors.length; i++) {
            colorList.add( new Words(miwokColors[i], colors[i], iconIDs[i], soundIDs[i]) );
        }
        mAdapter = new RecycleViewAdapter(colorList, R.color.colorCategory, colorActivity.this);
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
