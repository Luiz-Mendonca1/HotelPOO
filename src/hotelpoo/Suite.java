/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;

/**
 *
 * @author luize
 */
public class Suite extends Quarto {
    private static final double TAXA_SUITE = 2.0;
    private static final double TAXA_SERVICO = 50.0;
    
    public Suite(String numero, double precoBase) {
        super(numero, precoBase);
    }
    
    @Override
    public double calcularPreco(int dias) {
        return (precoBasePorNoite * TAXA_SUITE * dias) + (TAXA_SERVICO * dias);
    }
    
    @Override
    public String toString() {
        return "Suíte " + numero + " - R$" + (precoBasePorNoite * TAXA_SUITE + TAXA_SERVICO) + "/noite";
    }
}
