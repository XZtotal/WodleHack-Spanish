/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package wordlehack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author vilor
 */
public class WordleHack {
    
    private static final int maxPalabras = 90000; 

    private static String[] palabras = new String[90000];
    
    private static String malla = "-----";
    
    private static String[] letrasValidas = new String[50];
    private static int nLetrasValidas = 0;
    
    private static String[] letrasNoValidas = new String[50];
    private static int nLetrasNoValidas = 0;
    
            
            
    public static void main(String[] args) {
        // TODO code application logic here
        
        // lemario.txt palabrasRae.txt
        String nombreFichero = "lemario.txt";
        //Declarar una variable BufferedReader
        BufferedReader br = null;
        try {
           //Crear un objeto BufferedReader al que se le pasa 
           //   un objeto FileReader con el nombre del fichero
           br = new BufferedReader(new FileReader(nombreFichero));
           
           int n = 0;
           //Leer la primera línea, guardando en un String
           String texto = br.readLine();
           palabras[n]=texto;
           n++;
           //Repetir mientras no se llegue al final del fichero
           while(texto != null && n<maxPalabras)
           {
               //Hacer lo que sea con la línea leída
               //System.out.println(texto);
               //Leer la siguiente línea
               texto = br.readLine();
               palabras[n]=texto;
               n++;
           }
           palabras = acotarArrayString(n-1, palabras);
//            for(int i=0;i<100;i++){
//                //Hacer lo que sea con la línea leída
//               System.out.println(texto);
//               //Leer la siguiente línea
//               texto = br.readLine();
//            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if(br != null)
                    br.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
        
        //-------------------------------kkkkkkkkkkkkkkkkkkkkkkkkkkkkkk
        
        
        palabras = soloPalabrasConNLetras(5, palabras);
        
        
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        
        System.out.println("");
        
        quitarAcentos(palabras);
        //imprimirPalabras();
        //malla = "-ielo";
        //descartarPalabrasPorMalla();
        //imprimirPalabras();
        
        
        boolean as = true;
        while (as) {
            System.out.println("Letras Acertadas: "+ malla);            
            System.out.println("Letras Validas: "+ stringArrayATexto(letrasValidas));
            System.out.println("Letras No Validas: "+ stringArrayATexto(letrasNoValidas));
            
            String res = "";
            String res2 = "";
             //Letras Acertadas
            do {       
                System.out.println("Introduce una nueva letra acertada, enter para saltar");
                res = "";
                res = scanner.nextLine();
                
                if(res != "" && res.length() == 1){
                    System.out.println("Introduce la posicion (1 al 5), enter para saltar");
                    res2 = scanner.nextLine();
                    if(stringAInt(res2)>=1 && stringAInt(res2) <=5 ){
                        malla = remplazarLetra(stringAInt(res2)-1, res, malla);
                        System.out.println("Letras Acertadas: "+ malla); 
                        
                        letrasValidas[nLetrasValidas] = res;
                        nLetrasValidas++;
                    }
                }                
            } while (res != "");
            
            System.out.println("    ");
            
             //Letras Validas
            do {       
                
                System.out.println("Introduce una letra Valida, enter para saltar");
                res = "";
                res = scanner.nextLine();
                
                if(res != "" && res.length() == 1){
                    letrasValidas[nLetrasValidas] = res;
                    nLetrasValidas++;
                    System.out.println("Letras Validas: "+ stringArrayATexto(letrasValidas));
                    
                    }
                           
            } while (res != "");
            System.out.println("hecho!!! 2");
            
            //Letras No Validas
            do {       
                System.out.println("Introduce una letra NO Valida, enter para saltar");
                res = "";
                res = scanner.nextLine();
                
                if(res != "" && res.length() == 1){
                    letrasNoValidas[nLetrasNoValidas] = res;
                    nLetrasNoValidas++;
                    System.out.println("Letras No Validas: "+ stringArrayATexto(letrasNoValidas));
                    }
                           
            } while (res != "");
            System.out.println("hecho!!! 3");
            
            //Calculo
            
            descartarPalabrasPorMalla();
            descartarPalabrasPorLetrasValidas();
            descartarPalabrasPorLetrasNoValidas();
            
            System.out.println("PALABRAS POSIBLES: ");
            System.out.println("--------------- ");
           imprimirPalabras();
           System.out.println("--------------- ");
            
            
            
            
        }
        
        
        
    }
    
    
    public static String[] soloPalabrasConNLetras(int n,String[] s){
        String[] r = new String[s.length];
        int c = 0; //contador
        for(int i=0; i<s.length; i++){
            if (s[i] != null && s[i].length() == n) {
                r[c] = s[i];
                c++;
            }
        }
        return r;
    }
    
    public static String[] acotarArrayString(int maxN, String[] s){
        String[] r=new String[maxN];
        for(int i = 0; i<=maxN;i++){
            r[i]=s[i];
        }
        return r;
    }
    
    public static void quitarAcentos(String[] s){
        int i = 0;
        
        while( i < s.length && s[i] != null){
            String letras = "";
            for (int j = 0; j <s[i].length(); j++) {
                
                String letra = null;
                //System.out.println("assss -> " + s[i].charAt(j));
                switch(s[i].charAt(j)){
                    case 'á' -> {letra = "a"; }
                    case 'é' -> {letra = "e"; }
                    case 'í' -> {letra = "i"; }
                    case 'ó' -> {letra = "o"; }
                    case 'ú' -> {letra = "u"; }
                    
                        
                    
                }
                if(letra != null){
                    letras = letras + letra;
                }else{
                    letras = letras + s[i].charAt(j);
                }
                
            }
            s[i]=letras;
            i++;
        }
        
    }
    
    public static void imprimirPalabras(){
        int i = 0;
        while (i < palabras.length && palabras[i] != null) {            
            System.out.println(palabras[i]);
            i++;
        }
        
    }
    
    public static String remplazarLetra(int pos, String l, String s){
        String r = "";
        for (int i = 0; i < s.length(); i++) {
            if(i == pos){
                r = r + l;
            }else{
                r = r + s.charAt(i);
            }
        }
        
        
        return r;
    }
    
    public static String stringArrayATexto(String[] s){
        String r = "";
        int i = 0;
        while (i < s.length && s[i] != null) {            
            r = r + s[i] + ", ";
            i++;
        }
        
        return r;
    }
    
    public static int stringAInt(String s){
        int r = 0;
        
        try {
            Integer.parseInt(s);
            r = Integer.parseInt(s);
        } catch (Exception e) {
            r = 0;
        }
        return r;
    }
    
    public static void descartarPalabrasPorMalla(){
        int i = 0;
            while (i <= palabras.length && palabras[i] != null ) {                
                boolean valido = true;
                for (int j = 0; j < palabras[i].length(); j++) {
                    
                    if( malla.length() != palabras[i].length() || ( malla.charAt(j) != '-' && palabras[i].charAt(j) != malla.charAt(j) ) ){
                        valido = false;
                    }
                    
                }
                if(!valido){
                    palabras[i] = null;
                }
                
                
                i++;
            }
            ordenarStringArray(palabras);
    
    }
    
    public static void descartarPalabrasPorLetrasNoValidas(){
        
        for (int i = 0; i < nLetrasNoValidas ; i++) {
            
            int j = 0;
             while (j <= palabras.length && palabras[j] != null ) {
                 if(palabras[j].contains(letrasNoValidas[i])){
                     palabras[j]=null;
                 }
                 j++;
             }
            ordenarStringArray(palabras);
        }
        
            
    
    }
    
    public static void descartarPalabrasPorLetrasValidas(){
        
        for (int i = 0; i < nLetrasValidas ; i++) {
            
            int j = 0;
             while (j <= palabras.length && palabras[j] != null ) {
                 if(!palabras[j].contains(letrasValidas[i])){
                     palabras[j]=null;
                 }
                 j++;
             }
            ordenarStringArray(palabras);
        }
        
            
    
    }
    
    public static void ordenarStringArray(String[] s) {
        int c = 0;
        for (int i = 0; i < s.length; i++) {
            if( s[i] != null){
                if (c != i){
                   s[c] = s[i];  
                   s[i] = null;
                }
                c++;
            }
        }
    }
}
