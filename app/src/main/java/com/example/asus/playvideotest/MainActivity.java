package com.example.asus.playvideotest;

import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private VideoView videoView;
    private Button play;
    private  Button pause;
    private  Button replay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(Button)findViewById(R.id.play);
        pause=(Button)findViewById(R.id.pause);
        replay=(Button)findViewById(R.id.replay);
       videoView=(VideoView)findViewById(R.id.video_view);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        replay.setOnClickListener(this);
        initVideoPath();

    }
    private void initVideoPath(){
        File file =new File(Environment.getExternalStorageDirectory(),"mv.mp4");
        String externalStorageState = Environment.getExternalStorageState();
        Log.d("mediaactivity",externalStorageState);
        Toast.makeText(MainActivity.this,externalStorageState,Toast.LENGTH_SHORT);
        videoView.setVideoPath(file.getPath());
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.play:
                if(!videoView.isPlaying())
                {
                    Log.d("mediaactivity","play");
                    videoView.start();
                }
                break;
            case R.id.pause:
                if(videoView.isPlaying())
                {
                    Log.d("mediaactivity","pause");
                    videoView.pause();
                }
                break;
            case R.id.replay:
                if(videoView.isPlaying())
                {
                    Log.d("mediaactivity","replay");
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        if(videoView!=null)
        {
            videoView.suspend();
        }
    }
}
