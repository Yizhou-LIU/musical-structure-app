package example.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    String mSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mSong = intent.getStringExtra("song");

        TextView tvDetail = (TextView)findViewById(R.id.tv_detail);
        tvDetail.setText(mSong);

        ImageButton imgSongPlay = (ImageButton)findViewById(R.id.imageButton);
        imgSongPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MusicPlayActivity.class);
                intent.putExtra("songname", mSong);
                startActivity(intent);
            }
        });
    }
}
