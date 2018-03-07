package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Chosing an Kategory from the Database
 */
public class PlayChouseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper mDatabaseHelper;

    Spinner spinner;

    private ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_chouse);

        mDatabaseHelper = new DatabaseHelper(this);

        Button btnPlay = (Button) findViewById(R.id.btnPlay);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayChouseActivity.this, PlayGameActivity.class);
                startActivity(intent);
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
     * updating the Spinner
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
    }

    /**
     * if Nothing is Selected
     *
     * @param arg0
     */
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    private void populateListView() {
        mDatabaseHelper.getSavedKartei(spinner.getSelectedItem().toString());
    }
}
