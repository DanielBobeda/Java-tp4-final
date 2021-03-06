/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno.gui;

import dao.AlumnoDAOSQL;
import dao.AlumnoDAOTXT;
import dao.DAO;
import dao.DAOAlumnoFactory;
import dao.DAOAlumnoFactoryException;
import dao.DAOException;
import java.awt.HeadlessException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import persona.Alumno;
import persona.PersonaException;

/**
 *
 * @author Gabriel
 */
public class AlumnoGUI extends javax.swing.JFrame {
    //crea un dao de tipo DAO(crud)
    private DAO<Alumno, Integer> dao;
    //crea un daoSQL de tipo AlumnoDAOSQL
    private AlumnoDAOSQL daoSQL;
    //crea un daoSQL de tipo AlumnoDAOTXT
    private AlumnoDAOTXT daoTXT;    
    /**
     * Creates new form AlumnoGUI
     */
    public AlumnoGUI() {
        initComponents();
        //centra la ventana
        setLocationRelativeTo(null);
        //Pone titulo a la ventana
        setTitle("Alumnos GUI");
        //crea un nuevo AlumnoModel
        alumnoModel = new AlumnoModel();
        //setMode le da a la tabla el modelo pre cargado en alumnoModel
        alumnosTable.setModel(alumnoModel);
        //le quita visibilidad al panel sql y al urlDBTextField
        jPanel1.setVisible(false);
        urlDBTextField.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        repoComboBox = new javax.swing.JComboBox<>();
        fileSystemButton = new javax.swing.JButton();
        fullPathTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        alumnosTable = new javax.swing.JTable();
        verEliminadosRadioButton = new javax.swing.JRadioButton();
        agregarButton = new javax.swing.JButton();
        modificarButton = new javax.swing.JButton();
        eliminarButton = new javax.swing.JButton();
        consultarButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        urlDBTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        userDBTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        passwordDBField = new javax.swing.JPasswordField();
        connDBButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        repoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TXT", "SQL" }));
        repoComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                repoComboBoxItemStateChanged(evt);
            }
        });
        repoComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                repoComboBoxActionPerformed(evt);
            }
        });

        fileSystemButton.setText("...");
        fileSystemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileSystemButtonActionPerformed(evt);
            }
        });

        fullPathTextField.setEditable(false);
        fullPathTextField.setBackground(new java.awt.Color(204, 204, 204));

        alumnosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        alumnosTable.setEnabled(false);
        jScrollPane1.setViewportView(alumnosTable);

        verEliminadosRadioButton.setText("Ver Eliminados");
        verEliminadosRadioButton.setEnabled(false);
        verEliminadosRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verEliminadosRadioButtonActionPerformed(evt);
            }
        });

        agregarButton.setText("Agregar");
        agregarButton.setEnabled(false);
        agregarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarButtonActionPerformed(evt);
            }
        });

        modificarButton.setText("Modificar");
        modificarButton.setEnabled(false);
        modificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarButtonActionPerformed(evt);
            }
        });

        eliminarButton.setText("Eliminar");
        eliminarButton.setEnabled(false);
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });

        consultarButton.setText("Consultar");
        consultarButton.setEnabled(false);
        consultarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarButtonActionPerformed(evt);
            }
        });

        urlDBTextField.setText("jdbc:mysql://127.0.0.1:3306/efc?serverTimezone=UTC");

        jLabel1.setText("URL");

        jLabel3.setText("Usuario");

        jLabel2.setText("Clave");

        connDBButton.setText("Conectar");
        connDBButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connDBButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(urlDBTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userDBTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(passwordDBField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(connDBButton)
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(urlDBTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(userDBTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(passwordDBField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connDBButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(repoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(consultarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(eliminarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(modificarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(agregarButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(verEliminadosRadioButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fullPathTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileSystemButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(repoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fileSystemButton)
                    .addComponent(fullPathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(verEliminadosRadioButton)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregarButton)
                        .addGap(26, 26, 26)
                        .addComponent(modificarButton)
                        .addGap(28, 28, 28)
                        .addComponent(eliminarButton)
                        .addGap(27, 27, 27)
                        .addComponent(consultarButton)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileSystemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileSystemButtonActionPerformed
        // pasos para abrir la ventana de dialogo para seleccionar el archivo
        //crea JFileChoser que se usa para seleccionar un archivo
        JFileChooser chooser = new JFileChooser();
        //muestra una ventana con 3 opciones
        //devuelve un entero con: cancel, aprovado o error
        //guarda el entero seleccionado en la ventana emergente en resp
        int resp = chooser.showOpenDialog(this);
        //si la respuesta es distinta de Aprove me voy sin hacer nada
        if (resp!=JFileChooser.APPROVE_OPTION) {
            return;
        }
        //si no es distinto de  aprove, guarda en file el archivo seleccionado (getselectedfile)
        File file = chooser.getSelectedFile();
        //el texto de fullPathTextField es file.getAbsolutPath la ruta del archivo guardada en file
        fullPathTextField.setText(file.getAbsolutePath());
        //crea el mapa con que se va a cargar la factory
        Map<String, String> config = new HashMap<>();
        //carga la dao parametros de entrada, tipo de dao, para el txt
        config.put(DAOAlumnoFactory.TIPO_DAO, DAOAlumnoFactory.TIPO_DAO_TXT);
        //carga la dao el nombre del archivo en el fullpathfield
        config.put(DAOAlumnoFactory.FILE_NAME, fullPathTextField.getText());
        //el DAO recibe el tipo txt y el nombre del archivo
        try {
            //Alumno DAOFactory admite txt o sql
            dao = DAOAlumnoFactory.getInstance().getDAO(config);
            daoTXT = (AlumnoDAOTXT) dao;
        } catch (DAOAlumnoFactoryException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            //  !!!!! TODO Mostrar error en un POPUP
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        System.out.println("¡¡¡ DAO TXT creado !!!");
        try {
    //a traves del findAll le pasa la lista de alumnos al modelo
             alumnoModel.setLista(dao.findAll(verEliminadosRadioButton.isSelected()));
        } catch (DAOException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            // TODO Mostrar error en un POPUP
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Habilia los botones luego de seleccionar un archivo para trabajar
        verEliminadosRadioButton.setEnabled(true);
        alumnosTable.setEnabled(true);
        agregarButton.setEnabled(true);
        modificarButton.setEnabled(true);
        eliminarButton.setEnabled(true);
        consultarButton.setEnabled(true);
    }//GEN-LAST:event_fileSystemButtonActionPerformed

    private void repoComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_repoComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_repoComboBoxActionPerformed

    private void modificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarButtonActionPerformed
    //guarda en alu, llama al metodo getSelectedAlumno que controla si se selecciono un alumno y lo devuelve
        Alumno alu = getSelectedAlumno();
    //si es null, no selecciono alumno, no devuelve nada.
        if (alu==null) {
            return;
        }
        
        // TODO Show Modal
        //recupera el alumno que se quiere mostrar de la lista y se lo pasa al dialog con el metodo mostrar
        AlumnoDialog dialog = new AlumnoDialog(this, true);
        try {//usa el metodo mostrar para cargar los textField
            dialog.mostrar(alu, true);
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Error al intentar cargar a "+alu.getApyNom()+". Error: " + ex , "Error al Modificar", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            //llama al dao update, actualiza
            dao.update(alu);
        } catch (DAOException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Error al intentar modificar a "+alu.getApyNom()+". Error: " + ex , "Error al Modificar", JOptionPane.ERROR_MESSAGE);
            return;
        }
    //actualiza la modificación,  alumnoModel.fireTableDataChanged();
        //usa el metodo creado para refrescar y no repetir los cambios
        try {
            alumnoModel.setLista(dao.findAll(verEliminadosRadioButton.isSelected()));
        } catch (DAOException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        alumnoModel.refrescarModelo();
    }//GEN-LAST:event_modificarButtonActionPerformed

//metodo usado en los botones para buscar un alumno
    private Alumno getSelectedAlumno() throws HeadlessException {
        //guarda en selectedRow 1 si hay un alumno seleccionado
        final int selectedRow = alumnosTable.getSelectedRow();
    //si selectedRow es menor a 0, muestra mensaje informando que no se selecciono fila
        if (selectedRow<0) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado un alumno", "Error", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
    //si no es menor a 0, ejecuta la getLita que devuelve el alumno seleccionado
        return alumnoModel.getLista().get(selectedRow);
    }

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
    //guarda en alu, llama al metodo getSelectedAlumno que controla si se selecciono un alumno y lo devuelve
        Alumno alu = getSelectedAlumno();
    //si es null, no selecciono alumno, no devuelve nada.
        if (alu==null) {
            return;
        }
        //guarda en int el valor de la respuesta
        //JOptionPanel.showConfirmDialog muestra un cuadro en el que se puede confirmar si eliminamos el alumno
        int resp = JOptionPane.showConfirmDialog(this, "Estás seguro que querés eliminar a "+alu.getApyNom(), "Confirmar Eliminación", 
                //muestra el boton SI - NO para aceptar eliminar el alumno, tipo de mensaje QUESTION, pregunta.
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    //si la respuesta es igual a YES.OPTION    
        if (resp==JOptionPane.YES_OPTION) {
            try {
                //llamo al dao.delete
                dao.delete(alu.getDni());
                //Actualiza la taabla
                alumnoModel.setLista(dao.findAll(verEliminadosRadioButton.isSelected()));
            } catch (DAOException ex) {
                Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
                // TODO mensaje
                JOptionPane.showMessageDialog(this, "Problema al eliminar el archivo", "Error", JOptionPane.INFORMATION_MESSAGE);

            }
            
            alumnoModel.refrescarModelo();
        }
    }//GEN-LAST:event_eliminarButtonActionPerformed

    private void consultarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarButtonActionPerformed
        Alumno alu = getSelectedAlumno();
        if (alu==null) {
            return;
        }
        
        // TODO Show Modal
        AlumnoDialog dialog = new AlumnoDialog(this, true);
        try {
            //llama al metodo mostrar para carcar los textfield del dialog nueva ventana 
            dialog.mostrar(alu, false);
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Error al intentar consultar a "+alu.getApyNom() +". Error: " + ex, "Error al Consultar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_consultarButtonActionPerformed

    private void agregarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarButtonActionPerformed
        // Show Modal
        //crea dialog de tipo AlumnoDialog
        AlumnoDialog dialog = new AlumnoDialog(this, true);
        //crea un alumno
        Alumno alu;
        try {
            //envia null y false al metodo mostrar, como no hay alumno, lo crea.
            alu = dialog.mostrar(null, false);
        } catch (PersonaException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Problema al eliminar el archivo", "Error", JOptionPane.INFORMATION_MESSAGE);
        return;
        }
        //si alumno es distinto de null, lo crea
        if (alu!=null) {
            try {
            //llama al crear del dao
                dao.create(alu);
                JOptionPane.showMessageDialog(this, "Alumno creado correctamente", "Alumno Creado", JOptionPane.INFORMATION_MESSAGE);

            } catch (DAOException ex) {
                JOptionPane.showMessageDialog(this, "Error, el alumno ya existe", "Error", JOptionPane.INFORMATION_MESSAGE);
                Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
            //toma la lista de alumnos y refresca la tabla
            alumnoModel.getLista().add(alu);
            alumnoModel.refrescarModelo();
            }
    }//GEN-LAST:event_agregarButtonActionPerformed

    private void repoComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_repoComboBoxItemStateChanged
        //guarda en tipo dao la seleccion del comboBox
        String tipoDAO = repoComboBox.getSelectedItem().toString();
        //si es txt, le quita visibilidad al panel de sql
        if ("TXT".equals(tipoDAO)) {
            jPanel1.setVisible(false);
            fileSystemButton.setEnabled(true);
            if(daoTXT!=null)
            {
                dao = daoTXT;
            }
        }
        //si es sql, le da visibilidad al panel de sql
        else if (daoSQL==null){
            jPanel1.setVisible(true);
        //habilita el textField para cargar la URL
            urlDBTextField.setEnabled(true);
        //quita visibilidad al boton de ruta txt
            fileSystemButton.setEnabled(false);
        }
        else {
            //carga a dao, daoSQL
            dao = daoSQL;
        }
        try {
            alumnoModel.setLista(dao.findAll(verEliminadosRadioButton.isSelected()));
        } catch (DAOException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_repoComboBoxItemStateChanged

    private void connDBButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connDBButtonActionPerformed
    //TIPO_DAO_SQL   Carga los parametros necesarios de los textField para usar TIPO_DAO_SQL en DAOAlumnoFactory
        //crea el mapa y lo carga con los datos para la onfiguracion de la coneccion SQL
        Map<String, String> config = new HashMap();
            //tipo de dao TIPO_DAO_SQL (se diferencia en la factory del TIPO_DAO_TXT)
            config.put(DAOAlumnoFactory.TIPO_DAO, DAOAlumnoFactory.TIPO_DAO_SQL);
            //se almacena la url sin el usuario /el esquema efc creado - url: jdbc:mysql://127.0.0.1:3306/?user=root
            config.put(DAOAlumnoFactory.URL_DB, urlDBTextField.getText());
            config.put(DAOAlumnoFactory.USER, userDBTextField.getText()); //"root"
            //carga el PASSWORD de la Factory con un String matando el valor de passwordDBField.getPassword
            config.put(DAOAlumnoFactory.PASSWORD,String.valueOf(passwordDBField.getPassword())); //"1234"
        try {
    //carga en dao tipo_txt o tipo_sql con la Factory
            dao = DAOAlumnoFactory.getInstance().getDAO(config);
            daoSQL = (AlumnoDAOSQL) dao;
        } catch (DAOAlumnoFactoryException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Error al intentar establecer conexión con la BD.", "Error al Conectar", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("Connection DB OK!!!");
        
        ///* probando carga de la lista en SQL
        try {
    //a traves del findAll le pasa la lista de alumnos al modelo
             alumnoModel.setLista(dao.findAll(verEliminadosRadioButton.isSelected()));
        } catch (DAOException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            // TODO Mostrar error en un POPUP
            JOptionPane.showMessageDialog(this,"Error al intentar eliminar al cargar la lista ", "Error de lista", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Habilia los botones luego de seleccionar un archivo para trabajar
        verEliminadosRadioButton.setEnabled(true);
        alumnosTable.setEnabled(true);
        agregarButton.setEnabled(true);
        modificarButton.setEnabled(true);
        eliminarButton.setEnabled(true);
        consultarButton.setEnabled(true);
        //*/
    }//GEN-LAST:event_connDBButtonActionPerformed

 //agregado, refresco automatico con clic en ver eliminados
    private void verEliminadosRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verEliminadosRadioButtonActionPerformed
        try {
    //a traves del findAll le pasa la lista de alumnos al modelo
             alumnoModel.setLista(dao.findAll(verEliminadosRadioButton.isSelected()));
             alumnoModel.refrescarModelo();
        } catch (DAOException ex) {
            Logger.getLogger(AlumnoGUI.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this,"Error al intentar ver eliminados. Error: " + ex, "Error ver eliminados", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_verEliminadosRadioButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AlumnoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AlumnoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AlumnoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AlumnoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AlumnoGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarButton;
    private javax.swing.JTable alumnosTable;
    private javax.swing.JButton connDBButton;
    private javax.swing.JButton consultarButton;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JButton fileSystemButton;
    private javax.swing.JTextField fullPathTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modificarButton;
    private javax.swing.JPasswordField passwordDBField;
    private javax.swing.JComboBox<String> repoComboBox;
    private javax.swing.JTextField urlDBTextField;
    private javax.swing.JTextField userDBTextField;
    private javax.swing.JRadioButton verEliminadosRadioButton;
    // End of variables declaration//GEN-END:variables

    AlumnoModel alumnoModel;
}
