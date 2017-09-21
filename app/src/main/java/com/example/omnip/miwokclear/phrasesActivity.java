package com.example.omnip.miwokclear;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class phrasesActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RecycleViewAdapter mAdapter;

    String [] phrases = {"Where are you going?",
            "What is your name?",
            "My name is...","How are you feeling?","I’m feeling good.","Are you coming?","Yes, I’m coming.",
            "I’m coming.","Let’s go.","Come here."};
    String [] miwokPhrases = {"minto wuksus","tinnә oyaase'nә"," oyaaset...", "michәksәs?", "kuchi achit",
            "әәnәs'aa?", "hәә’ әәnәm"," әәnәm"," yoowutis"," әnni'nem"};
    int [] soundIDs = {R.raw.phrase_where_are_you_going,R.raw.phrase_what_is_your_name,R.raw.phrase_my_name_is
            ,R.raw.phrase_how_are_you_feeling,R.raw.phrase_im_feeling_good,R.raw.phrase_are_you_coming
            ,R.raw.phrase_yes_im_coming,R.raw.phrase_im_coming,R.raw.phrase_lets_go,R.raw.phrase_come_here};

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

        ArrayList<Words> phrasesList = new ArrayList<>();
        for (int i = 0; i < phrases.length; i++) {
            phrasesList.add( new Words(miwokPhrases[i], phrases[i],-1, soundIDs[i]) );
        }
        mAdapter = new RecycleViewAdapter(phrasesList, R.color.phrasesCategory,phrasesActivity.this);
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
