package org.elkuku.calculadordelivaec;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class CalculadorDelIVAEc extends ActionBarActivity {

    private EditText edtTotal;
    private TextView txtSinIVA;
    private TextView txtIVA;

    public CalculadorDelIVAEc() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculador_del_ivaec);

        Button btnClear = (Button) findViewById(R.id.btn_clear);

        edtTotal = (EditText) findViewById(R.id.edt_total);
        txtSinIVA = (TextView) findViewById(R.id.txt_siniva);
        txtIVA = (TextView) findViewById(R.id.txt_iva);

        btnClear.setOnClickListener(new View.OnClickListener() { public void onClick(View v) {
            edtTotal.setText("");
            txtSinIVA.setText("");
            txtIVA.setText("");
        }});

        TextWatcher textWatcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){  calculateResult(); }
        };

        edtTotal.addTextChangedListener(textWatcher);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculador_del_ivaec, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void calculateResult() throws NumberFormatException {
        double sinIVA, IVA, total;

        txtSinIVA = (TextView) findViewById(R.id.txt_siniva);
        txtIVA = (TextView) findViewById(R.id.txt_iva);

        Editable edit = edtTotal.getText();

        if (edit.toString().equals(""))
            return;

        try {
            total = Double.parseDouble(edit.toString());
        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Numero invalido", Toast.LENGTH_SHORT).show();
            return;
        }

        sinIVA = total / 1.12;
        IVA = total - sinIVA;

        txtSinIVA.setText(new DecimalFormat("#,###,##0.00").format(sinIVA));
        txtIVA.setText(new DecimalFormat("#,###,##0.00").format(IVA));
    }
}
