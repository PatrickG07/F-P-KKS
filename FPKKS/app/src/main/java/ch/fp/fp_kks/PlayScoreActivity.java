package ch.fp.fp_kks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PlayScoreActivity extends AppCompatActivity {

    float rainfall[] ={Background.correct, Background.wrong};
    String score[] ={"Richtig", "Falsch"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_score);
        setupPieChart();
    }

    private void setupPieChart() {
        List<PieEntry> pieEntries = new ArrayList<>();
        for(int i = 0; 1 <rainfall.length; i++){
            pieEntries.add(new PieEntry(rainfall[i], score[i]));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries, "Richtig & Falsch");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart) findViewById(R.id.PCScore);
        chart.setData(data);
        chart.invalidate();
    }


}
