package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.impl.TransactionOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl.TransferOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.LogistaSendingTransferException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.SaldoInsuficienteException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.UsersEqualsInTransfer;
import dev.marcus.picPaySimplificado.repositories.TransactionReporitory;
import dev.marcus.picPaySimplificado.services.interfaces.TransactionService;
import dev.marcus.picPaySimplificado.services.interfaces.TransferService;
import dev.marcus.picPaySimplificado.services.interfaces.UsuarioService;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionReporitory transactionRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TransferService transferService;

    private TransactionOutDTO transformTransactionInTransactioOutDTO (Transaction transaction){
        if (transaction.getTypeTransaction() == TypeTransaction.TRANSFER) {
            var transfer = transferService.getTransfer(transaction.getId());
            var usuarioRecebedorData = this.usuarioService.transforUsuarioToUsuarioDTO(transfer.getUsuarioRecebedor());
            var usuarioData = this.usuarioService.transforUsuarioToUsuarioDTO(transaction.getUsuario());
            return new TransferOutDTOImpl(usuarioData, usuarioRecebedorData, transaction);
        }
        var usuarioData = this.usuarioService.transforUsuarioToUsuarioDTO(transaction.getUsuario());
        return new TransactionOutDTOImpl(usuarioData, transaction);
    }

    private void validaSaldoForTransaction(Usuario usuario, float valorADecrementar){
        if (valorADecrementar > usuario.getSaldo()) {
            throw new SaldoInsuficienteException();
        }
    }

    private void validaUsersEqualsInTransfer(Usuario usuario, TransactionDTO transactionData){
        if (usuario.getId().toString().equals(transactionData.getIdUsuarioRecebedor().toString())) {
            throw new UsersEqualsInTransfer();
        }
    }

    private void validaLogistaSendTransfer(Usuario usuario){
        if (usuario.getRole() == Roles.LOGISTA) {
            throw new LogistaSendingTransferException();
        }
    }

    private void validaTransaction(Usuario usuario, TransactionDTO transactionData){
        if (transactionData.getTypeTransaction() != TypeTransaction.DEPOSITO) {
            if(transactionData.getTypeTransaction() == TypeTransaction.TRANSFER){
                this.validaLogistaSendTransfer(usuario);
                this.validaUsersEqualsInTransfer(usuario, transactionData);
            }
            this.validaSaldoForTransaction(usuario, transactionData.getValor());
        }
    }

    @Override
    public List<TransactionOutDTO> getTransactions() {
        var transactions = transactionRepository.findAll();
        var transactionsOutData = new ArrayList<TransactionOutDTO>();
        for(Transaction transaction : transactions){
            var transactionOutData = this.transformTransactionInTransactioOutDTO(transaction);
            transactionsOutData.add(transactionOutData);
        }
        return transactionsOutData;
    }

    @Override
    public TransactionOutDTO createTransaction(TransactionDTO transactionData, String userEmail) {
        var usuario = usuarioService.getUsuarioByEmail(userEmail);
        this.validaTransaction(usuario, transactionData);
        if(transactionData.getTypeTransaction() == TypeTransaction.TRANSFER){
            var usuarioRecebedor = usuarioService.getUsuario(transactionData.getIdUsuarioRecebedor());
            var newTransfer = transferService.creteTransfer(transactionData, usuario, usuarioRecebedor);
            return this.transformTransactionInTransactioOutDTO(newTransfer);
        }
        var newTransaction = new Transaction(transactionData.getValor(), LocalDateTime.now(), transactionData.getTypeTransaction(), usuario);
        transactionRepository.save(newTransaction);
        return this.transformTransactionInTransactioOutDTO(newTransaction);
    }

    @Override
    public TransactionOutDTO getTransaction(UUID id) {
        @SuppressWarnings("null")
        var transaction = this.transactionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(TypeEntities.TRANSACTION, id));
        return transformTransactionInTransactioOutDTO(transaction);
    }
}
