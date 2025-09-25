/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelpoo;

/**
 *
 * @author luize
 */
public class Cliente {
    private String nome;
    private String documento;
    private String telefone;
    private String email;
    
    public Cliente(String nome, String documento, String telefone, String email) {
        if (!validarCPF(documento)) {
            throw new IllegalArgumentException("CPF deve ter exatamente 11 dígitos (apenas números) ou 14 caracteres com formatação (xxx.xxx.xxx-xx)");
        }
        if (!validarEmail(email)) {
            throw new IllegalArgumentException("Email deve conter o símbolo @ para ser válido");
        }
        
        this.nome = nome.trim();
        this.documento = documento.trim();
        this.telefone = telefone.trim();
        this.email = email.trim();
    }
    
    // Métodos de validação
    private boolean validarCPF(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return false;
        }
        
        // Remove formatação (pontos, hífens e espaços)
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        
        // Verifica se tem exatamente 11 dígitos
        return cpfLimpo.length() == 11;
    }
    
    private boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        // Verifica se contém @ e tem pelo menos 3 caracteres (x@x)
        return email.contains("@") && email.length() >= 3 && 
               email.indexOf("@") > 0 && email.indexOf("@") < email.length() - 1;
    }
    
    // Getters
    public String getNome() { return nome; }
    public String getDocumento() { return documento; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    
    // Setters para alteração de dados
    public void setNome(String nome) { 
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim(); 
        }
    }
    
    public void setDocumento(String documento) { 
        if (documento != null && !documento.trim().isEmpty()) {
            if (!validarCPF(documento)) {
                throw new IllegalArgumentException("CPF deve ter exatamente 11 dígitos (apenas números) ou 14 caracteres com formatação (xxx.xxx.xxx-xx)");
            }
            this.documento = documento.trim(); 
        }
    }
    
    public void setTelefone(String telefone) { 
        if (telefone != null && !telefone.trim().isEmpty()) {
            this.telefone = telefone.trim(); 
        }
    }
    
    public void setEmail(String email) { 
        if (email != null && !email.trim().isEmpty()) {
            if (!validarEmail(email)) {
                throw new IllegalArgumentException("Email deve conter o símbolo @ para ser válido");
            }
            this.email = email.trim(); 
        }
    }
    
    public void alterarDados(String nome, String documento, String telefone, String email) {
        setNome(nome);
        setDocumento(documento);
        setTelefone(telefone);
        setEmail(email);
    }
    
    @Override
    public String toString() {
        return nome + " (" + documento + ")";
    }
}
