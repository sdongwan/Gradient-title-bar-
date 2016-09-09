package com.songdongwan.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements GradationScrollView.ScrollViewListener {
    private GradationScrollView gradationScrollView;
    private ImageView imageView;
    private TextView textView;
    private NoScrollListview listView;
    private int height;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        initData();
    }

    private void initData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,new String[]{"a","a","b","a","a","v","a","a","a","a","a","b","a","a","v","a","a","a","a","a","b","a","a","v","a","a","a"});
        listView.setAdapter(adapter);
    }

    private void initListeners() {
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = imageView.getHeight();

                gradationScrollView.setScrollViewListener(MainActivity.this);
            }
        });
    }

    private void initViews() {
        gradationScrollView= (GradationScrollView) findViewById(R.id.myScrollView);
        imageView= (ImageView) findViewById(R.id.imageView);
        textView= (TextView) findViewById(R.id.titlebar);
        listView= (NoScrollListview) findViewById(R.id.listView);
        linearLayout= (LinearLayout) findViewById(R.id.ll);

    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        // TODO Auto-generated method stub
        if (y <= 0) {   //设置标题的背景颜色
//            textView.setBackgroundColor(Color.argb((int) 0, 0,0,0));
            linearLayout.setBackgroundColor(Color.argb((int) 0, 0,0,0));
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
            // textView.setTextColor(Color.argb((int) alpha, 255,255,255));
            //textView.setBackgroundColor(Color.argb((int) alpha, 0,0,0));
            linearLayout.setBackgroundColor(Color.argb((int) alpha, 0,0,0));
        } else {    //滑动到banner下面设置普通颜色
            //textView.setBackgroundColor(Color.argb((int) 255, 0,0,0));
            linearLayout.setBackgroundColor(Color.argb((int) 255, 0,0,0));
        }
    }
}
