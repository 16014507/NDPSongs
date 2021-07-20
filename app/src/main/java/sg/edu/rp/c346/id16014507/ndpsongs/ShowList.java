package sg.edu.rp.c346.id16014507.ndpsongs;

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

public class ShowList extends AppCompatActivity {
    Button btn5Star;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        btn5Star = findViewById(R.id.btnShow5Stars);
        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(ShowList.this);

        al = new ArrayList<>();
        al = dbh.getAllSongs();
        aa = new ArrayAdapter<Song>(ShowList.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();

        btn5Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al = dbh.getFiveStar(5);
                aa.notifyDataSetChanged();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = al.get(position);

                Intent i = new Intent(ShowList.this, EditActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowList.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
        dbh.close();
    }

}