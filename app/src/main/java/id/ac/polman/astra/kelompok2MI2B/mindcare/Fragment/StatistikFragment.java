package id.ac.polman.astra.kelompok2MI2B.mindcare.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import id.ac.polman.astra.kelompok2MI2B.mindcare.R;

public class StatistikFragment extends Fragment {

    ArrayList<BarEntry> barArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistik, container, false);

        BarChart barChart = view.findViewById(R.id.barchart);
        getData();

        BarDataSet barDataSet = new BarDataSet(barArrayList, "Mood");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(getLabels()));

        return view;
    }

    private void getData() {
        barArrayList = new ArrayList<>();
        barArrayList.add(new BarEntry(0f, 2f));
        barArrayList.add(new BarEntry(1f, 4f));
        barArrayList.add(new BarEntry(2f, 6f));
        barArrayList.add(new BarEntry(3f, 8f));
        barArrayList.add(new BarEntry(4f, 10f));
    }

    private String[] getLabels() {
        return new String[]{"Sangat Buruk", "Buruk", "Normal", "Baik", "Sangat Baik"};
    }
}
