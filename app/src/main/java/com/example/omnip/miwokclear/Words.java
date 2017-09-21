package com.example.omnip.miwokclear;

/**
 * Created by omnip on 28/08/2017.
 */

public class Words {

    private String miwok;
    private String english;
    private int wordIconId = -1;
    private int audioSource;

    public Words(String miwok, String english){
        this.miwok = miwok;
        this.english = english;
    }
    public Words(String miwok, String english, int iconId){
        this.miwok = miwok;
        this.english = english;
        this.wordIconId = iconId;
    }
    public Words(String miwok, String english, int iconId, int soundId){
        this.miwok = miwok;
        this.english = english;
        this.wordIconId = iconId;
        this.audioSource = soundId;
    }


    public int getWordIconSource() {
        return wordIconId;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiwok() {
        return miwok;
    }

    public int getAudioSource() {
        return audioSource;
    }
}
