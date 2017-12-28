package be.helmo.natamobile.view.implementations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.Map;

import be.helmo.natamobile.R;
import be.helmo.natamobile.adapter.BirdRecicleViewAdapter;
import be.helmo.natamobile.adapter.RecyclerItemClickListener;
import be.helmo.natamobile.controller.implementations.IdentifyBirdController;
import be.helmo.natamobile.controller.interfaces.IIdentifyBirdController;
import be.helmo.natamobile.models.Bird;
import be.helmo.natamobile.view.interfaces.IIdentifyBirdView;

/**
 * Created by Maréchal Thibaut on 27/12/2017.
 */

public class IdentifyBirdActivity extends AbstractActivity implements IIdentifyBirdView {
    private IIdentifyBirdController controller;
    private RecyclerView recyclerViewBirdList;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_bird);
        Bundle extras = getIntent().getExtras();
        recyclerViewBirdList =  findViewById(R.id.BirdRecyclerView);
        recyclerViewBirdList.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewBirdList.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerViewBirdList, new RecyclerItemClickListener.OnItemClickListener(){
            @Override public void onItemClick(View view, int position) {
                birdSelected(position);
            }
            @Override public void onLongItemClick(View view, int position) {
                birdSelected(position);
            }
        }));
        controller = new IdentifyBirdController(this, (Map<String,String>) extras.get("mapAttributes"));
    }

    @Override
    public void populateListView(List<Bird> birds){
        recyclerViewBirdList.setAdapter(new BirdRecicleViewAdapter(birds));
    }

    private void birdSelected(int position){
        Bird selectedbird = controller.getPossibleBirds().get(position);
        Intent data = new Intent();
        data.putExtra("BirdName", selectedbird.getName());
        setResult(RESULT_OK, data);
        finish();
    }
}
