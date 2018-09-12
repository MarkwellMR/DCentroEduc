/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.controller;

import com.centroeduc.dao.AlumnoDAO;
import com.centroeduc.model.Alumno;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmAlumno;

/**
 *
 * @author javam2018
 */
public class AlumnoControlador implements ActionListener {

    JFrmAlumno alumno = new JFrmAlumno();
    AlumnoDAO dao = new AlumnoDAO();
    Alumno dato = new Alumno();

    public AlumnoControlador(JFrmAlumno form) {
        this.alumno = form;
        this.alumno.jBtnGuardar.addActionListener(this);
        listaAlumno();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("final");
        if (evento.getSource() == this.alumno.jBtnGuardar) {
            guardarAlumno();

        }
    }

    public void guardarAlumno() {
        String mensaje = null;
        dato.setNombre(this.alumno.jTxtNombre.getText());
        dato.setApellido(this.alumno.jTxtApellido.getText());
        dato.setDireccion(this.alumno.jTxtDireccion.getText());
        dato.setEmail(this.alumno.jTxtEmail.getText());
        dato.setTelEmergencia(Integer.parseInt(this.alumno.jTxtTelEmergencia.getText()));
        dato.setCodEncargado(this.alumno.jTxtCodEncargado.getText());
        dato.setCodSecretaria(this.alumno.jTxtCodSecretaria.getText());
        dato.setFechanac(this.alumno.jTxtFechaNac.getText());
        dato.setPadecimiento(this.alumno.jTxtAPadecimiento.getText());
        dato.setEstado(1);
        mensaje = dao.ingresarAlum(dato);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaAlumno();

    }
    
    public void listaAlumno(){
        ArrayList<Alumno> list = new ArrayList();
        list = dao.mostrarAlumno();
        DefaultTableModel tabla = new DefaultTableModel();
        this.alumno.jTblAlumno.setModel(tabla);
        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Direccion");
        tabla.addColumn("Email");
        tabla.addColumn("Tel Emergencia");
        tabla.addColumn("Cod Encargado");
        tabla.addColumn("Cod Secretaria");
        tabla.addColumn("Fecha Nacimiento");
        tabla.addColumn("Padecimientos");
        
        Object[] columna = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodAlumno();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getApellido();
            columna[3] = list.get(i).getDireccion();
            columna[4] = list.get(i).getEmail();
            columna[5] = list.get(i).getTelEmergencia();
            columna[6] = list.get(i).getCodEncargado();
            columna[7] = list.get(i).getCodSecretaria();
            columna[8] = list.get(i).getFechanac();
            columna[9] = list.get(i).getPadecimiento();
            
            tabla.addRow(columna);
            
        }
    }

    public void limpiarControles() {
        alumno.jTxtNombre.setText(null);
        alumno.jTxtApellido.setText(null);
        alumno.jTxtDireccion.setText(null);
        alumno.jTxtEmail.setText(null);
        alumno.jTxtTelEmergencia.setText(null);
        alumno.jTxtCodEncargado.setText(null);
        alumno.jTxtCodSecretaria.setText(null);
        alumno.jTxtFechaNac.setText(null);
        alumno.jTxtAPadecimiento.setText(null);

    }

}
