package com.example.trung.memogame;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderboardAdapter extends ArrayAdapter<Highscore> {
    private Context mContext;
    private int mRes;
    private int lastPosition = -1;

    static class ViewHolder{
        TextView name;
        TextView score;
        TextView round;
    }

    public LeaderboardAdapter( Context context, int resource,  ArrayList<Highscore> objects) {
        super(context, resource, objects);
        mContext = context;
        mRes = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the highscore information
        String name = getItem(position).getName();
        String score = getItem(position).getScore();
        String round = getItem(position).getRound();

        //Create high score object
        Highscore highscore = new Highscore(name,score,round);

        //view result with animation
        final View result;

        // create view holder
        ViewHolder holder;
        if (convertView == null ){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mRes,parent,false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.lb_name);
            holder.score = convertView.findViewById(R.id.lb_score);
            holder.round = convertView.findViewById(R.id.lb_round);

            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        //set animation logic when scrolling
        Animation ani = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down : R.anim.load_up);
        // apply logic
        result.startAnimation(ani);
        //return new position for animation logic
        lastPosition = position;

        // set text on to textview
        holder.name.setText(highscore.getName());
        holder.score.setText(highscore.getScore());
        holder.round.setText(highscore.getRound());

        return convertView;
    }
}
