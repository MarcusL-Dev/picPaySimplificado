package dev.marcus.picPaySimplificado.domain.entities.transaction;

import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "transaction")
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "valor", nullable = false)
    private double valor;

    @ManyToOne
    @JoinColumn(name = "usuarioPagante")
    private Usuario usuarioPagante;

    @ManyToOne
    @JoinColumn(name = "usuarioRecebedor")
    private Usuario usuarioRecebedor;

    public Transaction(TransactionDTO transactionData, Usuario usuarioPagante, Usuario usuarioRecebedor){
        setUsuarioPagante(usuarioPagante);
        setUsuarioRecebedor(usuarioRecebedor);
        setValor(transactionData.valor());
    }
    
}
