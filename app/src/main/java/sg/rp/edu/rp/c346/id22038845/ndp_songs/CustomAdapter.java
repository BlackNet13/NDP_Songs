package sg.rp.edu.rp.c346.id22038845.ndp_songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;

    int layout_id;

    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView =inflater.inflate(layout_id,parent,false);

        TextView tvTitle = rowView.findViewById(R.id.tV1);
        TextView tvSingers = rowView.findViewById(R.id.tV2);
        TextView tvStars = rowView.findViewById(R.id.tV3);




        Song currentSongs = songList.get(position);
        String starsStr = "";

        int rand = (int)(Math.random()*4);

        ImageView imgView = rowView.findViewById(R.id.iV);
        imgView.setImageResource(imgResource[rand]);

        LinearLayout layoutBg = rowView.findViewById(R.id.layoutBg);
        layoutBg.setBackgroundResource(bgResource[rand]);

        for(int i=0; i<currentSongs.getStars(); i++){
            starsStr+="★";
        }

        tvTitle.setText(currentSongs.getTitle());
        tvSingers.setText(currentSongs.getSingers());
        tvStars.setText(starsStr);

        //<a href="https://www.flaticon.com/free-icons/music" title="music icons">Music icons created by Freepik - Flaticon</a>

        return rowView;
    }
    private int [] imgResource ={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4};
    private int [] bgResource = {R.color.cranberry, R.color.greyishBlue,R.color.purple,R.color.yellow};
}

