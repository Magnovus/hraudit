package com.example.magnovus.hraudit;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout layoutHidden, layoutMain, layoutContent;
    private FloatingActionButton fabButton;
    private boolean isOpen = false;
    GridView gridView;
    ImageButton profileSetting;

    int[] images = {
            R.drawable.food,
            R.drawable.lock,
            R.drawable.office_pixel,
            R.drawable.security,
            R.drawable.e4n7bafgb0c,
            R.drawable.superplatformer,
            R.drawable.download,
            R.drawable.sadsd
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(this, images);
        gridView.setAdapter(gridAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        layoutMain = findViewById(R.id.main);
        layoutHidden = findViewById(R.id.layoutHidden);
        layoutContent = findViewById(R.id.layoutContent);

        fabButton = findViewById(R.id.fab1);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewMenu();
            }
        });

        profileSetting=findViewById(R.id.profileSetting);
        profileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,basicProfile.class);
                startActivity(intent);
            }
        });

    }

    private void viewMenu() {
        if (!isOpen) {
            int x = layoutContent.getRight() / 2;
            int y = layoutContent.getBottom() - 200;
            int startRadius = 0;
            int endRadius = (int) Math.hypot(layoutMain.getWidth(), layoutMain.getHeight());

            fabButton.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), android.R.color.background_dark, null)));
            fabButton.setImageResource(R.drawable.ic_highlight_off_white_48dp);

            Animator animator = ViewAnimationUtils.createCircularReveal(layoutHidden, x, y, startRadius, endRadius);
            layoutHidden.setVisibility(View.VISIBLE);
            animator.start();
            isOpen = true;
        } else {
            int x = layoutContent.getRight() / 2;
            int y = layoutContent.getBottom() - 200;
            int startRadius = Math.max(layoutContent.getWidth(), layoutContent.getHeight());
            int endRadius = 0;

            fabButton.setBackgroundTintList(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null)));
            fabButton.setImageResource(R.drawable.ic_add_circle_outline_white_48dp);

            Animator animator = ViewAnimationUtils.createCircularReveal(layoutHidden, x, y, startRadius, endRadius);
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    layoutHidden.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            animator.start();
            isOpen = false;
        }


    }
}
