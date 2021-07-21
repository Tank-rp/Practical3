package sg.edu.rp.c346.id20022678.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
 * I declare that this code was written by me.
 * I will not copy or allow others to copy my code.
 * I understand that copying code is considered as plagiarism.
 *
 * Student Name: Tan Ke Ting
 * Student ID: 200226678
 */

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etSalespot;
    TextView tvAllSalespot;
    Button btnInsert;
    ListView lv;
    ArrayList<Client> al;
    ArrayAdapter<Client> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper dbh = new DBHelper(MainActivity.this);
        etName = findViewById(R.id.editTextTextName);
        etSalespot = findViewById(R.id.editTextNumber);
        tvAllSalespot = findViewById(R.id.textView);
        btnInsert = findViewById(R.id.button);

        lv = findViewById(R.id.lv);

        al = new ArrayList<>();

        aa = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        if(dbh.getAllClients().size() == 0) {
        }
        else{
            al.clear();
            al.addAll(dbh.getAllClients());
        }
        aa.notifyDataSetChanged();

        int allSalespot = dbh.getAllSalespotential();
        tvAllSalespot.setText("Total Sales Potential +$" + String.valueOf(allSalespot));

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((etName.getText().toString().isEmpty()) || (etSalespot.getText().toString().isEmpty())){
                    Toast.makeText(MainActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    String name = etName.getText().toString();
                    String value= etSalespot.getText().toString();
                    int finalValue=Integer.parseInt(value);
                    dbh.insertClient(name,finalValue);
                    al.clear();
                    al.addAll(dbh.getAllClients());
                    aa.notifyDataSetChanged();
                    int allSalespot = dbh.getAllSalespotential();
                    tvAllSalespot.setText("Total Sales Potential $" + String.valueOf(allSalespot));
                }
            }
        });
    }
}