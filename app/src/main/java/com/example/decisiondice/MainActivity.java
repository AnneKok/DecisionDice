package com.example.decisiondice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import retrofit2.Call;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "CdgOhLWmfD6gO4MqwAsQYpvHu";
    private static final String TWITTER_SECRET = "LueXAWdgCWluDHq2I5hGlMOw4RwPpx0fsD8RehXuqtMxmyx3Ii";
    private TwitterLoginButton loginButton;
    private String twitterID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        // Disable Pick a category button by default
        final Button categoryButton = (Button) findViewById(R.id.pickCategoryButton);
        categoryButton.setEnabled(false);
        categoryButton.setVisibility(View.INVISIBLE);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;

                String msg = "@" + session.getUserName() + " logged in!";
                twitterID = session.getUserName();
                TwitterIDHolder.getInstance().setID(twitterID);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                // Enable Pick a category button
                categoryButton.setEnabled(true);
                categoryButton.setVisibility(View.VISIBLE);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Make sure that the loginButton hears the result from any
        // Activity that it triggered.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    /** Called when the user taps the Pick a category button */
    public void toPickCategory(View view) {
        Intent intent = new Intent(this, CategoryPicker.class);
        startActivity(intent);
        intent.putExtra("twitterID", twitterID);
    }

}
