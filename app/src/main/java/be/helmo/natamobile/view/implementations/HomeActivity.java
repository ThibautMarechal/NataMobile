package be.helmo.natamobile.view.implementations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.SessionListViewAdapter;
import be.helmo.natamobile.presenter.implementations.HomePresenter;
import be.helmo.natamobile.presenter.interfaces.IHomePresenter;
import be.helmo.natamobile.tools.ImageViewUrlBinder;
import be.helmo.natamobile.view.interfaces.IHomeView;

public class HomeActivity extends AbstractActivity implements IHomeView {
    private static final int PERMISSION_REQUET_LOCATION_ACCES = 42;
    private final IHomePresenter presenter;

    public HomeActivity() {
        this.presenter = new HomePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Button startNewSessionButton = findViewById(R.id.startSessionButton);
        startNewSessionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewSession();
            }
        });
        //LIST SESSION
        SessionListViewAdapter<String[]> adapter = new SessionListViewAdapter<>(this, presenter.getSessions());
        ListView sessionListView = findViewById(R.id.homeSessionListView);
        sessionListView.setAdapter(adapter);

        //USERNAME
        TextView username = findViewById(R.id.homeUsername);
        username.setText(presenter.getUsername());
        //EMAIL
        TextView email = findViewById(R.id.home_email);
        email.setText(presenter.getUserEmail());
        //PICTURE
        final ImageView pp = findViewById(R.id.homeImageProfile);
        String ppUrl = presenter.getUserPictureProfile();
        ImageViewUrlBinder.bind(pp, ppUrl);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUET_LOCATION_ACCES: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   startNewSession();
                }
            }
        }
    }

    private void startNewSession() {
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUET_LOCATION_ACCES);
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        presenter.startNewSession(location.getLongitude(), location.getLatitude());
    }
}
