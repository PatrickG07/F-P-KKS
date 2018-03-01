package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Create2 extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    ListView lvQuestion, lvAncer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create2);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnDone = (Button) findViewById(R.id.btnDone);

        lvQuestion = (ListView) findViewById(R.id.lvQuestion);
        lvAncer = (ListView) findViewById(R.id.lvAncer);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // adding Question and Ancer to Database and show it on top
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populateListView() {
        Cursor data1 = mDatabaseHelper.getDataQuestion(Background.ids);
        ArrayList<String> listData1 = new ArrayList<>();
        while (data1.moveToNext()) {

            String Text = data1.getString(1);
            listData1.add(Text);
        }
        ListAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        lvQuestion.setAdapter(adapter1);

        Cursor data2 = mDatabaseHelper.getDataAnswer(Background.ids);
        ArrayList<String> listData2 = new ArrayList<>();
        while (data2.moveToNext()) {

            String Text = data2.getString(2);
            listData2.add(Text);
        }
        ListAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        lvAncer.setAdapter(adapter2);
    }
}
