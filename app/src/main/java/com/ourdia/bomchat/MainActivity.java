package com.ourdia.bomchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final  int  MY_Request_Code = 1212;
    List<AuthUI.IdpConfig> provider;
    Button btn_singn_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_singn_out = (Button)findViewById(R.id.btn_singn_out);

        btn_singn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //layout
                AuthUI.getInstance().signOut(MainActivity.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btn_singn_out.setEnabled(false);
                        showSignInOprion();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this,""+e.getMessage(),Toast.LENGTH_LONG).show();


                    }
                });
            }

        });

                // init provider
        provider = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(),// Email Builder
      //  new AuthUI.IdpConfig.PhoneBuilder().build(),  // Phone Builder
        new AuthUI.IdpConfig.FacebookBuilder().build(),  // facebook Builder
        new AuthUI.IdpConfig.GoogleBuilder().build()  // Google Builder


        );

    showSignInOprion();

    }

    private void showSignInOprion() {
     startActivityForResult(
             AuthUI.getInstance()
             .createSignInIntentBuilder()
             .setAvailableProviders(provider)
             .setTheme(R.style.myTheme).build(),MY_Request_Code
     );

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode== MY_Request_Code){
            IdpResponse reponse  = IdpResponse.fromResultIntent(data);
            // get user
            if (resultCode == RESULT_OK){
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // show email on Toast
                Toast.makeText(this,""+user.getEmail(),Toast.LENGTH_LONG).show();
                //sign out   button
                btn_singn_out.setEnabled(true);
            }else {
                Toast.makeText(this,""+reponse.getError().getMessage() ,Toast.LENGTH_LONG).show();
            }


        }

    }
}
