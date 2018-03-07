package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.BaseKeyListener;
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

/**
 * Activity for editing the Database entitis
 */
public class EditActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper mDatabaseHelper;

    private ListView lvSaves;

    private Spinner spinner;

    private ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mDatabaseHelper = new DatabaseHelper(this);

        FloatingActionButton fabBack = (FloatingActionButton) findViewById(R.id.fabBack);
        FloatingActionButton fabEdit = (FloatingActionButton) findViewById(R.id.fabEdit);
        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabDelete);

        fabBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fabEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, EditTextActivity.class);
                startActivity(intent);
            }
        });

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Background.text2 = true;

                Intent intent = new Intent(EditActivity.this, Create2Activity.class);
                startActivity(intent);
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete();
            }
        });

        lvSaves = (ListView) findViewById(R.id.lvAncer);

        lvSaves.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDatabaseHelper.getDataForDelete(lvSaves.getItemAtPosition(position).toString().trim());

                Background.kategroy = false;
            }
        });

        spinner = (Spinner) findViewById(R.id.sSpinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();

        Cursor data = mDatabaseHelper.getKarteien();
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
            String Text = data.getString(0);
            listData1.add(Text);
        }
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        lvSaves.setAdapter(adapter);
    }

    /**
     * for deleting the selected row in the database
     */
    private void onDelete() {
        if (!Background.kategroy) {
            mDatabaseHelper.deleteDate(Background.ids);
            Background.ids = 0;
            populateListView();
        } else {
            mDatabaseHelper.deleteKartei(Background.ids);
            Intent intent = new Intent(EditActivity.this, EditActivity.class);
            startActivity(intent);
        }
    }

    /**
     * for Selecting an item in the Spinner
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        System.out.println(Background.ids);

        Background.kategroy = true;

        populateListView();
    }

    /**
     * in no item is Selected in Spinner
     *
     * @param arg0
     */
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
