/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package megapoly;



import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import static megapoly.Casilla.TipoCasilla.CALLE;
import static megapoly.Casilla.TipoCasilla.CARCEL;
import static megapoly.Casilla.TipoCasilla.DIRECTOCARCEL;
import static megapoly.Casilla.TipoCasilla.IMPUESTOS;
import static megapoly.Casilla.TipoCasilla.SALIDA;
import static megapoly.Casilla.TipoCasilla.SUERTE;

/**
 *
 * @author monic
 */
public class Juego {

    private final Jugador[] jugadores;
    private final Casilla[] tablero;
    private int jActual;

    public Juego(String nJ1, String nJ2) {
        this.tablero = new Casilla[16];
        this.jugadores = new Jugador[2];
        jugadores[0] = new Jugador(nJ1);
        jugadores[1] = new Jugador(nJ2);
        this.jActual = 0;
        
        // Inicializar jugadores
        /*for (int i = 0; i < jugadores.length; i++) {
            String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del Jugador " + (i + 1));
            jugadores[i] = new Jugador(nombre);
        }*/
        //casillas
        tablero[0] = new Casilla("salida", Casilla.TipoCasilla.SALIDA, 0);
        tablero[1] = new Casilla("Calle Albondiga", Casilla.TipoCasilla.CALLE, 20);
        tablero[2] = new Casilla("Impuesto al lujo", Casilla.TipoCasilla.IMPUESTOS, 0);
        tablero[3] = new Casilla("Calle Hamburguesa", Casilla.TipoCasilla.CALLE, 20);
        tablero[4] = new Casilla("Carcel", Casilla.TipoCasilla.CARCEL, 0);
        tablero[5] = new Casilla("Calle Carbonara", Casilla.TipoCasilla.CALLE, 20);
        tablero[6] = new Casilla("Suerte", Casilla.TipoCasilla.SUERTE, 0);
        tablero[7] = new Casilla("Calle Bolognesa", Casilla.TipoCasilla.CALLE, 20);
        tablero[8] = new Casilla("Suerte", Casilla.TipoCasilla.DESCANSO, 0);
        tablero[9] = new Casilla("Calle Sushi", Casilla.TipoCasilla.CALLE, 20);
        tablero[10] = new Casilla("Hacienda somos todos", Casilla.TipoCasilla.IMPUESTOS, 0);
        tablero[11] = new Casilla("Calle Ramen", Casilla.TipoCasilla.CALLE, 20);
        tablero[12] = new Casilla("Ve a la carcel", Casilla.TipoCasilla.DIRECTOCARCEL, 0);
        tablero[13] = new Casilla("Calle Salmorejo", Casilla.TipoCasilla.CALLE, 20);
        tablero[14] = new Casilla("Suerte", Casilla.TipoCasilla.SUERTE, 0);
        tablero[15] = new Casilla("Calle Flamenquín", Casilla.TipoCasilla.CALLE, 20);
       
    }
    
    public Jugador getJactual() {
        return jugadores[jActual];
    }
    
    public void avanzarTurno() {
        jActual++;
        if (jActual >= jugadores.length) {
            jActual = 0; // Reiniciar al principio del array cuando se alcanza el final
        }
    }
    
    public int tirarDado() {
        Dado dado = new Dado(); // Crear una instancia de la clase Dado
        return dado.tirarDado(); // Llamar al método tirarDado() de la clase Dado
    }
    
    public int getSaldoJugador1() {
        return jugadores[0].getDinero();
    }
    public int getSaldoJugador2() {
        return jugadores[1].getDinero();
    }
    
