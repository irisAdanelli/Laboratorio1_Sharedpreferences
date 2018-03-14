package mx.edu.ittepic.laboratorio1_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nom, fech,horas, numPla, numPos, cel, dir;
    CheckBox basic, lujo;
    SeekBar meseros;
    Button save, get;
    int hr,npl,nps;
    TextView mostrarM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom = findViewById(R.id.nomCliente);
        fech= findViewById(R.id.fecha);
        horas = findViewById(R.id.horas);
        numPla = findViewById(R.id.numplatillos);
        numPos = findViewById(R.id.numpostres);
        cel = findViewById(R.id.cel);
        dir = findViewById(R.id.dir);

        basic = findViewById(R.id.basica);
        lujo = findViewById(R.id.lujo);
        mostrarM=findViewById(R.id.etiquetaseek);
        meseros = findViewById(R.id.seek);

        save=findViewById(R.id.btnsave);
        get=findViewById(R.id.btnget);

        //hr=Integer.parseInt(horas.getText().toString());
        //npl=Integer.parseInt(numPla.getText().toString());
        //nps=Integer.parseInt(numPos.getText().toString());


        meseros.setProgress(1);
        meseros.setMax(10);


        meseros.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //hace un llamado a la perilla cuando se arrastra
                    @Override
                    public void onProgressChanged(SeekBar seekBar1,
                                                  int progress, boolean fromUser) {
                        mostrarM.setText(String.valueOf(progress));
                    }

                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar1) {
                    }

                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar1) {
                    }
                }
        );


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createShared();
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readShared();
            }
        });

        //SharedPreferences preferences= getSharedPreferences("Credenciales",MODE_PRIVATE);
    }


    public void createShared(){
        SharedPreferences settings = getSharedPreferences("Eventos",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("nombre",nom.getText().toString());
        editor.putString("celular",cel.getText().toString());
        editor.putString("direc",dir.getText().toString());
        editor.putString("fecha",fech.getText().toString());
        editor.putString("hora",horas.getText().toString());
        editor.putString("numpl",numPla.getText().toString());
        editor.putString("numps",numPos.getText().toString());
        editor.putBoolean("basic",basic.isChecked());
        editor.putBoolean("lujo",lujo.isChecked());
        editor.putInt("seekm",Integer.parseInt(mostrarM.getText().toString()));



        editor.commit();
        Toast.makeText(this, "Guardado...", Toast.LENGTH_SHORT).show();
    }

    private void readShared(){
        SharedPreferences settings = getSharedPreferences("Eventos",MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();


        String nombr = settings.getString("nombre",nom.getText().toString());
        String celulars = settings.getString("celular",cel.getText().toString());
        String direction = settings.getString("direc",dir.getText().toString());
        String fechas = settings.getString("fecha",fech.getText().toString());
        String hour = settings.getString("hora",horas.getText().toString());
        String nplat = settings.getString("fecha",fech.getText().toString());
        String npostre = settings.getString("numps",numPos.getText().toString());
        Boolean bas = settings.getBoolean("basic",basic.isChecked());
        Boolean luj = settings.getBoolean("lujo",lujo.isChecked());
        Integer progreso = settings.getInt("seekm",0);

        nom.setText(nombr);
        cel.setText(celulars);
        dir.setText(direction);
        fech.setText(fechas);
        horas.setText(""+hour);
        numPla.setText(""+nplat);
        numPos.setText(""+npostre);
        basic.setChecked(bas);
        lujo.setChecked(luj);
        //mostrarM.setText(String.valueOf(progreso));
        meseros.setProgress(progreso);


        Toast.makeText(this, "Reading...", Toast.LENGTH_SHORT).show();
    }
}
