package be.helmo.natamobile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import be.helmo.natamobile.R;
import be.helmo.natamobile.models.Bird;

/**
 * Created by Maréchal Thibaut on 27/12/2017.
 */

public class BirdViewHolder extends RecyclerView.ViewHolder{

    private TextView textViewView;
    private ImageView imageView;

    //itemView est la vue correspondante à 1 cellule
    public BirdViewHolder(View itemView) {
        super(itemView);

        textViewView = itemView.findViewById(R.id.text);
        imageView = itemView.findViewById(R.id.image);
    }

    //puis ajouter une fonction pour remplir la cellule en fonction d'un MyObject
    public void bind(Bird b){
        textViewView.setText(b.getName());
        Picasso.with(imageView.getContext()).load(b.getPicturePath()).centerCrop().fit().into(imageView);
    }
}
