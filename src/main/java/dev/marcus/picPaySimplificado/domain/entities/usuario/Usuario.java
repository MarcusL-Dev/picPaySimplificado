package dev.marcus.picPaySimplificado.domain.entities.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.marcus.picPaySimplificado.domain.entities.transaction.Transaction;
import dev.marcus.picPaySimplificado.domain.entities.transaction.TypeTransaction;
import dev.marcus.picPaySimplificado.domain.entities.transfer.Transfer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "nome", nullable = false, length = 256)
    private String nome;
    @Column(name = "email", nullable = false, unique = true, length = 256)
    private String email;
    @Column(name = "senha", nullable = false, length = 128)
    private String senha;

    @Column(name = "role", nullable = false)
    private Roles role;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuarioRecebedor")
    private List<Transfer> transferenciasRecebidas = new ArrayList<>();

    public Usuario(
        String nome,
        String email,
        String senha,
        Roles role
    ){
        this.setNome(nome);
        this.setEmail(email);
        this.setSenha(senha);
        this.setRole(role);
    }

    public float getSaldo(){
        float saldo = 0;
        for(Transaction transaction : this.getTransactions()){
            if (transaction.getTypeTransaction() == TypeTransaction.DEPOSITO) {
                saldo = saldo + transaction.getValor();  
            }else{
                saldo = saldo - transaction.getValor();
            }
        }
        for(Transfer transferRecebida : this.getTransferenciasRecebidas()){
            saldo = saldo + transferRecebida.getValor();
        }
        return saldo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Roles.COMUM) return List.of(
            new SimpleGrantedAuthority("ROLE_COMUM")
        );
        else return List.of(
            new SimpleGrantedAuthority("ROLE_LOGISTA")
        );
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
