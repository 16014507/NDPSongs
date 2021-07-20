package sg.edu.rp.c346.id16014507.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {
    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton rbtn1, rbtn2, rbtn3, rbtn4, rbtn5;
    Button btnUpdate, btnDelete, btnCancel;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etID = findViewById(R.id.etSongID);
        etTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);
        rbtn4 = findViewById(R.id.rbtn4);
        rbtn5 = findViewById(R.id.rbtn5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent = getIntent();
        song = (Song) intent.getSerializableExtra("song");
        etID.setText(String.valueOf(song.get_id()));
        etTitle.setText(String.valueOf(song.getTitle()));
        etSingers.setText(song.getSingers());
        etYear.setText(song.getYear());
        int stars = song.getStars();
        if (stars == 1) {
            rbtn1.setChecked(true);
        }
        else if (stars == 2) {
            rbtn2.setChecked(true);
        }
        else if (stars == 3) {
            rbtn3.setChecked(true);
        }
        else if (stars == 4) {
            rbtn4.setChecked(true);
        }
        else if (stars == 5) {
            rbtn5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.setTitle(etTitle.getText().toString());
                song.setSingers(etSingers.getText().toString());
                song.setYear(Integer.parseInt(etYear.getText().toString()));
                song.setStars(getStars());
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.updateSong(song);
                setResult(RESULT_OK);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(song.get_id());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
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