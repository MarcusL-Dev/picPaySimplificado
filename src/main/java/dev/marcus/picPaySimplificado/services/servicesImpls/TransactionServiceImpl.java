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
import dev.marcus.picPaySimplificado.domain.entities.transfer.Transfer;
import dev.marcus.picPaySimplificado.domain.entities.transfer.DTOs.impl.TransferOutDTOImpl;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.UsersEqualsInTransfer;
import dev.marcus.picPaySimplificado.repositories.TransactionReporitory;
import dev.marcus.picPaySimplificado.services.interfaces.LogistaService;
import dev.marcus.picPaySimplificado.services.interfaces.TransactionService;
import dev.marcus.picPaySimplificado.services.interfaces.TransferService;
import dev.marcus.picPaySimplificado.services.interfaces.UserComumService;
import dev.marcus.picPaySimplificado.services.interfaces.UsuarioService;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionReporitory transactionRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UserComumService userComumService;
    @Autowired
    private LogistaService logistaService;
    @Autowired
    private TransferService transferService;

    private UsuarioOutDTO transforUsuarioToUsuarioDTO(Usuario usuario){
        if (usuario.getRole() == Roles.COMUM) {
            return userComumService.getUserComum(usuario.getId());
        }else{
            return logistaService.getLogista(usuario.getId()); 
        }
    }

    @Override
    public List<TransactionOutDTO> getTransactions() {
        var transactions = transactionRepository.findAll();
        var transactionsOutData = new ArrayList<TransactionOutDTO>();
        TransactionOutDTO transactionOutData;
        for(Transaction transaction : transactions){

            var usuario = transaction.getUsuario();
            var usuarioData = this.transforUsuarioToUsuarioDTO(usuario);

            if(transaction.getTypeTransaction() == TypeTransaction.TRANSFER){
                var transfer = (Transfer) this.getTransaction(transaction.getId());
                var usuarioRecebedor = transfer.getUsuarioRecebedor();
                var usuarioRecebedorData = this.transforUsuarioToUsuarioDTO(usuarioRecebedor);    
                transactionOutData = new TransferOutDTOImpl(usuarioData, usuarioRecebedorData, transfer);
            }else{
                transactionOutData = new TransactionOutDTOImpl(usuarioData, transaction);
            }
            transactionsOutData.add(transactionOutData);
        }
        return transactionsOutData;
    }

    @Override
    public TransactionOutDTO createTransaction(TransactionDTO transactionData, String userEmail) {
        var usuario = usuarioService.getUsuarioByEmail(userEmail);
        var usuarioData = this.transforUsuarioToUsuarioDTO(usuario);

        if(transactionData.typeTransaction() == TypeTransaction.TRANSFER){
            if (usuario.getId().toString().equals(transactionData.idUsuarioRecebedor().toString())) {
                System.out.println("entrou");
                throw new UsersEqualsInTransfer();
            }
            var usuarioRecebedor = usuarioService.getUsuario(transactionData.idUsuarioRecebedor());
            var usuarioRecebedorData = this.transforUsuarioToUsuarioDTO(usuarioRecebedor);
            var newTransfer = transferService.creteTransfer(transactionData, usuario, usuarioRecebedor);
            return new TransferOutDTOImpl(usuarioData, usuarioRecebedorData, newTransfer);
        }

        var newTransaction = new Transaction(transactionData.valor(), LocalDateTime.now(), transactionData.typeTransaction(), usuario);
        transactionRepository.save(newTransaction);
        return new TransactionOutDTOImpl(usuarioData, newTransaction);
    }

    @Override
    public TransactionOutDTO getTransaction(UUID id) {
        @SuppressWarnings("null")
        var transaction = this.transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            throw new EntityNotFoundException(TypeEntities.TRANSACTION, id);
        }
        var getTransaction = transaction.get();
        var usuarioPaganteData = this.transforUsuarioToUsuarioDTO(getTransaction.getUsuario());

        if (getTransaction.getTypeTransaction() == TypeTransaction.TRANSFER) {
            var transfer = transferService.getTransfer(getTransaction.getId());
            var usuarioRecebedorData = this.transforUsuarioToUsuarioDTO(transfer.getUsuarioRecebedor());
            return new TransferOutDTOImpl(usuarioPaganteData, usuarioRecebedorData, getTransaction);
        }

        return new TransactionOutDTOImpl(usuarioPaganteData, getTransaction);
    }
}
