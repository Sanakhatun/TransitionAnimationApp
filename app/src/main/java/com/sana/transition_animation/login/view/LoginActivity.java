package com.sana.transition_animation.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.sana.transition_animation.home.view.HomeActivity;
import com.sana.transition_animation.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout rlRoot;
    private TextInputEditText etUserName, etPassword;
    private Button bLogin;

    /* Note: Sometimes animation would not work on devices because animation option of device may be disable..
     *       So, if problem may then enable Transition Animation option to your device.
     *       Settings -> Developer Options -> Transition Animation Scale -> 1x for LG.
     *     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        setListeners();

        rlRoot.startAnimation(setAnimation(R.anim.bottom_up,(new AccelerateDecelerateInterpolator()),true));
    }

    private void init() {
        rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        etUserName = (TextInputEditText) findViewById(R.id.etUserName);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);

    }


    private void setListeners() {
        bLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bLogin:
                /* Activity Transition Animation */
                openIntent();

                break;
        }
    }


    private void openIntent() {
        Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(homeIntent);
        /* Following the documentation, right after starting the activity and we can also use it after finishing the activity i.e.after finish()
           we override the transition */
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    private Animation setAnimation(@AnimRes int id, Interpolator interpolator, Boolean fillAfter){
        Animation animation = AnimationUtils.loadAnimation(this, id);

        if(interpolator!=null)
            animation.setInterpolator(interpolator);
        else
            animation.setInterpolator((new LinearInterpolator()));

        animation.setFillAfter(fillAfter);
        return animation;
    }
}
