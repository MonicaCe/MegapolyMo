/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package megapoly;

/**
 *
 * @author monic
 */
public class Cuenta_bancaria {
    
    private int saldo;
    private String titular;
    
    Cuenta_bancaria( String titular){
        this.titular=titular; 
        saldo=1000;
    }
    
    @Override
   public String toString(){
       return titular+" tu saldo actual es "+saldo;
   }
   
   public String getsaldo(){
       return "tu saldo actual es "+saldo;
   }
    
   public String getingreso(int ingreso){
       if(ingreso>=0){
            saldo+=ingreso;
            return  titular+" tu saldo actual es "+saldo;
       }else{
            return titular+ " no se ha podido efectuar el ingreso" ;
            }
   }
       
    public String getretirada(int retirada){
       if(saldo-retirada<=0){
           return titular+" no tienes suficiente saldo";
           
       }else{
           saldo-=retirada;
           return titular+" tu saldo es "+saldo;   
       }   
    }
       
    public String transferencia(int pago, Cuenta_bancaria cuentaDestino) {
        if (pago >= 0 && saldo >= pago) {
            saldo -= pago;  // Deduce el monto de la cuenta de origen
            cuentaDestino.saldo += pago;  // Agrega el monto a la cuenta de destino
            return "Transferencia realizada."+titular+" tu saldo actual: " + saldo;
        } else {
            return "No se ha podido realizar la transferencia";
        }
}
   
} 





