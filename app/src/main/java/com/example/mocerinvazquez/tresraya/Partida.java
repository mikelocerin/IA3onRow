package com.example.mocerinvazquez.tresraya;



import java.util.ArrayList;
import java.util.Random;


public class Partida  {

    boolean turno = false;
    boolean partida = false;
    int jugador;
    int dificultad;
    int numjug;
    int[] array1={0,1,2};
    int[] array2={3,4,5};
    int[] array3={6,7,8};
    int[] array4={0,3,6};
    int[] array5={1,4,7};
    int[] array6={2,5,8};
    int[] array7={0,4,8};
    int[] array8={6,4,2};
    int[][] combo={array1,array2,array3,array4,array5,array6,array7,array8};
    ArrayList<Integer> casillasdisponibles=new ArrayList<Integer>(8);
    public Partida(int jugador,int dificultad){
        this.jugador=jugador;
        this.dificultad=dificultad;
        numjug=1;
        for(int i=0;i<=8;i++){
            casillasdisponibles.add(i);
        }

    }
    public boolean check(ArrayList<Integer> marcas) {
        int count=0;
        for(int[] array:combo) {
            for (int j : array) {
                for (int i = 0; i < marcas.size(); i++) {
                    if (marcas.get(i) == j) {
                        count++;
                        break;
                    }
                }
            }
            if(count==3){
                return true;
            }
            else {
                count = 0;
            }
        }

        return false;
    }
    public void changeturn() {
        if (numjug == 1) {
            numjug = 2;
        } else if (numjug == 2) {
            numjug = 1;
        }
    }
    public int turnCP(ArrayList<Integer>marca1,ArrayList<Integer>marca2){
        int marca=0;
        if(dificultad==1){
            Random ra=new Random();
           int index=ra.nextInt(casillasdisponibles.size());
            marca=casillasdisponibles.get(index);
        }
        else if(dificultad==3){
            // El ultimo condicional es en el caso de que la marca=0, ya que es lo devuelto y en realidad si que está ocupada
            //AUNQUE COUNT ES IGUAL A 2, SI LA CASILLA ESTÁ OCUPADA POR EL JUGADOR, NO ENTRA EN EL IF
            if(checkCount(marca2)==2&&marca1.contains(findMarca(marca2,marca1))==false&&marca2.contains(findMarca(marca2,marca1))==false){
                marca=findMarca(marca2,marca1);
            }
            else {
                MiniMax demo = new MiniMax(casillasdisponibles, marca1, marca2);
                marca = demo.optimo();
            }
        }
        return marca;
    }
    public int checkCount(ArrayList<Integer> marcas) {
        int count = 0;
        int max=0;
        for (int[] array : combo) {
            for (int j : array) {
                for (int i = 0; i < marcas.size(); i++) {
                    if (marcas.get(i) == j) {
                        count++;
                        break;
                    }
                }
            }
            if (count>max) {
                max=count;
            }
                count = 0;

        }
        return max;
    }
    public int findMarca(ArrayList<Integer>marca2,ArrayList<Integer>marca1){
        int count = 0;
        int marca=0;
        for (int[] array : combo) {
            for (int j : array) {
                for (int i = 0; i < marca2.size(); i++) {
                    if (marca2.get(i) == j) {
                        count++;
                        break;
                    }
                }
            }
            if (count == 2) {
                for (int j : array) {
                    //EN EL CASO DE TENER COUNT 2 EN DOS O  MAS DIRECCIONES BUSCA LA QUE NO ESTÉ OCUPADA POR JUGADOR
                    if (checkCombi(marca2) != 1) {
                        if (marca2.contains(j) == false && marca1.contains(j) == false) {
                            marca = j;
                            break;
                        }
                        //SI SOLO TIENE COUNT EN UNA DIRECCIÓN, ESTA PASARÁ A SER LA ELEGIDA
                    } else {
                        if (marca2.contains(j) == false) {
                            marca = j;
                            break;
                        }
                    }
                }
            }
            count=0;
        }
        return marca;
    }
    public int checkCombi(ArrayList<Integer>marca2){
        int count = 0;
        int count1=0;

        for (int[] array : combo) {
            for (int j : array) {
                for (int i = 0; i < marca2.size(); i++) {
                    if (marca2.get(i) == j) {
                        count++;
                        break;
                    }
                }
            }
            if (count==2) {
                count1++;
            }
            count = 0;

        }
        return count1;
    }



}
