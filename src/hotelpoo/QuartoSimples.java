/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;

/**
 *
 * @author luize
 */
public class QuartoSimples extends Quarto {
    public QuartoSimples(String numero, double precoBase) {
        super(numero, precoBase);
    }
    
    @Override
    public double calcularPreco(int dias) {
        return precoBasePorNoite * dias;
    }
    
    @Override
    public String toString() {
        return "Quarto Simples " + numero + " - R$" + precoBasePorNoite + "/noite";
    }
}
