
package com.centroeduc.controller;

import com.centroeduc.dao.AdminDAO;
import com.centroeduc.dao.SecreDAO;
import com.centroeduc.model.Administrador;
import com.centroeduc.model.Secretaria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmSecre;


public class SecreControlador implements ActionListener {
    
    JFrmSecre logica = new JFrmSecre();
    SecreDAO daoS = new SecreDAO();
    Secretaria datosS = new Secretaria();
    Administrador Adm = new Administrador();
    AdminDAO dao = new AdminDAO();
    
    
    public SecreControlador(JFrmSecre formulario){
            this.logica = formulario;
            this.logica.jBtnGuardarS.addActionListener(this);   
            listaSecre();
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         System.out.println("SI LLEGO");
         if (e.getSource()== this.logica.jBtnGuardarS) {
             guardarSecre();
         }
         
    }
    
        public void guardarSecre(){
        String mensaje=null;
        
        datosS.setCodigo(this.logica.jTxtCodigoS.getText());
        datosS.setNombre(this.logica.jTxtNombreS.getText());
        datosS.setApellido(this.logica.jTxtApellidoS.getText());
        datosS.setDireccion(this.logica.jTxtDireccionS.getText());
        datosS.setEmail(this.logica.jTxtEmailS.getText());
        datosS.setTelCasa(Integer.parseInt(this.logica.jTxtTelCasaS.getText()));
        datosS.setTelMovil(Integer.parseInt(this.logica.jTxtTelMovilS.getText()));
        datosS.setFechanac(this.logica.jTxtFechaNacS.getText());
        datosS.setCui(Long.parseLong(this.logica.jTxtCuiS.getText())); 
        Adm.setCodigo(this.logica.jTxtCodigoAdmS.getText());
        datosS.setPass(this.logica.jPsfContraS.getText());
        datosS.setEstado(1);
        
            mensaje = daoS.nuevaSecretaria(datosS, Adm);
        //mensaje = daoS.nuevaSecretaria(datosS);
        
        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaSecre();
 
    }
    
    
    public void listaSecre(){
        ArrayList<Secretaria> lista = new ArrayList();
        lista= daoS.MostrarSecretaria();
        DefaultTableModel tablaS = new DefaultTableModel();
        this.logica.jTblSecre.setModel(tablaS);
        
        tablaS.addColumn("Codigo");
        tablaS.addColumn("nombre");
        tablaS.addColumn("apellido");
        tablaS.addColumn("movil");
        
         Object[] columna= new Object[4];
        
        
         for (int i = 0; i < lista.size(); i++) { 
             columna[0] = lista.get(i).getCodigo();
             columna[1] = lista.get(i).getNombre();
             columna[2] = lista.get(i).getApellido();
             columna[3] = lista.get(i).getTelMovil();
             
             tablaS.addRow(columna);
         }                                  

    }
    
    public void limpiarControles(){
          logica.jTxtCodigoS.setText(null);
          logica.jTxtNombreS.setText(null);
          logica.jTxtApellidoS.setText(null);
          logica.jTxtDireccionS.setText(null);
          logica.jTxtEmailS.setText(null);
          logica.jTxtTelCasaS.setText(null);
          logica.jTxtTelMovilS.setText(null);
          logica.jTxtFechaNacS.setText(null);
          logica.jTxtCuiS.setText(null);
          logica.jPsfContraS.setText(null);
          logica.jTxtCodigoS.requestFocus();
          
         }
    
    
    

   
    
    
}
