package ch.fp.fp_kks;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Showing an PieChart for the correct and wrong answers
 */
public class PlayScoreActivity extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;

    float rainfall[] = {Background.correct, Background.wrong};
    String score[] = {"Correct", "Wrong"};
    ListView lvshow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_score);
        setupPieChart();

        mDatabaseHelper = new DatabaseHelper(this);

        final Button btnDone = (Button) findViewById(R.id.btnDone);
        lvshow = (ListView) findViewById(R.id.lvshow);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayScoreActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        populateListView();
    }

    /**
     * showing the PieChart
     */
    private void setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for (int i = 0; i < rainfall.length; i++) {
            pieEntries.add(new PieEntry(rainfall[i], score[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart) findViewById(R.id.PCScore);
        chart.setData(data);
        chart.invalidate();
    }
    private void populateListView() {
        Cursor data1 = mDatabaseHelper.getData(Background.ids);
        ArrayList<String> listData1 = new ArrayList<>();
        int id2 = 0;
        String Text = "Question         Answer        entered";
        listData1.add(Text);
        while (data1.moveToNext()) {
            Text = data1.getString(0) +"         "+data1.getString(1) +"        "+Background.result.get(id2);
            listData1.add(Text);
            id2++;
        }
        ListAdapter adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData1);
        lvshow.setAdapter(adapter1);
    }
}