    public void realizarTurno() {

        int carcel = 4;
        int turnosCarcel = 0;
        
        while (jugadores[0].getDinero() > 0 && jugadores[1].getDinero() > 0) {
            
            Jugador jugadorActual = jugadores[jActual];
            
            System.out.println("Turno de " + jugadorActual.getNombre());
           
                int tirada= tirarDado();
                //comprueblo si el jugador comienza el turno en la cacel
                if (jugadorActual.getPosicion() == carcel) {
                    if (tirada >= 5) {
                        System.out.println("Enhorabuena " + jugadorActual.getNombre() + ", has salido de la cárcel");

                    } else {
                        System.out.println("no sales de la cárcel :/");
                        turnosCarcel++;
                        continue;
                    }
                }

                int posicionAnterior = jugadorActual.getPosicion();
                 
            
            
                System.out.println("Has sacado un " + tirada);

                jugadorActual.movimiento(tirada, 16);

                System.out.println(jugadorActual.getPosicion());
                if (posicionAnterior + tirada > tablero.length) {
                    System.out.println("recibes 20mm");
                }

                System.out.println("has caido en " + tablero[jugadorActual.getPosicion()].getNombre());
                Casilla casillaActual = tablero[jugadorActual.getPosicion()];

                switch (casillaActual.getTipo()) {

                    case CALLE:
                        if (casillaActual.getPropietario() != null && casillaActual.getPropietario() != jugadorActual) {
                            jugadorActual.transaccion(-20);
                            casillaActual.getPropietario().transaccion(20);
                            System.out.println("Has caído en la propiedad de " + casillaActual.getPropietario().getNombre() + ". Pagas 20mm.");
                            System.out.println(jugadorActual.getNombre() + " tu saldo actual es " + jugadorActual.getDinero());
                        } else if (casillaActual.getPropietario() == jugadorActual) { 
                            System.out.println(jugadorActual.getNombre() + " esta calle es de tu propiedad");
                        } else if (casillaActual.getPropietario() == null) {
                            String pregunta = "Deseas comprar la calle" + casillaActual.getNombre() + "?";

                            Object[] opciones = {"Si", "No"};
                            try {
                                int respuesta = JOptionPane.showOptionDialog(null,
                                        pregunta,
                                        "Compra de calle",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        opciones,
                                        opciones[0]);
                                switch (respuesta) {
                                    case 0 -> {
                                        if (jugadorActual.getDinero() >= 50) {

                                            jugadorActual.transaccion(-50);
                                            casillaActual.setPropietario(jugadorActual);

                                            System.out.println(jugadorActual.getNombre() + " has comprado " + casillaActual.getNombre());
                                            System.out.println("tu saldo actual es " + jugadorActual.getDinero());

                                        } else {
                                            System.out.println("lo siento no tienes suficiente mm. Otra vez será");
                                        }
                                    }
                                    case 1 ->
                                        System.out.println("uuuuhh huelo el miedo a la bancarrota...");

                                }

                            } catch (HeadlessException e) {
                                System.err.println("Error: " + e.getMessage());
                            }
                        }

                            break;

                        

                    case CARCEL:
                    
                            if(turnosCarcel==0){
                            System.out.println(jugadorActual.getNombre() + " has caido en carcel, para salir de ella saca un 5 o un 6");
                            }
                            break;

                    case DIRECTOCARCEL:

                        jugadorActual.setPosicion(carcel);
                        System.out.println(jugadorActual.getNombre() + " vas directamente a la cárcel.");
                        break;

                    case IMPUESTOS:
                        System.out.println("oh oh... Impuesto especial de la banca. Paga 10mm");
                        jugadorActual.transaccion(-10);
                        System.out.println("tu saldo actual es " + jugadorActual.getDinero());
                        break;

                    case SUERTE:

                        switch (tirarDado()) {

                            case 1, 2 -> {
                                System.out.println("Has ganado un premio en el concurso de belleza. Recibe 20 Mm.");
                                jugadorActual.transaccion(20);
                                System.out.println(jugadorActual.getNombre() + " tu saldo actual es " + jugadorActual.getDinero());
                            }
                            case 3, 4 -> {
                                System.out.println("Hoy es tu cumpleaños. Recibe 10 Mm de tu contrincante.");
                                jugadorActual.transaccion(10);
                                Jugador otroJugador = jugadores[0];
                                if (jugadorActual == jugadores[0]) {
                                    otroJugador = jugadores[1];
                                }
                                otroJugador.transaccion(-10);
                                System.out.println(jugadorActual.getNombre() + " tu saldo actual es " + jugadorActual.getDinero());
                                System.out.println(otroJugador.getNombre() + " tu saldo actual es " + otroJugador.getDinero());
                            }

                            case 5, 6 -> {
                                System.out.println("Recibes una multa de tráfico. Paga 10 Mm");
                                jugadorActual.transaccion(-10);
                                System.out.println(jugadorActual.getNombre() + " tu saldo actual es " + jugadorActual.getDinero());
                            }

                        }
                        break;

                    case SALIDA:
                        System.out.println("Has caido en la salida, recibes 20 Mm");
                        jugadorActual.transaccion(20);
                        System.out.println(jugadorActual.getNombre() + " tu saldo actual es " + jugadorActual.getDinero());
                        break;

                }
                JOptionPane.showMessageDialog(null, "Haz clic en 'Aceptar' para avanzar al siguiente turno");
                avanzarTurno(); // Llama al método para avanzar al siguiente turno
                }
       
        }

    }

    

