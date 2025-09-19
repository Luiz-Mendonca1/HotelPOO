/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private static int nextId = 1;
    
    private final int id;
    private final Cliente cliente;
    private final Quarto quarto;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final double valorTotal;
    private boolean ativa;
    
    public Reserva(Cliente cliente, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        this.id = nextId++;
        this.cliente = cliente;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.ativa = true;
        
        int dias = (int) ChronoUnit.DAYS.between(checkIn, checkOut);
        this.valorTotal = quarto.calcularPreco(dias);
        quarto.setDisponivel(false);
    }
    
    // Getters
    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Quarto getQuarto() { return quarto; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public double getValorTotal() { return valorTotal; }
    public boolean isAtiva() { return ativa; }
    
    public void cancelar() {
        this.ativa = false;
        quarto.setDisponivel(true);
    }
    
    @Override
    public String toString() {
        return "Reserva #" + id + " - " + cliente.getNome() + " - " + quarto.getNumero() + 
               " - " + checkIn + " a " + checkOut + " - R$" + valorTotal;
    }
}