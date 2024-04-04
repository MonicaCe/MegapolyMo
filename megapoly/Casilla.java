/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package megapoly;

import javax.swing.JPanel;

/**
 *
 * @author monic
 */
public class Casilla {
   
    private String nombre;
    private TipoCasilla tipo;
    private int precio;
    private Jugador propietario;
    private boolean comprada;
   

    
    

    public Casilla(String nombre, TipoCasilla tipo, int precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.propietario = null;
        this.comprada=false;
    }
    
    public enum TipoCasilla {
        CALLE,
        SUERTE,
        CARCEL,
        IMPUESTOS,
        SALIDA,
        DIRECTOCARCEL,
        DESCANSO
    }

    public String getNombre() {
        return nombre;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public int getPrecio() {
        return precio;
    }

    public Jugador getPropietario() {
        return propietario;
    }

    public void setPropietario(Jugador propietario) {
        this.propietario = propietario;
    }
     public boolean isComprada() {
        return comprada;
    }

}
