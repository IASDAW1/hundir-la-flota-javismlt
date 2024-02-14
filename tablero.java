import java.util.Scanner;

public class tablero {

char [][] tablero; //tablero del juego con barcos visibles
char [][] tablero1; //tablero del juego con barcos no visibles (tablero para el jugador)
barco[] barcos; //array de barcos
barco[][] barcotablero; //referencia de barcos




//CONSTRUCTOR
    public tablero (int filas, int columnas, barco[] barcos){
        this.tablero = new char [filas][columnas]; 
        this.tablero1 = new char [filas][columnas];
        this.barcos = barcos; 
        this.barcotablero = new barco[filas][columnas]; 
    }

    


//INICIALIZA EL TABLERO CON CARACTERES QUE REPRESENTAN EL AGUA (-)
    void inicializartablero() {
    //recorremos la matriz inicializando todas las posiciones con el valor que representa el agua (-)
    
    //TABLERO BARCOS VISIBLES
    for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = '-'; 
        }
    }

    //TABLERO DEL JUGADOR (BARCOS NO VISIBLES)
    for (int i = 0; i < tablero1.length; i++) {
        for (int j = 0; j < tablero1[i].length; j++) {
                tablero1[i][j] = '-'; 
        }
    }
    }




//IMPRIMIR TABLERO
    void imprimirtablero(){
    //TABLERO BARCOS VISIBLES
    /* 
    for (int i = 0; i < tablero.length; i++) {
        for (int j = 0; j < tablero[i].length; j++) {
            System.out.print(tablero[i][j] + " ");
        }
    System.out.println();
    }*/


    //TABLERO DEL JUGADOR, SOLO MUESTRA AGUA Y UNA X CUANDO ESTE HUNDE UNA PARTE DEL BARCO
    for (int i = 0; i < tablero1.length; i++) {
        for (int j = 0; j < tablero1[i].length; j++) {
            System.out.print(tablero1[i][j] + " ");
        }
    System.out.println();
    }

    
    //TABLERO CON LAS POSICIONES REFERENCIADAS A CADA BARCO
    /* 
    for (int i = 0; i < barcotablero.length; i++) {
        for (int j = 0; j < barcotablero[i].length; j++) {
            System.out.print(barcotablero[i][j] + " ");
        }
    System.out.println();
    }
    */
    }   




//VERIFICA SI ES POSIBLE COLOCAR UN BARCO
boolean puedecolocarbarco(barco barcos, int fila, int columna) {
    //comprobamos que la fila y columna pasadas por parametros son correctas.
    //establecemos delimitaciones, ya que si la fila y columna estan entre la longitud maxima del tablero tanto a lo ancho como largo, restandole tres (longitud del barco) no sera posible colocarlo.
    //tambien comprobamos que las coordenadas donde se desea colocar el barco sea agua y no haya ningun barco colocado.
    if (fila >= 0 && columna >= 0 && fila <= tablero.length - 3 && columna <= tablero[0].length - 3 
        && tablero[fila][columna] == '-' 
        && tablero[fila+1][columna] == '-' && tablero[fila+2][columna] == '-' //colocar de forma vertical
        && tablero[fila][columna+1] == '-' && tablero[fila][columna+2] == '-' ) {//colocar de forma horizontal
        return true;
    } else {
        return false;
    }
    }




//COLOCAR UN BARCO EN EL TABLERO
void colocarbarcoentablero(barco barcos, int fila, int columna){
    //comprobamos que el barco se pueda colocar en las coordenadas pasadas por parametro
    if (puedecolocarbarco(barcos, fila, columna) == true) {
        //generamos un numero (1 o 2) para determinar de forma aleatoria como se colocara el barco (vertical o horizonatal)
        int aleatorio = (int) ((Math.random() * 2 ) + 1);

        //colocamos el barco inicializando con el caracter 'O' las posiciones que el barco ocupara
        //HORIZONTAL
        if (aleatorio == 1) {
            for (int i = 0; i < 3; i++) {
                tablero[fila][columna + i] = 'O';
                barcotablero[fila][columna + i] = barcos; //guardamos en la matriz barcotalero que barco es el referenciado en cada posicion
            }
        } 

        //VERTICAL
        if (aleatorio == 2) {
            for (int i = 0; i < 3; i++) {
                tablero[fila + i][columna] = 'O';
                barcotablero[fila+i][columna] = barcos; //guardamos en la matriz barcotalero que barco es el referenciado en cada posicion
            }
        } 
    }
    }


    

//COLOCA BARCOS EN POSICIONES ALEATORIAS DEL TABLERO
    void colocarbarcos(barco[] barcos) {
    
    //recorremos el array que contiene los distintos barcos
    for (int i = 0; i < barcos.length; i++) {
    barco b = barcos[i];
    int intentos = 0;
    
    do {
    //generamos de forma aleatoria la fila y columna donde se colocara el barco
    int aleatorioColumna = (int) (Math.random() * (tablero[0].length - 3) + 1);
    int aleatorioFila = (int) (Math.random() * (tablero.length - 3) + 1);
    //si el barco puede ser colocado se colocara, en caso contrario se repetira el proceso hasta que este pueda ser colocado
        if (puedecolocarbarco(b, aleatorioFila, aleatorioColumna) == true) {
            colocarbarcoentablero(b, aleatorioColumna, aleatorioFila);
            break;
        }
    intentos++;
    } while (intentos < 1000000000); //para evitar un bucle infinito
    }
    }


    

//JUGAR
void jugar() {

    Scanner sc = new Scanner(System.in);

    int cantbarcos = barcos.length; //cantidad de barcos 
    //System.out.println(cantbarcos);

    System.out.println("HUNDIR LA FLOTA");
    System.out.println("---------------");
    System.out.println("*INSTRUCCIONES*");
    System.out.println("EL AGUA ESTA REPRESENTADA POR EL CARACTER ( - )");
    System.out.println("CUANDO UN BARCO SEA TOCADO, SERA REPRESENTADO POR EL CARACTER ( X )");
    System.out.println("INTRODUZCA LAS COORDENADAS PARA DISPARAR, EJE X E Y ( DESDE 0 A 11 )");
    System.out.println();
    while (cantbarcos > 0) { // bucle mientras queden barcos en el tablero el juego no acabara

        System.out.println("Ingrese la fila:");
        int fila = sc.nextInt();
        System.out.println("Ingrese la columna:");
        int columna = sc.nextInt();

        //delimitaciones de coordenadas
        if (fila < 0 || columna < 0 || fila >= tablero.length || columna >= tablero[0].length) {
            System.out.println("Coordenadas invalidas");
        }

        //si el disparo toca agua
        if (tablero[fila][columna] == '-') {
            System.out.println("¡Fallaste, has tocado agua!");
        }
        
        //si el disparo toca un barco
        if (tablero[fila][columna] == 'O') {
            //identificamos que barco ha sido golpeado
            barco barcogolpeado = barcotablero[fila][columna];
            System.out.println("¡Has golpeado el barco " + barcogolpeado.getnombre() + "!");
            //hundimos la parte golpeada y la marcamos con una X
            barcogolpeado.hundirparte();
            tablero1[fila][columna] = 'X'; 
            
            //comprobamos si el barco ha sido hundido completamente
            if (barcogolpeado.hasidohundido() == true) { 
                System.out.println("¡Has hundido el barco " + barcogolpeado.getnombre() + "!");
                cantbarcos--;
            }
        }

        imprimirtablero();
    }

    System.out.println("FIN DEL JUEGO, TODOS LOS BARCOS HAN SIDO HUNDIDOS");
}



}