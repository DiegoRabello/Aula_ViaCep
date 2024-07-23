package com.main.api;

import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Menu {
    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nMenu: \n");
        System.out.println("1- Cadastrar Usuário");
        System.out.println("2- Sair");
        System.out.println("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cadastraUsuario();
                break;
            case 2:

                Endereco endereco = bucarCep("25710193");
                if (endereco != null) {
                    System.out.println("Endereço encontrado:");
                    System.out.println("Rua: " + endereco.getLogradouro());
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Cidade: " + endereco.getLocalidade());
                    System.out.println("UF: " + endereco.getUf());
                } else {
                    System.out.println("Cep não encontrado!");
                }
                break;
            default:
                break;
        }
    }

    public static void cadastraUsuario() {
        boolean sair = false;
        while (sair == false) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o Nome do usuário: ");
            String nome = scanner.nextLine();
            System.out.println("Digite o Cpf do usuário: ");
            String cpf = scanner.nextLine();
            System.out.println("Digite o Email do usuário: ");
            String email = scanner.nextLine();
            System.out.println("Digite o Telefone do usuário: ");
            String telefone = scanner.nextLine();

            System.out.println("nonfirme Suas Informações Pessoais: " +
                    "\nNome: " + nome +
                    "\nCpf: " + cpf +
                    "\nEmail: " + email +
                    "\nTelefone: " + telefone);

            System.out.println("\n Você confirma suas Informações Pessoais s/n ? ");
            String confirma = scanner.nextLine();
            if (confirma.equalsIgnoreCase("s")) {

                System.out.println("Informações Cadastradas com Sucesso!");

                Usuario usuario = new Usuario(nome, telefone, email);

                System.out.println("Digite Seu cep: ");
                String cep = scanner.nextLine();

                Endereco endereco = bucarCep(cep);
                
                if (endereco != null) {
                    System.out.println("Endereço encontrado:");
                    System.out.println("Rua: " + endereco.getLogradouro());
                    System.out.println("Bairro: " + endereco.getBairro());
                    System.out.println("Cidade: " + endereco.getLocalidade());
                    System.out.println("UF: " + endereco.getUf());
                } else {
                    System.out.println("Cep não encontrado!");
                }
                break;

            } else {
                System.out.println("Informações não Cadastradas!, Tente Novamente!");
                sair = true;

            }
            menuPrincipal();
        }
    }

    public static Endereco bucarCep(String cep) {

        Endereco endereco = new Endereco();
        OkHttpClient client = new OkHttpClient();

        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        Request request = new Request.Builder().url(url).build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String result = response.body().string();

                Gson gson = new Gson();

                endereco = gson.fromJson(result, Endereco.class);

            } else {
                System.out.println("Cep não encontrado!" + response.code());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return endereco;
    }
}
