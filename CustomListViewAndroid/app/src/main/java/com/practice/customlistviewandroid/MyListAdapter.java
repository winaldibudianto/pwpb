package com.practice.customlistviewandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyListAdapter extends ArrayAdapter<Hero> {

    private List<Hero> heroList;

    private Context context;

    private int resource;

    public MyListAdapter(Context context, int resource, List<Hero> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View view = layoutInflater.inflate(this.resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewTeam = view.findViewById(R.id.textViewTeam);
        Button buttonDelete = view.findViewById(R.id.buttonDelete);

        Hero hero = heroList.get(position);

        imageView.setImageDrawable(this.context.getResources().getDrawable(hero.getImage()));
        textViewName.setText(hero.getName());
        textViewTeam.setText(hero.getTeam());

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeHero(position);
            }
        });

        return view;
    }

    private void removeHero(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle("Are you sure you want to delete this?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                heroList.remove(position);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
