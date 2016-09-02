package com.example.lessvolume0817;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by 강우진 on 2016-08-17.
 */
public class _2testpopup extends Activity{
    Button button3;
    private Typeface mTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout._2testpopup);


        button3 = (Button) findViewById(R.id.popup_answer);
        button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){
                Intent intent1= new Intent(_2testpopup.this,_3ready_test.class);
                startActivity(intent1);
            }
        });


        mTypeface = Typeface.createFromAsset(getAssets(), "BMJUA.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);
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