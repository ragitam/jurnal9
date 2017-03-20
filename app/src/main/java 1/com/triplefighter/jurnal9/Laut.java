package com.triplefighter.jurnal9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Laut extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Hewan> nomor = extractBinatang();

        ListView daftarNomor = (ListView) findViewById(R.id.detail);
        AdapterUtkHewan adapter = new AdapterUtkHewan(this, nomor, R.color.category_laut);
        daftarNomor.setAdapter(adapter);

        daftarNomor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                releaseMediaPlayer();

                mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                int hasil = mAudioManager.requestAudioFocus(afChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (hasil == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(Laut.this,
                            nomor.get(position).getAudioResource());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(onCompletionListener);
                }
                Log.i("NumbersActivity", nomor.get(position). toString());

            }
        });
    }
    private void releaseMediaPlayer() {

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(afChangeListener);
        }
    }

    private MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    private AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };

    private int getImageResourceId(String nama){
        nama = nama.toLowerCase().replace(" ","_");
        return getResources().getIdentifier(nama, "drawable", getPackageName());
    }

    private int getAudioResourceId(String nama){
        nama = nama.toLowerCase().replace(" ","_");
        return getResources().getIdentifier(nama, "raw", getPackageName());
    }

    public ArrayList<Hewan> extractBinatang() {

        ArrayList<Hewan> binatang = new ArrayList<>();

        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(json);

            JSONArray earthquakeArray = baseJsonResponse.getJSONArray("laut");

            for (int i = 0; i < earthquakeArray.length(); i++) {
                JSONObject currentEarthquake = earthquakeArray.getJSONObject(i);
                String namaHewan = currentEarthquake.getString("nama");
                String namaLatin = currentEarthquake.getString("latin");
                int gambar = getImageResourceId(currentEarthquake.getString("nama"));
                int audio = getAudioResourceId(currentEarthquake.getString("nama"));
                Hewan earthquake = new Hewan(namaHewan, namaLatin,gambar,audio);
                binatang.add(earthquake);
            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return binatang;
    }
}
