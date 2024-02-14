public class barco {

int longitud; //longitud del barco
String nombre; //nombre del barco
boolean[] partes; //partes del barco golpeadas
int contador; //contador para las partes hundidas



//CONSTRUCTOR
    public barco (String nombre){
        this.nombre = nombre; //nombre del barco pasado por parametro
        this.longitud = 3; //longitud estatica de los barcos (3 partes)
        this.partes = new boolean[longitud]; //array partes del barco inicializado en false
        for(int i= 0; i<partes.length;i++){
            partes[i] = false;
        }
        this.contador = 0; // contador inicializado a 0
    }   




//MARCA LA PARTE DEL BARCO GOLPEADA
    void hundirparte () {
    
    partes[contador] = true; //se inicializa a true la parte hundida
    contador ++; // se suma 1 al contador cada vez que se hunde una parte
    /* prueba para comoprobar que cada vez que se llama al metodo desde tablero.jugar se hunde una parte y se inicializa a true la posicion
    for(int i = 0; i<partes.length; i++){
        System.out.println(partes[i]);
    }
    */
    }


    

//DEVUELVE TRUE SI TODAS LAS PARTES DEL BARCO HAN SIDO GOLPEADAS
    boolean hasidohundido () {
        
    boolean hundido = false;

    if (partes[0] == true && partes[1] == true && partes[2] == true) { //si todas las partes del array han sido hundidas, entonces el barco se declara hundido
        hundido = true;
    } else {
        hundido = false;
    }
    return hundido;
    }




//NOMBRE DEL BARCO
    String getnombre(){
       return this.nombre;
    }
}
