package com.chetz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalculatorActivity extends AppCompatActivity {
    @BindView(R.id.edt_boxone)
    EditText edtBoxOne;
    @BindView(R.id.edt_boxtwo)
    EditText edtBoxTwo;
    @BindView(R.id.txt_result)
    TextView txtResult;
    @BindView(R.id.btn_addition)
    Button btnAddition;
    @BindView(R.id.btn_subtraction)
    Button btnSubtraction;
    @BindView(R.id.btn_multiplication)
    Button btnMultiplication;
    @BindView(R.id.btn_division)
    Button btnDivision;


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
            Intent mainIntent = new Intent(CalculatorActivity.this, MainActivity.class);
            startActivity(mainIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick(R.id.btn_addition)
    public void addition() {
        Character add = '+';
        calculator(edtBoxOne.getText().toString(), edtBoxTwo.getText().toString(), add);

    }

    @OnClick(R.id.btn_subtraction)
    public void subtraction() {
        Character sub = '-';
        calculator(edtBoxOne.getText().toString(), edtBoxTwo.getText().toString(), sub);
    }

    @OnClick(R.id.btn_multiplication)
    public void multiplication() {
        Character mul = '*';
        calculator(edtBoxOne.getText().toString(), edtBoxTwo.getText().toString(), mul);
    }

    @OnClick(R.id.btn_division)
    public void division() {
        Character div = '/';
        calculator(edtBoxOne.getText().toString(), edtBoxTwo.getText().toString(), div);
    }

    public void calculator(String numberOneStr, String numberTwoStr, Character operation) {
        int numberOne = Integer.parseInt(numberOneStr);
        int numberTwo = Integer.parseInt(numberTwoStr);
        int result = 0;

        if (operation.equals('+')) {
            result = calc.addition(numberOne, numberTwo);

        } else if (operation.equals('-')) {
            result = calc.subtraction(numberOne, numberTwo);

        } else if (operation.equals('*')) {
            result = calc.multiplication(numberOne, numberTwo);

        } else if (operation.equals('/')) {
            result = calc.division(numberOne, numberTwo);

        }
        txtResult.setText(String.valueOf((result)));
    }

}






