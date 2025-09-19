/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hotelpoo;

/**
 *
 * @author luize
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Quarto> quartos;
    private List<Cliente> clientes;
    private List<Reserva> reservas;
    
    public Hotel() {
        this.quartos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        inicializarQuartos();
    }
    
    private void inicializarQuartos() {
        // Adicionando quartos de exemplo
        quartos.add(new QuartoSimples("101", 100.0));
        quartos.add(new QuartoSimples("102", 100.0));
        quartos.add(new QuartoLuxo("201", 150.0));
        quartos.add(new QuartoLuxo("202", 150.0));
        quartos.add(new Suite("301", 200.0));
        quartos.add(new Suite("302", 200.0));
    }
    
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public List<Quarto> getQuartosDisponiveis() {
        List<Quarto> disponiveis = new ArrayList<>();
        for (Quarto quarto : quartos) {
            if (quarto.isDisponivel()) {
                disponiveis.add(quarto);
            }
        }
        return disponiveis;
    }
    
    public Reserva fazerReserva(Cliente cliente, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        if (!quarto.isDisponivel()) {
            throw new IllegalStateException("Quarto não disponível");
        }
        
        Reserva reserva = new Reserva(cliente, quarto, checkIn, checkOut);
        reservas.add(reserva);
        return reserva;
    }
    
    public boolean cancelarReserva(int idReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == idReserva && reserva.isAtiva()) {
                reserva.cancelar();
                return true;
            }
        }
        return false;
    }
    
    public List<Reserva> getReservasAtivas() {
        List<Reserva> ativas = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.isAtiva()) {
                ativas.add(reserva);
            }
        }
        return ativas;
    }
    
    public void gerarRelatorio() {
        System.out.println("=== RELATÓRIO DO HOTEL ===");
        System.out.println("Total de quartos: " + quartos.size());
        System.out.println("Quartos disponíveis: " + getQuartosDisponiveis().size());
        System.out.println("Reservas ativas: " + getReservasAtivas().size());
        
        System.out.println("\nQuartos disponíveis:");
        for (Quarto quarto : getQuartosDisponiveis()) {
            System.out.println("  - " + quarto);
        }
        
        System.out.println("\nReservas ativas:");
        for (Reserva reserva : getReservasAtivas()) {
            System.out.println("  - " + reserva);
        }
    }
    
    // Getters
    public List<Quarto> getQuartos() { return quartos; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Reserva> getReservas() { return reservas; }
}