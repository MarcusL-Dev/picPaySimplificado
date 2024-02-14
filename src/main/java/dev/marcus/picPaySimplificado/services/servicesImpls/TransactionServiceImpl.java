package dev.marcus.picPaySimplificado.services.servicesImpls;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionOutDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import dev.marcus.picPaySimplificado.domain.entities.usuario.DTOs.UsuarioOutDTO;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.EntityNotFoundException;
import dev.marcus.picPaySimplificado.infra.exceptions.typeExceptions.TypeEntities;
import dev.marcus.picPaySimplificado.repositories.TransactionRepository;
import dev.marcus.picPaySimplificado.services.interfaces.LogistaService;
import dev.marcus.picPaySimplificado.services.interfaces.TransactionService;
import dev.marcus.picPaySimplificado.services.interfaces.UserComumService;
import dev.marcus.picPaySimplificado.services.interfaces.UsuarioService;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UserComumService userComumService;
    @Autowired
    private LogistaService logistaService;

    private UsuarioOutDTO transforUsuarioInUsuarioDTO(Usuario usuario){
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

        for(Transaction transaction : transactions){

            var usuarioPagante = transaction.getUsuarioPagante();
            var usuarioPaganteData = this.transforUsuarioInUsuarioDTO(usuarioPagante);

            var usuarioRecebedor = transaction.getUsuarioRecebedor();
            var usuarioRecebedorData = this.transforUsuarioInUsuarioDTO(usuarioRecebedor);

            var transactionOutData = new TransactionOutDTO(usuarioPaganteData, usuarioRecebedorData, transaction);
            transactionsOutData.add(transactionOutData);
        }

        return transactionsOutData;
    }

    @Override
    public TransactionOutDTO createTransaction(TransactionDTO transactionData, String userEmail) {
        var usuarioPagante = usuarioService.getUsuarioByEmail(userEmail);
        var usuarioRecebedor = usuarioService.getUsuario(transactionData.idUsuarioRecebedor());
        var newTransaction = new Transaction(transactionData, usuarioPagante, usuarioRecebedor);

        var usuarioPaganteData = this.transforUsuarioInUsuarioDTO(usuarioPagante);
        var usuarioRecebedorData = this.transforUsuarioInUsuarioDTO(usuarioRecebedor);

        return new TransactionOutDTO(usuarioPaganteData ,usuarioRecebedorData, newTransaction);
    }

    @Override
    public TransactionOutDTO getTransaction(UUID id) {
        @SuppressWarnings("null")
        var transaction = this.transactionRepository.findById(id);
        if (!transaction.isPresent()) {
            throw new EntityNotFoundException(TypeEntities.TRANSACTION, id);
        }
        var getTransaction = transaction.get();
        var usuarioPaganteData = this.transforUsuarioInUsuarioDTO(getTransaction.getUsuarioPagante());
        var usuarioRecebedorData = this.transforUsuarioInUsuarioDTO(getTransaction.getUsuarioRecebedor());

        return new TransactionOutDTO(usuarioPaganteData, usuarioRecebedorData, getTransaction);
    }
    
}
