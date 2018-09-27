/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroeduc.controller;

import com.centroeduc.dao.AlumnoGradoDAO;
import com.centroeduc.model.AlumnoGrado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import vista.JFrmAlumnoGrado;

/**
 *
 * @author Usuario
 */
public class AlumnoGradoControlador implements ActionListener {

    JFrmAlumnoGrado grado = new JFrmAlumnoGrado();
    AlumnoGradoDAO dao = new AlumnoGradoDAO();
    AlumnoGrado dato = new AlumnoGrado();

    public AlumnoGradoControlador(JFrmAlumnoGrado form) {
        this.grado = form;
        this.grado.jBtnGuardar.addActionListener(this);
        listaAlumnoGrado();
        cargarDatos();

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("final");
        if (evento.getSource() == this.grado.jBtnGuardar) {
            JOptionPane.showMessageDialog(null,"hola");
            guardarAlumnGrad();
        }

    }

    public void guardarAlumnGrad() {
        String mensaje = null;
        
        mensaje = dao.asignarAG(Integer.parseInt(this.grado.jCbxNomAlumn.getSelectedItem().toString()), Integer.parseInt(this.grado.jCbxCodCGSP.getSelectedItem().toString()), Integer.parseInt(this.grado.jTxtCiclo.getText()));
        
                
        JOptionPane.showMessageDialog(null, mensaje);
        //actualizar grado
        listaAlumnoGrado();
        //actualizar datos de la taba
        cargarDatos();
       
    }
    public void cargarDatos (){
       ArrayList<AlumnoGrado> list = new ArrayList();
       list=dao.DatosAlumnoGrado();
       DefaultComboBoxModel modelo = new DefaultComboBoxModel();
       DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < list.size(); i++) {
            
            modelo.addElement(list.get(i).CodAlumnos);
            model.addElement(list.get(i).Ccursgradsecprof);
            System.out.println("mostrar ALUMNO"+list.get(i).CodAlumnos);
            System.out.println("mostrar CGSP"+ list.get(i).Ccursgradsecprof);
            
            
        }
        
        this.grado.jCbxNomAlumn.setModel(modelo);
        this.grado.jCbxCodCGSP.setModel(model);
         

        
    }
   
    public void listaAlumnoGrado(){
        ArrayList<AlumnoGrado> list = new ArrayList();
        list=dao.MostrarDatosAlumnosGrado();
        DefaultTableModel tabla = new DefaultTableModel();
        this.grado.jTblAlumnGrad.setModel(tabla);
        tabla.addColumn("Codigo de Alumno Grado");
        tabla.addColumn("Codigo de Alumnos");
        tabla.addColumn("Codigo de CGSP");
        tabla.addColumn("Ciclo");
        
        Object[] columna = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodAlumnoGrado();
            columna[1] = list.get(i).getCodAlumnos();
            columna[2] = list.get(i).getCcursgradsecprof();
            columna[3] = list.get(i).getYear();
            
            tabla.addRow(columna);
        }
        
        
    }

    
}
