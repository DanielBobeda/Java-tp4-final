/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import fecha.MiCalendario;
import fecha.MiCalendarioException;

/**
 *
 * @author Gabriel
 */
public class Alumno extends Persona {

    private Double promedio;
    
    private Integer cantMatAprob;

    //fechaIng;
    private MiCalendario fechaIng;
    
    private char estado;
    

    
    public Alumno() {
        super();
    }

    public Alumno(Double promedio, Integer cantMatAprob, Integer dni, String apyNom, Character sexo, 
            MiCalendario fechaNac,char estado,MiCalendario fechaIng) throws PersonaException {
        super(dni, apyNom, sexo, fechaNac);
        this.promedio = promedio;
        this.cantMatAprob = cantMatAprob;
        this.fechaIng = fechaIng;
        setEstado(estado);
    }

        //Crea el metodo getFechaIng, si pide getFechaIng, devuelve FechaIng.
    public MiCalendario getFechaIng() {
        return fechaIng;
    }
    //Crea el metodo setFechaIng con el atributo fechaIng y lo guarda en el fechaIng de la clase
    public void setFechaIng(MiCalendario fechaIng) {
        this.fechaIng = fechaIng;
    }
    //Crea el metodo getPromedio
    public Double getPromedio() {
        return promedio;
    }
    //Crea el metodo setPromedio
    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
    //Crea el metodo getCantMatAprob
    public Integer getCantMatAprob() {
        return cantMatAprob;
    }
    //Crea el metodo setCantMatArob
    public void setCantMatAprob(Integer cantMatAprob) {
        this.cantMatAprob = cantMatAprob;
    }

    @Override
    public String toString() {
        // Parte cargada, cuando llamen al metodo devuelve:
        return super.toString()+DELIM+String.format("%s",promedio)+DELIM+String.format("%02d",cantMatAprob)+DELIM+fechaIng+DELIM+String.format("%c",estado); 
    }
    
    public static Alumno string2Alumno(String[] campos) throws PersonaException, MiCalendarioException {
        //crea alu y lo instancia con un nuevo alumno
        Alumno alu = new Alumno();
        //crea index en 0
        int index = 0;
        //llama al setDni, le asigna el campo 0 sin fomrato string y aumenta index +1
        alu.setDni(Integer.valueOf(campos[index++]));
        //llama setApyNom, le asigna el campo 1 sin string y aumenta index +1
        alu.setApyNom(campos[index++]);
        //llama al setSexo, le asigna campo 2 y aumenta index +1
        alu.setSexo(campos[index++].charAt(0));
        
        // 23/08/1974
        //crea un string separado por 3 partes "/" (split separa las partes)
        //le asigna a campos el valor de index para posicionarse en la fecha txt
        //Crea fechaNac y le asigna el contenido string del campo fecha
        String[] fechaNac = campos[index++].split("/");
        //crea dia y le ingresa la primera parte
        int dia = Integer.valueOf(fechaNac[0]);
        //crea mes y le ingresa la primera parte
        int mes = Integer.valueOf(fechaNac[1]);
        //crea anio y le ingresa la primera parte
        int anio = Integer.valueOf(fechaNac[2]);
        //Guarda alu.setFechaNac la fecha de txt con formato MiCalendario
        alu.setFechaNac(new MiCalendario(dia, mes, anio));
                
        alu.setPromedio(Double.valueOf(campos[index++]));
        
        alu.setCantMatAprob(Integer.valueOf(campos[index++]));
        
        //crea un string separado por 3 partes "/" (split separa las partes)
        String[] fechaIng = campos[index++].split("/");
        //crea dia y le ingresa la primera parte
        int di = Integer.valueOf(fechaIng[0]);
        //crea mes y le ingresa la primera parte
        int me = Integer.valueOf(fechaIng[1]);
        //crea anio y le ingresa la primera parte
        int an = Integer.valueOf(fechaIng[2]);
        //Guarda alu.setFechaNac la fecha de txt con formato MiCalendario
        alu.setFechaIng(new MiCalendario(di, me, an));
                
        alu.setEstado(campos[index++].charAt(0));
        return alu;
    }
    //Crea el metodo getEstado
    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) throws PersonaException {
        // TODO validar por A, B o M
        //Crea sexoLowerCase y le asigna en sexo en minusculas toLowerCase(sexo)
        Character estadotoUpperCase = Character.toUpperCase(estado);
        //Si sexoLowerCase es distinto "a" "b" o "m" lanza la exception
        if (!estadotoUpperCase.equals('A') && !estadotoUpperCase.equals('B')&& !estadotoUpperCase.equals('M')) {
            throw new PersonaException("Error al setear el Estado (debe contener A / B / M)");
        }
        //Si es "A", "B" o "M" asigna  sexoLowerCase al sexo de la clase
        this.estado = estadotoUpperCase;
    }
    
    
}
