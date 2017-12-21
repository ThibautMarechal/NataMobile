package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import be.helmo.natamobile.R;
import be.helmo.natamobile.presenter.interfaces.ILoginPresenter;
import be.helmo.natamobile.presenter.implementations.LoginPresenter;
import be.helmo.natamobile.view.interfaces.ILoginView;

public class LoginActivity extends AbstractActivity implements ILoginView {
    ILoginPresenter presenter;
    public LoginActivity(){
        this.presenter = new LoginPresenter(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        Button loginButton = (Button)findViewById(R.id.loginConnect);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.connect();
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
                Toast.makeText(getBaseContext(), "Facebook connection not implemented now", Toast.LENGTH_LONG).show();
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
