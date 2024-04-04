/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package megapoly;

/**
 *
 * @author monic
 */
public class Jugador {

     private final String nombre;
     private int dinero;
     private int posicion;
   

    
  
     
   Jugador(String nombre){//constructor
       this.nombre=nombre;
       dinero=1000;
       posicion=0;
       
   }

   
    public String getTurno(){
        
        return "Es tu turno "+nombre;
    }
    
    public String transaccion(int dinero){
        this.dinero+=dinero;
        return "Ahora tienes "+dinero;
    }
    
    public void movimiento(int tirada, int tamanoTablero){
        posicion+=tirada;
        if (posicion >= tamanoTablero) {
        posicion -= tamanoTablero;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public int getDinero() {
        return dinero;
    }

    public int getPosicion(){
        return posicion;
    }

    public void setPosicion(int indice) {
        this.posicion = indice;
    }
    
   
    
    
}
