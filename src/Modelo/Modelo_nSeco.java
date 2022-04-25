/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.lang.Math;


/**
 *
 * @author Marcos
 */
public class Modelo_nSeco {
    private CieloRraso cieloRraso;
    private Muro muro;
    private int presupuestoN;
    private Date fechaPresupuesto;
    private HashMap<String,Muro>muros;
    private HashMap<String,CieloRraso>cieloRrasos;
    

    public Modelo_nSeco() {
    
    }
    //Getters y Setters
    public CieloRraso getCieloRraso() {
        return cieloRraso;
    }
    public void setCieloRraso(CieloRraso cieloRraso) {
        this.cieloRraso = cieloRraso;
    }
    public Muro getMuro() {
        return muro;
    }
    public void setMuro(Muro muro) {
        this.muro = muro;
    }
    public int getPresupuestoN() {
        return presupuestoN;
    }
    public void setPresupuestoN(int presupuestoN) {
        this.presupuestoN = presupuestoN;
    }
    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }
    public void setFechaPresupuesto(Date fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }
    public HashMap<String, Muro> getMuros() {
        return muros;
    }
    public void setMuros(HashMap<String, Muro> muros) {
        this.muros = muros;
    }
    public HashMap<String, CieloRraso> getCieloRrasos() {
        return cieloRrasos;
    }
    public void setCieloRrasos(HashMap<String, CieloRraso> cieloRrasos) {
        this.cieloRrasos = cieloRrasos;
    }
    
    //Agregar a contenedor de muros
    public void agregarMuro(String key, Muro _muro){
         this.getMuros().put(key, _muro);
    }
    //Agregar a contenedor de cielorrasos
    public void agregarCielorraso(String key, CieloRraso _cieloRraso){
        this.getCieloRrasos().put(key, _cieloRraso);
    }
    //Obtener un muro del contenedor
    public Muro obtenerMuro(String _key){
        return this.getMuros().get(_key);
    }
    //Obtener un cielorraso del contenedor
    public CieloRraso obtenerCielorraso(String _key){
        return this.getCieloRrasos().get(_key);
    }
    
    //Cálculo de Cielorraso desmontable
    public void calcularCielorrasoDesmonatble(boolean _placaCuadrada, double _superficie, boolean _desperdicio){
        final double LARGUEROS06 = 1.6; // mlineal<-
        final double LARGUEROS120 = 0.8; // mlineal<-
        final double PLACA0606 = 0.36; // m2
        final double PLACA06120 = 0.72; // m2
        final int FIJACIONES = 6; // un
        final double PERIMETRALES = 1.5; // mlineal<-
        final double AGALVANIZADO = 1.30; // mlineal<-
        final double TRAVESANIOS = 1.60; // mlineal<-
        final double DESPERDICIO = 1.05; // 5%
        
        //Instancia de cielorraso
        CieloRraso cieloras = new CieloRraso();
        
        this.setCieloRraso(cieloras);
        
        //Seteo de la superficie del cielorraso
        this.cieloRraso.setSuperficie(_superficie);

        if(_placaCuadrada){ ////Para placas de 0.6x0.6

            //Determinar cantidad de placas
            this.cieloRraso.setCantPlacas((int)(Math.ceil(((this.cieloRraso.getSuperficie()*1.05)/PLACA0606))));
            //Determinar cantidad de largueros
            this.cieloRraso.setLargueros((int)(Math.ceil((this.cieloRraso.getSuperficie()*LARGUEROS06)/3.66)));
            //Determinar travesaños
            this.cieloRraso.setTravesanios((int)(Math.ceil((this.cieloRraso.getSuperficie()*TRAVESANIOS)/0.61)));

        }else{ ////Para placas de 1.20 x 0.6

            //Determinar cantidad de placas
            this.cieloRraso.setCantPlacas((int)(Math.ceil((this.cieloRraso.getSuperficie()*1.05)/PLACA06120)));
            //Determinar cantidad de largueros
            this.cieloRraso.setLargueros((int)(Math.ceil((this.cieloRraso.getSuperficie()*LARGUEROS120)/3.66)));
            //Determinar Travesaños
            this.cieloRraso.setTravesanios((int)(Math.ceil((this.cieloRraso.getSuperficie()*TRAVESANIOS)/1.22)));
        }
        //Determinar cantidad de fijaciones
        this.cieloRraso.setCantFijaciones((int)(Math.ceil(this.cieloRraso.getSuperficie()*FIJACIONES)));
        //Determinar cantidad de perimetrales
        this.cieloRraso.setPerimetrales((int)(Math.ceil((this.cieloRraso.getSuperficie()*PERIMETRALES)/3.05)));
        //Determinar cantidad de alambre
        this.cieloRraso.setAlambreN14((int)(Math.ceil(this.cieloRraso.getSuperficie()*AGALVANIZADO)));
        //Cálculo con desperdicio
        if(_desperdicio){
            this.cieloRraso.setCantPlacas((int)(Math.ceil(this.cieloRraso.getCantPlacas()*DESPERDICIO)));
            this.cieloRraso.setLargueros((int)(Math.ceil(this.cieloRraso.getLargueros()*DESPERDICIO)));
            this.cieloRraso.setTravesanios((int)(Math.ceil(this.cieloRraso.getTravesanios()*DESPERDICIO)));
            this.cieloRraso.setCantFijaciones((int)(Math.ceil(this.cieloRraso.getCantFijaciones()*DESPERDICIO)));
            this.cieloRraso.setPerimetrales((int)(Math.ceil(this.cieloRraso.getPerimetrales()*DESPERDICIO)));
            this.cieloRraso.setAlambreN14((int)(Math.ceil(this.cieloRraso.getAlambreN14()*DESPERDICIO)));
        }
    }
    
    //Cálculo de Cielorraso Junta Tomada
    public void calcularCielorrasoJuntaTomada(double _superficie, boolean _desperdicio){
        final double PLACA240X120 = 2.88; // m2
        final int FIJACIONES = 6; // un
        final double SOLERA35 = 1.10; //mlineal
        final double MONTANTE34 = 3.20; //mlineal
        final int TORNILLOST1 = 16; // un
        final int TORNILLOST2 = 18; // un
        final double CINTAPAPEL = 1.65; //mlineal
        final double MASILLA = 0.90; // kg
        final double ENDUIDO = 1.0; // litros
        final double DESPERDICIO = 1.05; // 5%
        
        //Instancia de cielorraso
        CieloRraso cieloras = new CieloRraso();
        
        this.setCieloRraso(cieloras);
        
        //Seteo de la superficie del cielorraso
        this.cieloRraso.setSuperficie(_superficie);
        
        //Determinar cantidad de placas
        this.cieloRraso.setCantPlacas((int)(Math.ceil(this.cieloRraso.getSuperficie()/PLACA240X120)));
        this.cieloRraso.setCantFijaciones((int)(Math.ceil(this.cieloRraso.getSuperficie()*FIJACIONES)));
        this.cieloRraso.setCantSolera35mm((int)(Math.ceil((this.cieloRraso.getSuperficie()*SOLERA35)/2.6)));
        this.cieloRraso.setCantMontante34mm((int)(Math.ceil((this.cieloRraso.getSuperficie()*MONTANTE34)/2.6)));
        this.cieloRraso.setCantTornillosT1((int)(Math.ceil(this.cieloRraso.getSuperficie()*TORNILLOST1)));
        this.cieloRraso.setCantTornillosT2((int)(Math.ceil(this.cieloRraso.getSuperficie()*TORNILLOST2)));
        this.cieloRraso.setCantCintaPapel((int)(Math.ceil(this.cieloRraso.getSuperficie()*CINTAPAPEL)));
        this.cieloRraso.setCantMasilla((int)(Math.ceil(this.cieloRraso.getSuperficie()*MASILLA)));
        this.cieloRraso.setCantEnduido((int)(Math.ceil(this.cieloRraso.getSuperficie()*ENDUIDO)));
        //Cálculo con desperdicio
        if(_desperdicio){
            this.cieloRraso.setCantPlacas((int)(Math.ceil(this.cieloRraso.getCantPlacas()*DESPERDICIO)));
            this.cieloRraso.setCantFijaciones((int)(Math.ceil(this.cieloRraso.getCantFijaciones()*DESPERDICIO)));
            this.cieloRraso.setCantSolera35mm((int)(Math.ceil(this.cieloRraso.getCantSolera35mm()*DESPERDICIO)));
            this.cieloRraso.setCantMontante34mm((int)(Math.ceil(this.cieloRraso.getCantMontante34mm()*DESPERDICIO)));
            this.cieloRraso.setCantTornillosT1((int)(Math.ceil(this.cieloRraso.getCantTornillosT1()*DESPERDICIO)));
            this.cieloRraso.setCantTornillosT2((int)(Math.ceil(this.cieloRraso.getCantTornillosT2()*DESPERDICIO)));
            this.cieloRraso.setCantCintaPapel((int)(Math.ceil(this.cieloRraso.getCantCintaPapel()*DESPERDICIO)));
            this.cieloRraso.setCantMasilla((int)(Math.ceil(this.cieloRraso.getCantMasilla()*DESPERDICIO)));
            this.cieloRraso.setCantEnduido((int)(Math.ceil(this.cieloRraso.getCantEnduido()*DESPERDICIO)));
        }
    }
    
    //Cálculo de muro
    public void calcularMuro(double _superficie, boolean _simple, boolean _doble, boolean _desperdicio){
        final double PLACA240X120 = 2.05; // m2
        final double SOLERA70 = 1.0; // mlineal
        final double MONTANTE69 = 3.0;// mlineal
        final int TORNILLOST1 = 10;// un
        final int TORNILLOST2T3 = 30;// un
        final double CINTAPAPEL = 3.30;// mlineal
        final double MASILLA = 1.80; // kg
        final double ENDUIDO = 2.0; //litros
        final double FIJACIONES = 3.50; // un
        final double AISLANTE = 1.05; // m2
        final double DESPERDICIO =  1.05; // 5%
        
        //Instancia de muro
        Muro muro1 = new Muro();
        this.setMuro(muro1);
        
        //Seteo de la superficie del muro
        this.muro.setSuperficie(_superficie);
        
        if(_simple){ //MURO SIMPLE
            this.muro.setCantPlacas((int)(Math.ceil((this.muro.getSuperficie()*PLACA240X120)/2.88)));
            this.muro.setCantSolera70mm((int)(Math.ceil((this.muro.getSuperficie()*SOLERA70)/2.60)));
            this.muro.setCantMontante69mm((int)(Math.ceil((this.muro.getSuperficie()*MONTANTE69)/2.60)));
            this.muro.setCantTornillosT1((int)(Math.ceil(this.muro.getSuperficie()*TORNILLOST1)));
            this.muro.setCantTornillosT2((int)(Math.ceil(this.muro.getSuperficie()*TORNILLOST2T3)));
            this.muro.setCantCintaPapel((int)(Math.ceil(this.muro.getSuperficie()*CINTAPAPEL)));
            this.muro.setCantMasilla((int)(Math.ceil(this.muro.getSuperficie()*MASILLA)));
            this.muro.setCantEnduido((int)(Math.ceil(this.muro.getSuperficie()*ENDUIDO)));
            this.muro.setCantFijaciones((int)(Math.ceil(this.muro.getSuperficie()*FIJACIONES)));
            this.muro.setCantMatAislante((int)(Math.ceil(this.muro.getSuperficie()*AISLANTE)));
            //Cálculo de desperdicios
            if(_desperdicio){
                this.muro.setCantPlacas((int)(Math.ceil(this.muro.getCantPlacas()*DESPERDICIO)));
                this.muro.setCantSolera70mm((int)(Math.ceil(this.muro.getCantSolera70mm()*DESPERDICIO)));
                this.muro.setCantMontante69mm((int)(Math.ceil(this.muro.getCantMontante69mm()*DESPERDICIO)));
                this.muro.setCantTornillosT1((int)(Math.ceil(this.muro.getCantTornillosT1()*DESPERDICIO)));
                this.muro.setCantTornillosT2((int)(Math.ceil(this.muro.getCantTornillosT2()*DESPERDICIO)));
                this.muro.setCantCintaPapel((int)(Math.ceil(this.muro.getCantCintaPapel()*DESPERDICIO)));
                this.muro.setCantMasilla((int)(Math.ceil(this.muro.getCantMasilla()*DESPERDICIO)));
                this.muro.setCantEnduido((int)(Math.ceil(this.muro.getCantEnduido()*DESPERDICIO)));
                this.muro.setCantFijaciones((int)(Math.ceil(this.muro.getCantFijaciones()*DESPERDICIO)));
                this.muro.setCantMatAislante((int)(Math.ceil(this.muro.getCantMatAislante()*DESPERDICIO)));
            }
        }else if(_doble){ //MURO DOBLE
            this.muro.setCantPlacas((int)(Math.ceil((this.muro.getSuperficie()*(PLACA240X120*2))/2.88)));
            this.muro.setCantSolera70mm((int)(Math.ceil((this.muro.getSuperficie()*SOLERA70)/2.60)));
            this.muro.setCantMontante69mm((int)(Math.ceil((this.muro.getSuperficie()*MONTANTE69)/2.60)));
            this.muro.setCantTornillosT1((int)(Math.ceil(this.muro.getSuperficie()*TORNILLOST1)));
            this.muro.setCantTornillosT2((int)(Math.ceil(this.muro.getSuperficie()*(TORNILLOST2T3/2))));
            this.muro.setCantTornillosT3((int)(Math.ceil(this.muro.getSuperficie()*TORNILLOST2T3)));
            this.muro.setCantCintaPapel((int)(Math.ceil(this.muro.getSuperficie()*CINTAPAPEL)));
            this.muro.setCantMasilla((int)(Math.ceil(this.muro.getSuperficie()*MASILLA)));
            this.muro.setCantEnduido((int)(Math.ceil(this.muro.getSuperficie()*ENDUIDO)));
            this.muro.setCantFijaciones((int)(Math.ceil(this.muro.getSuperficie()*FIJACIONES)));
            this.muro.setCantMatAislante((int)(Math.ceil(this.muro.getSuperficie()*AISLANTE)));
            //Cálculo de desperdicios
            if(_desperdicio){
                this.muro.setCantPlacas((int)(Math.ceil(this.muro.getCantPlacas()*DESPERDICIO)));
                this.muro.setCantSolera70mm((int)(Math.ceil(this.muro.getCantSolera70mm()*DESPERDICIO)));
                this.muro.setCantMontante69mm((int)(Math.ceil(this.muro.getCantMontante69mm()*DESPERDICIO)));
                this.muro.setCantTornillosT1((int)(Math.ceil(this.muro.getCantTornillosT1()*DESPERDICIO)));
                this.muro.setCantTornillosT2((int)(Math.ceil(this.muro.getCantTornillosT2()*DESPERDICIO)));
                this.muro.setCantTornillosT3((int)(Math.ceil(this.muro.getCantTornillosT3()*DESPERDICIO)));
                this.muro.setCantCintaPapel((int)(Math.ceil(this.muro.getCantCintaPapel()*DESPERDICIO)));
                this.muro.setCantMasilla((int)(Math.ceil(this.muro.getCantMasilla()*DESPERDICIO)));
                this.muro.setCantEnduido((int)(Math.ceil(this.muro.getCantEnduido()*DESPERDICIO)));
                this.muro.setCantFijaciones((int)(Math.ceil(this.muro.getCantFijaciones()*DESPERDICIO)));
                this.muro.setCantMatAislante((int)(Math.ceil(this.muro.getCantMatAislante()*DESPERDICIO)));
            }
        }
    }
    
  
    public String toStringPresTomada(ArrayList<String> valores) {
        String txt = "";
        if(valores.size()==9){
         txt = "PRESUPUESTO PARA" + "\n"+ "CIELORRASO JUNTA TOMADA DE SUP: " + this.cieloRraso.getSuperficie() +
                    " m2.\n\nCant. de Placas:  " +  this.cieloRraso.getCantPlacas() +"  P.Unitario   $"+ valores.get(0) +
                    "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValPlaca() +   
                        "\n\nCant. de Soleras 35mm:  " + this.cieloRraso.getCantSolera35mm() +"  P.Unitario   $"+ valores.get(1) +
                        "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValSolera35() +
                            "\n\nCant. de Montantes 34mm:  " + this.cieloRraso.getCantMontante34mm() +"  P.Unitario   $"+valores.get(2) +
                            "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValMontante34() +
                                "\n\nCant. de Tornillos T1:  " + this.cieloRraso.getCantTornillosT1()+"  P.Unitario   $"+ valores.get(3) + 
                                "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValTornillosT1() +
                                    "\n\nCant. de Tornillos T2:  " + this.cieloRraso.getCantTornillosT2()+"  P.Unitario   $"+ valores.get(4) +
                                     "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValTornillosT2() +
                                        "\n\nCant. de Masilla:  "+ this.cieloRraso.getCantMasilla() +"  P.Unitario   $"+ valores.get(5) +
                                        "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValMasilla() +
                                            "\n\nCant. de Cinta de Papel:   "+  this.cieloRraso.getCantCintaPapel()+"  P.Unitario   $"+ valores.get(6) +
                                            "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValCintaPapel() +
                                                "\n\nCant. de Enduido:  "+ this.cieloRraso.getCantEnduido()+"  P.Unitario   $"+ valores.get(7) +
                                                "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValEnduido() + 
                                                    "\n\nCant. de Fijaciones:  "+ this.cieloRraso.getCantFijaciones()+"  P.Unitario   $"+ valores.get(8) +
                                                    "  SubTotal: $"+this.cieloRraso.getPresupuesto().getValFijaciones();
        }
       return txt; 
    }   
    public String toStringPresDesmontable(ArrayList<String> valores) {
        String txt = "";
        if(valores.size()==6){
         txt = "PRESUPUESTO PARA" + "\n"+ "CIELORRASO DESMONTABLE DE SUP: " + this.cieloRraso.getSuperficie() +
                    " m2.\n\nCant. de Placas:  " +  this.cieloRraso.getCantPlacas() +"  P.Unitario   $"+ valores.get(0) +
                    "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValPlaca() +   
                        "\n\nCant. de Fijaciones:  " + this.cieloRraso.getCantFijaciones() +"  P.Unitario   $"+ valores.get(1) +
                        "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValFijaciones() +
                            "\n\nCant. de Perimetrales:  " + this.cieloRraso.getPerimetrales() +"  P.Unitario   $"+valores.get(2) +
                            "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValPerimetrales() +
                                "\n\nCant. de Largueros:  " + this.cieloRraso.getLargueros()+"  P.Unitario   $"+ valores.get(3) + 
                                "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValLargueros() +
                                    "\n\nCant. de Travesaños:  " + this.cieloRraso.getTravesanios()+"  P.Unitario   $"+ valores.get(4) +
                                     "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValTravesanios() +
                                        "\n\nCant. de Alambre n14:  "+ this.cieloRraso.getAlambreN14() +"  P.Unitario   $"+ valores.get(5) +
                                        "  SubTotal: $"+ this.cieloRraso.getPresupuesto().getValAlambre();
        }
       return txt; 
    }
    public String toStringPresSimple(ArrayList<String> valores) {
        String txt = "";
        if(valores.size()==10){
         txt = "PRESUPUESTO PARA" + "\n"+ "MURO SIMPLE DE SUP: " + this.muro.getSuperficie() +
                    " m2.\n\nCant. de Placas:  " +  this.muro.getCantPlacas() +"  P.Unitario   $"+ valores.get(0) +
                    "  SubTotal: $"+ this.muro.getPresupuesto().getValPlaca() +   
                        "\n\nCant. de Soleras 70mm  :" + this.muro.getCantSolera70mm() +"  P.Unitario   $"+ valores.get(1) +
                        "  SubTotal: $"+ this.muro.getPresupuesto().getValSolera70() +
                            "\n\nCant. de Montantes 69mm:  " + this.muro.getCantMontante69mm() +"  P.Unitario   $"+valores.get(2) +
                            "  SubTotal: $"+ this.muro.getPresupuesto().getValMontante69() +
                                "\n\nCant. de Torn. T1:  " + this.muro.getCantTornillosT1()+"  P.Unitario   $"+ valores.get(3) + 
                                "  SubTotal: $"+ this.muro.getPresupuesto().getValTornillosT1() +
                                    "\n\nCant. de Torn. T2:  " + this.muro.getCantTornillosT2()+"  P.Unitario   $"+ valores.get(4) +
                                     "  SubTotal: $"+ this.muro.getPresupuesto().getValTornillosT2() +
                                        "\n\nCant. de Masilla:  "+ this.muro.getCantMasilla()+"  P.Unitario   $"+ valores.get(5) +
                                        "  SubTotal: $"+ this.muro.getPresupuesto().getValMasilla()+
                                            "\n\nCant. Cinta Papel:  "+ this.muro.getCantCintaPapel()+"  P.Unitario   $"+ valores.get(6) +
                                            "  SubTotal: $"+ this.muro.getPresupuesto().getValCintaPapel()+
                                                "\n\nCant. Enduido:  "+ this.muro.getCantEnduido()+"  P.Unitario   $"+ valores.get(7) +
                                                "  SubTotal: $"+ this.muro.getPresupuesto().getValEnduido()+
                                                    "\n\nCant. Fijaciones:  "+ this.muro.getCantFijaciones()+"  P.Unitario   $"+ valores.get(8) +
                                                    "  SubTotal: $"+ this.muro.getPresupuesto().getValFijaciones()+
                                                        "\n\nCant. Aislante:  "+ this.muro.getCantMatAislante()+"  P.Unitario   $"+ valores.get(9) +
                                                        "  SubTotal: $"+ this.muro.getPresupuesto().getValAislante();
        }                                    
       return txt; 
    }
    public String toStringPresDoble(ArrayList<String> valores) {
        String txt = "";
        if(valores.size()==11){
         txt = "PRESUPUESTO PARA" + "\n"+ "MURO SIMPLE DE SUP: " + this.muro.getSuperficie() +
                    " m2.\n\nCant. de Placas:  " +  this.muro.getCantPlacas() +"  P.Unitario   $"+ valores.get(0) +
                    "  SubTotal: $"+ this.muro.getPresupuesto().getValPlaca() +   
                        "\n\nCant. de Soleras 70mm  :" + this.muro.getCantSolera70mm() +"  P.Unitario   $"+ valores.get(1) +
                        "  SubTotal: $"+ this.muro.getPresupuesto().getValSolera70() +
                            "\n\nCant. de Montantes 69mm:  " + this.muro.getCantMontante69mm() +"  P.Unitario   $"+valores.get(2) +
                            "  SubTotal: $"+ this.muro.getPresupuesto().getValMontante69() +
                                "\n\nCant. de Torn. T1:  " + this.muro.getCantTornillosT1()+"  P.Unitario   $"+ valores.get(3) + 
                                "  SubTotal: $"+ this.muro.getPresupuesto().getValTornillosT1() +
                                    "\n\nCant. de Torn. T2:  " + this.muro.getCantTornillosT2()+"  P.Unitario   $"+ valores.get(4) +
                                     "  SubTotal: $"+ this.muro.getPresupuesto().getValTornillosT2() +
                                        "\n\nCant. de Torn. T2:  " + this.muro.getCantTornillosT3()+"  P.Unitario   $"+ valores.get(5) +
                                        "  SubTotal: $"+ this.muro.getPresupuesto().getValTornillosT3()+  
                                            "\n\nCant. de Masilla:  "+ this.muro.getCantMasilla()+"  P.Unitario   $"+ valores.get(6) +
                                            "  SubTotal: $"+ this.muro.getPresupuesto().getValMasilla()+
                                                "\n\nCant. Cinta Papel:  "+ this.muro.getCantCintaPapel()+"  P.Unitario   $"+ valores.get(7) +
                                                "  SubTotal: $"+ this.muro.getPresupuesto().getValCintaPapel()+
                                                    "\n\nCant. Enduido:  "+ this.muro.getCantEnduido()+"  P.Unitario   $"+ valores.get(8) +
                                                    "  SubTotal: $"+ this.muro.getPresupuesto().getValEnduido()+
                                                        "\n\nCant. Fijaciones:  "+ this.muro.getCantFijaciones()+"  P.Unitario   $"+ valores.get(9) +
                                                        "  SubTotal: $"+ this.muro.getPresupuesto().getValFijaciones()+
                                                            "\n\nCant. Aislante:  "+ this.muro.getCantMatAislante()+"  P.Unitario   $"+ valores.get(10) +
                                                            "  SubTotal: $"+ this.muro.getPresupuesto().getValAislante();
        }                                    
       return txt; 
    }
}      
