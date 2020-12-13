/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

/**
 *
 * @author Gabriel
 */
public class PersonaException extends Exception {

    public PersonaException(String mensaje) {
        // Invocamos al ctor de Exception
        super(mensaje);
    }
    
}
