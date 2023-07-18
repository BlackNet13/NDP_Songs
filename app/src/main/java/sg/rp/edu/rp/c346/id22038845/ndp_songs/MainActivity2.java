package sg.rp.edu.rp.c346.id22038845.ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    ArrayList<String> strList;
    ListView lvResults; //this stays in custom Adapter, required


    Button btnStars;

    Spinner ddSpn;
    ArrayAdapter<String> spnA;

    ArrayList<Song> data; //<--does this count? I dont think so
    ArrayList<Song> alSong; //new to replace listStr arraylist

    // when applying customAdapter, arrayadapter orginally used will be perish *joke*
    //in this case is the listStr
    //in it's place is the custom adapter, arraylist will be replace with the one with the class

    CustomAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        lvResults = findViewById(R.id.lv);
        btnStars =findViewById(R.id.btnStars);
        ddSpn = findViewById(R.id.spinner);

        spnA = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);


        List<String> dist = new ArrayList<>();
        dist.add("Filter by Year");
        DBHelper db = new DBHelper(MainActivity2.this);
        List<Song> songList =db.getAllSongs();
        for(Song song : songList){
            int year = song.getYear();

            if(!dist.contains(String.valueOf(year))){
                dist.add(String.valueOf(year));
            }}

        for(int i= 0; i< dist.size(); i++){
            spnA.add(dist.get(i));
        }

        ddSpn.setAdapter(spnA);


        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = data.get(position);
                Intent x = new Intent(MainActivity2.this,MainActivity3.class);
                x.putExtra("data",song);
                startActivity(x);
            }
        });

        ddSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    data = db.getSongByYr(Integer.parseInt(parent.getItemAtPosition(position).toString()));

                }else{
                    data = db.getAllSongs();

                }
                //ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
                alSong = new ArrayList<>();
                adapter = new CustomAdapter(MainActivity2.this,R.layout.row,alSong);

                lvResults.setAdapter(adapter);
                db.close();
                //listStr.clear(); <--does not need clearing?
                for(int i = 0; i <data.size(); i++){
                    alSong.add(data.get(i));
                    //listStr.notifyDataSetChanged(); <-- does not need notify change?
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper db = new DBHelper(MainActivity2.this);

        data = new ArrayList<>(); //suggested?

        btnStars.setOnClickListener(new View.OnClickListener() { //for star filter
            @Override
            public void onClick(View v) {
                data = db.getAllSongs(5);
                //ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
                //lvResults.setAdapter(listStr);
                alSong = new ArrayList<>();
                adapter = new CustomAdapter(MainActivity2.this,R.layout.row,alSong);

                lvResults.setAdapter(adapter);
                db.close();
                //listStr.clear();
                for(int i = 0; i <data.size(); i++){
                    alSong.add(data.get(i));
                    //listStr.notifyDataSetChanged();
                }
            }
        });


        data = db.getAllSongs();

        strList = new ArrayList<String>();

        //ArrayAdapter listStr = new ArrayAdapter<>(MainActivity2.this, android.R.layout.simple_list_item_1,strList);
        //lvResults.setAdapter(listStr);
        alSong = new ArrayList<>();
        adapter = new CustomAdapter(MainActivity2.this,R.layout.row,alSong);

        lvResults.setAdapter(adapter);
        db.close();
        //listStr.clear();
        for(int i = 0; i <data.size(); i++){
            alSong.add(data.get(i));
            //listStr.notifyDataSetChanged();
        }

    }
}