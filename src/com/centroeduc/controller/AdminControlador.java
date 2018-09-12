
package com.centroeduc.controller;

import com.centroeduc.dao.AdminDAO;
import com.centroeduc.model.Administrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmAdmin;

/**
 *
 * @author javam2018
 */
public class AdminControlador implements ActionListener {

    //atributos
    JFrmAdmin logica = new JFrmAdmin();
    AdminDAO dao = new AdminDAO();
    Administrador datos = new Administrador();
    //metodos
    //metodo constructor
    public AdminControlador(JFrmAdmin formulario) {
        
        this.logica = formulario;
        this.logica.jBtnGuardar.addActionListener(this);
        listaAdmin();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("llegue");
        if (evento.getSource() == this.logica.jBtnGuardar) {
            guardarAdmin();
        }
    }

    public void guardarAdmin() {
        String mensaje=null;
        
        datos.setCodigo(this.logica.jTxtCodigo.getText());
        datos.setNombre(this.logica.jTxtNombre.getText());
        datos.setApellido(this.logica.jTxtApellido.getText());
        datos.setDireccion(this.logica.jTxtDireccion.getText());
        datos.setEmail(this.logica.jTxtEmail.getText());
        datos.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasa.getText()));
        datos.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovil.getText()));
        datos.setFechanac(this.logica.jTxtFechaNac.getText());
        datos.setCui(Long.parseLong(this.logica.jTxtCui.getText()));
        datos.setPass(this.logica.jPsfContra.getText());
        datos.setEstado(1);
        mensaje = dao.registerAdmin(datos);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaAdmin();
        
    }
     public void listaAdmin(){
         ArrayList<Administrador> list = new ArrayList();
        list = dao.listAdmin();
        DefaultTableModel tabla = new DefaultTableModel();
        this.logica.jTblAdmin.setModel(tabla);//asignando el modelo a la tabla
        //AGREGAR TITULOS A LA TABLA
        tabla.addColumn("Codigo");
        tabla.addColumn("nombre");
        tabla.addColumn("apellido");
        tabla.addColumn("movil");
        
        //ESTABLECER LA CANTIDAD DE COLUMNAS CON RELACION A LOS TITULOS
        Object[] columna= new Object[4];
        
        //ASIGNANDO A CADA COLUMNA UN VALOR
         for (int i = 0; i < list.size(); i++) { // SE RECORRE LA LISTA (ARRAYLIST) QUE NOS DA EL DAO
             columna[0] = list.get(i).getCodigo();
             columna[1] = list.get(i).getNombre();
             columna[2] = list.get(i).getApellido();
             columna[3] = list.get(i).getTelMovil();
             
             tabla.addRow(columna);
         }
        
        
        
     }
    
    public void limpiarControles(){
        logica.jTxtCodigo.setText(null);
        logica.jTxtNombre.setText(null);
        logica.jTxtApellido.setText(null);
        logica.jTxtDireccion.setText(null);
        logica.jTxtEmail.setText(null);
        logica.jTxtTelCasa.setText(null);
        logica.jTxtTelMovil.setText(null);
        logica.jTxtFechaNac.setText(null);
        logica.jTxtCui.setText(null);
        logica.jPsfContra.setText(null);
        logica.jTxtCodigo.requestFocus();
    }
}


