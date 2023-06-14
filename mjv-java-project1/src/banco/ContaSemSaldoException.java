package banco;

public class ContaSemSaldoException extends Exception {
    private ContaCorrente contaCorrente;

    public ContaSemSaldoException(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    @Override
    public String getMessage() {
        return String.format("ERRO: A Conta N° %d não possui saldo suficiente para essa transação", contaCorrente.getNumeroConta());
    }
}
