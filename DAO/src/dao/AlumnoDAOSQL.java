/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import fecha.MiCalendario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import persona.Alumno;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class AlumnoDAOSQL extends DAO<Alumno, Integer>{
    //crea un objeto de tipo connection, luego ahi guarda la conexion
    private Connection conn;
    //crea selectPS para guardar la estructira del select - read-
    private PreparedStatement selectPS;
    //crea insertPS, luego guarda la estructura de carga - create-
    private PreparedStatement insertPS;
    //crea selectCountPS para para cargar la estructura de existe (1 existe, 0 no existe)
    private PreparedStatement selectCountPS;
    //se crea updatePS para usarlo en el update
    private PreparedStatement updatePS;
    //se crea deletePS para usarlo en el delete
    private PreparedStatement deletePS;
    //se crea selectPS para usarlo en el selectAll
    private PreparedStatement findAllPS;

    AlumnoDAOSQL(String url, String password) {
        //url de ejemplo para documentarla (jdbc:mysql://localhost:3306/?user=root)
        //clic derecho sobre local connection y copy JDBK connection string... de MySQL
        //url propia: jdbc:mysql://127.0.0.1:3306/?user=root
    }
//constructor de AlumnoDAOSQL con url, user y password
//Se quito el public para que solo se puede acceder desde su paquete,
    AlumnoDAOSQL(String url, String user, String password) throws AlumnoDAOException {
        try {
            //url de ejemplo para documentarla (jdbc:mysql://localhost:3306/?user=root)
            //clic derecho sobre local connection y copy JDBK connection string... de MySQL
            //url propia: jdbc:mysql://127.0.0.1:3306/?user=root
            //guarda los parametros en un objeto connection para conectarse a la base
            conn = DriverManager.getConnection(url, user, password);
            
            // Read - prepara la estructura, hace un select del dni que luego se ingresara
            String sqlSelect = "SELECT * FROM alumnos WHERE DNI = ?;";
            selectPS = conn.prepareStatement(sqlSelect);
            
            // Count - lo usa para saber si existe un alumno da 1 o 0 ya que no hay dni duplicados
            String sqlSelectCount = "SELECT count(*) FROM alumnos WHERE DNI = ?;";
            selectCountPS = conn.prepareStatement(sqlSelectCount);
            
            // Insert - deja lista una cadena sqlInsert con los datos que cargaran en MySQL
            //se carga luego en el create
            String sqlInsert = "INSERT INTO alumnos\n" +
                "(DNI,\n" +
                "APY_NOM,\n" +
                "FEC_NAC,\n" +
                "CANT_MAT_APROB,\n" +
                "PROMEDIO,\n" +
                "SEXO,\n" +
                "FEC_ING,\n" +
                "ESTADO)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?);";
            //cada signo se pregunta representa un dato que luego sera cargado
            //guarda todo lo cargado en insertPS
            insertPS = conn.prepareStatement(sqlInsert);
            
            // update
            String sqlUpdate = "UPDATE `alumnos`\n" +
                            "SET\n" +
                            "`APY_NOM` =?,\n" +
                            "`FEC_NAC` =?,\n" +
                            "`CANT_MAT_APROB` =?,\n" +
                            "`PROMEDIO` =?,\n" +
                            "`SEXO` =?,\n" +
                            "`FEC_ING` =?,\n" +
                            "`ESTADO` =?\n" +
                            "WHERE `DNI` = ?";             
            updatePS = conn.prepareStatement(sqlUpdate);
            
            /*1delete (cambio de estado a B)
            String sqlDelete = "UPDATE `alumnos`\n" +
                            "SET\n" +
                            "`ESTADO` ='B'\n" +
                            "WHERE `DNI` = ?"; 
            deletePS = conn.prepareStatement(sqlDelete);
            //1*/
            
            ///*2 delete eliminacion física;
            String sqlDelete = "DELETE from alumnos\n" +
                            "WHERE dni = ?"; 
            deletePS = conn.prepareStatement(sqlDelete);
            //2*/
            
            
            //findall
            String sqlFindAll = "SELECT * FROM alumnos";
            findAllPS=conn.prepareStatement(sqlFindAll);
            
          /*// findall solo A y M - 
            String sqlFindAll = "SELECT * FROM alumnos WHERE ESTADO <> 'B';";
            findAllPS = conn.prepareStatement(sqlFindAll);
          */
          
        } catch (SQLException ex) {
            throw new AlumnoDAOException("Error Sql ==>"+ex.getMessage());
        }
    }
    
    @Override
    public Alumno read(Integer dni) throws AlumnoDAOException {
        Alumno alu = null;
        try {
            //selectPS, recibe el dni
            selectPS.setInt(1, dni);
            //ejecuta la Query
            ResultSet rs = selectPS.executeQuery();
            //si re tiene  otra fila se vuelve a ejecutar
            if(rs.next()) {
                //crea un alumno y lo carga con lo leido del MySQL
                alu = new Alumno();
                //toma el alumno desde el metodo creado setAlumnoProperties
                setAlumnoProperties(alu, rs);
                
                /* Visto en clase
                alu.setDni(rs.getInt("DNI"));
                alu.setApyNom(rs.getString("APY_NOM"));
                //crea un mi calendario (lo carga con fecha_nac de MySQL
                //en mi calendario arma el constructor date para para reparar la diferencia de formatos
                alu.setFechaNac(new MiCalendario(rs.getDate("FEC_NAC")));
                alu.setCantMatAprob(rs.getInt("CANT_MAT_APROB"));
                alu.setPromedio(rs.getDouble("PROMEDIO"));
                */
            }
        } catch (SQLException | PersonaException ex) {
            throw new AlumnoDAOException("Error Sql al intentar leer un alumno ==>"+ex.getMessage());
        }
        
        return alu;
    }

    @Override
    public void create(Alumno alu) throws AlumnoDAOException {
    ///*    
        try {
            //carga la estructura insert con los datos que se ingresaran
            int index = 1;
            insertPS.setInt(index++, alu.getDni());
            insertPS.setString(index++, alu.getApyNom());
            //busca el metodo dentro de mi calendario para pasar el objeto miCalendario a date
            insertPS.setDate(index++, /*new Date(20, 04, 2005));//*/alu.getFechaNac().getSqlDate());
            insertPS.setInt(index++, alu.getCantMatAprob());
            insertPS.setDouble(index++, alu.getPromedio());
            insertPS.setString(index++, String.valueOf(alu.getSexo()));
            insertPS.setDate(index++, /*new Date(20, 04, 2005));//*/alu.getFechaIng().getSqlDate());
            insertPS.setString(index++, String.valueOf(alu.getEstado()));
            //ejecuta la carga o da error SQLException
            insertPS.executeUpdate();
            
        } catch (SQLException ex) {
            throw new AlumnoDAOException("Error Sql al intentar insertar un alumno ==>"+ex.getMessage());
        }
        //*/
    }

    @Override
    public void update(Alumno alu) throws AlumnoDAOException {
        try {
            int index = 1;
            updatePS.setString(index++, alu.getApyNom());       
            updatePS.setDate(index++, alu.getFechaNac().getSqlDate());
            //updatePS.setString(index++, String.valueOf(alu.getSexo())); 
            //updatePS.setDate(index++,MiCalendario.convert2SqlDate(alu.getFechaIng()));
            updatePS.setInt(index++, alu.getCantMatAprob());    
            updatePS.setDouble(index++, alu.getPromedio());
            updatePS.setString(index++, String.valueOf(alu.getSexo()));
            updatePS.setDate(index++, /*new Date(20, 04, 2005));//*/alu.getFechaIng().getSqlDate());

            updatePS.setString(index++, String.valueOf(alu.getEstado()));
            updatePS.setInt(index++, alu.getDni());
            updatePS.executeUpdate();
        } catch (SQLException ex) {
            throw new AlumnoDAOException("Error Sql al actualizar el alumno ==>"+ex.getMessage());
        }
    }

    @Override
    public void delete(Integer dni)  throws AlumnoDAOException {
        try {
            deletePS.setInt(1, dni);                                
            deletePS.executeUpdate();
        } catch (SQLException ex) {
            throw new AlumnoDAOException("Error Sql al eliminar el registro"+ex.getMessage());
        }
    }
    
    //carga alu, desde la sql, luego se va a usar findAll
public void setAlumnoProperties(Alumno alu, ResultSet rs) throws AlumnoDAOException, SQLException, PersonaException {
        alu.setDni(rs.getInt("DNI"));
        alu.setApyNom(rs.getString("APY_NOM"));
        alu.setFechaNac(new MiCalendario(rs.getDate("FEC_NAC")));
        alu.setCantMatAprob(rs.getInt("CANT_MAT_APROB"));        
        alu.setPromedio(rs.getDouble("PROMEDIO"));
        alu.setSexo(rs.getString("SEXO").charAt(0));
        alu.setFechaIng(new MiCalendario(rs.getDate("FEC_ING")));        
        alu.setEstado(rs.getString("ESTADO").charAt(0));
}
    @Override
    public List<Alumno> findAll() throws AlumnoDAOException, DAOException {
        ///*3 - FORMA PEDIDA - llama al metodo boolean en false
        //llamo al findall con el boolean el false.
        return findAll(false);
        //3*/
        
    /*2//tp reentrega
            List<Alumno> lista = new ArrayList<>();
        
        Alumno alu = null;
        try {
            ResultSet rs = findAllPS.executeQuery();
            while(rs.next()) {
                alu = new Alumno();
                
                setAlumnoProperties(alu, rs);
                /*
                alu.setDni(rs.getInt("DNI"));
                alu.setApyNom(rs.getString("APEY_NOM"));
                alu.setSexo(rs.getString("SEXO").charAt(0));
                alu.setFechaNac(new MiCalendario(rs.getDate("FEC_NAC")));
                alu.setFechaIng(new MiCalendario(rs.getDate("FEC_ING")));
                alu.setPromedio(rs.getDouble("PROMEDIO"));
                alu.setCantMatAprob(rs.getInt("CANT_MAT_APROB"));
                alu.setEstado(rs.getString("ESTADO").charAt(0));
                /
                lista.add(alu);
            }
        } catch (SQLException | PersonaException ex) {
            throw new AlumnoDAOException("Error Sql al intentar leer un alumno ==>"+ex.getMessage());
        }
        
        return lista;
    //2*/
    
    /*1//tp entregado originalmente
        try {
            //crea una lista
            List<Alumno> alumnos = new ArrayList<>();
            Alumno alu;
            //ejecuta el query con los datos que se van cargando en resultSet rs
            ResultSet rs = findAllPS.executeQuery(); 
            //mientras rs pueda seguir, xq exista algo.
            while (rs.next()) {
                //crea un nuevo alumno
                alu=new Alumno();
                //toma el alumno desde setAlumnoProperties
                setAlumnoProperties(alu, rs);
                //agrega el alumno a la lista
                char estado = alu.getEstado();
                switch (estado){
                    case 'A':
                        alumnos.add(alu);
                        System.out.println(alu);
                        break;
                    case 'M':
                        alumnos.add(alu);
                        System.out.println(alu);
                        break;
                }
                //alumnos.add(alu);
                //muestra en pantalla para probarlo
                //System.out.println(alu);
            }            
            return alumnos;
        } catch (SQLException | PersonaException ex) {
            throw  new AlumnoDAOException(ex.getMessage());
        }
    //1*/
    }

    @Override
    public List<Alumno> findAll(boolean all) throws DAOException {
try {
            //crea una lista
            List<Alumno> alumnos = new ArrayList<>();
            Alumno alu;
            //ejecuta el query con los datos que se van cargando en resultSet rs
            ResultSet rs = findAllPS.executeQuery(); 
            //mientras rs pueda seguir, xq exista algo.
            while (rs.next()) {
                //crea un nuevo alumno
                alu=new Alumno();
                //toma el alumno desde setAlumnoProperties
                setAlumnoProperties(alu, rs);
                
                ///*2//agregado              
                if (all || alu.getEstado()!='B') {
                    alumnos.add(alu);
                }
                    
                System.out.println("Alumno de la lista ==> "+alumnos);
                //2*/
                
                /*1//entrega original
                //agrega el alumno a la lista
                alumnos.add(alu);
                //muestra en pantalla para probarlo
                System.out.println(alu);
                //1*/
            }            
            return alumnos;
        } catch (SQLException | PersonaException ex) {
            throw  new AlumnoDAOException(ex.getMessage());
        }
    }
    
    @Override
    public boolean existe(Integer dni) throws AlumnoDAOException {
        try {
            selectCountPS.setInt(1, dni);
            //crea ResultSet y le asigna el selectCountPS ejecutado
            ResultSet rs = selectCountPS.executeQuery();
            //sin hay resultado y es mayor es mayor a 0 devuelve true
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException ex) {
            throw new AlumnoDAOException("Error Sql al intentar leer un alumno ==>"+ex.getMessage());
        }
    }
    
    @Override//para cerrar el sql
    public void close() throws DAOException {
        try {//si la conexion es distinto de null y no esta cerrada
            if (conn!=null && !conn.isClosed()) {
                //cierro la conexion
                conn.close();
            }
        } catch (SQLException ex) {
            throw new DAOException("Error al cerrar DB ==> "+ex.getMessage());
        }
    }
    
}
