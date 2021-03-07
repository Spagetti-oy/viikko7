package com.example.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView text, text2, text3;
    String teksti;
    EditText textInput, textEditor;
    TextWatcher watcher = null;
    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        System.out.println("Kansio sijainti: " + context.getFilesDir());
        //this.testFunction();
        //testikommentti

        text = (TextView) findViewById(R.id.textView);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textEditor);

        textInput = (EditText) findViewById(R.id.textInput);
        textEditor = (EditText) findViewById(R.id.textEditor);
        watcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                teksti = textInput.getText().toString();
                text.setText(teksti);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        textInput.addTextChangedListener(watcher);
    }

    public void testFunction(View v) {
        System.out.println("Hello World!");
        teksti = textInput.getText().toString();
        text2.setText(teksti);

    }

    public void lataa(View v) {

        try {
            InputStream ins = context.openFileInput(teksti);

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            StringBuilder sb = new StringBuilder();
            String s = "";

            while ((s=br.readLine()) != null) {
                //System.out.println(s);
                sb.append(s);
                sb.append("\n");
            }
            text3.setText(sb);
            ins.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Ladattu");
        }
    }

    public void tallenna(View v) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(teksti, Context.MODE_PRIVATE));

            String s = "";
            s = teksti = textEditor.getText().toString();
            osw.write(s);
            osw.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Tallennettu");
        }
    }


}

