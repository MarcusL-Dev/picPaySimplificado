package dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions;

public class UsersEqualsInTransfer extends RuntimeException{
    public UsersEqualsInTransfer(){
        super("usuarios da transferencia não podem ser o mesmo");
    }
}
