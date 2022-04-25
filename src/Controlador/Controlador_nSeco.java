/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.Frame;

import Modelo.Modelo_nSeco;
import Vista.Vista_nSeco;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Marcos
 */
public class Controlador_nSeco implements ActionListener, KeyListener, MouseMotionListener{

   private Modelo_nSeco model;
   private Vista_nSeco view;
   private boolean muro; //flags
   private boolean cieloraso; //flags
   private boolean juntaTomada; //flags cielorraso
   private boolean desmontable; //flags cielorraso
   private boolean placa06x06;  //flags desmontable-->false = 0.60x1.20
   private boolean simple;      //flags muro
   private boolean doble;       //flags muro
   private KeyListener envenTecla;
   private MouseMotionListener mouseMotion;
   private int iteradorMaterial;
   private ArrayList<String>valores = new ArrayList();
   private final String[] OPCIONES = {"ACEPTAR","CANCELAR"};
   private Properties propiedades = new Properties();
   private InputStream flujoEntrada = null;
   private String nombreOrg;
   private String RutaDelSistema=""; 
   
   public Controlador_nSeco( Modelo_nSeco model, Vista_nSeco view) {
      
       this.model = model;
       this.view = view;
       this.iteradorMaterial = 0;
       
       KeyListener evenTecla = new KeyListener() {
      
            @Override
            public void keyTyped(KeyEvent ke) {
             
            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {
                
                    
            }
       };
       
        this.envenTecla = evenTecla;
        this.mouseMotion = mouseMotion;
        //this.view.dispose();
        this.iniciarVista();

        this.escucharControles(this.envenTecla, this.mouseMotion);//parametro para el keylistener del txtUnidad
        //Selectores en view
        this.view.selectorCielorrasos.setVisible(false);
        this.view.selectorDesmontable.setVisible(false);
        this.view.selectorDoble.setVisible(false);
        this.view.selectorJuntaTomada.setVisible(false);
        this.view.selectorMuros.setVisible(false);
        this.view.selectorSimple.setVisible(false);
        this.view.selectorPlaca.setVisible(false);
        this.view.lblPlaca.setVisible(false);
        //Botones en view
        this.view.btnDoble.setVisible(false);
        this.view.btnDesmontable.setVisible(false);
        this.view.btnJuntaTomada.setVisible(false);
        this.view.btnPlaca.setVisible(false);
        this.view.btnSimple.setVisible(false);
        //Controles presupuestar
        this.view.lblUnidad.setVisible(false);
        this.view.txtCantidad.setVisible(false);
        //Toggle placa0.60x0.60
        this.setPlaca06x06(false);
        //Obtener ruta del sistema
        this.RutaDelSistema = getClass().getProtectionDomain().getCodeSource().getLocation().toString();
        //Acortar Ruta del sistema
        this.RutaDelSistema = RutaDelSistema.substring(6, (RutaDelSistema.length()-16));
   }
   
   //Getters y setters
    public boolean isMuro() {
        return muro;
    }
    public void setMuro(boolean muro) {
        this.muro = muro;
    }
    public boolean isCieloraso() {
        return cieloraso;
    }
    public void setCieloraso(boolean cieloraso) {
        this.cieloraso = cieloraso;
    }
    public Modelo_nSeco getModel() {
        return model;
    }
    public void setModel(Modelo_nSeco model) {
        this.model = model;
    }
    public Vista_nSeco getView() {
        return view;
    }
    public void setView(Vista_nSeco view) {
        this.view = view;
    }
    public boolean isJuntaTomada() {
        return juntaTomada;
    }
    public void setJuntaTomada(boolean juntaTomada) {
        this.juntaTomada = juntaTomada;
    }
    public boolean isDesmontable() {
        return desmontable;
    }
    public void setDesmontable(boolean desmontable) {
        this.desmontable = desmontable;
    }
    public boolean isPlaca06x06() {
        return placa06x06;
    }
    public void setPlaca06x06(boolean placa06x06) {
        this.placa06x06 = placa06x06;
    }
    public boolean isSimple() {
        return simple;
    }
    public void setSimple(boolean simple) {
        this.simple = simple;
    }
    public boolean isDoble() {
        return doble;
    }
    public void setDoble(boolean doble) {
        this.doble = doble;
    }
    public int getIteradorMaterial() {
        return iteradorMaterial;
    }
    public void setIteradorMaterial(int iteradorMaterial) {
        this.iteradorMaterial = iteradorMaterial;
    }
    public ArrayList<String> getValores() {
        return valores;
    }
    public void setValores(ArrayList<String> valores) {
        this.valores = valores;
    }
    public String getNombreOrg() {
        return nombreOrg;
    }
    public void setNombreOrg(String nombreOrg) {
        this.nombreOrg = nombreOrg;
    }
 
   public void escucharControles(KeyListener kl, MouseMotionListener ml){
        this.view.btnCielorrasos.addActionListener(this);
        this.view.btnJuntaTomada.addActionListener(this);
        this.view.btnDesmontable.addActionListener(this);
        this.view.btnDoble.addActionListener(this);
        this.view.btnMuros.addActionListener(this);
        this.view.btnSimple.addActionListener(this);
        this.view.btnCalcular.addActionListener(this);
        this.view.btnCerrar2.addActionListener(this);
        this.view.btnMinim.addActionListener(this);
        this.view.btnPlaca.addActionListener(this);
        this.view.btnToPDF.addActionListener(this);
        this.view.btnPresupuesto.addActionListener(this);
        this.view.btnConfiguracion.addActionListener(this);
        this.view.txtCantidad.addKeyListener(this);
        this.view.txtMetros.addKeyListener(this);
        this.view.btnPresupuesto.addMouseMotionListener(this); 
   }
   //Método para cargar datos de propiedades
   public void cargarPropiedades(){
       try {
           this.flujoEntrada = new FileInputStream(this.RutaDelSistema + "nSeco.properties"); //Objeto tipo FileInputStream "flujoEntrada" contiene el archivo "nSeco.properties" que queremos cargar
           this.propiedades.load(this.flujoEntrada);//Cargando propiedades con el objeto tipo FileInputStream "flujoEntrada" 
           this.nombreOrg = this.propiedades.getProperty("nombreOrganizacion"); //Guarda en el atributo nombreOrg la configuración del archivo "nSeco.properties"
           //correspondiente a la clave "nombreOrganizacion"
       } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage(),"'nSECO PROJECT",JOptionPane.ERROR_MESSAGE,this.icono());
       } catch (IOException ex){
           JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage(),"'nSECO PROJECT",JOptionPane.ERROR_MESSAGE,this.icono());
       }
       
   }
   public void modificarPropiedades(String ingreso){
       
       try {
           this.flujoEntrada = new FileInputStream(this.RutaDelSistema + "nSeco.properties"); //Objeto tipo FileInputStream "flujoEntrada" contiene el archivo "nSeco.properties" que queremos cargar
           this.propiedades.load(this.flujoEntrada);//Cargando propiedades con el objeto tipo FileInputStream "flujoEntrada" 
           this.propiedades.setProperty("nombreOrganizacion",ingreso);//Cambiamos el valor que corresponde a la clave "nombreOrganización" en el archvo "nSeco.properties"
           this.propiedades.store(new FileWriter("nSeco.properties"), "Datos de propiedades modificados");//Almacenamos las modificaciones del objeto "propiedades" en el
           
           JOptionPane.showMessageDialog(null, "NOMBRE CARGADO CORRECTAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
           this.cargarPropiedades();//Se cargan de nuevo las propiedades
           
       } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage(),"'nSECO PROJECT",JOptionPane.ERROR_MESSAGE,this.icono());
       } catch (IOException ex){
           JOptionPane.showMessageDialog(null,"ERROR: "+ ex.getMessage(),"'nSECO PROJECT",JOptionPane.ERROR_MESSAGE,this.icono());
       }
   }
   public void iniciarVista(){
       this.view.pack();
       this.view.dispose();
       this.view.setUndecorated(true);
       this.view.setLocationRelativeTo(null);
       this.view.setVisible(true);
   } 
   //Método para retornar el icono de la app
   public Icon icono(){
       Icon icono =new ImageIcon(getClass().getResource("/ImagenesGUI/LOGOnSECOJOp.png"));
       
       return icono;
   }
   public void exportarPDF(/*String nombreOrg*/){
      JFileChooser filedlg = new JFileChooser(); //Objeto FileChooser
            int option = filedlg.showSaveDialog(view); //Cuadro de dialogo para guardar
            String filePath = "";
            if(option == JFileChooser.APPROVE_OPTION){
                File f = filedlg.getSelectedFile();
                 filePath = f.toString();
            }
            try{
                FileOutputStream archivo = new FileOutputStream(filePath+".pdf");
                //Armado del PDF 
                Document doc = new Document();
                PdfWriter.getInstance(doc, archivo); //Asociar documento al Output Stream
                //apertura del PDF
                doc.open();
                //Escritura en el PDF
                doc.add(new Paragraph(obtenerFecha()+"\n"+this.view.txtPresupuesto.getText()+"\n\n"+
                    this.getNombreOrg()));
                //Cierre del PDF
                doc.close();
                JOptionPane.showMessageDialog(null, "PDF EXPORTADO CORRECTAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "ERROR: "+e.getMessage(),"'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            } 
   }
   public String obtenerFecha(){
       Date fecha = new Date();
       SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/YYYY");
       return formatoFecha.format(fecha);
   }
   //Método para obtener los valores ingresados 
   public void obtenerPrecios(boolean controlesVisibles, int index){
        if(controlesVisibles){
            if((this.isCieloraso()) && !(this.isMuro())){//CIERLORRASO
                if((this.isJuntaTomada()) && !(this.isDesmontable())){//PRECIOS JUNTA TOMADA
                    if(index<9){ //Control de indice para llenar campos
                        this.valores.add(index, this.view.txtCantidad.getText());//Captura del dato ingresado en la GUI
                        this.view.txtCantidad.setText("");//Limpiar control
                        this.view.lblUnidad.setText(this.labelsJuntaTomada(index+1));//Cambiar la etiqueta del label
                        this.setIteradorMaterial(index+1);//Invrementar el campo del iterador
                    }
                    if(this.iteradorMaterial==9){
                      if(valores.size()==9){ JOptionPane.showMessageDialog(null, "DATOS CARGADOS CORRECTAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());  
                      this.preciosJuntaTomada();//Cálculo de subtotales de los materiales
                      this.view.txtPresupuesto.setText(this.model.toStringPresTomada(this.valores)+
                            "\n\nTOTAL PRESUPUESTADO: $"+ this.model.getCieloRraso().getPresupuesto().sumaJuntaTomada());//Imprime en la GUI el presupuesto
                      this.valores.clear();//Contenedor de valores
                      this.setIteradorMaterial(0);//Vuelve el iterador a cero  
                      this.view.txtCantidad.setVisible(false);//Controles invisibles
                      this.view.lblUnidad.setVisible(false);//Controles invisibles
                      }else{
                          JOptionPane.showMessageDialog(null, "LOS DATOS NO SE CARGARON CORRECTAMENTE. INTENTE NUEVAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                          this.valores.clear();
                          this.setIteradorMaterial(0);
                      }
                    }
                }else if(!(this.isJuntaTomada()) && (this.isDesmontable())){//PRECIOS DESMONTABLE
                     if(index<6){ //Control de indice para llenar campos
                        this.valores.add(index, this.view.txtCantidad.getText());//Captura del dato ingresado en la GUI
                        this.view.txtCantidad.setText("");//Limpiar control
                        this.view.lblUnidad.setText(this.labelsDesmontable(index+1));//Cambiar la etiqueta del label
                        this.setIteradorMaterial(index+1);//Invrementar el campo del iterador
                    }
                    if(this.iteradorMaterial==6){
                      if(valores.size()==6){ JOptionPane.showMessageDialog(null, "DATOS CARGADOS CORRECTAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                      this.preciosDesmontable();//Cálculo de subtotales de los materiales
                      this.view.txtPresupuesto.setText(this.model.toStringPresDesmontable(this.valores)+
                            "\n\nTOTAL PRESUPUESTADO: $"+this.model.getCieloRraso().getPresupuesto().sumaDesmontable());//Imprime en la GUI el presupuesto
                      this.valores.clear();//Contenedor de valores
                      this.setIteradorMaterial(0);//Vuelve el iterador a cero  
                      this.view.txtCantidad.setVisible(false);//Controles invisibles
                      this.view.lblUnidad.setVisible(false);//Controles invisibles
                      }else{
                          JOptionPane.showMessageDialog(null, "LOS DATOS NO SE CARGARON CORRECTAMENTE. INTENTE NUEVAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                          this.valores.clear();
                          this.setIteradorMaterial(0);
                      }
                    }
                }
            }else if(!(this.isCieloraso()) && (this.isMuro())){//MURO
                if((this.isSimple()) && !(this.isDoble())){//PRECIOS SIMPLE
                    if(index<10){ //Control de indice para llenar campos
                        this.valores.add(index, this.view.txtCantidad.getText());//Captura del dato ingresado en la GUI
                        this.view.txtCantidad.setText("");//Limpiar control
                        this.view.lblUnidad.setText(this.labelsSimple(index+1));//Cambiar la etiqueta del label
                        this.setIteradorMaterial(index+1);//Incrementar el campo del iterador
                    }
                    if(this.iteradorMaterial==10){
                      if(valores.size()==10) {JOptionPane.showMessageDialog(null, "DATOS CARGADOS CORRECTAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                      this.preciosSimple();//Cálculo de subtotales de los materiales
                      this.view.txtPresupuesto.setText(this.model.toStringPresSimple(this.valores)+
                            "\n\nTOTAL PRESUPUESTADO: $"+this.model.getMuro().getPresupuesto().sumaSimple());//Imprime en la GUI el presupuesto
                      this.valores.clear();//Contenedor de valores
                      this.setIteradorMaterial(0);//Vuelve el iterador a cero  
                      this.view.txtCantidad.setVisible(false);//Controles invisibles
                      this.view.lblUnidad.setVisible(false);//Controles invisibles
                      }else{
                          JOptionPane.showMessageDialog(null, "LOS DATOS NO SE CARGARON CORRECTAMENTE. INTENTE NUEVAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                          this.valores.clear();
                          this.setIteradorMaterial(0);
                      }
                    }
                }else if(!(this.isSimple()) && (this.isDoble())){//PRECIOS DOBLE
                    if(index<11){ //Control de indice para llenar campos
                        this.valores.add(index, this.view.txtCantidad.getText());//Captura del dato ingresado en la GUI
                        this.view.txtCantidad.setText("");//Limpiar control
                        this.view.lblUnidad.setText(this.labelsDoble(index+1));//Cambiar la etiqueta del label
                        this.setIteradorMaterial(index+1);//Incrementar el campo del iterador
                    }if(this.iteradorMaterial==11){
                      if(valores.size()==11) {JOptionPane.showMessageDialog(null, "DATOS CARGADOS CORRECTAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                      this.preciosDoble();//Cálculo de subtotales de los materiales
                      this.view.txtPresupuesto.setText(this.model.toStringPresDoble(this.valores)+
                            "\n\nTOTAL PRESUPUESTADO: $"+this.model.getMuro().getPresupuesto().sumaDoble());//Imprime en la GUI el presupuesto
                      this.valores.clear();//Contenedor de valores  
                      this.setIteradorMaterial(0);//Vuelve el iterador a cero 
                      this.view.txtCantidad.setVisible(false);//Controles invisibles
                      this.view.lblUnidad.setVisible(false);//Controles invisibles
                      }else{
                          JOptionPane.showMessageDialog(null, "LOS DATOS NO SE CARGARON CORRECTAMENTE. INTENTE NUEVAMENTE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                          this.valores.clear();
                          this.setIteradorMaterial(0);
                      }
                    }
                }
            }
        }
   }
   public String labelsJuntaTomada(int index){
       String label="";
       switch(index){
           case 0: label ="PLACA  $";
                break;
           case 1: label ="SOLERA $";
                break;
           case 2: label ="MONTANT $";
                break;
           case 3: label ="TORN. T1 $";
                break;
           case 4: label ="TORN. T2 $";
                break;
           case 5: label ="MASILLA $";
                break;
           case 6: label ="CINTA P. $";
                 break;
           case 7: label ="ENDUIDO $";
                break;
           case 8: label ="FIJACION $";
                break;
           default: label ="";
                break;
       } 
       return label;
   }
   public String labelsDesmontable(int index){
       String label="";
       switch(index){
           case 0: label ="PLACA  $";
                break;
           case 1: label ="FIJACION $";
                break;
           case 2: label ="PERIMET $";
                break;
           case 3: label ="LARGUER $";
                break;
           case 4: label ="TRAVES $";
                break;
           case 5: label ="ALAMBRE $";
                break;
           default: label ="";
                break;
       } 
       return label;
   }
   public String labelsSimple(int index){
       String label="";
       switch(index){
           case 0: label ="PLACA  $";
                break;
           case 1: label ="SOLERA $";
                break;
           case 2: label ="MONTANT $";
                break;
           case 3: label ="TORN. T1 $";
                break;
           case 4: label ="TORN. T2 $";
                break;
           case 5: label ="MASILLA $";
                break;
           case 6: label ="CINTA P. $";
                 break;
           case 7: label ="ENDUIDO $";
                break;
           case 8: label ="FIJACION $";
                break;
           case 9: label ="AISLANTE $";
                break;
           default: label ="";
                break;
       } 
       return label;
   }
   public String labelsDoble(int index){
       String label="";
       switch(index){
           case 0: label ="PLACA  $";
                break;
           case 1: label ="SOLERA $";
                break;
           case 2: label ="MONTANT $";
                break;
           case 3: label ="TORN. T1 $";
                break;
           case 4: label ="TORN. T2 $";
                break;
           case 5: label ="TORN. T3 $";
                break;
           case 6: label ="MASILLA $";
                 break;
           case 7: label ="CINTA P. $";
                break;
           case 8: label ="ENDUIDO $";
                break;
           case 9: label ="FIJACION $";
                break;
           case 10: label ="AISLANTE $";
                break;
           default: label ="";
                break;
       } 
       return label;
   }
   public void preciosJuntaTomada(){
       this.model.getCieloRraso().getPresupuesto().setValPlaca(//PLACA
            this.model.getCieloRraso().getCantPlacas()*(Double.parseDouble(this.valores.get(0))));
       this.model.getCieloRraso().getPresupuesto().setValSolera35(//SOLERA 35
            this.model.getCieloRraso().getCantSolera35mm()*(Double.parseDouble(this.valores.get(1))));
       this.model.getCieloRraso().getPresupuesto().setValMontante34(//MONTANTE 34
            this.model.getCieloRraso().getCantSolera35mm()*(Double.parseDouble(this.valores.get(2))));
       this.model.getCieloRraso().getPresupuesto().setValTornillosT1(//TOR T1
            this.model.getCieloRraso().getCantTornillosT1()*(Double.parseDouble(this.valores.get(3))));
       this.model.getCieloRraso().getPresupuesto().setValTornillosT2(//TOR T2
            this.model.getCieloRraso().getCantTornillosT2()*(Double.parseDouble(this.valores.get(4))));
       this.model.getCieloRraso().getPresupuesto().setValMasilla(//MASILLA
            this.model.getCieloRraso().getCantMasilla()*(Double.parseDouble(this.valores.get(5))));
       this.model.getCieloRraso().getPresupuesto().setValCintaPapel(//CINTA
            this.model.getCieloRraso().getCantCintaPapel()*(Double.parseDouble(this.valores.get(6))));
       this.model.getCieloRraso().getPresupuesto().setValEnduido(//ENDUIDO
            this.model.getCieloRraso().getCantEnduido()*(Double.parseDouble(this.valores.get(7))));
       this.model.getCieloRraso().getPresupuesto().setValFijaciones(//FIJACIONES
            this.model.getCieloRraso().getCantFijaciones()*(Double.parseDouble(this.valores.get(8))));
   }
   public void preciosDesmontable(){
       this.model.getCieloRraso().getPresupuesto().setValPlaca(//PLACA
            this.model.getCieloRraso().getCantPlacas() * (Double.parseDouble(this.valores.get(0))));
       this.model.getCieloRraso().getPresupuesto().setValFijaciones(//FIJACIONES
            this.model.getCieloRraso().getCantFijaciones() * (Double.parseDouble(this.valores.get(1))));
       this.model.getCieloRraso().getPresupuesto().setValPerimetrales(//PERIMETRALES
            this.model.getCieloRraso().getPerimetrales() * (Double.parseDouble(this.valores.get(2))));
       this.model.getCieloRraso().getPresupuesto().setValLargueros(//LARGUEROS
            this.model.getCieloRraso().getLargueros() * (Double.parseDouble(this.valores.get(3))));
       this.model.getCieloRraso().getPresupuesto().setValTravesanios(//TRAVESAÑOS
            this.model.getCieloRraso().getTravesanios() * (Double.parseDouble(this.valores.get(4))));
       this.model.getCieloRraso().getPresupuesto().setValAlambre(//ALAMBRE N14
            this.model.getCieloRraso().getAlambreN14() * (Double.parseDouble(this.valores.get(5))));
   }
   public void preciosSimple(){
       this.model.getMuro().getPresupuesto().setValPlaca(//PLACA
            this.model.getMuro().getCantPlacas() * (Double.parseDouble(this.valores.get(0))));
       this.model.getMuro().getPresupuesto().setValSolera70(//SOLERAS
            this.model.getMuro().getCantSolera70mm() * (Double.parseDouble(this.valores.get(1))));
       this.model.getMuro().getPresupuesto().setValMontante69(//MONTANTES
            this.model.getMuro().getCantMontante69mm() * (Double.parseDouble(this.valores.get(2))));
       this.model.getMuro().getPresupuesto().setValTornillosT1(//TOR T1
            this.model.getMuro().getCantTornillosT1() * (Double.parseDouble(this.valores.get(3))));
       this.model.getMuro().getPresupuesto().setValTornillosT2(//TOR T2
            this.model.getMuro().getCantTornillosT2() * (Double.parseDouble(this.valores.get(4))));
       this.model.getMuro().getPresupuesto().setValMasilla(//MASILLA
            this.model.getMuro().getCantMasilla() * (Double.parseDouble(this.valores.get(5))));
       this.model.getMuro().getPresupuesto().setValCintaPapel(//CINTA DE PAPEL
            this.model.getMuro().getCantCintaPapel() * (Double.parseDouble(this.valores.get(6))));
       this.model.getMuro().getPresupuesto().setValEnduido(//ENDUIDO
            this.model.getMuro().getCantEnduido() * (Double.parseDouble(this.valores.get(7))));
       this.model.getMuro().getPresupuesto().setValFijaciones(//FIJACIONES
            this.model.getMuro().getCantFijaciones() * (Double.parseDouble(this.valores.get(8))));
       this.model.getMuro().getPresupuesto().setValAlambre(//AISLANTE
            this.model.getMuro().getCantMatAislante() * (Double.parseDouble(this.valores.get(9))));
   }
   public void preciosDoble(){
       this.model.getMuro().getPresupuesto().setValPlaca(//PLACA
            this.model.getMuro().getCantPlacas() * (Double.parseDouble(this.valores.get(0))));
       this.model.getMuro().getPresupuesto().setValSolera70(//SOLERAS
            this.model.getMuro().getCantSolera70mm() * (Double.parseDouble(this.valores.get(1))));
       this.model.getMuro().getPresupuesto().setValMontante69(//MONTANTES
            this.model.getMuro().getCantMontante69mm() * (Double.parseDouble(this.valores.get(2))));
       this.model.getMuro().getPresupuesto().setValTornillosT1(//TOR T1
            this.model.getMuro().getCantTornillosT1() * (Double.parseDouble(this.valores.get(3))));
       this.model.getMuro().getPresupuesto().setValTornillosT2(//TOR T2
            this.model.getMuro().getCantTornillosT2() * (Double.parseDouble(this.valores.get(4))));
       this.model.getMuro().getPresupuesto().setValTornillosT3(//TOR T3
            this.model.getMuro().getCantTornillosT3() * (Double.parseDouble(this.valores.get(5))));
       this.model.getMuro().getPresupuesto().setValMasilla(//MASILLA
            this.model.getMuro().getCantMasilla() * (Double.parseDouble(this.valores.get(6))));
       this.model.getMuro().getPresupuesto().setValCintaPapel(//CINTA DE PAPEL
            this.model.getMuro().getCantCintaPapel() * (Double.parseDouble(this.valores.get(7))));
       this.model.getMuro().getPresupuesto().setValEnduido(//ENDUIDO
            this.model.getMuro().getCantEnduido() * (Double.parseDouble(this.valores.get(8))));
       this.model.getMuro().getPresupuesto().setValFijaciones(//FIJACIONES
            this.model.getMuro().getCantFijaciones() * (Double.parseDouble(this.valores.get(9))));
       this.model.getMuro().getPresupuesto().setValAislante(//AISLANTE
            this.model.getMuro().getCantMatAislante() * (Double.parseDouble(this.valores.get(10))));
   }
   
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource().equals(this.view.btnCielorrasos)){//BTN CIELORRASOS
            //Estado botones
            this.view.btnJuntaTomada.setVisible(true);//True
            this.view.btnDesmontable.setVisible(true);//True
            this.view.btnPlaca.setVisible(false);
            this.view.btnDoble.setVisible(false);
            this.view.btnSimple.setVisible(false);
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(true);//True
            this.view.selectorMuros.setVisible(false);
            this.view.selectorDesmontable.setVisible(false);
            this.view.selectorJuntaTomada.setVisible(false);
            this.view.selectorPlaca.setVisible(false);
            this.view.selectorDoble.setVisible(false); 
            this.view.selectorSimple.setVisible(false);
            this.view.lblPlaca.setVisible(false);
            //Estado Flags
            this.setCieloraso(true);//true
            this.setMuro(false);
            this.setDesmontable(false);
            this.setJuntaTomada(false);
            //Estado controles
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");            
            this.view.chkDesperdicio.setSelected(false);
            this.view.txtCantidad.setVisible(false);
            this.view.lblUnidad.setVisible(false);
            ///////////////////////////////////////////
        
        }else if(ae.getSource().equals(this.view.btnMuros)){ //BTN MUROS
            //Estado botones
            this.view.btnJuntaTomada.setVisible(false);
            this.view.btnDesmontable.setVisible(false);
            this.view.btnPlaca.setVisible(false);
            this.view.btnDoble.setVisible(true);//True
            this.view.btnSimple.setVisible(true);//True
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(false);
            this.view.selectorMuros.setVisible(true);//True
            this.view.selectorDesmontable.setVisible(false);
            this.view.selectorJuntaTomada.setVisible(false);
            this.view.selectorPlaca.setVisible(false);
            this.view.selectorDoble.setVisible(false); 
            this.view.selectorSimple.setVisible(false);
            this.view.lblPlaca.setVisible(false);
            //Estado Flags
            this.setCieloraso(false);
            this.setMuro(true);//true
            this.setDesmontable(false);
            this.setJuntaTomada(false);
            //Estado controles
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");            
            this.view.chkDesperdicio.setSelected(false);
            this.view.txtCantidad.setVisible(false);
            this.view.lblUnidad.setVisible(false);
            ///////////////////////////////////////////
        
        }else if(ae.getSource().equals(this.view.btnCerrar2)){ //BTN CERRAR
            this.view.dispose();
            //////////////////////////////////////////
        
        }else if(ae.getSource().equals(this.view.btnMinim)){ //BTN MINIMIZAR
            this.view.setState(Frame.ICONIFIED);
            /////////////////////////////////////////
        
        }else if((ae.getSource().equals(this.view.btnJuntaTomada))&&(this.isCieloraso())){//JUNTA TOMADA
             //Estado botones
            this.view.btnJuntaTomada.setVisible(true);//true
            this.view.btnDesmontable.setVisible(true);//true
            this.view.btnPlaca.setVisible(false);
            this.view.btnDoble.setVisible(false);
            this.view.btnSimple.setVisible(false);
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(true);//True
            this.view.selectorJuntaTomada.setVisible(true);//True
            this.view.selectorMuros.setVisible(false);
            this.view.selectorDesmontable.setVisible(false);
            this.view.selectorPlaca.setVisible(false);
            this.view.selectorDoble.setVisible(false); 
            this.view.selectorSimple.setVisible(false);
            this.view.lblPlaca.setVisible(false);
            //Estado Flags
            this.setCieloraso(true);//true
            this.setMuro(false);
            this.setJuntaTomada(true);//true
            this.setDesmontable(false);
            this.setPlaca06x06(false);
            this.setDoble(false);
            this.setSimple(false);
            //Estado controles
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");            
            this.view.chkDesperdicio.setSelected(false);
            this.view.txtCantidad.setVisible(false);
            this.view.lblUnidad.setVisible(false);
            JOptionPane.showMessageDialog(null, "PARA DECIMALES EN LOS METROS CUADRADOS UTILIZÁ EL PUNTO ' . '","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            this.view.txtMetros.requestFocus();
            ////////////////////////////////////////////////   
        
        }else if((ae.getSource().equals(this.view.btnDesmontable))&&(this.isCieloraso())){//BTN DESMONTABLE
            //Estado botones
            this.view.btnJuntaTomada.setVisible(true);//True
            this.view.btnDesmontable.setVisible(true);//True
            this.view.btnPlaca.setVisible(true);//True
            this.view.btnDoble.setVisible(false);
            this.view.btnSimple.setVisible(false);
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(true);//True
            this.view.selectorMuros.setVisible(false);
            this.view.selectorDesmontable.setVisible(true);//True
            this.view.selectorJuntaTomada.setVisible(false);
            this.view.selectorPlaca.setVisible(false);
            this.view.selectorDoble.setVisible(false); 
            this.view.selectorSimple.setVisible(false);
            this.view.lblPlaca.setVisible(true);//True
            //Estado Flags
            this.setCieloraso(true);//true
            this.setMuro(false);
            this.setJuntaTomada(false);
            this.setDesmontable(true);//True
            this.setPlaca06x06(false);
            this.setDoble(false);
            this.setSimple(false);
            //Estado controles
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");            
            this.view.chkDesperdicio.setSelected(false);
            this.view.txtCantidad.setVisible(false);
            this.view.lblUnidad.setVisible(false);
            JOptionPane.showMessageDialog(null, "SELECCIONADO PLACAS 1.20m x 0.60m POR DEFECTO","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            JOptionPane.showMessageDialog(null, "PARA DECIMALES EN LOS METROS CUADRADOS UTILIZÁ EL PUNTO ' . '","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            this.view.txtMetros.requestFocus();
            /////////////////////////////////////////////////
        
        }else if((ae.getSource().equals(this.view.btnPlaca))&&(this.isCieloraso())&&(this.isDesmontable())){//BTN PLACA
            //Estado botones
            this.view.btnJuntaTomada.setVisible(true);//True
            this.view.btnDesmontable.setVisible(true);//True
            this.view.btnPlaca.setVisible(true);//True
            this.view.btnDoble.setVisible(false);
            this.view.btnSimple.setVisible(false);
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(true);//True
            this.view.selectorMuros.setVisible(false);
            this.view.selectorDesmontable.setVisible(true);//True
            this.view.selectorJuntaTomada.setVisible(false);
            this.view.selectorDoble.setVisible(false); 
            this.view.selectorSimple.setVisible(false);
            //Estado Flags
            this.setCieloraso(true);//true
            this.setMuro(false);
            this.setJuntaTomada(false);
            this.setDesmontable(true);//True
            this.setDoble(false);
            this.setSimple(false);
            //Toggle lblPlaca, Placa06x06
            if(this.isPlaca06x06()){
                 this.setPlaca06x06(false);
                 this.view.lblPlaca.setVisible(true);
                 this.view.selectorPlaca.setVisible(false);
                 JOptionPane.showMessageDialog(null, "SELECCIONADO PLACAS 1.20m x 0.60m","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }else{
                this.setPlaca06x06(true);
                this.view.lblPlaca.setVisible(false);
                this.view.selectorPlaca.setVisible(true);//true
                 JOptionPane.showMessageDialog(null, "SELECCIONADO PLACAS 0.60m x 0.60m","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }
            JOptionPane.showMessageDialog(null, "PARA DECIMALES EN LOS METROS CUADRADOS UTILIZÁ EL PUNTO ' . '","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            this.view.txtMetros.requestFocus();
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");      
            /////////////////////////////////////////////////
        
        }else if((ae.getSource().equals(this.view.btnDoble))&&(this.isMuro())){//BTN DOBLE
            //Estado botones
            this.view.btnJuntaTomada.setVisible(false);
            this.view.btnDesmontable.setVisible(false);
            this.view.btnPlaca.setVisible(false);
            this.view.btnDoble.setVisible(true);//True
            this.view.btnSimple.setVisible(true);//True
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(false);
            this.view.selectorMuros.setVisible(true);//True
            this.view.selectorDesmontable.setVisible(false);
            this.view.selectorJuntaTomada.setVisible(false);
            this.view.selectorPlaca.setVisible(false);
            this.view.selectorDoble.setVisible(true);//true 
            this.view.selectorSimple.setVisible(false);
            this.view.lblPlaca.setVisible(false);
            //Estado Flags
            this.setCieloraso(false);
            this.setMuro(true);//true
            this.setDoble(true);//true
            this.setSimple(false);
            this.setPlaca06x06(false);
            this.setJuntaTomada(false);
            this.setDesmontable(false);
            //Estado controles
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");            
            this.view.chkDesperdicio.setSelected(false);
            this.view.txtCantidad.setVisible(false);
            this.view.lblUnidad.setVisible(false);
            JOptionPane.showMessageDialog(null, "PARA DECIMALES EN LOS METROS CUADRADOS UTILIZÁ EL PUNTO ' . '","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            this.view.txtMetros.requestFocus();
            //////////////////////////////////////
        
        }else if((ae.getSource().equals(this.view.btnSimple))&&(this.isMuro())){//BTN SIMPLE
            //Estado botones
            this.view.btnJuntaTomada.setVisible(false);
            this.view.btnDesmontable.setVisible(false);
            this.view.btnDoble.setVisible(true);//True
            this.view.btnSimple.setVisible(true);//True
            //Estado selectores
            this.view.selectorCielorrasos.setVisible(false);
            this.view.selectorMuros.setVisible(true);//True
            this.view.selectorDesmontable.setVisible(false);
            this.view.selectorJuntaTomada.setVisible(false);
            this.view.selectorPlaca.setVisible(false);
            this.view.selectorDoble.setVisible(false); 
            this.view.selectorSimple.setVisible(true);//true
            this.view.lblPlaca.setVisible(false);
            //Estado Flags
            this.setCieloraso(false);
            this.setMuro(true);//true
            this.setDoble(false);
            this.setSimple(true);//true
            this.setDesmontable(false);
            this.setJuntaTomada(false);
            this.setPlaca06x06(false);
            //Estado controles
            this.view.txtMetros.setText("");
            this.view.txtPresupuesto.setText("");            
            this.view.chkDesperdicio.setSelected(false);
            this.view.txtCantidad.setVisible(false);
            this.view.lblUnidad.setVisible(false);
            JOptionPane.showMessageDialog(null, "PARA DECIMALES EN LOS METROS CUADRADOS UTILIZÁ EL PUNTO ' . '","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            this.view.txtMetros.requestFocus();
            //////////////////////////////////////
        
        }else if(ae.getSource().equals(this.view.btnCalcular)){//BTN CALCULAR
            try{
                if(this.isCieloraso()){///CIELORRASOS
                    if(this.isJuntaTomada()){///JUNTA TOMADA
                        this.model.calcularCielorrasoJuntaTomada(
                            Double.valueOf(this.view.txtMetros.getText()),this.view.chkDesperdicio.isSelected());
                        this.view.txtPresupuesto.setText(this.model.getCieloRraso().toStringTomada());
                    }else if(this.isDesmontable()){///DESMONTABLE
                        if(this.isPlaca06x06()){///PLACA 0,60x0,60
                            this.model.calcularCielorrasoDesmonatble(
                                this.isPlaca06x06(), Double.valueOf(this.view.txtMetros.getText()), this.view.chkDesperdicio.isSelected());
                            this.view.txtPresupuesto.setText(this.model.getCieloRraso().toStringDesmontable());
                        }else{///PLACA 0,60x1,20
                            this.model.calcularCielorrasoDesmonatble(
                                this.isPlaca06x06(), Double.valueOf(this.view.txtMetros.getText()), this.view.chkDesperdicio.isSelected());
                            this.view.txtPresupuesto.setText(this.model.getCieloRraso().toStringDesmontable());
                        }
                    }
                }else if(this.isMuro()){///MUROS
                   this.model.calcularMuro(
                        Double.valueOf(this.view.txtMetros.getText()),this.isSimple(),this.isDoble(),this.view.chkDesperdicio.isSelected());
                   if(this.isSimple()){
                       this.view.txtPresupuesto.setText(this.model.getMuro().toStringSimple());
                   }else if(this.isDoble()){
                       this.view.txtPresupuesto.setText(this.model.getMuro().toStringDoble());
                   }   
                }else{
                    JOptionPane.showMessageDialog(null, "SELECCIONE AL MENOS UNA OPCIÓN: 'CIELORRASO' O 'MURO'","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "FORMATO INCORRECTO. INGRESE SÓLO NUMEROS POR FAVOR","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }
            /////////////////////////////////////   
        }else if(ae.getSource().equals(this.view.btnToPDF)){///BTN export to PDF
            if(this.view.txtPresupuesto.getText().equals("")){
                JOptionPane.showMessageDialog(null, "SIN PRESUPUESTO. REALICE UN PRESUPUESTO PARA EXPORTAR A PDF","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }else{
                this.exportarPDF();
            }
        }else if(ae.getSource().equals(this.view.btnPresupuesto)){///BTN PRESUPUESTO
            if((!this.isMuro()&& !this.isCieloraso())){
                JOptionPane.showMessageDialog(null,"PARA PRESUPUESTAR MATERIALES, PRIMERO SELECCIONE EL TIPO DE CONSTRUCCIÓN","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }else{
                this.view.txtCantidad.setText("");
                this.view.txtCantidad.setVisible(true);
                this.view.lblUnidad.setVisible(true);
                this.view.lblUnidad.setText("      $");
                JOptionPane.showMessageDialog(null, "'ENTER' PARA INGRESAR LOS VALORES DE LOS MATERIALES","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                this.view.lblUnidad.setText("PLACA $");
                this.valores.clear();
                this.view.txtCantidad.requestFocus();
            }   
        }else if(ae.getSource().equals(this.view.btnConfiguracion)){
            
            try{
                String s = JOptionPane.showInputDialog(null, "INGRESÁ EL NOMBRE DE TU ORGANIZACIÓN O EMPRENDIMIENTO", "'nSECO PROJECT", JOptionPane.INFORMATION_MESSAGE);
                this.modificarPropiedades(s);
                
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null,"ERROR: " + ex.getMessage() + " NO SE HA GUARDADO EL NOMBRE","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
            }
            
        }      
        
    }

    @Override
    public void keyTyped(KeyEvent ke) {
         char validarIngreso = ke.getKeyChar();
        if(ke.getSource().equals(this.view.txtCantidad)){//EN txtUnidad
                //Verificar caracter ingresado
                if(Character.isLetter(validarIngreso)){//SOLO NÚMEROS
                    JOptionPane.showMessageDialog(null, "INGRESE SÓLO NÚMEROS","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                    ke.consume();
                }        
        }else if(ke.getSource().equals(this.view.txtMetros)){
            //Verificar caracter ingresado
            if(Character.isLetter(validarIngreso)){//SOLO NÚMEROS
                JOptionPane.showMessageDialog(null, "INGRESE SÓLO NÚMEROS","'nSECO PROJECT",JOptionPane.PLAIN_MESSAGE,this.icono());
                ke.consume();
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent ke) {
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        if(ke.getKeyChar()=='\n'){//Al sotar enter 
            if(ke.getSource().equals(this.view.txtCantidad)){//txtCantidad
                if(!this.view.txtCantidad.getText().equals("")){
                    this.obtenerPrecios(this.view.txtCantidad.isVisible(), this.iteradorMaterial);
                }
            }
        }
    }
    //EVENTOS MOUSE
    @Override
    public void mouseDragged(MouseEvent me) {
    }
    @Override
    public void mouseMoved(MouseEvent me) {
        if(me.getSource().equals(this.view.btnPresupuesto)){
            
        }
    }
}
