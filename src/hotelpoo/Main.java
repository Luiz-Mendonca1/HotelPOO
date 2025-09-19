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

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        
        // Cadastrando clientes
        Cliente cliente1 = new Cliente("João Silva", "123.456.789-00", "11-99999-9999", "joao@email.com");
        Cliente cliente2 = new Cliente("Maria Santos", "987.654.321-00", "11-98888-8888", "maria@email.com");
        
        hotel.adicionarCliente(cliente1);
        hotel.adicionarCliente(cliente2);
        
        // Consultando quartos disponíveis
        System.out.println("Quartos disponíveis:");
        for (Quarto quarto : hotel.getQuartosDisponiveis()) {
            System.out.println(quarto);
        }
        
        // Fazendo reservas
        Quarto quarto1 = hotel.getQuartos().get(0); // Quarto Simples 101
        Quarto quarto2 = hotel.getQuartos().get(4); // Suite 301
        
        LocalDate checkIn = LocalDate.now().plusDays(1);
        LocalDate checkOut = checkIn.plusDays(3);
        
        Reserva reserva1 = hotel.fazerReserva(cliente1, quarto1, checkIn, checkOut);
        Reserva reserva2 = hotel.fazerReserva(cliente2, quarto2, checkIn, checkOut.plusDays(2));
        
        System.out.println("\nReservas realizadas:");
        System.out.println("Reserva 1: " + reserva1.getValorTotal());
        System.out.println("Reserva 2: " + reserva2.getValorTotal());
        
        // Gerando relatório
        hotel.gerarRelatorio();
        
        // Cancelando uma reserva
        hotel.cancelarReserva(reserva1.getId());
        System.out.println("\nApós cancelamento:");
        hotel.gerarRelatorio();
    }
}