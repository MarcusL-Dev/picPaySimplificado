package dev.marcus.picPaySimplificado.domain.entities.logista;

import dev.marcus.picPaySimplificado.domain.entities.logista.DTOs.LogistaDTO;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Roles;
import dev.marcus.picPaySimplificado.domain.entities.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "logista")
@Table(name = "logistas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Logista extends Usuario{
    @Column(name = "cnpj", nullable = false, unique = true, length = 14)
    private String cnpj;

    public Logista(LogistaDTO logistaData, String senha){
        super(
            logistaData.nome(),
            logistaData.email(),
            senha,
            Roles.LOGISTA
        );
        setCnpj(logistaData.cnpj());
    }
}
