package com.example.dioum2touba.dtsmediacom;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;
public class AccueilActivity extends AwesomeSplash {

    @Override
    public void initSplash(ConfigSplash configSplash) {

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(1000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.logo_accueil); //or any other drawable
        configSplash.setAnimLogoSplashDuration(3000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Path
        // configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(2000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.colorAccent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(2000);
        configSplash.setPathSplashFillColor(R.color.black); //path object filling color

        //Customize Title
        configSplash.setTitleSplash("DTS Bambey");
        configSplash.setTitleTextColor(R.color.colorAccent);
        configSplash.setTitleTextSize(50f); //float value
        configSplash.setAnimTitleDuration(1000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        //configSplash.setTitleFont(getResources ().getFont ()); //provide string to your font located in assets/fonts/

    }

    @Override
    public void animationsFinished(){
            Intent intent = new Intent (getApplicationContext(), IndexActivity.class);
            startActivity (intent);
            finish ();
    }

}
