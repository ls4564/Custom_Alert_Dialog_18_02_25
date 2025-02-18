package com.example.custom_alert_dialog_18_02_25;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    TextView tv1,tv2,tv3,tv4;
    ListView lv;
    EditText eD1 , eD2;
    LinearLayout xml_dialog;
    AlertDialog.Builder adb;

    double[] arr = new double[20];
    boolean bool = false;
    double n1 = 0;
    double q = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weddings();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        tv2.setText(String.format("%.2f", arr[position]));
        tv3.setText( (position)+1 + " ");
        tv4.setText(String.format("%.2f",sum_numbers(position)) + "");
    }

    public void data_cliced(View view)
    {
        xml_dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.layout,null);
        eD1 = (EditText) xml_dialog.findViewById(R.id.eD1);
        eD2 = (EditText) xml_dialog.findViewById(R.id.eD2);

        adb = new AlertDialog.Builder(this);
        adb.setView(xml_dialog);
        adb.setPositiveButton("Enter",myclick);
        adb.setNeutralButton("Cancel",myclick);
        adb.show();
    }


    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            if(which == DialogInterface.BUTTON_POSITIVE)
            {
                String str1, str2;
                str1 = eD1.getText().toString();
                str2 = eD1.getText().toString();
                if(!check_legal_input(str1) && !check_legal_input(str2))
                {
                    n1 =  Double.parseDouble(str1);
                    q =  Double.parseDouble(str2);
                    update_data();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Illegal input", Toast.LENGTH_SHORT).show();

                }
            } else if (which == DialogInterface.BUTTON_NEGATIVE)
            {
                dialog.cancel();
            }
        }
    };

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

    public boolean check_legal_input(String input)
    {
        // true not good
        // false  good
        if(input.isEmpty() || input.isEmpty() || input.equals(".") || input.equals("-") || input.equals(".-") || input.equals("-.") || input.equals("+") || input.equals(".+") || input.equals("+."))
        {
            return true;
        }
        return false;
    }
}