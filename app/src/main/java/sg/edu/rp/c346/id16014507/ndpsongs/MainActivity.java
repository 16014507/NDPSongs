package sg.edu.rp.c346.id16014507.ndpsongs;

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

    EditText etSongTitle, etSingers, etYear;
    RadioGroup rgStars;
    Button btnInsert, btnShowList;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnUpdate);
        btnShowList = findViewById(R.id.btnDelete);

        al = new ArrayList<>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etSongTitle.getText().toString();
                String singers = etSingers.getText().toString();
                Integer year = Integer.parseInt(etYear.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singers, year, getStars());

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowList.class);
                startActivity(intent);
            }
        });

    }
    private int getStars() {
        int stars = 1;
        if(rgStars.getCheckedRadioButtonId() == R.id.rbtn1) {
            stars = 1;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rbtn2) {
            stars = 2;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rbtn3) {
            stars = 3;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rbtn4) {
            stars = 4;
        }
        else if(rgStars.getCheckedRadioButtonId() == R.id.rbtn5) {
            stars = 5;
        }
        return stars;
    }
}