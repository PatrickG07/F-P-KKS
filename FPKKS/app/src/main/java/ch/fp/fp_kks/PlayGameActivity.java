package ch.fp.fp_kks;

import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class PlayGameActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    private TextView tvQuestion;

    String text1, text2;

    Cursor data;

    int id;

    ArrayList<String> listData1 = new ArrayList<>();
    ArrayList<String> listData2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__game);

        mDatabaseHelper = new DatabaseHelper(this);

        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabPrevius);
        FloatingActionButton fabDone = (FloatingActionButton) findViewById(R.id.fabDone);
        FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.fabNext);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        EditText etAncer = (EditText) findViewById(R.id.etAncer);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listData1.get(id) == listData2.get(id)) {
                    Background.correct++;
                } else {
                    Background.wrong++;
                }

                id++;

                populateListView();
            }
        });

        data = mDatabaseHelper.getData(Background.ids);

        while (data.moveToNext()) {
            text1 = data.getString(1);
            listData1.add(text1);
            text2 = data.getString(0);
            listData2.add(text2);
        }

        populateListView();
    }

    /**
     * for showing all database enetys
     */
    private void populateListView() {
        listData1.get(id);
    }
}
