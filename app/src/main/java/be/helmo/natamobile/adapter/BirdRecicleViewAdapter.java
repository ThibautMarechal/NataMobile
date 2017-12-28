package be.helmo.natamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.helmo.natamobile.R;
import be.helmo.natamobile.models.Bird;

/**
 * Created by Maréchal Thibaut on 27/12/2017.
 */

public class BirdRecicleViewAdapter extends RecyclerView.Adapter<BirdViewHolder> {
    List<Bird> list;

    //ajouter un constructeur prenant en entrée une liste
    public BirdRecicleViewAdapter(List<Bird> list) {
        this.list = list;
    }

    //cette fonction permet de créer les viewHolder
    //et par la même indiquer la vue à inflater (à partir des layout xml)
    @Override
    public BirdViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_cards,viewGroup,false);
        return new BirdViewHolder(view);
    }

    //c'est ici que nous allons remplir notre cellule avec le texte/image de chaque MyObjects
    @Override
    public void onBindViewHolder(BirdViewHolder myViewHolder, int position) {
        Bird myObject = list.get(position);
        myViewHolder.bind(myObject);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}