package com.example.custom_alert_dialog_18_02_25;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

import java.text.DecimalFormat;

/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    /**
     * The Tv 1.
     */
    TextView tv1,
    /**
     * The Tv 2.
     */
    tv2,
    /**
     * The Tv 3.
     */
    tv3,
    /**
     * The Tv 4.
     */
    tv4;
    /**
     * The Lv.
     */
    ListView lv;
    /**
     * The E d 1.
     */
    EditText eD1 ,
    /**
     * The E d 2.
     */
    eD2;
    /**
     * The Xml dialog.
     */
    LinearLayout xml_dialog;
    /**
     * The Adb.
     */
    AlertDialog.Builder adb;

    /**
     * The Arr.
     */
    double[] arr = new double[20];
    /**
     * The Bool.
     */
    boolean bool = false;
    /**
     * The N 1.
     */
    double n1 = 0;
    /**
     * The Q.
     */
    double q = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weddings();
    }

    /**
     * Update data.
     */
    public void update_data()
    {
        tv1.setText(String.valueOf(n1));
        give_arr(n1,q,bool);//make

        String[] arrStr = new String[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            arrStr[i] = give_format(arr[i]);
        }
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrStr);
        lv.setAdapter(adp);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setOnItemClickListener(this);
    }

    /**
     * Weddings.
     */
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

    /**
     * Give arr.
     *
     * @param a      the a
     * @param k      the k
     * @param option the option
     */
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
        tv2.setText(give_format(arr[position]));
        tv3.setText( (position)+1 + " ");
        tv4.setText(give_format(sum_numbers(position)));
    }

    /**
     * Data cliced.
     *
     * @param view the view
     */
    public void data_cliced(View view)
    {
        xml_dialog = (LinearLayout) getLayoutInflater().inflate(R.layout.layout,null);
        eD1 = (EditText) xml_dialog.findViewById(R.id.eD1);
        eD2 = (EditText) xml_dialog.findViewById(R.id.eD2);

        adb = new AlertDialog.Builder(this);
        adb.setView(xml_dialog);
        adb.setTitle( "Data Settings:" ) ;
        adb.setPositiveButton("Enter",myclick);
        adb.setNeutralButton("Cancel",myclick);
        adb.show();
    }


    /**
     * The Myclick.
     */
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

    /**
     * Sum numbers double.
     *
     * @param index the index
     * @return the double
     */
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

    /**
     * Give format string.
     *
     * @param num the num
     * @return the string
     */
    public String give_format(double num)
    {
        String format = Double.toString(num);
        if(format.replace(".", "").length() > 10)
        {
            DecimalFormat scientificFormat = new DecimalFormat("0.#########E0");
            format = scientificFormat.format(num);
            return format;
        }
        else
        {
            return String.format("%.2f",num);
        }
    }

    /**
     * Check legal input boolean.
     *
     * @param input the input
     * @return the boolean
     */
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

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.it2)
        {
            Intent si = new Intent(this, MainActivity2.class);
            startActivity(si);
        }
        return true;
    }
}