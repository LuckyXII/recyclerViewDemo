package com.example.omnip.miwokclear;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

/**
 * Created by omnip on 28/08/2017.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.WordsHolder> {

    ArrayList<Words> mWordList;
    int colorID;
    public MediaPlayer mediaPlayer;
    AudioManager mAudioManager;
    Context mContext;
    AudioManager.OnAudioFocusChangeListener focusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch(focusChange){
                case(AUDIOFOCUS_LOSS):
                    mediaPlayer.release();
                    mediaPlayer = null;
                    break;
                case(AUDIOFOCUS_LOSS_TRANSIENT):
                case(AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK):
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case(AUDIOFOCUS_GAIN):
                    mediaPlayer.start();
                default:
                    break;

            }
        }
    };

    MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mp.release();
            mediaPlayer = null;
            mAudioManager.abandonAudioFocus(focusChangeListener);
        }
    };

    //constructor
    public RecycleViewAdapter(ArrayList<Words> words, int color, Context context){
        mWordList = words;
        colorID = color;
        mContext = context;
    }

    //inflate View
    @Override
    public WordsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_layout,parent,false);
        inflatedView.setBackgroundResource(colorID);
        return new WordsHolder(inflatedView);
    }

    //create row items
    @Override
    public void onBindViewHolder(final WordsHolder holder, int position) {
        final Words word = mWordList.get(position);

        if(word.getWordIconSource() == -1) {
            holder.mIcon.setVisibility(View.GONE);
        }else{
            holder.mIcon.setVisibility(View.VISIBLE);
            holder.mIcon.setImageResource(word.getWordIconSource());
        }
        holder.mMiwokText.setText(word.getMiwok());
        holder.mEnglishText.setText(word.getEnglish());
        holder.row.setBackgroundResource(colorID);

        holder.playBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                //initialize audioManager
                mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);


                int result = mAudioManager.requestAudioFocus(focusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request absolute temporary focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(holder.itemView.getContext(), word.getAudioSource());
                    if(mediaPlayer != null) {
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    //create view Holder
    public static class WordsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView mIcon;
        TextView mMiwokText;
        TextView mEnglishText;
        ImageView playBtn;
        LinearLayout row;


        public WordsHolder(View v){
            super(v);
            row = (LinearLayout) v.findViewById(R.id.wordRow);
            mIcon = (ImageView) v.findViewById(R.id.listIcon);
            mMiwokText = (TextView) v.findViewById(R.id.miwokText);
            mEnglishText = (TextView) v.findViewById(R.id.englishText);
            playBtn = (ImageView) v.findViewById(R.id.playBtn);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }

    }
}
