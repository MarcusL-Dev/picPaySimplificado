package dev.marcus.picPaySimplificado.domain.entities.transfer;

import java.time.LocalDateTime;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.DTOs.TransactionDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity(name = "transfer")
@Table(name = "transfers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transfer extends Transaction{

    @ManyToOne
    @JoinColumn(name = "id_usuarioRecebedor")
    private Usuario usuarioRecebedor;

    public Transfer(TransactionDTO transactionData, LocalDateTime dataHora, Usuario usuario, Usuario usuarioRecebedor){
        super(
            transactionData.valor(),
            dataHora,
            TypeTransaction.TRANSFER,
            usuario
        );
        setUsuarioRecebedor(usuarioRecebedor);
    }

    public void setUsuarioRecebedor(Usuario usuarioRecebedor) {
        this.usuarioRecebedor = usuarioRecebedor;
    }

    public Usuario getUsuarioRecebedor() {
        return usuarioRecebedor;
    }
}
