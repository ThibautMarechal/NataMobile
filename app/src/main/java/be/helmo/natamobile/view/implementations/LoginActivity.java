package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.helmo.natamobile.R;
import be.helmo.natamobile.controller.implementations.LoginController;
import be.helmo.natamobile.controller.interfaces.ILoginController;
import be.helmo.natamobile.view.interfaces.ILoginView;

public class LoginActivity extends AbstractActivity implements ILoginView {
    private ILoginController controller;
    private EditText emailEditText;
    private EditText passwordEditText;
    public LoginActivity(){
        this.controller = new LoginController(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        emailEditText = findViewById(R.id.loginEmail);
        passwordEditText = findViewById(R.id.loginPassword);
        Button loginButton = findViewById(R.id.loginConnect);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticate();
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

    private void authenticate() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(email.isEmpty()){
            displayToast("Email vide");
        }
        if(password.isEmpty()){
            displayToast("Mot de passe vide");
        }
        if(!password.isEmpty() && !email.isEmpty()){
            //TODO AUTH HERE
            showView(ViewEnum.HOME);
        }else{
            //TEMPORAIRE
            showView(ViewEnum.HOME);
        }
    }

    @Override
    public String getUserName(){
        TextView usernameTextView = findViewById(R.id.loginEmail);
        return usernameTextView.toString();
    }

    @Override
    public String getPassword(){
        TextView passwordTextView = findViewById(R.id.loginPassword);
        return passwordTextView.toString();
    }
}
