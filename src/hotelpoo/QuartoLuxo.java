/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;

/**
 *
 * @author luize
 */
public class QuartoLuxo extends Quarto {
    private static final double TAXA_LUXO = 1.5;
    
    public QuartoLuxo(String numero, double precoBase) {
        super(numero, precoBase);
    }
    
    @Override
    public double calcularPreco(int dias) {
        return precoBasePorNoite * TAXA_LUXO * dias;
    }
    
    @Override
    public String toString() {
        return "Quarto Luxo " + numero + " - R$" + (precoBasePorNoite * TAXA_LUXO) + "/noite";
    }
}
