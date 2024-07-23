package com.main.api;

import java.util.ArrayList;

public class Usuario {
    
    private static String nome;
    private static String cpf;
    private static String email;
    private static String telefone;

    
    //construtor
    public Usuario(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
       
    }
    
    //getters e setters
    public static String getNome() {
        return nome;
    }
    public static String getCpf() {
        return cpf;
    }
    public static String getEmail() {
        return email;
    }
    public static String getTelefone() {
        return telefone;
    }
    public void setNome(String nome ) {
        if (nome != null) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não pode ser nulo");
        }
    }
    
    public void setCpf(String cpf) {
        if (cpf.length() == 11) {
            this.cpf = cpf; 
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }
    
    public void setEmail(String email) {
        if (email.contains("@") && email.contains(".")) {
            this.email = email;  
        } else {
            throw new IllegalArgumentException("Email inválido");
        }
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
    
}
