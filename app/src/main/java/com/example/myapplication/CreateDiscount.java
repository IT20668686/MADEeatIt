package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

import java.util.Calendar;

public class CreateDiscount extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    String date,item;
    String[] items={"Year End","New Year","Promotional"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    private TextInputEditText disName,disAmount;
    private Button btnSave;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_discount);
        disName=findViewById(R.id.disName);
        disAmount=findViewById(R.id.disAmount);
        btnSave=findViewById(R.id.save_button);
        autoCompleteTxt=findViewById(R.id.autoComplete);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                item=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item,Toast.LENGTH_SHORT).show();
            }
        });
        initDatePicker();
        dateButton=findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             reference=FirebaseDatabase.getInstance("https://mad-eatit-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("discount");
             String name=disName.getText().toString();
             String amount=disAmount.getText().toString();
             DiscountHelperClass helperClass=new DiscountHelperClass(name,item,amount,date);
                reference.push().setValue(helperClass);
                StyleableToast.makeText(CreateDiscount.this,"Discount Added successfully",Toast.LENGTH_SHORT,R.style.toaststyle).show();
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        month=month+1;
        int day=cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day,month,year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
               month=month+1;
                date=makeDateString(day,month,year);
               dateButton.setText(date);
            }
        };
        Calendar cal=Calendar.getInstance();
        int year=cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH);
        int day=cal.get(Calendar.DAY_OF_MONTH);
        int style= AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this,style,dateSetListener,year,month,day);
    }
    private String makeDateString(int day,int month,int year){
        return getMonthFormat(month)+" "+" "+day+" "+" "+year;
    }
    private String getMonthFormat(int month) {
        if (month==1)
            return "JAN";
        if (month==2)
            return "FEB";
        if (month==3)
            return "MAR";
        if (month==4)
            return "APR";
        if (month==5)
            return "MAY";
        if (month==6)
            return "JUN";
        if (month==7)
            return "JUL";
        if (month==8)
            return "AUG";
        if (month==9)
            return "SEP";
        if (month==10)
            return "OCT";
        if (month==11)
            return "NOV";
        if (month==12)
            return "DEC";
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }



}