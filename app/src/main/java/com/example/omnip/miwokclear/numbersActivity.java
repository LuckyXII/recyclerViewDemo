package com.example.omnip.miwokclear;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class numbersActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RecycleViewAdapter mAdapter;

    String [] numbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two", "twenty-three",
            "twenty-four", "twenty-five", "twenty-six", "twenty-seven", "twenty-eight",
            "twenty-nine", "thirty", "thirty-one", "thirty-two", "thirty-three",
            "thirty-four", "thirty-five", "thirty-six", "thirty-seven", "thirty-eight",
            "thirty-nine", "forty", "forty-one", "forty-two", "forty-three", "forty-four",
            "forty-five", "forty-six", "forty-seven", "forty-eight", "forty-nine", "fifty",
            "fifty-one", "fifty-two", "fifty-three", "fifty-four", "fifty-five", "fifty-six",
            "fifty-seven", "fifty-eight", "fifty-nine", "sixty", "sixty-one", "sixty-two",
            "sixty-three", "sixty-four", "sixty-five", "sixty-six", "sixty-seven", "sixty-eight",
            "sixty-nine", "seventy", "seventy-one", "seventy-two", "seventy-three", "seventy-four",
            "seventy-five", "seventy-six", "seventy-seven", "seventy-eight", "seventy-nine",
            "eighty", "eighty-one", "eighty-two", "eighty-three", "eighty-four", "eighty-five",
            "eighty-six", "eighty-seven", "eighty-eight", "eighty-nine", "ninety", "ninety-one",
            "ninety-two", "ninety-three", "ninety-four", "ninety-five", "ninety-six", "ninety-seven",
            "ninety-eight", "ninety-nine", "one hundred"};
    String [] miwokNumbers = {"lutti","otiiko","tolookosu","oyyisa","massokka","temmokka","kenekaku",
            "kawinta","wo’e","na’aacha"};
    int [] iconIDs = {R.drawable.number_one,R.drawable.number_two,R.drawable.number_three,
            R.drawable.number_four,R.drawable.number_five,R.drawable.number_six,R.drawable.number_seven,
            R.drawable.number_eight,R.drawable.number_nine,R.drawable.number_ten};
    int [] soundIDs = {R.raw.number_one,R.raw.number_two,R.raw.number_three,R.raw.number_four,R.raw.number_five,
            R.raw.number_six,R.raw.number_seven,R.raw.number_eight,R.raw.number_nine,R.raw.number_ten};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_wordlist);
        mRecyclerView = (RecyclerView) findViewById(R.id.listActivityView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        Drawable divider = getDrawable(R.drawable.separator);
        RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(divider);
        mRecyclerView.addItemDecoration(dividerDecoration);

        ArrayList<Words> numbersList = new ArrayList<>();
        for (int i = 0; i < miwokNumbers.length; i++) {
            if(i < 10){
                numbersList.add( new Words(miwokNumbers[i], numbers[i],iconIDs[i],soundIDs[i]) );
            }
        }
        mAdapter = new RecycleViewAdapter(numbersList, R.color.numbersCategory, numbersActivity.this);
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

