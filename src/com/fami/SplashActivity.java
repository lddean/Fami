package com.fami;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.fami.R;
import com.fami.user.activities.LogInActivity;
import com.fami.user.utils.DialogUtils;
import com.quickblox.core.QBEntityCallbackImpl;
import com.quickblox.core.QBSettings;
import com.quickblox.auth.QBAuth;
import com.quickblox.auth.model.QBSession;

import java.util.List;

public class SplashActivity extends Activity{
	
	public static final String APP_ID = "18962";
    public static final String AUTH_KEY = "pFADDkbmhk2sJXG";
    public static final String AUTH_SECRET = "xx2HjfpvY9mUwRh";
    
    private Context context;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;

        initUI();
        
        // Initialize QuickBlox application with credentials.
        //
        QBSettings.getInstance().fastConfigInit(APP_ID, AUTH_KEY, AUTH_SECRET);

        // Create QuickBlox session
        //
        QBAuth.createSession(new QBEntityCallbackImpl<QBSession>() {
            @Override
            public void onSuccess(QBSession qbSession, Bundle bundle) {
            	loginApp();
            }

            @Override
            public void onError(List<String> errors) {
                // print errors that came from server
                DialogUtils.showLong(context, errors.get(0));
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    
    private void initUI() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void loginApp() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }
}
