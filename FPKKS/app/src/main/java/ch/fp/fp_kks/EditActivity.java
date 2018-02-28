package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private ListView LVSaves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);

        LVSaves = (ListView) findViewById(R.id.listView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mDatabaseHelper.getUpdate();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onDelete();
            }
        });

        LVSaves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Background.text1 = (LVSaves.getItemAtPosition(position).toString().trim());
                mDatabaseHelper.getDataForDelete();
            }
        });

        populateListView();
    }
    /**
     * for showing all database enetys
     */
    private void populateListView() {
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData1 = new ArrayList<>();
        while (data.moveToNext()) {

            String Text = data.getString(1) +"    "+ data.getString(2);
            listData1.add(Text);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        LVSaves.setAdapter(adapter);
    }

    /**
     * for deleting the selected row in the database
     */
    private void onDelete() {
        mDatabaseHelper.deleteDate();
        Background.ids = 0;
        populateListView();
    }
}
