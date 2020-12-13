/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persona;

import fecha.MiCalendario;

/**
 *
 * @author Gabriel
 */
public class Persona {
    
    private static final int APYNOM_MAXLENGHT = 20;

    public static final String DELIM = "\t";
    
    private Integer dni;
    
    private String apyNom;
    
    private Character sexo;
    //fechaNac es de tipo calendaria, una subclase de calendar   
    private MiCalendario fechaNac;
//El constructor por defecto, instancia, crea una persona sin datos.
    public Persona() {
    }
//El constructor solo con un DNI
    public Persona(Integer dni) throws PersonaException {
        setDni(dni);
    }
//El constructor con todos los datos con la excepción Persona excepction
    public Persona(int dni, String apyNom, Character sexo, MiCalendario fechaNac) throws PersonaException {
        setDni(dni);
        setApyNom(apyNom);
        setSexo(sexo);
        this.fechaNac = fechaNac;
    }
//Crea el metodo getDni, publico, cuando piden getDni, devuelve el dni de persona
    public Integer getDni() {
        return dni;
    }
   
//Crea el metodo setDni (con una variable entera dni), para controlar el ingreso de datos
    public final void setDni(Integer dni) throws PersonaException {
        //Si el ingreso es null o menor igual a cero, lanza la clase PersonaExceptión
        if (dni==null || dni<=0) {
            // Error
           
            throw new PersonaException("Error al setear el DNI (solo se permiten valores positivos)");
        }
        
        else if (dni>99999999)
            throw new PersonaException("Error debe ingresar DNI menor a 8 digitos");
                    
            //Si no es nulo ni negativo, le asigna este dni al dni de la clase
        this.dni = dni;
    }
    //Crea el metodo getApyNom, al llamarlo devuelve el nombre
    public String getApyNom() {
        return apyNom;
    }
    //Crea el metodo setApyNom
    public final void setApyNom(String apyNom) throws PersonaException {
        //Si es nulo o ISEMPTY (no tiene contenido), lanza la exception. .trim(recorta caracteres vacios al inicio o al final)
        if (apyNom == null || apyNom.trim().isEmpty()) {
            throw new PersonaException("Error al setear el Nombre y Apellido (debe tener contenido)");
        }
        //Si no está vacio ni es null, lo almacena en apyNom .trim(recorta caracteres vacios al inicio o al final)
        this.apyNom = apyNom.trim();
    }
    //Crea el metodo getSexo, si pide getSexo, devuelve sexo.
    public Character getSexo() {
        return sexo;
    }
    //Crea el metodo setSexo, con el caracter sexo
    public void setSexo(Character sexo) throws PersonaException {
        //Crea sexoLowerCase y le asigna en sexo en minusculas toLowerCase(sexo)
        Character sexoLowerCase = Character.toLowerCase(sexo);
        //Si sexoLowerCase es distinto "f" o de "m" lanza la exception
        if (!sexoLowerCase.equals('f') && !sexoLowerCase.equals('m')) {
            throw new PersonaException("Error al setear el sexo (debe contener f/m/F/M)");
        }
        //Si es "f" o "m" asigna  sexoLowerCase al sexo de la clase
        this.sexo = sexoLowerCase;
    }
    //Crea el metodo getFechaNac, si pide getFechaNac, devuelve gerFechaNac.
    public MiCalendario getFechaNac() {
        return fechaNac;
    }
    //Crea el metodo setFechaNac y la guarda en FechaNac de la clase
    public void setFechaNac(MiCalendario fechaNac) {
        this.fechaNac = fechaNac;
    }
    //Crea el metodo publico toString
    @Override
    public String toString() {
        //asigna a apyn, si el largo total de apyNom es mayor a 20, entonces lo corta a 20, si no lo deja como esta
        String apyn = apyNom.length()>APYNOM_MAXLENGHT?apyNom.substring(0, APYNOM_MAXLENGHT):apyNom;
        // Cuando llamen al metodo devuelve
        // dni, String.format lo pasa a cadena y de 8 caracteres
        // apyNom String.format lo pasa a cadena y le da respetando 20 caracteres
        // sexo - fechaNac los muestra sin modificar su formato
        return String.format("%08d", dni)+DELIM+String.format("%"+APYNOM_MAXLENGHT+"s", apyn)+DELIM+
                sexo+DELIM+fechaNac;
    }

    
}
