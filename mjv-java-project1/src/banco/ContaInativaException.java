package banco;

public class ContaInativaException extends Exception{

    private ContaCorrente conta;

    public ContaInativaException(ContaCorrente c){
        conta = c;
    }

@Override
    public String getMessage(){
        return String.format("ERRO: Conta n° %s  foi Cancelada" , conta.getNumeroConta());
    }

}
