package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayGameActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    private TextView tvQuestion;

    String text1, text2;

    Cursor data;

    int id, id2;

    ArrayList<String> listData1 = new ArrayList<>();
    ArrayList<String> listData2 = new ArrayList<>();

    ArrayList<String> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play__game);

        Background.correct = 0;
        Background.wrong = 0;

        mDatabaseHelper = new DatabaseHelper(this);

        final FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabPrevius);
        final FloatingActionButton fabDone = (FloatingActionButton) findViewById(R.id.fabDone);
        FloatingActionButton fabNext = (FloatingActionButton) findViewById(R.id.fabNext);

        tvQuestion = (TextView) findViewById(R.id.tvQuestion);

        final EditText etAncer = (EditText) findViewById(R.id.etAncer);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id--;

                populateListView();
            }
        });

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Finish();

                Intent intent = new Intent(PlayGameActivity.this, PlayScoreActivity.class);
                startActivity(intent);
            }
        });

        fabNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id == id2) {
                    result.add(etAncer.getText().toString());
                } else {
                    result.set(id, etAncer.getText().toString());
                }

                if (listData1.size() - 1 != id) {
                    if (id == id2) {
                        id2++;
                    }
                    id++;
                } else {
                    fabDone.setVisibility(View.VISIBLE);
                }

                if (id > 0) {
                    fabBack.setVisibility(View.VISIBLE);
                }

                etAncer.setText("");

                populateListView();
            }
        });

        data = mDatabaseHelper.getData(Background.ids);

        while (data.moveToNext()) {
            text1 = data.getString(0);
            listData1.add(text1);
            text2 = data.getString(1);
            listData2.add(text2);
        }
        populateListView();
    }

    /**
     * for showing all database enetys
     */
    private void populateListView() {
        tvQuestion.setText(listData1.get(id));
    }

    /**
     * checks the Array lists
     */
    private void Finish() {
        for (int j = 0; j < listData1.size(); j++) {
            if (listData2.get(j).trim().equals(result.get(j).trim())) {
                Background.correct++;
            } else {
                Background.wrong++;
            }
        }
    }
}
