package banco;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

public class Terminal {

    public static void main(String[] args) {

        System.out.println("\n $ $ $ $ $ $ $ $ $ $ $ $ $   BANK  $ $ $ $ $ $ $ $ $ $ $ $ $ \n");

        ContaCorrente c1 = new ContaCorrente(1, 1);

        c1.getCliente().setNome("Joaquim");
        c1.getCliente().setDataNascimento(LocalDate.of(2000, 6, 14));

        try {
            c1.depositar(1200D, "DEPOSITO", "Deposito em especie");
        } catch (ContaInativaException e) {
            System.out.println(e.getMessage());
        }
        try {
            c1.sacar(200D, "SAQUE", "Saque ATM");
        } catch (ContaSemSaldoException e) {
            System.out.println(e.getMessage());
        }

        ContaCorrente c2 = new ContaCorrente(2, 1);

        c2.getCliente().setDataNascimento(LocalDate.of(2000, 12, 25));
        c2.getCliente().setNome("Natalicio");
        try {
            c2.depositar(1300D, "DEPOSITO", "Deposito em especie");
        } catch (ContaInativaException e) {
           System.out.println(e.getMessage());
        }
        try {
            c2.sacar(10000D, "SAQUE", "Saque ATM");
        } catch (ContaSemSaldoException e) {
            System.out.println(e.getMessage());
        }

        cancelarConta(c2, "Comprou colchão novo");

        try {
            c1.transferir(c2, c1.getSaldo() + 200D);
        } catch (ContaInativaException | ContaSemSaldoException e) {
            System.out.println(e.getMessage());
        }

        try {
            c1.transferir(c2, c1.getSaldo());
            c1.cancelarConta("Nao tem limite");
            c2.transferir(c1, 1.0);

        } catch (ContaInativaException | ContaSemSaldoException e) {
            System.out.println(e.getMessage());
        }

        try {
            c1.depositar(200D,"DEPOSITO", "Deposito ATM");
        } catch (ContaInativaException e) {
           System.out.println(e.getMessage());
        }

        imprimirTransacoes(c1.getTransacoes());
        imprimirTransacoes(c2.getTransacoes());

        List<Transacao> extrato = c2.consultarExtrato(LocalDate.of(2023, 6, 14), LocalDate.of(2023, 6, 14));

        imprimirTransacoes(extrato);

    }

    private static void cancelarConta(ContaCorrente c, String motivo) {
        try {
            if (c.cancelarConta(motivo)) {
                System.out.println("Conta Cancelada");
                imprimirTransacoes(c.getTransacoes());
            } else {
                System.out.println("\nNão foi possivel cancelar a conta\n");
            }
        } catch (ContaInativaException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void imprimirTransacoes(List<Transacao> transacoes) {

        System.out.println("IMPRIMINDO TRANSAÇÔES\n");

        for (Transacao transacao : transacoes) {
            System.out.println(transacao.getData() + "  " + transacao.getDescricao() + " " + transacao.getValor());
        }

        System.out.println("\n-----------------------------------------\n");
    }

}
