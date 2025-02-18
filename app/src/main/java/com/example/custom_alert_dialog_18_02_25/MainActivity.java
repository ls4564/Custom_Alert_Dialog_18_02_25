package com.example.custom_alert_dialog_18_02_25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    TextView tv1,tv2,tv3,tv4;
    ListView lv;
    Intent gi;

    double[] arr = new double[20];
    boolean bool = false;
    double n1 = 3;
    double q = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weddings();
        update_data();
    }
    public void update_data()
    {
        tv1.setText(String.valueOf(n1));
        give_arr(n1,q,bool);//make

        String[] arrStr = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            arrStr[i] = String.format("%.2f", arr[i]);
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrStr);
        lv.setAdapter(adp);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
    }
    public void weddings()
    {
        /*
        This function wedding all the widgets
         */
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        lv = (ListView) findViewById(R.id.lv);
    }

    public void give_arr(double a , double k ,boolean option)
    {
        //input - the function get 2 doubles variable and one boolean, false - Engineering, true - Invoice
        //output - the function field the arr with the Invoicing or engineering series
        arr[0] = a;
        if (option)//Invoice
        {
            for (int i = 1; i < 20; i++) {
                arr[i] = arr[i-1] + k;
            }
        }
        else//Engineering
        {
            for (int i = 1; i < 20; i++)
            {
                arr[i] =  arr[i-1] * k;
            }
        }
    }
    public double sum_numbers(int index)
    {
        //input - the function get index of the arr
        //output - the function return the sum of the number between 0-index
        double sum = 0;
        for(int i = 0 ; i <= index ; i++)
        {
            sum = sum + arr[i];
        }
        return sum;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        tv2.setText(String.format("%.2f", arr[position]));
        tv3.setText( (position)+1 + " ");
        tv4.setText(String.format("%.2f",sum_numbers(position)) + "");
    }



}