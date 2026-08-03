/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Marco Alvarez
 * @author Andres Sabogal
 * @author hcadavid
 */
public class CountThreadsMain{

    public ArrayList<Integer> numeros = new ArrayList<>();

    public static void main(String[] args){
       // ---------------------------------------------//
        int tamaño,parte,a,b,sobrantes,indice;
        ArrayList<Integer> Lista1 = new ArrayList<>();
        ArrayList<Integer> Lista2 = new ArrayList<>();
        ArrayList<Integer> Lista3 = new ArrayList<>();
        // ---------------------------------------------//
        System.out.print("Ingrese los números a imprimir");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIngrese el primer numero ");
        a = sc.nextInt();
        System.out.print("\nIngrese el segundo numero ");
        b = sc.nextInt();
        CountThreadsMain Contador = new CountThreadsMain();
        // ---------------------------------------------//
        if(Contador.orden(a,b)){
            Contador.lista(a,b);
        }else {
            Contador.lista(b,a);
        }
        // ---------------------------------------------//
        tamaño = Contador.numeros.size();
        parte = tamaño / 3;
        sobrantes = tamaño % 3;
        indice = 0;
        // ---------------------------------------------//
        for (int i = 0; i < parte; i++) {
            Lista1.add(Contador.numeros.get(indice++));
        }
        // ---------------------------------------------//
        for (int i = 0; i < parte; i++) {
            Lista2.add(Contador.numeros.get(indice++));
        }
        // ---------------------------------------------//
        for (int i = 0; i < parte+sobrantes; i++) {
            Lista3.add(Contador.numeros.get(indice++));
        }
        // ---------------------------------------------//
        CountThread contador1 = new CountThread(Lista1.get(0),Lista1.get(Lista1.size()-1));
        CountThread contador2 = new CountThread(Lista2.get(0),Lista2.get(Lista2.size()-1));
        CountThread contador3 = new CountThread(Lista3.get(0),Lista3.get(Lista3.size()-1));
        // ---------------------------------------------//
        //contador1.run();
        //contador2.run();
        //contador3.run();
        // ---------------------------------------------//
        contador1.start();
        contador2.start();
        contador3.start();
        // ---------------------------------------------//
    }

    /*
     * Esta clase le llegan dos numeros enteros y su función es
     * ordenar decir si "a" es menor igual a "b"
     */

    public boolean orden(int a, int b){
        return a <= b;
    }

    /*
     * Esta clase le llegan dos numeros enteros y su función es
     * crear una lista
     */

    public void lista(int a, int b){
        for (int i = a; i <= b; i++) {
            numeros.add(i);
        }
    }



}
