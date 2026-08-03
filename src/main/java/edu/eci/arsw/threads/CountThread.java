/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *  @author Marco Alvarez
 *  @author Andres Sabogal
 *  @author hcadavid
 */
public class CountThread extends Thread {

    private int a,b;

    /*
     * Esta clase inicial donde se le definen los números a imprimir.
     */

    public CountThread(int a,int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public void run(){
        System.out.println("El hilo Inicia ");
        this.Imprimir(a,b);
        System.out.println("El hilo se termina ");
    }

    /*
     * Esta clase le llegan dos numeros enteros y su funcion es
     * imprimir los numeros en orden ascendente
     */

    public void Imprimir(int a,int b){
        for(int i = a;i<=b;i++){
            System.out.println(i);
        }
    }

}

