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
public class Presupuesto {
    //BLOQUE COMÚN
    private double valPlaca; ///Determinar el tipo segun condiciones de cálculo ingresadas
    private double valFijaciones;
    private double valCintaPapel;
    private double valMasilla;
    private double valEnduido;
    
    //BLOQUE CIELORRASO
    private double valSolera35;
    private double valMontante34;
    private double valPerimetrales;
    private double valLargueros;
    private double valTravesanios;
    private double valAlambre;
    
    //BLOQUE MURO
    private double valSolera70;
    private double valMontante69;
    private double valTornillosT1;
    private double valTornillosT2;
    private double valTornillosT3;
    private double valAislante;
    
    private double totalP;

    public Presupuesto() {
    }

    public double getTotalP() {
        return totalP;
    }
    public void setTotalP(double totalP) {
        this.totalP = totalP;
    }
    public double getValPlaca() {
        return valPlaca;
    }
    public void setValPlaca(double valPlaca) {
        this.valPlaca = valPlaca;
    }
    public double getValFijaciones() {
        return valFijaciones;
    }
    public void setValFijaciones(double valFijaciones) {
        this.valFijaciones = valFijaciones;
    }
    public double getValCintaPapel() {
        return valCintaPapel;
    }
    public void setValCintaPapel(double valCintaPapel) {
        this.valCintaPapel = valCintaPapel;
    }
    public double getValMasilla() {
        return valMasilla;
    }
    public void setValMasilla(double valMasilla) {
        this.valMasilla = valMasilla;
    }
    public double getValEnduido() {
        return valEnduido;
    }
    public void setValEnduido(double valEnduido) {
        this.valEnduido = valEnduido;
    }
    public double getValSolera35() {
        return valSolera35;
    }
    public void setValSolera35(double valSolera35) {
        this.valSolera35 = valSolera35;
    }
    public double getValMontante34() {
        return valMontante34;
    }
    public void setValMontante34(double valMontante34) {
        this.valMontante34 = valMontante34;
    }
    public double getValPerimetrales() {
        return valPerimetrales;
    }
    public void setValPerimetrales(double valPerimetrales) {
        this.valPerimetrales = valPerimetrales;
    }
    public double getValLargueros() {
        return valLargueros;
    }
    public void setValLargueros(double valLargueros) {
        this.valLargueros = valLargueros;
    }
    public double getValTravesanios() {
        return valTravesanios;
    }
    public void setValTravesanios(double valTravesanios) {
        this.valTravesanios = valTravesanios;
    }
    public double getValAlambre() {
        return valAlambre;
    }
    public void setValAlambre(double valAlambre) {
        this.valAlambre = valAlambre;
    }
    public double getValSolera70() {
        return valSolera70;
    }
    public void setValSolera70(double valSolera70) {
        this.valSolera70 = valSolera70;
    }
    public double getValMontante69() {
        return valMontante69;
    }
    public void setValMontante69(double valMontante69) {
        this.valMontante69 = valMontante69;
    }
    public double getValTornillosT1() {
        return valTornillosT1;
    }
    public void setValTornillosT1(double valTornillosT1) {
        this.valTornillosT1 = valTornillosT1;
    }
    public double getValTornillosT2() {
        return valTornillosT2;
    }
    public void setValTornillosT2(double valTornillosT2) {
        this.valTornillosT2 = valTornillosT2;
    }
    public double getValTornillosT3() {
        return valTornillosT3;
    }
    public void setValTornillosT3(double valTornillosT3) {
        this.valTornillosT3 = valTornillosT3;
    }
    public double getValAislante() {
        return valAislante;
    }
    public void setValAislante(double valAislante) {
        this.valAislante = valAislante;
    }
    
    public double sumaJuntaTomada(){
        return this.valPlaca + this.valSolera35 + this.valMontante34 + this.valTornillosT1 +
                    this.valTornillosT2 + this.valMasilla + this.valCintaPapel + 
                        this.valEnduido + this.valFijaciones;
    }
    public double sumaDesmontable(){
        return this.valPlaca + this.valFijaciones + this.valPerimetrales + this.valLargueros +
                    this.valTravesanios + this.valAlambre;
    }
    public double sumaSimple(){
        return this.valPlaca + this.valSolera70 + this.valMontante69 + this.valTornillosT1 +
                    this.valTornillosT2 + this.valMasilla + this.valCintaPapel + this.valEnduido +
                        this.valFijaciones + this.valAislante;
    }
    public double sumaDoble(){
        return this.valPlaca + this.valSolera70 + this.valMontante69 + this.valTornillosT1 +
                    this.valTornillosT2 + this.valTornillosT3 + this.valMasilla + this.valCintaPapel + this.valEnduido +
                        this.valFijaciones + this.valAislante;
    }
}
