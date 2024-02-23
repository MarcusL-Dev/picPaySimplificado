package dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions;

public class LogistaSendingTransferException extends RuntimeException{
    public LogistaSendingTransferException(){
        super("Logistas nao podem enviar valores por tranferecia");
    }
}
