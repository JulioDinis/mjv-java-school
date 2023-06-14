package banco;


import java.time.LocalDate;

public class Transacao {
    private LocalDate data;
    private String descricao;
    private Double valor;
    private String tipo; //enum

    @Override
    public String toString() {
        return "Transacao{" +
                "data=" + data +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    public String getTipo() {
        return tipo;
    }
    public Transacao(){
        data = LocalDate.now();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}