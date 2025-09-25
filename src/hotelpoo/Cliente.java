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
        this.nome = nome;
        this.documento = documento;
        this.telefone = telefone;
        this.email = email;
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
