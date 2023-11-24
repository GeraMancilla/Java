package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa el numero de alumnos: ");
        int numAlumnos = entrada.nextInt();
        System.out.println("Ingresa el numero de Parciales: ");
        int numParciales = entrada.nextInt();
        //Defino la matriz
        double notas[][] = new double[numAlumnos][numParciales];
        //Para lo de arriba de la tabla 
        String elementoA[]=new String[numParciales];
        //Lleno el arreglo de arriba
        for(int i=0;i<numParciales;i++){
            elementoA[i]="Parcial "+(i+1);
        }
        //Para lo de la izquierda de la tabla
        String elementoB[]=new String[numAlumnos];
        //Lleno el arreglo de la izquierda
        for(int i =0;i<numAlumnos;i++){
            elementoB[i]="Alumno "+(i+1);
        }

        //Lleno la matriz
        for (int index = 0; index < notas.length; index++) {
            System.out.println("Ingresa la calificacion para el alumno "+(index+1)+": ");
            for (int jindex = 0; jindex < notas[index].length; jindex++) {
                System.out.println("Calificacion Parcial "+(jindex+1)+": ");
                double calif = entrada.nextDouble();
                notas[index][jindex] += calif;
            }
        }
        // Mostrar Matriz
        System.out.println("\nMatriz de Calificaciones:");
        System.out.print("        |");
        for(int i=0;i<numParciales;i++){
            System.out.printf("%10s |", elementoA[i]);
        }
        System.out.println();
        for(int i=0;i<numAlumnos;i++){
            System.out.printf("%7s |", elementoB[i]);
            for(int j =0;j<numParciales;j++ ){
                System.out.printf("%10.2f |", notas[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        //Arreglos para reprobados y aprobados
        int reprobadosP[]=new int[numParciales];
        int aprobadosP[]=new int[numParciales];
        //Lleno los arreglos de reprobados y aprobados
        for (int i = 0; i < numAlumnos; i++) {
            for(int j = 0;j<numParciales;j++){
                if(notas[i][j]<70){
                    reprobadosP[j]+=1;
                }else if(notas[i][j]>=70){
                    aprobadosP[j]+=1;
                }
            }
           

        }
        //Imprimo los arreglos
        for(int l = 0; l < numParciales; l++){
                System.out.println("Total aprobados parcial "+(l+1)+": "+aprobadosP[l]);
        }
        System.out.println();
        for(int l = 0; l < numParciales; l++){
                System.out.println("Total reprobados parcial "+(l+1)+": "+reprobadosP[l]);
        }

        System.out.println();
        Porcentajes(numParciales, numAlumnos, notas, suma(reprobadosP, aprobadosP));
        int suma[] = suma(reprobadosP, aprobadosP);
        System.out.println("Resumen:");
        System.out.println("Total aprobados: "+suma[1]);
        System.out.println("Total reprobados: "+suma[0]);
        System.out.println("Total alumnos: "+numAlumnos);
        System.out.println("Total parciales: "+numParciales);
    }

    public static void Porcentajes(int numParciales,int numAlumnos,double notas[][],int suma[]){
        double PorceA = (double) suma[0]/ (numAlumnos * numParciales) * 100;
        double PorceB = (double) suma[1]/ (numAlumnos * numParciales) * 100;
        System.out.println("Porcentajes:");
        System.out.println("Aprobados: "+PorceB+"%");
        System.out.println("No aprobados: "+PorceA+"%");
    }
    public static int[] suma(int reprobadosP[],int aprobadosP[]){
        int sumaA = 0;
        int sumaB = 0;
        for(int i = 0;i<reprobadosP.length;i++){
            sumaA += reprobadosP[i];
        }
        for (int i =0;i < aprobadosP.length;i++){
            sumaB += aprobadosP[i];
        }
        int suma[] = {sumaA,sumaB};
        return suma;

    }

}