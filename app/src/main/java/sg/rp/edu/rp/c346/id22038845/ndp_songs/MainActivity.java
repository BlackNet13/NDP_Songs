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

public class MainActivity extends AppCompatActivity {


    Button btnInsert, btnShowList;
    EditText ed1, ed2, ed3;
    RadioGroup rdGrp;

    ArrayList<String> strList;

    int rating = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        rdGrp = findViewById(R.id.rdGrp);

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);

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

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);


                db.insertSong(ed1.getText().toString(),ed2.getText().toString(),ed3.getText().toString(), rating);
                Toast toast = Toast.makeText(MainActivity.this, "Song: <"+ ed1.getText()+"> has been inserted",Toast.LENGTH_LONG);

                toast.show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                startActivity(intent);
            }
        });

    }
}