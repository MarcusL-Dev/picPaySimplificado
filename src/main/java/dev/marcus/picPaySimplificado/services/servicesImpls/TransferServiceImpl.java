package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.transfer.Transfer;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.repositories.TransferRepository;
import dev.marcus.picPaySimplificado.services.interfaces.TransferService;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Transfer creteTransfer(TransactionDTO transactionData, Usuario usuario, Usuario usuarioRecebedor) {
        var newTransfer = new Transfer(transactionData, LocalDateTime.now(), usuario, usuarioRecebedor);
        transferRepository.save(newTransfer);
        return newTransfer;
    }

    @Override
    public Transfer getTransfer(UUID id) {
        @SuppressWarnings("null")
        var transfer = transferRepository.findById(id);
        if (!transfer.isPresent()) {
                throw new EntityNotFoundException(TypeEntities.TRANSFER, id);
        }
        return transfer.get();
    }
}
