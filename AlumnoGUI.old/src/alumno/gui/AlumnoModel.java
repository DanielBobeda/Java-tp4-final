/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import persona.Alumno;

/**
 *
 * @author Gabriel
 */
//Crea un modelo de tabla abstracta 
public class AlumnoModel extends AbstractTableModel {
//crea una lista de alumnos
    private List<Alumno> lista;
    //almacena los encabezados que luego cargara en el metodo name    
    private static final String[] ENCABEZADOS = {"DNI", "Apellido y Nombre", "Fecha Nac.", "Estado"};
//    private static final String[] ENCABEZADOS = {"DNI", "Apellido y Nombre", "Sexo", "Fecha Nac.", "Promedio", "Cant. Materias", "Fecha Ing.", "Estado"};
//sobreescribe los metodos del padre
    //creo el constructor por defecto    
    public AlumnoModel() {
    //Evito la exception, da una lista vacia si no se carga la lista de getRowCount
        lista = new ArrayList<>();
    }
//recibe una lista y la guarda en la list de arriba
    public void setLista(List<Alumno> lista) {
        this.lista = lista;
//refresca la lista cada vez que se hace alguna modificacion
        fireTableDataChanged();
    }
//getRowCount devuelve el tamaño de la lista    
//este metodo de llama automaticamente cuando carga la tabla    
    @Override
    public int getRowCount() {
        return lista.size();
    }
//la cantidad de las columnas de la tabla las calcula con la cantidad de columnas del encabezado  
    @Override
    public int getColumnCount() {
        return ENCABEZADOS.length;
    }
//toma del encabezado el nombre de las columnas de la tabal    
    @Override
    public String getColumnName(int column) {
        return ENCABEZADOS[column];
    }
    //es el metodo que devuelve la informacion de cada fila y columna. 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//carga en la pantalla lo que recibe, llena celda por celda.
//toma en alu un alumno de la lista
        Alumno alu = lista.get(rowIndex);
//si colum  index es 0 carga dni, si es 1 carga apynom, 2 fechanac, etc.
        switch (columnIndex) {
            case 0: return alu.getDni();
            case 1: return alu.getApyNom();
            //case 2: return alu.getSexo();
            case 2: return alu.getFechaNac();
            //case 4: return alu.getPromedio();
            //case 5: return alu.getCantMatAprob();
            //case 6: return alu.getFechaIng();
            case 3: return alu.getEstado();
            default: return null;
        }
    }
//si llaman a getLista devuelve la lista
    public List<Alumno> getLista() {
        return lista;
    }
//Metodo para refrescar al modificar   
    public void refrescarModelo() {
        fireTableDataChanged();
    }

}
