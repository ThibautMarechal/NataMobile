package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.helmo.natamobile.R;
import be.helmo.natamobile.controller.implementations.LoginController;
import be.helmo.natamobile.controller.interfaces.ILoginController;
import be.helmo.natamobile.tools.Environment;
import be.helmo.natamobile.view.interfaces.ILoginView;

public class LoginActivity extends AbstractActivity implements ILoginView {
	private ILoginController controller;
	private EditText emailEditText;
	private EditText passwordEditText;

	public LoginActivity() {
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
		Button signinButton = (Button) findViewById(R.id.signIn);
		signinButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("http://192.168.128.13:8081/NataWeb-0.1/user/signin"));
				startActivity(viewIntent);
			}
		});
		Button facebookButton = (Button) findViewById(R.id.facebookConnect);
		facebookButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				displayToast("Facebook connection not implemented now");
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putLong(Environment.ID, controller.getLoggedUser().getId());
		editor.putString(Environment.NAME, controller.getLoggedUser().getFullName());
		editor.putString(Environment.EMAIL, getUserName());
		editor.putString(Environment.PASSWORD, getPassword());
		editor.putString(Environment.PICTURE, controller.getLoggedUser().getPicture());
		editor.apply();
	}

	private void authenticate() {
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		if (email.isEmpty()) {
			displayToast("Email vide");
		}
		if (password.isEmpty()) {
			displayToast("Mot de passe vide");
		}
		if (!password.isEmpty() && !email.isEmpty()) {
			//TODO AUTH HERE
//            showView(ViewEnum.HOME);
			controller.connect();
		} else {
			//TEMPORAIRE
//            showView(ViewEnum.HOME);
//            displayToast("CONNECTED");
		}
	}

	@Override
	public String getUserName() {
		TextView usernameTextView = findViewById(R.id.loginEmail);
		return usernameTextView.getText().toString();
	}

	@Override
	public String getPassword() {
		TextView passwordTextView = findViewById(R.id.loginPassword);
		return passwordTextView.getText().toString();
	}
}
