package be.helmo.natamobile.view.implementations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import be.helmo.natamobile.R;
import be.helmo.natamobile.presenter.implementations.HomePresenter;
import be.helmo.natamobile.presenter.interfaces.IHomePresenter;
import be.helmo.natamobile.view.interfaces.IHomeView;

public class HomeActivity extends AbstractActivity implements IHomeView {
    private final HomeActivity self;
    private final IHomePresenter presenter;

    public HomeActivity() {
        this.self = this;
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
                final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                if (ActivityCompat.checkSelfPermission(self, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(self, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(self,
                            new String[]{Manifest.permission.READ_CONTACTS},1/*???*/);
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1,
                        1, new LocationListener() {
                            @Override
                            public void onLocationChanged(final Location location) {
                                Log.d("Location", "Location change");
                                //presenter.startNewSession(location);
                            }

                            @Override
                            public void onStatusChanged(String s, int i, Bundle bundle) {
                                //ignored
                            }

                            @Override
                            public void onProviderEnabled(String s) {
                                //ignored
                            }

                            @Override
                            public void onProviderDisabled(String s) {
                                //ignored
                            }
                        });
            }
        });
    }
}
