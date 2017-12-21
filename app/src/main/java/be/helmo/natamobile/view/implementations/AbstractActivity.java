package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import be.helmo.natamobile.view.interfaces.IView;

/**
 * Created by marechthib on 19/12/2017.
 */

public abstract class AbstractActivity extends AppCompatActivity implements IView {
    public void showView(ViewEnum view){
        Intent intent = new Intent(this, view.getActivityClass());
        startActivity(intent);
    }
}
