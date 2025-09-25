package hotelpoo;

public class ResultadoRenovacao {
    private final boolean sucesso;
    private final double valorAntigo;
    private final double valorNovo;
    private final double diferenca;
    private final String mensagem;
    
    public ResultadoRenovacao(boolean sucesso, double valorAntigo, double valorNovo, String mensagem) {
        this.sucesso = sucesso;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
        this.diferenca = valorNovo - valorAntigo;
        this.mensagem = mensagem;
    }
    
    public ResultadoRenovacao(boolean sucesso, String mensagem) {
        this(sucesso, 0, 0, mensagem);
    }
    
    // Getters
    public boolean isSucesso() { return sucesso; }
    public double getValorAntigo() { return valorAntigo; }
    public double getValorNovo() { return valorNovo; }
    public double getDiferenca() { return diferenca; }
    public String getMensagem() { return mensagem; }
    
    public String getMensagemCompleta() {
        if (!sucesso) {
            return "❌ " + mensagem;
        }
        
        String resultado = " " + mensagem + "\n";
        resultado += String.format("💰 Valor anterior: R$ %.2f\n", valorAntigo);
        resultado += String.format("💰 Valor novo: R$ %.2f\n", valorNovo);
        
        if (diferenca > 0) {
            resultado += String.format(" Diferença a PAGAR: R$ %.2f", diferenca);
        } else if (diferenca < 0) {
            resultado += String.format(" Diferença a RECEBER: R$ %.2f", Math.abs(diferenca));
        } else {
            resultado += " Sem alteração no valor";
        }
        
        return resultado;
    }
}