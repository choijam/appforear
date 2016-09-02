package com.example.lessvolume0817;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class _1select_test extends Activity implements View.OnClickListener {
   private Button button1;
    private Button button2;
    private Typeface mTypeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._1select_test);

        button1 = (Button) findViewById(R.id.simpletest);
        button2= (Button) findViewById(R.id.precisetest);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        mTypeface = Typeface.createFromAsset(getAssets(), "210.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.simpletest:
                startActivity(new Intent(this, _2testpopup.class));
                break;
            case R.id.precisetest:
                startActivity(new Intent(this, _2testpopup.class));
                break;
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
