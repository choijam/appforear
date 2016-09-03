package com.example.lessvolume0817;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class _4simpletest extends Activity{
    double  data2;
    Button left20_1000;
    Button left40_1000;
    Button left60_1000;
    Button left80_1000;
    MediaPlayer secondSound_1, secondSound_2, secondSound_3, secondSound_4;

    MediaPlayer secondSound2_1, secondSound2_2, secondSound2_3, secondSound2_4;
    private Typeface mTypeface;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout._4left_simpletest1000);
        secondSound_1 = MediaPlayer.create(this, R.raw.thousand__twenty);
        secondSound_2 = MediaPlayer.create(this, R.raw.thousand__forty);
        secondSound_3 = MediaPlayer.create(this, R.raw.thousand__sixty);
        secondSound_4 = MediaPlayer.create(this, R.raw.thousand__eighty);
        secondSound2_1 = MediaPlayer.create(this, R.raw.thousand__twenty);
        secondSound2_2 = MediaPlayer.create(this, R.raw.thousand__forty);
        secondSound2_3 = MediaPlayer.create(this, R.raw.thousand__sixty);
        secondSound2_4 = MediaPlayer.create(this, R.raw.thousand__eighty);

        left20_1000 = (Button) findViewById(R.id.db20_1000);
        left40_1000 = (Button) findViewById(R.id.db40_1000);
        left60_1000 = (Button) findViewById(R.id.db60_1000);
        left80_1000 = (Button) findViewById(R.id.db80_1000);

        mTypeface = Typeface.createFromAsset(getAssets(), "210.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);
        TextView one=(TextView)findViewById(R.id.canhear);
        one.setTypeface(Typeface.createFromAsset(getAssets(),"the.ttf"));
        TextView two=(TextView)findViewById(R.id.cannothear);
        two.setTypeface(Typeface.createFromAsset(getAssets(),"the.ttf"));
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.db20_1000:
                secondSound_1.start();
                left20_1000.setEnabled(false);
                left40_1000.setVisibility(View.GONE);
                left60_1000.setVisibility(View.GONE);
                left80_1000.setVisibility(View.GONE);

                ImageButton button = (ImageButton) findViewById(R.id.lefthear);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        secondSound_1.stop();
                        left20_1000.setEnabled(true);
                        left40_1000.setVisibility(View.VISIBLE);
                        left60_1000.setVisibility(View.VISIBLE);
                        left80_1000.setVisibility(View.VISIBLE);
                        data2 = 20;   //////////////데이터저장=주파수1000 데시벨20
                    }
                });
            default:
                break;
        }
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
}