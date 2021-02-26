package com.example.mocerinvazquez.tresraya;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;;import java.util.ArrayList;

/**
 * TRES EN RAYA
 */

public class MainActivity extends Activity {

    int jugador;
    int dificultad;
    int[] casillas=new int[9];
    Partida game;
    boolean estado=false;
    ArrayList<Integer> marcas1=new ArrayList<Integer>();
    ArrayList<Integer> marcas2=new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Identificar casillas
        casillas[0]=R.id.a1;
        casillas[1]=R.id.a2;
        casillas[2]=R.id.a3;
        casillas[3]=R.id.b1;
        casillas[4]=R.id.b2;
        casillas[5]=R.id.b3;
        casillas[6]=R.id.c1;
        casillas[7]=R.id.c2;
        casillas[8]=R.id.c3;

    }
    public void start(View view){

        Button boton1=(Button) findViewById(R.id.unjug);
        Button boton2=(Button) findViewById(R.id.dosjug);
        RadioGroup group = (RadioGroup) findViewById(R.id.radio);

        setJugador(view);
        System.out.println("jugador :"+jugador);
        if(jugador==1) {
            setDificultad(group.getCheckedRadioButtonId());
            if(dificultad!=0){
                setblank();
                boton1.setEnabled(false);
                boton2.setEnabled(false);
                group.setAlpha(0);
                game=new Partida(jugador,dificultad);
            }
        }
        else if(jugador==2) {
            setblank();
            boton1.setEnabled(false);
            boton2.setEnabled(false);
            group.setAlpha(0);
            game=new Partida(jugador,dificultad);

        }

    }
    public int setJugador(View vista){
        if(vista.getId()==R.id.unjug){
            jugador=1;}
        else if(vista.getId()==R.id.dosjug){
            jugador=2;}

        return jugador;
        }
    public void setXO(View view) {

            if (game == null) {
                Toast error = Toast.makeText(this, "Inicie la partida", Toast.LENGTH_LONG);
                error.setGravity(Gravity.CENTER, 0, 0);
                error.show();

            } else if (game != null) {

                if (game.numjug == 1) {
                    int marca;
                    int indx1=0;
                    int indx2=0;
                    ImageView toque = (ImageView) findViewById(view.getId());
                    toque.setImageResource(R.drawable.aspa);
                    for (int i = 0; i <= 8; i++) {
                        if (casillas[i] == view.getId()) {
                            marca = i;
                            marcas1.add(marca);
                            if(jugador==1)
                                indx1= game.casillasdisponibles.indexOf(marca);
                                game.casillasdisponibles.remove(indx1);

                            break;
                        }
                    }
                    if (game.check(marcas1) == true) {
                        Toast error = Toast.makeText(this, "GANA JUGADOR 1", Toast.LENGTH_LONG);
                        error.setGravity(Gravity.CENTER, 0, 0);
                        error.show();
                        estado = true;
                    } else if (marcas1.size() == 5 && game.check(marcas1) == false) {
                        Toast error = Toast.makeText(this, "EMPATE", Toast.LENGTH_LONG);
                        error.setGravity(Gravity.CENTER, 0, 0);
                        error.show();
                        estado = true;
                    }
                    //automaticamente genera el turno del computer
                    if (jugador == 1&&estado==false) {
                        int marcaCP = game.turnCP(marcas1,marcas2);

                        //DIBUJAMOS LA CASIILLA CON EL ENTERO QUE NOS PASA EL METODO
                        ImageView toquecp = (ImageView) findViewById(casillas[marcaCP]);
                        toquecp.setImageResource(R.drawable.circulo);

                        marcas2.add(marcaCP);
                        indx2= game.casillasdisponibles.indexOf(marcaCP);
                        game.casillasdisponibles.remove(indx2);

                        if (game.check(marcas2) == true) {
                            Toast error = Toast.makeText(this, "GANA EL MOVIL", Toast.LENGTH_LONG);
                            error.setGravity(Gravity.CENTER, 0, 0);
                            error.show();
                            estado = true;
                        }

                    } else {
                        game.changeturn();
                    }
                }

                 else if (game.numjug == 2) {
                    int marca;

                    if(jugador==2) {
                        ImageView toque = (ImageView) findViewById(view.getId());
                        toque.setImageResource(R.drawable.circulo);
                        for (int i = 0; i <= 8; i++) {
                            if (casillas[i] == view.getId()) {
                                marca = i;
                                marcas2.add(marca);
                                break;
                            }
                        }
                    }

                    if (game.check(marcas2) == true) {
                        Toast error = Toast.makeText(this, "GANA JUGADOR 2", Toast.LENGTH_LONG);
                        error.setGravity(Gravity.CENTER, 0, 0);
                        error.show();
                        estado=true;

                    }

                    game.changeturn();
                }
            }
        fin();
    }
    public int setDificultad(int difi){
        System.out.println("dentro de setdificultad");


            switch (difi) {
                case R.id.facil:
                    dificultad = 1;
                    break;
                case R.id.medio:
                    dificultad = 2;
                    break;
                case R.id.impo:
                    dificultad = 3;
                    break;
                default:
                    Toast error=Toast.makeText(this,"Marque una dificultad",Toast.LENGTH_LONG);
                    error.setGravity(Gravity.CENTER,0,0);
                    error.show();
                    break;
            }
        System.out.println("dificultad :"+dificultad);
        return dificultad;
    }
    public void setblank(){
         for (int casilla : casillas) {
             ImageView imagen = (ImageView) findViewById(casilla);
             imagen.setImageResource(R.drawable.casilla);
         }
    }
    public void fin(){

        if(estado==true) {

            marcas1.clear();
            marcas2.clear();
            game=null;
            System.out.println("en fin");

            Button boton1 = (Button) findViewById(R.id.unjug);
            Button boton2 = (Button) findViewById(R.id.dosjug);
            RadioGroup group = (RadioGroup) findViewById(R.id.radio);
            boton1.setEnabled(true);
            boton2.setEnabled(true);
            group.setAlpha(1);
            estado=false;
        }
        else{
            System.out.println("continua jugando");
        }


    }





}
