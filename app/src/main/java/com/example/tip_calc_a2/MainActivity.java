package com.example.tip_calc_a2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int procent = 5;
    double napiwek;
    double kwota = 0.00;
    double suma_koncowa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // do wysuwanego paska, czyli spinner text
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Opinia, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //do kalkulatora
        final EditText kwota_rachunku = (EditText) findViewById(R.id.kwota_rachunku);
        final TextView procent_napiwku = (TextView) findViewById(R.id.procent_napiwku);
        final TextView kwota_napiwku = (TextView) findViewById(R.id.kwota_napiwku);
        final TextView suma = (TextView) findViewById(R.id.suma);
        Button licz = (Button) findViewById(R.id.licz);
        Button minus = (Button) findViewById(R.id.minus);
        Button plus = (Button) findViewById(R.id.plus);

        //do gwiazdek
        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);
        Button potwierdz = (Button) findViewById(R.id.potwierdz);
        final TextView ratingTextView = (TextView) findViewById(R.id.wynik_opinii);

        //implementacja przycisku, który potwierdza przyznanie danej ilości gwiazdek
        potwierdz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ratingTextView.setText("Twoja opinia to: " + ratingRatingBar.getRating());
            }
        });

        //funkcja, która przelicza ile wynosi dany napiwek, formatuje wyniki oraz oblicza sume końcową
        licz.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                String rachunek_napis = kwota_rachunku.getText().toString();
                if(!rachunek_napis.equals(""))
                {
                    //Formatowanie wyniku, czyli kwoty rachunku
                    kwota = Double.valueOf(rachunek_napis);
                    kwota = kwota * 100;
                    kwota = Math.round(kwota);
                    kwota = kwota / 100;

                    kwota_rachunku.setText(String.format(Locale.getDefault(), "%.2f", kwota));
                    //Formatowanie wyniku, czyli napiwku
                    napiwek = (kwota * procent) / 100;
                    napiwek = napiwek * 100;
                    napiwek= Math.round(napiwek);
                    napiwek = napiwek / 100;

                    kwota_napiwku.setText(String.format(Locale.getDefault(), "%.2f", napiwek ));
                    //Suma końcowa, czyli suma kwoty i napiwku
                    suma_koncowa = kwota + napiwek;
                    suma.setText(String.format(Locale.getDefault(), "%.2f", suma_koncowa ));
                }
            }

        });
    /*
    Poniżej funkcja, która odpowiada za to, aby móc zmniejszać wartość procentową napiwku
     */
        minus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                if (procent > 0)
                {
                    procent--;
                    procent_napiwku.setText(procent + "%");
                }
            }

        });
    /*
        Poniżej funkcja, która odpowiada za to, aby móc zwiększać wartość procentową napiwku
         */
        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                procent++;
                procent_napiwku.setText(procent + "%");

            }

        });
    }
/*
Funkcje, które są potrzebne do zrobienia wysuwanego paska i wybieranie opcji na temat jedzenia
 */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long g)
    {
        String text = adapterView.getItemAtPosition(i).toString();
        //Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }
}
