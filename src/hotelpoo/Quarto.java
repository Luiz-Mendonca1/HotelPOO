/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;

/**
 *
 * @author luize
 */
public abstract class Quarto {
    protected String numero;
    protected double precoBasePorNoite;
    protected boolean disponivel;
    
    public Quarto(String numero, double precoBasePorNoite) {
        this.numero = numero;
        this.precoBasePorNoite = precoBasePorNoite;
        this.disponivel = true;
    }
    
    public abstract double calcularPreco(int dias);
    
    // Getters e Setters
    public String getNumero() { return numero; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public double getPrecoBase() { return precoBasePorNoite; }
}
