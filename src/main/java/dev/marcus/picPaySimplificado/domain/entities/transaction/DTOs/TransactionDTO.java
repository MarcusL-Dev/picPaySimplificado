package dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.impl.DepositoDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.impl.SaqueDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl.TransferDTOImpl;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "typeTransaction")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SaqueDTOImpl.class, name = "SAQUE"),
    @JsonSubTypes.Type(value = DepositoDTOImpl.class, name = "DEPOSITO"),
    @JsonSubTypes.Type(value = TransferDTOImpl.class, name = "TRANSFERENCIA")
})
public interface TransactionDTO {
    TypeTransaction getTypeTransaction();
    UUID getIdUsuarioRecebedor();
    float getValor();
}
