/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Marcos
 */
public class CieloRraso {
    
    private double superficie;
    private String nombreCielorraso;
    //--------------------Junta tomada
    private int cantPlacas;
    private int cantSolera35mm;
    private int cantMontante34mm;
    private int cantTornillosT1;
    private int cantTornillosT2;
    private int cantMasilla;
    private int cantCintaPapel;
    private int cantEnduido;
    private int cantFijaciones;
    //--------------------Desmontable
    private int perimetrales;
    private int largueros;
    private int travesanios;
    private int alambreN14;
    private Presupuesto presupuesto;

    public CieloRraso() {
        Presupuesto prep = new Presupuesto();
        this.presupuesto = prep;
    }
    
    //Getters y Setters
    public String getNombreCielorraso() {
        return nombreCielorraso;
    }
    public void setNombreCielorraso(String nombreCielorraso) {    
        this.nombreCielorraso = nombreCielorraso;
    }
    public double getSuperficie() {
        return superficie;
    }
    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
    public int getCantPlacas() {
        return cantPlacas;
    }
    public void setCantPlacas(int cantPlacas) {
        this.cantPlacas = cantPlacas;
    }
    public int getCantSolera35mm() {
        return cantSolera35mm;
    }
    public void setCantSolera35mm(int cantSolera35mm) {
        this.cantSolera35mm = cantSolera35mm;
    }
    public int getCantMontante34mm() {
        return cantMontante34mm;
    }
    public void setCantMontante34mm(int cantMontante34mm) {
        this.cantMontante34mm = cantMontante34mm;
    }
    public int getCantTornillosT1() {
        return cantTornillosT1;
    }
    public void setCantTornillosT1(int cantTornillosT1) {
        this.cantTornillosT1 = cantTornillosT1;
    }
    public int getCantTornillosT2() {
        return cantTornillosT2;
    }
    public void setCantTornillosT2(int cantTornillosT2) {
        this.cantTornillosT2 = cantTornillosT2;
    }
    public int getCantMasilla() {
        return cantMasilla;
    }
    public void setCantMasilla(int cantMasilla) {
        this.cantMasilla = cantMasilla;
    }
    public int getCantCintaPapel() {
        return cantCintaPapel;
    }
    public void setCantCintaPapel(int cantCintaPapel) {
        this.cantCintaPapel = cantCintaPapel;
    }
    public int getCantEnduido() {
        return cantEnduido;
    }
    public void setCantEnduido(int cantEnduido) {
        this.cantEnduido = cantEnduido;
    }
    public int getCantFijaciones() {
        return cantFijaciones;
    }
    public void setCantFijaciones(int cantFijaciones) {
        this.cantFijaciones = cantFijaciones;
    }
    public int getPerimetrales() {
        return perimetrales;
    }
    public void setPerimetrales(int perimetrales) {
        this.perimetrales = perimetrales;
    }
    public int getLargueros() {
        return largueros;
    }
    public void setLargueros(int largueros) {
        this.largueros = largueros;
    }
    public int getTravesanios() {
        return travesanios;
    }
    public void setTravesanios(int travesanios) {
        this.travesanios = travesanios;
    }
    public int getAlambreN14() {
        return alambreN14;
    }
    public void setAlambreN14(int alambreN14) {
        this.alambreN14 = alambreN14;
    }
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    
    //To String para cielorraso con junta tomada
    public String toStringTomada() {
        return "LISTADO DE MATERIALES PARA\nCIELORRASO JUNTA TOMADA DE SUP: " + superficie + " m2.\nCant. de Placas:   " + 
                    cantPlacas + "UN.\n\nCant. de Soleras 35mm:   " + cantSolera35mm + "UN.\n\nCant. de Montantes 34mm:   " + 
                        cantMontante34mm + "UN.\n\nCant. de Tornillos T1:   " + cantTornillosT1 + "UN.\n\nCant. de Tornillos T2:   " + 
                            cantTornillosT2 + "UN.\n\nCant. de Masilla:   " + cantMasilla + "KG.\n\nCant. de Cinta de Papel:   " + cantCintaPapel + 
                                "M.\n\nCant. de Enduido:   " + cantEnduido + "LT.\n\nCant. de Fijaciones:   " + cantFijaciones + "UN.";
    }
    //To String para cielorraso desmontable
    public String toStringDesmontable() {
        return "LISTADO DE MATERIALES PARA\nCIELORRASO DESMONTABLE DE SUP: " + superficie + " m2\n\nCant. Placas:   " + 
                    cantPlacas + "UN.\n\nCant. Fijaciones:   " + cantFijaciones + "UN.\n\nCant. Perimetrales:   " + 
                        perimetrales + "UN. \n\nCant. Largueros:   " + largueros + "UN.\n\nCant. Travesaños:   " + travesanios +
                            "UN.\n\nAlambre N14:   " + alambreN14 + "Mts.";
    }
    
}
