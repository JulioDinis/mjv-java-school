package banco;

import java.time.LocalDate;
import java.util.List;

public class Terminal {

    public static void main(String[] args) {

        System.out.println("\n $ $ $ $ $ $ $ $ $ $ $ $ $   BANK  $ $ $ $ $ $ $ $ $ $ $ $ $ \n");

        ContaCorrente c1 = new ContaCorrente(1, 1);

        c1.getCliente().setNome("Joaquim");
        c1.getCliente().setDataNascimento(LocalDate.of(2000, 6, 14));

        c1.depositar(1200D, "DEPOSITO", "Deposito em especie");
        c1.sacar(200D, "SAQUE", "Saque ATM");

        ContaCorrente c2 = new ContaCorrente(2, 1);

        c2.getCliente().setDataNascimento(LocalDate.of(2000, 12, 25));
        c2.getCliente().setNome("Natalicio");
        c2.depositar(1300D, "DEPOSITO", "Deposito em especie");
        c2.sacar(252D, "SAQUE", "Saque ATM");


        c1.transferir(c2, 20D);
        c2.transferir(c1, 150D);

        c1.transferir(c2, 500D);

        cancelarConta(c1, "Não possui cartaão de credito");

        c2.transferir(c1, 1418D);
        cancelarConta(c2, "Comprou colchão novo");


        imprimirTransacoes(c1);
        imprimirTransacoes(c2);
    }

    private static void cancelarConta(ContaCorrente c, String motivo) {
        if (c.cancelarConta(motivo)) {
            System.out.println("Conta Cancelada");
            imprimirTransacoes(c);
        } else {
            System.out.println("\n Não foi possivel cancelar a conta\n");
        }

    }

    public static void imprimirTransacoes(ContaCorrente c) {

        List<Transacao> transacoes = c.getTransacoes();
        for (Transacao transacao : transacoes) {
            System.out.println(transacao.getData() + "  " + transacao.getDescricao() + " " + transacao.getValor());
        }
        System.out.println("-----------------------------------\nSaldo: " + c.getSaldo());
    }
}
