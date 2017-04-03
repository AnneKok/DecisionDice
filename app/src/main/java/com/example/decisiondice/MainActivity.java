package com.example.decisiondice;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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

/**
 * Main class using the layout shown to the user when starting the application.
 *
 * @author Anne Kok
 */
public class MainActivity extends AppCompatActivity {

    // TODO: obfuscate keys
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

        setPickCategory(false);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                confirmSignIn(session);
                setPickCategory(true);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });

    }

    /**
     * Confirms sign in to user by showing a message.
     *
     * @param session session of the current user
     */
    protected void confirmSignIn(TwitterSession session) {
        String msg = "@" + session.getUserName() + " logged in!";
        twitterID = session.getUserName();
        TwitterIDHolder.getInstance().setID(twitterID);
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
    }

    /**
     * Enables or disables (and makes invisible) the Pick a category button.
     *
     * @param enabled
     */
    protected void setPickCategory(boolean enabled) {
        final Button categoryButton = (Button) findViewById(R.id.pickCategoryButton);
        if (enabled) {
            categoryButton.setEnabled(true);
            categoryButton.setVisibility(View.VISIBLE);
        } else {
            categoryButton.setEnabled(false);
            categoryButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Method required for Twitter login, to make sure the login button hears the result
     * from any activity that it triggered.
     *
     * @param requestCode the request
     * @param resultCode the result
     * @param data the Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Called when the user taps the Pick a category button.
     *
     * @param view the button that was pressed
     */
    public void toPickCategory(View view) {
        Intent intent = new Intent(this, CategoryPicker.class);
        startActivity(intent);
        intent.putExtra("twitterID", twitterID);
    }

}
