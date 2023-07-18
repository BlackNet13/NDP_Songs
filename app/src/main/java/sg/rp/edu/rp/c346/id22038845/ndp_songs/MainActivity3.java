package sg.rp.edu.rp.c346.id22038845.ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3 extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText ed1, ed2, ed3;
    RadioGroup rdGrp;

    ArrayList<String> strList;

    int rating = 0;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        rdGrp = findViewById(R.id.rdGrp);

        Intent x = getIntent();
        data = (Song) x.getSerializableExtra("data");
        String title = data.getTitle();
        String singer = data.getSingers();
        int year = data.getYear();
        int stars = data.getStars();

        ed1.setText(title);
        ed2.setText(singer);
        ed3.setText(year+""); //will crash the app or make it act weirdly if you dont convert it to text.

        switch(stars){
            case 1:
                rdGrp.check(R.id.radio1);
                break;
            case 2:
                rdGrp.check(R.id.radio2);
                break;
            case 3:
                rdGrp.check(R.id.radio3);
                break;
            case 4:
                rdGrp.check(R.id.radio4);
                break;
            case 5:
                rdGrp.check(R.id.radio5);
                break;
        }

        rdGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio1){
                    rating = 1;
                }else if(checkedId == R.id.radio2){
                    rating = 2;
                }else if(checkedId == R.id.radio3){
                    rating = 3;
                }else if(checkedId == R.id.radio4){
                    rating = 4;
                } else if (checkedId == R.id.radio5) {
                    rating = 5;
                }
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                data.setSong(ed1.getText().toString(),ed2.getText().toString(), Integer.parseInt(ed3.getText().toString()), rating);
                dbh.updateSong(data);
                dbh.close();
                Toast toast = Toast.makeText(MainActivity3.this, "Song: <"+ ed1.getText()+"> has been updated",Toast.LENGTH_LONG);

                toast.show();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity3.this);
                dbh.deleteSong(data.getId());
                Toast toast = Toast.makeText(MainActivity3.this, "Song has been deleted",Toast.LENGTH_LONG);

                toast.show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

}