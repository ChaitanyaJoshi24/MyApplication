package com.chetz.myapplication.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.chetz.myapplication.data.Math;
import com.chetz.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.edt_boxone)
    TextView edtBoxOne;
    @BindView(R.id.btn_addition)
    Button btnAddition;
    @BindView(R.id.btn_subtraction)
    Button btnSubtraction;
    @BindView(R.id.btn_multiplication)
    Button btnMultiplication;
    @BindView(R.id.btn_division)
    Button btnDivision;
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_four)
    Button btnFour;
    @BindView(R.id.btn_five)
    Button btnFive;
    @BindView(R.id.btn_six)
    Button btnSix;
    @BindView(R.id.btn_seven)
    Button btnSeven;
    @BindView(R.id.btn_eight)
    Button btnEight;
    @BindView(R.id.btn_nine)
    Button btnNine;
    @BindView(R.id.btn_zero)
    Button btnZero;
    @BindView(R.id.btn_clear)
    Button btnClear;
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.btn_modulo)
    Button btnModulo;

    boolean add,sub,mul,div;
    String numberOneStr,numberTwoStr;



    Math calc = new Math();
//    int numberOne = Integer.parseInt(edtBoxOne.getText().toString());
//    int numberTwo = Integer.parseInt(edtBoxTwo.getText().toString());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this);
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            SharedPreferences sharedPreferences = getSharedPreferences("myapplication", MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();
            Intent mainIntent = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btn_one)
    public void one(){
        edtBoxOne.setText(edtBoxOne.getText() + "1");
    }

    @OnClick(R.id.btn_two)
    public void two(){
        edtBoxOne.setText(edtBoxOne.getText() + "2");
    }

    @OnClick(R.id.btn_three)
    public void three(){
        edtBoxOne.setText(edtBoxOne.getText() + "3");
    }

    @OnClick(R.id.btn_four)
    public void four(){
        edtBoxOne.setText(edtBoxOne.getText() + "4");
    }

    @OnClick(R.id.btn_five)
    public void five(){
        edtBoxOne.setText(edtBoxOne.getText() + "5");
    }

    @OnClick(R.id.btn_six)
    public void six(){
        edtBoxOne.setText(edtBoxOne.getText() + "6");
    }

    @OnClick(R.id.btn_seven)
    public void seven(){
        edtBoxOne.setText(edtBoxOne.getText() + "7");
    }

    @OnClick(R.id.btn_eight)
    public void eight(){
        edtBoxOne.setText(edtBoxOne.getText() + "8");
    }

    @OnClick(R.id.btn_nine)
    public void nine(){
        edtBoxOne.setText(edtBoxOne.getText() + "9");
    }

    @OnClick(R.id.btn_zero)
    public void zero(){
        edtBoxOne.setText(edtBoxOne.getText() + "0");
    }

    @OnClick(R.id.btn_addition)
    public void addition() {
        numberOneStr = edtBoxOne.getText().toString();
        add = true;
        edtBoxOne.setText(null);

    }

    @OnClick(R.id.btn_subtraction)
    public void subtraction() {
        numberOneStr = edtBoxOne.getText().toString();
        sub = true;
        edtBoxOne.setText(null);
    }

    @OnClick(R.id.btn_multiplication)
    public void multiplication() {
        numberOneStr = edtBoxOne.getText().toString();
        mul = true;
        edtBoxOne.setText(null);
    }

    @OnClick(R.id.btn_division)
    public void division() {
        numberOneStr = edtBoxOne.getText().toString();
        div = true;
        edtBoxOne.setText(null);
    }

    @OnClick(R.id.btn_equal)
    public void equal() {
        numberTwoStr = edtBoxOne.getText().toString();
        double numberOne = Double.parseDouble(numberOneStr);
        double numberTwo = Double.parseDouble(numberTwoStr);
        double result = 0;


        if (add == true){

            result = calc.addition(numberOne, numberTwo);
            add = false;
        }

        else if (sub == true){
            result = calc.subtraction(numberOne, numberTwo);
            sub = false;
        }

        else if (mul == true){
            result = calc.multiplication(numberOne, numberTwo);
            mul = false;
        }

        else if (div == true){
            result = calc.division(numberOne, numberTwo);
            div = false;
        }
        edtBoxOne.setText(String.valueOf((result)));
    }

    @OnClick(R.id.btn_clear)
    public void clear() {
        edtBoxOne.setText("");
    }

    @OnClick(R.id.btn_dot)
    public void dot(){
        edtBoxOne.setText(edtBoxOne.getText() + ".");
    }

}






