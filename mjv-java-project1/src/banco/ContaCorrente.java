package banco;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaCorrente {
    private Cliente cliente = new Cliente();
    private Double saldo = 0D;
    private Integer numeroConta = 0;
    private Integer numeroAgencia = 0;
    private boolean ativa = true;

    private List<Transacao> transacoes = new ArrayList<>();

    public ContaCorrente(int numeroConta, int numeroAgencia) {
        this.numeroConta = numeroConta;
        this.numeroAgencia = numeroAgencia;
    }

    boolean sacar(double valor, String tipo, String descricao) {
        if (saldo < valor) {
            return false;
        }

        saldo = saldo - valor;
        incluirTransacao(valor, tipo, descricao);
        return true;
    }

    private void incluirTransacao(Double valor, String tipo, String descricao) {
        Transacao t = new Transacao();
        t.setTipo(tipo);
        t.setDescricao(descricao);
        t.setValor(valor);
        transacoes.add(t);
    }

    void transferir(ContaCorrente contaDestino, Double valor) {

        if (consultarSaldo() >= valor) {
            sacar(valor, "TRANSFERENCIA", "Transferido para " + contaDestino.getCliente().getNome());
            contaDestino.depositar(valor, "TRANSFERENCIA", "Transferencia recebida de: " + getCliente().getNome());
        }

    }

    Double consultarSaldo() {

        return saldo;
    }

    List<Transacao> consultarExtrato(LocalDate di, LocalDate df) {
        //logica
        return transacoes;
    }

    boolean cancelarConta(String justificativa) {

        if (saldo == 0) {
            this.ativa = false;
            incluirTransacao(0D, "CANCELAMENTO", "Conta Cancelada: " + justificativa);
            return true;
        } else {
            incluirTransacao(saldo, "FALHA", "Não foi possivel Cancelar");
        }
        return false;
    }

    void depositar(Double valor, String tipo, String descricao) {
        saldo = saldo + valor;
        incluirTransacao(valor, tipo, descricao);
    }

    private void receberTransferencia(Double valor) {
        saldo = saldo + valor;
        incluirTransacao(valor, "TRANSFERENCIA", "Tranferencia Recebida");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(Integer numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}