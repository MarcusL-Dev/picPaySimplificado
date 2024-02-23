package dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions;

public class SaldoInsuficienteException extends RuntimeException{
    public SaldoInsuficienteException(){
        super("Saldo insuficiente!");
    }
}
