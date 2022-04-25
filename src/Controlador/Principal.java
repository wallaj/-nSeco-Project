/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Controlador.Controlador_nSeco;
import Modelo.Modelo_nSeco;
import Vista.Vista_nSeco;
import com.sun.org.apache.bcel.internal.util.ClassPath;
import javax.swing.JOptionPane;


/**
 *
 * @author Marcos
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Modelo_nSeco modeloCal = new Modelo_nSeco();
       Vista_nSeco vistaCal = new Vista_nSeco();
       Controlador_nSeco controlador = new Controlador_nSeco(modeloCal, vistaCal);
       controlador.cargarPropiedades();
       
    }    
}
