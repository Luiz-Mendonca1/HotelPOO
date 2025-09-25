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
    
    public boolean removerCliente(String documento) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDocumento().equals(documento)) {
                // Verificar se o cliente tem reservas ativas
                boolean temReservasAtivas = false;
                for (Reserva reserva : reservas) {
                    if (reserva.getCliente().getDocumento().equals(documento) && reserva.isAtiva()) {
                        temReservasAtivas = true;
                        break;
                    }
                }
                
                if (!temReservasAtivas) {
                    clientes.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    public Cliente buscarCliente(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }
    
    public boolean alterarDadosCliente(String documento, String novoNome, String novoDocumento, 
                                     String novoTelefone, String novoEmail) {
        Cliente cliente = buscarCliente(documento);
        if (cliente != null) {
            cliente.alterarDados(novoNome, novoDocumento, novoTelefone, novoEmail);
            return true;
        }
        return false;
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
        // Validações
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        if (quarto == null) {
            throw new IllegalArgumentException("Quarto não pode ser nulo");
        }
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Datas de check-in e check-out são obrigatórias");
        }
        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de check-in não pode ser no passado");
        }
        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            throw new IllegalArgumentException("Data de check-out deve ser posterior ao check-in");
        }
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
    
    public boolean transferirHospede(int idReserva, String novoNumeroQuarto) {
        // Encontrar a reserva ativa
        Reserva reserva = null;
        for (Reserva r : reservas) {
            if (r.getId() == idReserva && r.isAtiva()) {
                reserva = r;
                break;
            }
        }
        
        if (reserva == null) {
            return false;
        }
        
        // Encontrar o novo quarto
        Quarto novoQuarto = null;
        for (Quarto q : quartos) {
            if (q.getNumero().equals(novoNumeroQuarto) && q.isDisponivel()) {
                novoQuarto = q;
                break;
            }
        }
        
        if (novoQuarto == null) {
            return false;
        }
        
        // Liberar quarto atual e ocupar novo quarto
        reserva.getQuarto().setDisponivel(true);
        novoQuarto.setDisponivel(false);
        
        // Atualizar reserva com novo quarto (usando reflexão para acessar campo privado)
        try {
            java.lang.reflect.Field quartoField = Reserva.class.getDeclaredField("quarto");
            quartoField.setAccessible(true);
            quartoField.set(reserva, novoQuarto);
            
            // Recalcular valor total
            java.lang.reflect.Field valorField = Reserva.class.getDeclaredField("valorTotal");
            valorField.setAccessible(true);
            int dias = (int) java.time.temporal.ChronoUnit.DAYS.between(reserva.getCheckIn(), reserva.getCheckOut());
            double novoValor = novoQuarto.calcularPreco(dias);
            valorField.set(reserva, novoValor);
            
            return true;
        } catch (Exception e) {
            // Se falhar, reverter mudanças
            reserva.getQuarto().setDisponivel(false);
            novoQuarto.setDisponivel(true);
            return false;
        }
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
        System.out.println("=== RELATÓRIO COMPLETO DO HOTEL ===");
        System.out.println("Total de quartos: " + quartos.size());
        System.out.println("Quartos disponíveis: " + getQuartosDisponiveis().size());
        System.out.println("Quartos ocupados: " + (quartos.size() - getQuartosDisponiveis().size()));
        System.out.println("Total de clientes: " + clientes.size());
        System.out.println("Reservas ativas: " + getReservasAtivas().size());
        System.out.println("Reservas canceladas: " + (reservas.size() - getReservasAtivas().size()));
        
        // Calcular receita total
        double receitaTotal = 0.0;
        for (Reserva reserva : getReservasAtivas()) {
            receitaTotal += reserva.getValorTotal();
        }
        System.out.println("Receita total (reservas ativas): R$ " + String.format("%.2f", receitaTotal));
        
        System.out.println("\n=== QUARTOS DISPONÍVEIS ===");
        for (Quarto quarto : getQuartosDisponiveis()) {
            System.out.println("  - " + quarto);
        }
        
        System.out.println("\n=== QUARTOS OCUPADOS ===");
        for (Quarto quarto : quartos) {
            if (!quarto.isDisponivel()) {
                System.out.println("  - " + quarto.getNumero() + " (Ocupado)");
            }
        }
        
        System.out.println("\n=== CLIENTES CADASTRADOS ===");
        for (Cliente cliente : clientes) {
            System.out.println("  - " + cliente.getNome() + " - " + cliente.getDocumento() + 
                             " - " + cliente.getTelefone() + " - " + cliente.getEmail());
        }
        
        System.out.println("\n=== RESERVAS ATIVAS ===");
        for (Reserva reserva : getReservasAtivas()) {
            System.out.println("  - " + reserva);
        }
        
        System.out.println("\n=== RESERVAS CANCELADAS ===");
        for (Reserva reserva : reservas) {
            if (!reserva.isAtiva()) {
                System.out.println("  - " + reserva);
            }
        }
    }
    
    public void gerarRelatorioResumido() {
        System.out.println("=== RELATÓRIO RESUMIDO ===");
        System.out.println("Quartos: " + getQuartosDisponiveis().size() + "/" + quartos.size() + " disponíveis");
        System.out.println("Clientes: " + clientes.size() + " cadastrados");
        System.out.println("Reservas: " + getReservasAtivas().size() + " ativas");
    }
    
    // Getters
    public List<Quarto> getQuartos() { return quartos; }
    public List<Cliente> getClientes() { return clientes; }
    public List<Reserva> getReservas() { return reservas; }
}