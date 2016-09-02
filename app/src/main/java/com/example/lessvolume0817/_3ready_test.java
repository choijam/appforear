package com.example.lessvolume0817;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class _3ready_test extends Activity {
    private Typeface mTypeface;
    TextView mStatusView;
    MediaRecorder mRecorder;
    Thread runner;
    Button button;

    final Runnable updater = new Runnable() {
        public void run() {
            updateTv();
        }
    };
    final Handler mHandler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._3ready_test);
        mStatusView = (TextView) findViewById(R.id.dB);
        button = (Button) findViewById(R.id.surroundcheck);

        if (runner == null) {
            runner = new Thread() {
                public void run() {
                    while (runner != null) {
                        try {
                            Thread.sleep(1000);
                            Log.i("Noise", "Tock");
                        } catch (InterruptedException e) {
                        }
                        ;
                        mHandler.post(updater);
                    }
                }
            };

            runner.start();
            Log.d("Noise", "start runner()");
        }

        mTypeface = Typeface.createFromAsset(getAssets(), "BMJUA.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);

        //TextView tv=(TextView)findViewById(R.id.dB);
        //tv.setTypeface(Typeface.createFromAsset(getAssets(),"the.ttf"));

    }


    public void onResume() {
        super.onResume();
        startRecorder();
    }

    public void onPause() {
        super.onPause();
        stopRecorder();
    }

    public void startRecorder() {
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try {
                mRecorder.prepare();
            } catch (java.io.IOException ioe) {
                android.util.Log.e("[Monkey]", "IOException: " +
                        android.util.Log.getStackTraceString(ioe));

            } catch (java.lang.SecurityException e) {
                android.util.Log.e("[Monkey]", "SecurityException: " +
                        android.util.Log.getStackTraceString(e));
            }
            try {
                mRecorder.start();
            } catch (java.lang.SecurityException e) {
                android.util.Log.e("[Monkey]", "SecurityException: " +
                        android.util.Log.getStackTraceString(e));
            }

        }
    }

    public void stopRecorder() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public double updateTv() {
        if(mRecorder!=null) {
            int amplitude = mRecorder.getMaxAmplitude();
            double currentDb = 20 * Math.log10((double) Math.abs(amplitude));
            mStatusView.setText(currentDb + "db");
            return currentDb;
        }
        return 0;
    }

    void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView)
                ((TextView) child).setTypeface(mTypeface);
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup) child);
        }
    }


    public void onClick(View v) {
       // stopRecorder();
        switch (v.getId()) {
            case R.id.surroundcheck:
                if(updateTv()<40) {//너무 씨끄러우면 다음 화면으로 안넘어감~
                Intent intent = new Intent(_3ready_test.this, _4simpletest.class);
                    startActivity(intent);
                    this.finish();
                }
                break;
            default:
                break;
        }
    }
}