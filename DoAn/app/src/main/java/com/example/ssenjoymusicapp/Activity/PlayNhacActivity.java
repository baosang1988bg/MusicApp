package com.example.ssenjoymusicapp.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssenjoymusicapp.Adapter.ViewPagerPlaylistNhac;
import com.example.ssenjoymusicapp.Fragment.Fragment_DiaNhac;
import com.example.ssenjoymusicapp.Fragment.Fragment_PlayDanhSachBaiHat;
import com.example.ssenjoymusicapp.Model.Baihat;
import com.example.ssenjoymusicapp.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    private static final String TAG = "TAG";
    Toolbar toolbarPLayNhac;
    TextView txtTimeSong, txtTotalTimeSong,txtindex,txtTenCaSi,txtTenBaiHat;
    SeekBar skTime;
    ImageButton imgPlay,imgPre, imgNext,imgRandom,imgRepeat;
    ViewPager viewPagerPlayNhac;

    public static ArrayList<Baihat> mangBaihat = new ArrayList<>();

    public static ViewPagerPlaylistNhac adapapternhac;

    Fragment_DiaNhac fragmentDiaNhac;
    Fragment_PlayDanhSachBaiHat fragmentPlayDanhSachBaiHat;
    MediaPlayer mediaPlayer;

    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        //Dùng để kiểm tra tín hiệu mạng.
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataIntent();
        init();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapapternhac.getItem(1) != null) {
                    if(mangBaihat.size() > 0){
                        fragmentDiaNhac.PLayNhac(mangBaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this,500);
                    }
                }
            }
        },1000);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                }else
                {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(repeat == false){
                   if(checkrandom == true){
                       checkrandom = false;
                       imgRepeat.setImageResource(R.drawable.iconsyned);
                       imgRandom.setImageResource(R.drawable.iconsuffle);
                   }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }
                else
                {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }
                else
                {
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        skTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangBaihat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < mangBaihat.size())
                    {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if(repeat == true)
                        {
                            if(position == 0)
                            {
                                position = mangBaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(mangBaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        if(position > (mangBaihat.size() - 1))
                        {
                            position = 0;
                        }
                        new PlayMp3().execute(mangBaihat.get(position).getLinkBaiHat());
                        fragmentDiaNhac.PLayNhac(mangBaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                 @Override
                 public void run() {
                     imgPre.setClickable(true);
                     imgNext.setClickable(true);
                    }
                },5000);
            }
        });
        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mangBaihat.size() > 0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null)
                    {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(position < mangBaihat.size())
                    {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if(position < 0 )
                        {
                            position = mangBaihat.size();
                        }
                        if(repeat == true)
                        {
                            position += 1;
                        }
                        if (checkrandom == true)
                        {
                            Random random = new Random();
                            int index = random.nextInt(mangBaihat.size());
                            if(index == position){
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMp3().execute(mangBaihat.get(position).getLinkBaiHat());
                        fragmentDiaNhac.PLayNhac(mangBaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(mangBaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void GetDataIntent() {
        Intent intent = getIntent();
        mangBaihat.clear();
        if(intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangBaihat.add(baihat);
            }
            if (intent.hasExtra("tatcacakhuc")) {
                ArrayList<Baihat> mangBH = intent.getParcelableArrayListExtra("tatcacakhuc");
                mangBaihat = mangBH;
            }
        }
    }

    private void init() {
        toolbarPLayNhac = findViewById(R.id.toolbarPlayNhac);
        txtTimeSong = findViewById(R.id.textviewTimeSong);
        txtTotalTimeSong = findViewById(R.id.textvietTotalTimeSong);
        skTime = findViewById(R.id.seebarSong);
        imgRandom = findViewById(R.id.imagebuttonsuffle);
        imgPlay = findViewById(R.id.imagebuttonplay);
        imgPre = findViewById(R.id.imagebuttonpreview);
        imgNext = findViewById(R.id.imagebuttonnext);
        imgRepeat = findViewById(R.id.imagebuttonrepeat);
        viewPagerPlayNhac = findViewById(R.id.viewpagerPlayNhac);
        txtindex = findViewById(R.id.textviewplaynhacindex);
        txtTenBaiHat = findViewById(R.id.textviewplaynhactenbaihat);
        txtTenCaSi = findViewById(R.id.textviewplaynhactencasi);
        setSupportActionBar(toolbarPLayNhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPLayNhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangBaihat.clear();
            }
        });
        toolbarPLayNhac.setTitleTextColor(Color.WHITE);
        fragmentDiaNhac = new Fragment_DiaNhac();
        fragmentPlayDanhSachBaiHat = new Fragment_PlayDanhSachBaiHat();
        adapapternhac = new ViewPagerPlaylistNhac(getSupportFragmentManager());
        adapapternhac.AddFragment(fragmentPlayDanhSachBaiHat);
        adapapternhac.AddFragment(fragmentDiaNhac);
        viewPagerPlayNhac.setAdapter(adapapternhac);
        fragmentDiaNhac =  (Fragment_DiaNhac) adapapternhac.getItem(1);
        if(mangBaihat.size()> 0)
        {
            getSupportActionBar().setTitle(mangBaihat.get(0).getTenBaiHat());
            new PlayMp3().execute(mangBaihat.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }

    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    @Override
                    public void onSeekComplete(MediaPlayer arg0) {
                        Log.d(TAG, "onSeekComplete() current pos : " + arg0.getCurrentPosition());
                        SystemClock.sleep(200);
                        mediaPlayer.start();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        //Định dạng cho giây vượt qua 60 thành giờ phút giây ,.....
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTimeSong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        skTime.setMax(mediaPlayer.getDuration());
    }
    private  void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    skTime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next  =  true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler2 = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if(position < mangBaihat.size())
                    {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                    if(repeat == true)
                    {
                        if(position == 0)
                        {
                            position = mangBaihat.size();
                        }
                        position -= 1;
                    }
                    if (checkrandom == true)
                    {
                        Random random = new Random();
                        int index = random.nextInt(mangBaihat.size());
                        if(index == position){
                            position = index - 1;
                        }
                        position = index;
                    }
                    if(position > (mangBaihat.size() - 1))
                    {
                        position = 0;
                    }
                    new PlayMp3().execute(mangBaihat.get(position).getLinkBaiHat());
                    fragmentDiaNhac.PLayNhac(mangBaihat.get(position).getHinhBaiHat());
                    getSupportActionBar().setTitle(mangBaihat.get(position).getTenBaiHat());
                     }
                    imgPre.setClickable(false);
                    imgNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPre.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    },5000);
                    next = false;
                    handler1.removeCallbacks(this);
                }
                else
                {
                    handler2.postDelayed(this,1000);
                }
            }
        },300);
    }
}