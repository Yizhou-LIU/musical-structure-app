package example.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {
    static final String[] song =  new String[] { "song1", "song2", "song3"};
    public static int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        ArrayList<String> report = new ArrayList<String>();
        report.add("song1");
        report.add("song2");
        report.add("song3");

        ListView listItem = (ListView)findViewById(R.id.listView);
        ListAdapter mAdapter = new ListAdapter(this, song);
        listItem.setAdapter(mAdapter);

        listItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MusicActivity.this, DetailActivity.class);
                num = i;
                intent.putExtra("song", song[i]);
                startActivity(intent);
            }
        });
    }
}
