package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Adding data to the Database with the FK from Create1Activity
 */
public class Create2Activity extends AppCompatActivity {

    String newEntry1, newEntry2;

    DatabaseHelper mDatabaseHelper;

    ListView lvQuestion, lvAnswer;

    EditText etQuestion, etAnswer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create2);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnDone = (Button) findViewById(R.id.btnDone);

        lvQuestion = (ListView) findViewById(R.id.lvQuestion);
        lvAnswer = (ListView) findViewById(R.id.lvAncer);

        etQuestion = (EditText) findViewById(R.id.etQuestion);
        etAnswer = (EditText) findViewById(R.id.etAncer);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = etQuestion.getText().toString();
                String text2 = etAnswer.getText().toString();
                if (text1.matches("") || text2.matches("")) {
                    Toast.makeText(Create2Activity.this, "Bitte definieren Sie eine Frage wie auch eine Antwort!", Toast.LENGTH_SHORT).show();
                } else {
                    newEntry1 = String.valueOf(etQuestion.getText());
                    newEntry2 = String.valueOf(etAnswer.getText());
                    mDatabaseHelper.addData(newEntry1, newEntry2, Background.ids);
                    populateListView();
                    etAnswer.setText("");
                    etQuestion.setText("");
                }
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(Background.text2);
                if (!Background.text2) {
                    Intent intent = new Intent(Create2Activity.this, MainActivity.class);
                    startActivity(intent);

                } else if (Background.text2) {
                    Intent intent = new Intent(Create2Activity.this, EditActivity.class);
                    startActivity(intent);
                }
            }
        });

        populateListView();
    }

    /**
     * Showing all data with that Kategory in ListViews
     */
    private void populateListView() {
        Cursor data1 = mDatabaseHelper.getData(Background.ids);
        ArrayList<String> listData1 = new ArrayList<>();
        ArrayList<String> listData2 = new ArrayList<>();
        while (data1.moveToNext()) {
            String Text = data1.getString(0);
            listData1.add(Text);
            Text = data1.getString(1);
            listData2.add(Text);
        }
        ListAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        lvQuestion.setAdapter(adapter1);

        ListAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData2);
        lvAnswer.setAdapter(adapter2);
    }
}
