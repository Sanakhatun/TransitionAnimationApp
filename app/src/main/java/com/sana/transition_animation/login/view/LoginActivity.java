package com.sana.transition_animation.login.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.AnimRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.sana.transition_animation.home.view.HomeActivity;
import com.sana.transition_animation.R;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlRoot;
    private TextInputLayout tl_username, tl_password;
    private TextInputEditText etUserName, etPassword;
    private Button bLogin;
    private String username = "", password = "";
    private final String TAG = getClass().getSimpleName();

    /**
     * Note: Sometimes animation would not work on devices because animation option of device may be disable..
     * So, if problem may then enable Transition Animation option to your device.
     * Settings -> Developer Options -> Transition Animation Scale -> 1x for LG.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        setListeners();

        rlRoot.startAnimation(setAnimation(R.anim.bottom_up, (new AccelerateDecelerateInterpolator()), true));
    }

    private void init() {
        rlRoot = (RelativeLayout) findViewById(R.id.rlRoot);
        tl_username = (TextInputLayout) findViewById(R.id.tl_username);
        tl_password = (TextInputLayout) findViewById(R.id.tl_password);
        etUserName = (TextInputEditText) findViewById(R.id.etUserName);
        etPassword = (TextInputEditText) findViewById(R.id.etPassword);

        bLogin = (Button) findViewById(R.id.bLogin);

    }

    /**
     * Register Listeners
     */

    private void setListeners() {

        try {

            etUserName.addTextChangedListener(textWatcherListener(tl_username, getString(R.string.please_enter_username)));
            etPassword.addTextChangedListener(textWatcherListener(tl_password, getString(R.string.please_enter_password)));
            bLogin.setOnClickListener(this);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bLogin:

                /* Activity Transition Animation */

                username = etUserName.getText().toString();
                password = etPassword.getText().toString();

                if (validation()) {
                    openIntent();
                }

                break;
        }
    }

    /**
     * Transition Animation Between Activities i.e from LoginActivity to HomeActivity
     */

    private void openIntent() {

        try {

            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
            homeIntent.putExtra("username", username);
            startActivity(homeIntent);
            /* Following the documentation, right after starting the activity and we can also use it after finishing the activity i.e.after finish()
               we override the transition */
            overridePendingTransition(R.anim.left_in, R.anim.left_out);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * To Set Animation
     *
     * @param id           Animation resource Id
     * @param interpolator Animation Effect
     * @param fillAfter
     * @return
     */

    private Animation setAnimation(@AnimRes int id, Interpolator interpolator, Boolean fillAfter) {
        Animation animation = AnimationUtils.loadAnimation(this, id);

        if (interpolator != null) {
            animation.setInterpolator(interpolator);
        } else {
            animation.setInterpolator((new LinearInterpolator()));
        }

        animation.setFillAfter(fillAfter);
        return animation;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private boolean validation() {
        try {

            if (username.equalsIgnoreCase("")) {

                tl_username.setError(getString(R.string.please_enter_username));
                return false;

            } else if (password.equalsIgnoreCase("")) {

                tl_password.setError(getString(R.string.please_enter_password));
                return false;

            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * TextWatcher Listener for multiple EditText
     *
     * @param view         EditText
     * @param errorMessage validation error message
     * @return
     */

    private TextWatcher textWatcherListener(final View view, final String errorMessage) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    if (view instanceof TextInputLayout) {

                        if (s.length() == 0) {
                            ((TextInputLayout) view).setError(errorMessage);
                        } else {
                            ((TextInputLayout) view).setError(null);
                        }

                    }

                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}
