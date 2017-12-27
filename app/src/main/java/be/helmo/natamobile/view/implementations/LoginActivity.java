package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.helmo.natamobile.R;
import be.helmo.natamobile.controller.implementations.LoginController;
import be.helmo.natamobile.controller.interfaces.ILoginController;
import be.helmo.natamobile.view.interfaces.ILoginView;

public class LoginActivity extends AbstractActivity implements ILoginView {
    ILoginController controller;
    public LoginActivity(){
        this.controller = new LoginController(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        Button loginButton = (Button)findViewById(R.id.loginConnect);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.connect();
            }
        });
        Button signinButton = (Button)findViewById(R.id.signIn);
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.stackoverflow.com/" /*TODO URL WEB SITE*/));
                startActivity(viewIntent);
            }
        });
        Button facebookButton = (Button)findViewById(R.id.facebookConnect);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("Facebook connection not implemented now");
            }
        });
    }

    @Override
    public String getUserName(){
        TextView usernameTextView = findViewById(R.id.loginUsername);
        return usernameTextView.toString();
    }

    @Override
    public String getPassword(){
        TextView passwordTextView = findViewById(R.id.loginPassword);
        return passwordTextView.toString();
    }
}
