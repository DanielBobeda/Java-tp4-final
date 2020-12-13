/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fecha;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Gabriel
 */
public class MiCalendario extends GregorianCalendar {

    public MiCalendario(int dia, int mes, int anio) throws MiCalendarioException {
        super(anio, mes-1, dia);
        
        // Apagamos la permisividad
        setLenient(false);
        
        // Lanzar una exception
        try {
            get(YEAR);
        }
        catch (IllegalArgumentException ex) {
            throw new MiCalendarioException("Error al formar fecha ==> "+ ex.getMessage());
        }
    }
    // crea el metodo que luego usa en read de alumnoDAOSQL
    public MiCalendario(Date date) {
        //pasa el date de MySQL a milisegundos, le da formato para crearlo en alumnoDAOSQL
        this.setTimeInMillis(date.getTime());
        //en calendar es setTimeInMillis en SQL es getTime
    }
    //constructor para hacer un calendario
    public MiCalendario(Calendar cal) {
        //formo el objeto en milisegundos
        this.setTimeInMillis(cal.getTimeInMillis());
        //se usa en mostrar alumnoDialog para pasar el formato de le fecha
    }

    public int getDia() {
        
        return get(DAY_OF_MONTH);
    }
            
    public int getMes() {
        
        return get(MONTH)+1;
    }

    public int getAnio() {
        
        return get(YEAR);
    }
    
    @Override
    public String toString() {
        
        return String.format("%02d/%02d/%04d", getDia(), getMes(), getAnio());
    }

    public Date getSqlDate() {
        // devuelve un java sql date, para cargarlo en MySQL
        return new Date(this.getTimeInMillis());
    }    
}
