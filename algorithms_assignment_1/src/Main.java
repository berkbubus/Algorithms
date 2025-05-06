import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Main {
    static void reverse(int[] a, int n)
    {
        int i, j;
        for (i = 0; i < n / 2; i++) {
            j = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = j;
        }
    }
    public static void main(String[] args) throws IOException, CsvException {

        /*
        int[] deneme={234323,2323232,4343,2123,435454,22,11,1,2,3,4,5,6,10,9,8,7,22,2321,1,4,45,22,435,432,43,12,433,55,4,33,11,32,12};
        Sort sort= new Sort();
        for(int i:deneme){
            System.out.println(i);
        }
        System.out.println("----------------------------"+deneme.length);
        reverse(deneme,deneme.length);
        for(int i:deneme){
            System.out.println(i);
        }
        System.out.println("----------------------------"+deneme.length);
        */
        /// int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251281};
        CSVReader csvReader = new CSVReader(new FileReader("TrafficFlowDataset.csv"));
        csvReader.skip(1);
        Sort sort= new Sort();
        int[] list2 = new int[251282];
        String[] line;
        int b=0;
        while ((line = csvReader.readNext()) != null) {
            list2[b++]=(Integer.parseInt(line[7]));
        }
        // Create sample data for linear runtime
        double[][] yAxis = new double[4][10];
        double[][] yAxisS = new double[4][10];
        double[][] yAxisSR = new double[4][10];
        int a=0;

        for(int i:inputAxis){
            long start;
            int[] deneme2;
            int[] deneme3;
            int[] deneme4;
            long finish;
            long timeElapsed;
            int[] deneme=new int[i];
            for(int j=0;j<i;j++){
                deneme[j]=list2[j];
            }
            deneme4 = Arrays.copyOf(deneme,deneme.length);
            int max=Arrays.stream(deneme).max().getAsInt();
            for(int jj=0;jj<10;jj++){

                start = System.currentTimeMillis();
                deneme2=sort.Merge(deneme);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxis[1][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                deneme2=sort.Pigeonhole(deneme);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxis[2][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                deneme2=sort.Counting(deneme, max);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxis[3][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                sort.Insertion(deneme);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxis[0][a]+=timeElapsed/10.0;

                deneme=Arrays.copyOf(deneme4,deneme4.length);

                start = System.currentTimeMillis();
                deneme2=sort.Merge(deneme2);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisS[1][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                deneme2=sort.Pigeonhole(deneme2);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisS[2][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                deneme2=sort.Counting(deneme2, max);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisS[3][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                sort.Insertion(deneme2);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisS[0][a]+=timeElapsed/10.0;

                reverse(deneme2,deneme2.length);

                start = System.currentTimeMillis();
                deneme3=sort.Merge(deneme2);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisSR[1][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                deneme3=sort.Pigeonhole(deneme2);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisSR[2][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                deneme3=sort.Counting(deneme2, max);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisSR[3][a]+=timeElapsed/10.0;

                start = System.currentTimeMillis();
                sort.Insertion(deneme2);
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                yAxisSR[0][a]+=timeElapsed/10.0;
            }
            a++;
        }
        // Save the char as .png and show it

        showAndSaveChart("Random Data", inputAxis, yAxis);
        showAndSaveChart("Sorted Data", inputAxis, yAxisS);
        showAndSaveChart("Reversely Sorted Data", inputAxis, yAxisSR);
    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion", doubleX, yAxis[0]);
        chart.addSeries("Merge", doubleX, yAxis[1]);
        chart.addSeries("Pigeonhole", doubleX, yAxis[2]);
        chart.addSeries("Counting", doubleX, yAxis[3]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
