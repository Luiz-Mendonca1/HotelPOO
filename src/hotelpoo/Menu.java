/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author luize
 */
public class Menu {
    private Hotel hotel;
    private Scanner scanner;
    private DateTimeFormatter formatter;
    
    public Menu() {
        this.hotel = new Hotel();
        this.scanner = new Scanner(System.in);
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }
    
    public void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerOpcao();
            processarOpcao(opcao);
        } while (opcao != 0);
        
        System.out.println("\nObrigado por usar o Sistema de Hotel! Até logo!");
        scanner.close();
    }
    
    private void exibirMenu() {
        System.out.println("\n==================================================");
        System.out.println("           SISTEMA DE HOTEL POO");
        System.out.println("==================================================");
        System.out.println("1.  Cadastrar Cliente");
        System.out.println("2.  Listar Clientes");
        System.out.println("3.  Alterar Dados do Cliente");
        System.out.println("4.  Remover Cliente");
        System.out.println("5.  Consultar Quartos Disponíveis");
        System.out.println("6.  Fazer Reserva");
        System.out.println("7.  Cancelar Reserva");
        System.out.println("8.  Transferir Hóspede");
        System.out.println("9.  Listar Reservas Ativas");
        System.out.println("10. Gerar Relatório Completo");
        System.out.println("11. Gerar Relatório Resumido");
        System.out.println("0.  Sair");
        System.out.println("==================================================");
        System.out.print("Escolha uma opção: ");
    }
    
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                alterarDadosCliente();
                break;
            case 4:
                removerCliente();
                break;
            case 5:
                consultarQuartosDisponiveis();
                break;
            case 6:
                fazerReserva();
                break;
            case 7:
                cancelarReserva();
                break;
            case 8:
                transferirHospede();
                break;
            case 9:
                listarReservasAtivas();
                break;
            case 10:
                hotel.gerarRelatorio();
                break;
            case 11:
                hotel.gerarRelatorioResumido();
                break;
            case 0:
                break;
            default:
                System.out.println("\n❌ Opção inválida! Tente novamente.");
        }
        
        if (opcao != 0) {
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
    }
    
    private void cadastrarCliente() {
        System.out.println("\n=== CADASTRAR CLIENTE ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF: ");
        String documento = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        try {
            Cliente cliente = new Cliente(nome, documento, telefone, email);
            hotel.adicionarCliente(cliente);
            System.out.println("\n✅ Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
    
    private void listarClientes() {
        System.out.println("\n=== CLIENTES CADASTRADOS ===");
        if (hotel.getClientes().isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (int i = 0; i < hotel.getClientes().size(); i++) {
                Cliente cliente = hotel.getClientes().get(i);
                System.out.println((i + 1) + ". " + cliente.getNome() + 
                                 " - " + cliente.getDocumento() + 
                                 " - " + cliente.getTelefone() + 
                                 " - " + cliente.getEmail());
            }
        }
    }
    
    private void alterarDadosCliente() {
        System.out.println("\n=== ALTERAR DADOS DO CLIENTE ===");
        
        System.out.print("CPF do cliente: ");
        String documento = scanner.nextLine();
        
        Cliente cliente = hotel.buscarCliente(documento);
        if (cliente == null) {
            System.out.println("❌ Cliente não encontrado!");
            return;
        }
        
        System.out.println("Cliente encontrado: " + cliente.getNome());
        System.out.println("Digite os novos dados (deixe em branco para manter o atual):");
        
        System.out.print("Novo nome [" + cliente.getNome() + "]: ");
        String novoNome = scanner.nextLine();
        if (novoNome.trim().isEmpty()) novoNome = cliente.getNome();
        
        System.out.print("Novo CPF [" + cliente.getDocumento() + "]: ");
        String novoDocumento = scanner.nextLine();
        if (novoDocumento.trim().isEmpty()) novoDocumento = cliente.getDocumento();
        
        System.out.print("Novo telefone [" + cliente.getTelefone() + "]: ");
        String novoTelefone = scanner.nextLine();
        if (novoTelefone.trim().isEmpty()) novoTelefone = cliente.getTelefone();
        
        System.out.print("Novo email [" + cliente.getEmail() + "]: ");
        String novoEmail = scanner.nextLine();
        if (novoEmail.trim().isEmpty()) novoEmail = cliente.getEmail();
        
        boolean sucesso = hotel.alterarDadosCliente(documento, novoNome, novoDocumento, novoTelefone, novoEmail);
        if (sucesso) {
            System.out.println("✅ Dados alterados com sucesso!");
        } else {
            System.out.println("❌ Erro ao alterar dados!");
        }
    }
    
    private void removerCliente() {
        System.out.println("\n=== REMOVER CLIENTE ===");
        
        System.out.print("CPF do cliente: ");
        String documento = scanner.nextLine();
        
        boolean sucesso = hotel.removerCliente(documento);
        if (sucesso) {
            System.out.println("✅ Cliente removido com sucesso!");
        } else {
            System.out.println("❌ Erro ao remover cliente! Verifique se o CPF está correto e se não há reservas ativas.");
        }
    }
    
    private void consultarQuartosDisponiveis() {
        System.out.println("\n=== QUARTOS DISPONÍVEIS ===");
        if (hotel.getQuartosDisponiveis().isEmpty()) {
            System.out.println("Nenhum quarto disponível no momento.");
        } else {
            for (Quarto quarto : hotel.getQuartosDisponiveis()) {
                System.out.println("• " + quarto);
            }
        }
    }
    
    private void fazerReserva() {
        System.out.println("\n=== FAZER RESERVA ===");
        
        // Listar clientes
        if (hotel.getClientes().isEmpty()) {
            System.out.println("❌ Nenhum cliente cadastrado! Cadastre um cliente primeiro.");
            return;
        }
        
        System.out.println("Clientes disponíveis:");
        for (int i = 0; i < hotel.getClientes().size(); i++) {
            System.out.println((i + 1) + ". " + hotel.getClientes().get(i));
        }
        
        System.out.print("Escolha o cliente (número): ");
        int indiceCliente = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indiceCliente < 0 || indiceCliente >= hotel.getClientes().size()) {
            System.out.println("❌ Cliente inválido!");
            return;
        }
        
        Cliente cliente = hotel.getClientes().get(indiceCliente);
        
        // Listar quartos disponíveis
        if (hotel.getQuartosDisponiveis().isEmpty()) {
            System.out.println("❌ Nenhum quarto disponível!");
            return;
        }
        
        System.out.println("\nQuartos disponíveis:");
        for (int i = 0; i < hotel.getQuartosDisponiveis().size(); i++) {
            System.out.println((i + 1) + ". " + hotel.getQuartosDisponiveis().get(i));
        }
        
        System.out.print("Escolha o quarto (número): ");
        int indiceQuarto = Integer.parseInt(scanner.nextLine()) - 1;
        
        if (indiceQuarto < 0 || indiceQuarto >= hotel.getQuartosDisponiveis().size()) {
            System.out.println("❌ Quarto inválido!");
            return;
        }
        
        Quarto quarto = hotel.getQuartosDisponiveis().get(indiceQuarto);
        
        // Datas
        System.out.print("Data de check-in (dd/MM/yyyy): ");
        LocalDate checkIn = lerData();
        if (checkIn == null) return;
        
        System.out.print("Data de check-out (dd/MM/yyyy): ");
        LocalDate checkOut = lerData();
        if (checkOut == null) return;
        
        try {
            Reserva reserva = hotel.fazerReserva(cliente, quarto, checkIn, checkOut);
            System.out.println("\n✅ Reserva realizada com sucesso!");
            System.out.println("ID da Reserva: " + reserva.getId());
            System.out.println("Valor Total: R$ " + String.format("%.2f", reserva.getValorTotal()));
        } catch (Exception e) {
            System.out.println("\n❌ Erro ao fazer reserva: " + e.getMessage());
        }
    }
    
    private void cancelarReserva() {
        System.out.println("\n=== CANCELAR RESERVA ===");
        
        if (hotel.getReservasAtivas().isEmpty()) {
            System.out.println("Nenhuma reserva ativa encontrada.");
            return;
        }
        
        System.out.println("Reservas ativas:");
        for (Reserva reserva : hotel.getReservasAtivas()) {
            System.out.println("• " + reserva);
        }
        
        System.out.print("ID da reserva para cancelar: ");
        int idReserva = Integer.parseInt(scanner.nextLine());
        
        boolean sucesso = hotel.cancelarReserva(idReserva);
        if (sucesso) {
            System.out.println("✅ Reserva cancelada com sucesso!");
        } else {
            System.out.println("❌ Erro ao cancelar reserva! Verifique o ID.");
        }
    }
    
    private void transferirHospede() {
        System.out.println("\n=== TRANSFERIR HÓSPEDE ===");
        
        if (hotel.getReservasAtivas().isEmpty()) {
            System.out.println("Nenhuma reserva ativa encontrada.");
            return;
        }
        
        System.out.println("Reservas ativas:");
        for (Reserva reserva : hotel.getReservasAtivas()) {
            System.out.println("• " + reserva);
        }
        
        System.out.print("ID da reserva para transferir: ");
        int idReserva = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Número do novo quarto: ");
        String novoQuarto = scanner.nextLine();
        
        boolean sucesso = hotel.transferirHospede(idReserva, novoQuarto);
        if (sucesso) {
            System.out.println("✅ Hóspede transferido com sucesso!");
        } else {
            System.out.println("❌ Erro na transferência! Verifique o ID da reserva e o número do quarto.");
        }
    }
    
    private void listarReservasAtivas() {
        System.out.println("\n=== RESERVAS ATIVAS ===");
        if (hotel.getReservasAtivas().isEmpty()) {
            System.out.println("Nenhuma reserva ativa encontrada.");
        } else {
            for (Reserva reserva : hotel.getReservasAtivas()) {
                System.out.println("• " + reserva);
            }
        }
    }
    
    private LocalDate lerData() {
        try {
            String dataStr = scanner.nextLine();
            return LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("❌ Formato de data inválido! Use dd/MM/yyyy");
            return null;
        }
    }
}
