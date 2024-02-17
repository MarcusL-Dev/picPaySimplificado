package dev.marcus.picPaySimplificado.domain.entities.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Entity(name = "TRANSACTION")
@Table(name = "TRANSACTIONS")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Setter
@Getter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TypeTransaction typeTransaction;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Transaction(
        BigDecimal valor,
        LocalDateTime dataHora,
        TypeTransaction typeTransaction,
        Usuario usuario
    ){
        setValor(valor);
        setDataHora(dataHora);
        setTypeTransaction(typeTransaction);
        setUsuario(usuario);
    }

}
