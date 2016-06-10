package com.peitersenprojects.firebase.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.peitersenprojects.firebase.R;
import com.peitersenprojects.firebase.model.LiquidInput;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostLiquiedInputActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "PostLiquidInputActivity";
    private static final int RC_SIGN_IN = 9001;
    private SignInButton mSignInButton;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mFirebaseAuth;

    private Button mSendButton;

    // Firebase instance variables
    private DatabaseReference mFirebaseDatabaseReference;
    private FirebaseRecyclerAdapter<LiquidInput, MainActivity.MessageViewHolder> mFirebaseAdapter;


    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liquid_input);

        // New child entries
        mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        final EditText amountText = (EditText) findViewById(R.id.liquid_input_amount_text);
        final EditText typeText = (EditText) findViewById(R.id.liquid_input_type_text);
        final Switch lacSwitch = (Switch) findViewById(R.id.liquid_input_lac_switch);

        mSendButton = (Button) findViewById(R.id.button);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                LiquidInput liquidInput = new LiquidInput(
                        Integer.valueOf(amountText.getText().toString()),
                        typeText.getText().toString(),
                        new Date(),
                        lacSwitch.isChecked());


                getTodaysDatabaseRef().push().setValue(liquidInput);

                //mFirebaseDatabaseReference.child("liquids")
                //        .push().setValue(liquidInput);

                switchBackToMainActivity();
            }
        });

        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
       // NOP
    }

    // Extra to a util
    private DatabaseReference getTodaysDatabaseRef() {

        final String today = dateFormat.format(new Date());
        final DatabaseReference todaysRef = mFirebaseDatabaseReference.child("liquids").child(today);

        if (todaysRef != null) {
            return todaysRef;
        }

        mFirebaseDatabaseReference.child("liquids").push().setValue(today);
        return mFirebaseDatabaseReference.child("liquids").child(today);
    }


    private void switchBackToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
