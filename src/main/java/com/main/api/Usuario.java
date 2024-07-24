package com.main.api;

import java.util.ArrayList;

public class Usuario {

    private static String nome;
    private static String cpf;
    private static String email;
    private static String telefone;

    // construtor
    public Usuario(String nome, String cpf, String email,String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;

    }
    public Usuario () {
        
    }

    // getters e setters
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

    public void setNome(String nome) {
        try {
            if (nome != null) {
                this.nome = nome;
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("Nome Inválido");
        }
    }

    public void setCpf(String cpf) {
        try {
            if (cpf.length() == 11) {
                this.cpf = cpf;
            } else {
                throw new IllegalArgumentException("CPF inválido");
            }

        } catch (Exception e) {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public void setEmail(String email) {
        try {
            if (email.contains("@") && email.contains(".")) {
                this.email = email;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    public void setTelefone(String telefone) {
        try {
            if (telefone.length() == 10) {

                this.telefone = telefone;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Telefone inválido");
        }

    }
}
