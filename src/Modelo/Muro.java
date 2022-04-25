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
public class Muro {
    

    private String nombreMuro;
    private double superficie;
    private int cantPlacas;
    private int cantSelladorIni;
    private int cantSolera70mm;
    private int cantMontante69mm;
    private int cantTornillosT1;
    private int cantTornillosT2;
    //----------------Doble
    private int cantTornillosT3;
    //---------------------
    private int cantMasilla;
    private int cantCintaPapel;
    private int cantEnduido;
    private int cantFijaciones;
    private double cantMatAislante;
    private Presupuesto presupuesto;

    public Muro() {
        Presupuesto prep = new Presupuesto();
        this.presupuesto = prep;
    }
   
    //Getters y Setters
    public String getNombreMuro() {
        return nombreMuro;
    }
    public void setNombreMuro(String nombreMuro) {
        this.nombreMuro = nombreMuro;
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
    public int getCantSelladorIni() {
        return cantSelladorIni;
    }
    public void setCantSelladorIni(int cantSelladorIni) {
        this.cantSelladorIni = cantSelladorIni;
    }
    public int getCantSolera70mm() {
        return cantSolera70mm;
    }
    public void setCantSolera70mm(int cantSolera70mm) {
        this.cantSolera70mm = cantSolera70mm;
    }
    public int getCantMontante69mm() {
        return cantMontante69mm;
    }
    public void setCantMontante69mm(int cantMontante69mm) {
        this.cantMontante69mm = cantMontante69mm;
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
    public int getCantTornillosT3() {
        return cantTornillosT3;
    }
    public void setCantTornillosT3(int cantTornillosT3) {
        this.cantTornillosT3 = cantTornillosT3;
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
    public double getCantMatAislante() {
        return cantMatAislante;
    }
    public void setCantMatAislante(double cantMatAislante) {
        this.cantMatAislante = cantMatAislante;
    }
    public Presupuesto getPresupuesto() {
        return presupuesto;
    }
    public void setPresupuesto(Presupuesto presupuesto) {
        this.presupuesto = presupuesto;
    }
    
    
    //ToString simple
    public String toStringSimple() {
        return "LISTADO DE MATERIALES PARA\nMURO SIMPLE DE SUP: " + superficie + "m2.\nCant. Placas:   " + cantPlacas + 
                    "UN.\nCant. Soleras 70mm:   " + cantSolera70mm + "UN.\nCant. Montantes 69mm:   " + cantMontante69mm + 
                        "UN.\nCant. Tornillos T1:   " + cantTornillosT1 + "UN.\nCant. Tornillos T2:   " + cantTornillosT2 + 
                            "UN.\nCant. Masilla:   " + cantMasilla + "KG.\nCant. Cinta de Papel:   " + cantCintaPapel + 
                                "M.\nCant. de Enduido:   " + cantEnduido + "LT.\nCant. de Fijaciones:   " + cantFijaciones + 
                                    "UN.\nCant. Material Aislante:   " + cantMatAislante + "m2.";
    }
    //ToString simple
    public String toStringDoble() {
        return "LISTADO DE MATERIALES PARA\nMURO DOBLE DE SUP: " + superficie + "m2\nCant. Placas:  " + cantPlacas + 
                    "UN.\nCant. Soleras 70mm:   " + cantSolera70mm + "UN.\nCant. Montantes 69mm:   " + cantMontante69mm + 
                        "UN.\nCant. Tornillos T1:   " + cantTornillosT1 + "UN.\nCant. Tornillos T2:   " + cantTornillosT2 + 
                            "UN.\nCant. Tornillos T3:   " + cantTornillosT3 + "UN.\nCant. Masilla:   " + cantMasilla + 
                                "KG.\nCant. CintaPapel:   " + cantCintaPapel + "M.\nCant. Enduido:   " + cantEnduido + 
                                    "LT.\nCant. Fijaciones:   " + cantFijaciones + "UN.\nCant. MatAislante:   " + cantMatAislante + "m2.";
    }
    
}
