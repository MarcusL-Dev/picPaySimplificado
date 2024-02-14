package dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(TypeEntities type, UUID id){
        super(type.toString()+" não encontrado com id: "+id.toString());
    }
}
