package com.example.dioum2touba.dtsmediacom;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class IndexActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int images[]={R.drawable.etu1,R.drawable.etu2,R.drawable.etu3,R.drawable.etu5,R.drawable.etu6
                ,R.drawable.etu7,R.drawable.etu8,R.drawable.etu9,R.drawable.etu10};

        viewFlipper=findViewById(R.id.v_fliper);
        appBarLayout=(AppBarLayout)findViewById(R.id.appbarid);
        viewPager=(ViewPager)findViewById(R.id.viewpager_id);

        ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentMenu(),"COURS");

        viewPager.setAdapter(adapter);

        for(int image:images){
            fliperImage(image);
        }

    }

    public void fliperImage(int image){

        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }


}
