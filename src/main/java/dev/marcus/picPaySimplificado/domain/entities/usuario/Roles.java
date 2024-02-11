package dev.marcus.picPaySimplificado.domain.entities.usuario;

public enum Roles {
    COMUM("comum"),
    LOGISTA("logista");

    String role; 

    Roles(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}
