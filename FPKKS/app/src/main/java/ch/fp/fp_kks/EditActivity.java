package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper mDatabaseHelper;
    private ListView LVSaves;

    Spinner spinner;

    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mDatabaseHelper = new DatabaseHelper(this);

        FloatingActionButton btnBack = (FloatingActionButton) findViewById(R.id.btnBack);
        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        FloatingActionButton btnDelete = (FloatingActionButton) findViewById(R.id.btnDelete);

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

        LVSaves = (ListView) findViewById(R.id.lvAncer);

        LVSaves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Background.text1 = (LVSaves.getItemAtPosition(position).toString().trim());
                mDatabaseHelper.getDataForDelete();
            }
        });

        spinner = (Spinner) findViewById(R.id.sSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();

        Cursor data = mDatabaseHelper.getKarteien();//Background.ids);
        while (data.moveToNext()) {
            String Text = data.getString(1);
            categories.add(Text);
        }

        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        populateListView();
    }
    /**
     * for showing all database enetys
     */
    private void populateListView() {

        mDatabaseHelper.getSavedKartei(spinner.getSelectedItem().toString());
        Cursor data = mDatabaseHelper.getData(Background.ids);
        ArrayList<String> listData1 = new ArrayList<>();
        while (data.moveToNext()) {
            String Text = data.getString(1);
            listData1.add(Text);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        LVSaves.setAdapter(adapter);
    }

    /**
     * for deleting the selected row in the database
     */
    private void onDelete() {
        mDatabaseHelper.deleteDate(Background.ids);
        Background.ids = 0;
        populateListView();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        populateListView();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
