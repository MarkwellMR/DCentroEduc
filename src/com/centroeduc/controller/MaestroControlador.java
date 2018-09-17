package com.centroeduc.controller;

import com.centroeduc.dao.MaestroDAO;
import com.centroeduc.model.Maestro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.JFrmMaestro;

/**
 *
 * @author Usuario
 */
public class MaestroControlador implements ActionListener {

    JFrmMaestro maestro = new JFrmMaestro();
    MaestroDAO dao = new MaestroDAO();
    Maestro mae = new Maestro();

    public MaestroControlador(JFrmMaestro ma) {

        this.maestro = ma;
        this.maestro.jBtnGuardar.addActionListener(this);
        listaMaestro();
    }

    @Override
    public void actionPerformed(ActionEvent eventoM) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("Maestro");
        if (eventoM.getSource() == this.maestro.jBtnGuardar) {
            guardarMaestro();
        }
    }

    public void guardarMaestro() {
        String mensaje = null;

        mae.setCodigo(this.maestro.jTxtCodigo.getText());
        mae.setNombre(this.maestro.jTxtNombre.getText());
        mae.setApellido(this.maestro.jTxtApellido.getText());
        mae.setDireccion(this.maestro.jTxtDireccion.getText());
        mae.setEmail(this.maestro.jTxtEmail.getText());
        mae.setTelCasa(Integer.parseInt(this.maestro.jTxtTelCasa.getText()));
        mae.setTelMovil(Integer.parseInt(this.maestro.jTxtTelMovil.getText()));
        mae.setFechanac(this.maestro.jTxtFechaNac.getText());
        mae.setCui(Long.parseLong(this.maestro.jTxtCui.getText()));
        mae.setCodigoA(this.maestro.jTxtCodigoA.getText());
        mae.setPass(this.maestro.jPsfContra.getText());
        mae.setEstado(1);
        mensaje = dao.insertarMaestro(mae);

        JOptionPane.showMessageDialog(null, mensaje);
        limpiarControles();
        listaMaestro();

    }
    

    public void listaMaestro() {
        ArrayList<Maestro> list = new ArrayList();
        list = dao.Mostrarprofesor();
        DefaultTableModel tabla = new DefaultTableModel();
        this.maestro.jTblMaestro.setModel(tabla);

        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Apellido");
        tabla.addColumn("Direccion");
        tabla.addColumn("Email");
        tabla.addColumn("Tel Casa");
        tabla.addColumn("Tel Movil");
        tabla.addColumn("Fecha de Nacimiento");
        tabla.addColumn("Cui");
        tabla.addColumn("Codigo Admin");

        Object[] columna = new Object[11];

        for (int i = 0; i < list.size(); i++) {
            columna[0] = list.get(i).getCodigo();
            columna[1] = list.get(i).getNombre();
            columna[2] = list.get(i).getApellido();
            columna[3] = list.get(i).getDireccion();
            columna[4] = list.get(i).getEmail();
            columna[5] = list.get(i).getTelCasa();
            columna[6] = list.get(i).getTelMovil();
            columna[7] = list.get(i).getFechanac();
            columna[8] = list.get(i).getCui();
            columna[9] = list.get(i).getCodigoA();

            tabla.addRow(columna);
        }
    }

    public void limpiarControles() {
        maestro.jTxtCodigo.setText(null);
        maestro.jTxtNombre.setText(null);
        maestro.jTxtApellido.setText(null);
        maestro.jTxtDireccion.setText(null);
        maestro.jTxtEmail.setText(null);
        maestro.jTxtTelCasa.setText(null);
        maestro.jTxtTelMovil.setText(null);
        maestro.jTxtFechaNac.setText(null);
        maestro.jTxtCui.setText(null);
        maestro.jTxtCodigoA.setText(null);
        maestro.jPsfContra.setText(null);
    }

}
