package com.sana.transition_animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlRoot;
    private LinearLayout llView;
    private LinearLayout llLogin;
    private LinearLayout llRegister;

    private EditText etFullName, etEmail, etRPassword, etUserName, etPassword;
    private Button bNext,bSubmit;

    private Animation animateRegistrationView, animateLoginView;

    /* Note: Sometimes animation would not work on devices because animation option of device may be disable..
     *       So, if problem may then enable Transition Animation option to your device.
     *       Settings -> Developer Options -> Transition Animation Scale -> 1x for LG.
     *     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        llView = (LinearLayout) findViewById(R.id.llView);
        llLogin = (LinearLayout) findViewById(R.id.llLogin);
        llRegister = (LinearLayout) findViewById(R.id.llRegister);

        etFullName = (EditText) findViewById(R.id.etFullName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etRPassword = (EditText) findViewById(R.id.etRPassword);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bNext = (Button) findViewById(R.id.bNext);
        bSubmit = (Button) findViewById(R.id.bSubmit);

        bNext.setOnClickListener(this);
        bSubmit.setOnClickListener(this);

        startAnimation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bNext:
               // bNext.setText(getResources().getString(R.string.submit));

                /* Replace view with another with animation */

                    startAnimationForRegisteration();
                    startAnimationForLogin();

                break;

            case R.id.bSubmit:

                /* Activity Transition Animation */
                openIntent();

                break;
        }
    }

    private void openIntent() {
        Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(homeIntent);
        /* Following the documentation, right after starting the activity and we can also use it after finishing the activity i.e.after finish()
           we override the transition */
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
    }

    private void startAnimation(){
        Animation animateLogo = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
        animateLogo.setInterpolator((new AccelerateDecelerateInterpolator()));
        animateLogo.setFillAfter(true);
        rlRoot.startAnimation(animateLogo);
    }

    private void startAnimationForRegisteration(){
        animateRegistrationView = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        llRegister.startAnimation(animateRegistrationView);
    }

    private void startAnimationForLogin(){
        llRegister.setVisibility(View.GONE);
        bNext.setVisibility(View.GONE);
        bSubmit.setVisibility(View.VISIBLE);
        llLogin.setVisibility(View.VISIBLE);
        animateLoginView = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        llLogin.startAnimation(animateLoginView);
    }

}
