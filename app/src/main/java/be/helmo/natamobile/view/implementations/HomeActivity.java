package be.helmo.natamobile.view.implementations;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.Picasso;

import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.SessionListViewAdapter;
import be.helmo.natamobile.controller.implementations.HomeController;
import be.helmo.natamobile.controller.interfaces.IHomeController;
import be.helmo.natamobile.tools.Environment;
import be.helmo.natamobile.view.interfaces.IHomeView;

public class HomeActivity extends AbstractActivity implements IHomeView {
	private static final int REQUEST_ALL_PERMISSION = 42;
	private final IHomeController controller;

	public HomeActivity() {
		this.controller = new HomeController(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		//ASK ALL PERMISSIONS
		if (permissionsNeeded()) {
			askAllPermission();
		}
		//NEW SESSION
		Button startNewSessionButton = findViewById(R.id.startSessionButton);
		startNewSessionButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startNewSession();
			}
		});
		controller.updateValues();
	}

	@Override
	public void updateUserUI() {
		//USERNAME
		TextView username = findViewById(R.id.homeUsername);
		username.setText(getSharedName());
		//EMAIL
		TextView email = findViewById(R.id.home_email);
		email.setText(getSharedEmail());
		//PICTURE
		ImageView pp = findViewById(R.id.homeImageProfile);
		String ppUrl = getSharedPicture();
		Picasso.with(pp.getContext()).load(ppUrl).centerCrop().fit().into(pp);
	}

	@Override
	public void updateSessionList() {
		//LIST SESSION
		SessionListViewAdapter adapter = new SessionListViewAdapter(this, controller.getSessions());
		ListView sessionListView = findViewById(R.id.homeSessionListView);
		sessionListView.setAdapter(adapter);
	}

	private boolean permissionsNeeded() {
		boolean permissionNeeded = false;
		for (int i = 0; i < permissions.length && !permissionNeeded; i++) {
			permissionNeeded |= PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, permissions[i]);
		}
		return permissionNeeded;
	}

	private void askAllPermission() {
		ActivityCompat.requestPermissions(this,
			  permissions,
			  REQUEST_ALL_PERMISSION);
	}

	@SuppressLint("MissingPermission") // All permissions asked before
	private void startNewSession() {
		//TODO SWAP THIS THINGS
		//FOR EMULATOR
		/*SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putString(Environment.CURRENT_SESSION_LAT, "1");
		editor.putString(Environment.CURRENT_SESSION_LON, "2");
		editor.apply();
		controller.startNewSession(0, 0);*/

		//FOR REAL SMARTPHONE
		FusedLocationProviderClient mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
		mFusedLocationClient.getLastLocation()
			  .addOnSuccessListener(this, new OnSuccessListener<Location>() {
				  @Override
				  public void onSuccess(Location location) {
					  if (location != null) {
						  SharedPreferences.Editor editor = sharedpreferences.edit();
						  editor.putString(Environment.CURRENT_SESSION_LAT, Double.toString(location.getLatitude()));
						  editor.putString(Environment.CURRENT_SESSION_LON, Double.toString(location.getLongitude()));
						  editor.apply();
						  controller.startNewSession(location.getLongitude(), location.getLatitude());
					  }
				  }
			  });

	}

	public String getSharedName() {
		return super.getSharedName();
	}

	public String getSharedEmail() {
		return super.getSharedEmail();
	}

	public String getSharedPassword() {
		return super.getSharedPassword();
	}

	public long getSharedId() {
		return super.getSharedId();
	}
}
