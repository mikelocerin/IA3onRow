package com.example.mocerinvazquez.tresraya;

import java.util.ArrayList;

public class MiniMax {

    ArrayList<Integer>tablahipotesis;
    ArrayList<Integer>marca1;
    ArrayList<Integer>marca2;
    int[] array1={0,1,2};
    int[] array2={3,4,5};
    int[] array3={6,7,8};
    int[] array4={0,3,6};
    int[] array5={1,4,7};
    int[] array6={2,5,8};
    int[] array7={0,4,8};
    int[] array8={6,4,2};
    int[][] combo={array1,array2,array3,array4,array5,array6,array7,array8};
    int marcafinal;
    public MiniMax(ArrayList<Integer>tabla,ArrayList<Integer>marca1,ArrayList<Integer>marca2){
        this.marca1=new ArrayList<Integer>(marca1);
        this.tablahipotesis=new ArrayList<Integer>(tabla);
        this.marca2=new ArrayList<Integer>(marca2);


    }
    public int optimo() {
        int marcahip=0;
        int aux=-99;
        int max=-99;

        for(int i=0;i<tablahipotesis.size();i++) {
            marcahip=tablahipotesis.get(i);
            marca2.add(marcahip);
            tablahipotesis.remove(i);
            if(check(marca2)==true) {
                marcafinal=marcahip;
            }
            else if(tablahipotesis.size()==0) {
                marcafinal=marcahip;
            }
            else if(check(marca2)==false &&tablahipotesis.size()!=0) {
                aux=min(tablahipotesis);
            }
            if(aux>max) {
                max=aux;
                marcafinal=marcahip;

            }
            if(tablahipotesis.size()==0) {
                tablahipotesis.add(marcahip);
                marca2.remove(marca2.indexOf(marcahip));
            }
            else {
                tablahipotesis.add(i, marcahip);;
                marca2.remove(marca2.indexOf(marcahip));
            }

        }
        return marcafinal;
    }
    public int min(ArrayList<Integer> tablerohip) {
        int marcahip;
        int min=99;
        int aux=99;

        for(int i=0;i<tablerohip.size();i++) {
            marcahip=tablerohip.get(i);
            marca1.add(marcahip);
            tablerohip.remove(i);
            if(check(marca1)==true) {
                min=-1;
            }
            else if(tablerohip.size()==0) {
                min=0;
            }
            else if(check(marca1)==false &&tablerohip.size()!=0) {
                aux=max(tablerohip);
            }
            if(aux<min) {
                min=aux;
            }
            if(tablerohip.size()==0) {
                tablerohip.add(marcahip);
                marca1.remove(marca1.indexOf(marcahip));
            }
            else {
                tablerohip.add(i, marcahip);
                marca1.remove(marca1.indexOf(marcahip));
            }
        }
        return min;
    }
    public int max(ArrayList<Integer> tablerohip) {
        int marcahip=0;
        int aux=-99;
        int max=-99;

        for(int i=0;i<tablerohip.size();i++) {
            marcahip=tablerohip.get(i);
            marca2.add(marcahip);

            tablerohip.remove(i);
            if(check(marca2)==true) {
                max=1;
            }
            else if(tablerohip.size()==0) {
                max=0;
            }
            else if(check(marca2)==false &&tablerohip.size()!=0) {
                aux=min(tablerohip);
            }
            if(aux>max) {
                max=aux;
            }
            if(tablerohip.size()==0) {
                tablerohip.add(marcahip);
                marca2.remove(marca2.indexOf(marcahip));
            }
            else {
                tablerohip.add(i, marcahip);
                marca2.remove(marca2.indexOf(marcahip));
            }

        }
        return max;

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

}
