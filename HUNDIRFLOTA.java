public class HUNDIRFLOTA {
public static void main(String[] args) {
    barco[] barcos = {
        new barco("a"),
    };

    tablero tablero = new tablero(12, 12, barcos);
        tablero.inicializartablero();
        tablero.colocarbarcoentablero(barcos[0], 4, 7);
        tablero.jugar();
        
}
}