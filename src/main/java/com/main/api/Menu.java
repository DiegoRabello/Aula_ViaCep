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
        System.out.println("2- Buscar Cep");
        System.out.println("3- Sair");
        System.out.println("Digite a opção desejada: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                cadastraUsuario();
                break;
            case 2:
                bucarCep();
                break;
            case 3:
                System.exit(0);
            default:
                break;
        }
    }

    public static void cadastraUsuario() {
        boolean sair = false;
        while (sair == false) {
           
            Scanner scanner = new Scanner(System.in);
            Usuario usuario = new Usuario();
            System.out.println("Digite o Nome do usuário: ");
            String nome = scanner.nextLine();
            usuario.setNome(nome);

            System.out.println("Digite o Cpf do usuário: ");
            String cpf = scanner.nextLine();
            usuario.setCpf(cpf);
            
            System.out.println("Digite o Email do usuário: ");
            String email = scanner.nextLine();
            usuario.setEmail(email);
            
            System.out.println("Digite o Telefone do usuário: ");
            String telefone = scanner.nextLine();
            usuario.setTelefone(telefone);

            scanner.nextLine();
            System.out.println("nonfirme Suas Informações Pessoais: " +
                    "\nNome: " + usuario.getNome() +
                    "\nCpf: " + cpf +
                    "\nEmail: " + email +
                    "\nTelefone: " + telefone);

            System.out.println("\n Você confirma suas Informações Pessoais s/n ? ");
            String confirma = scanner.nextLine();
            if (confirma.equalsIgnoreCase("s")) {

                System.out.println("Informações Cadastradas com Sucesso!");

                
                System.out.println("Cadastre agora seu Endereço.");
                bucarCep();


                break;

            } else {
                System.out.println("Informações não Cadastradas!, Tente Novamente!");
                sair = true;

            }
            menuPrincipal();
        }
    }

    public static Endereco bucarCep() {
        Scanner scanner = new Scanner(System.in);
        
        Endereco endereco = new Endereco();
        OkHttpClient client = new OkHttpClient();

        System.out.println("Digite Seu Cep: ");
        String cepDigitado = scanner.nextLine();
        
        String url = "https://viacep.com.br/ws/" + cepDigitado + "/json/";
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
        System.out.println("Digite um Complemento para seu Endereco: ");
        endereco.setComplemento(scanner.nextLine());
        System.out.println("Digite o Número da Sua Casa: ");
        endereco.setNumero(scanner.nextLine());
        if (endereco != null) {
            System.out.println("\nEndereço encontrado:");
            System.out.println("Rua: " + endereco.getLogradouro());
            System.out.println("Bairro: " + endereco.getBairro());
            System.out.println("Cidade: " + endereco.getLocalidade());
            System.out.println("UF: " + endereco.getUf());
            System.out.println("Complemento: " + endereco.getComplemento());
            System.out.println("Número: " + endereco.getNumero());
            System.out.println("CEP: " + endereco.getCep());
        } else {
            System.out.println("Cep não encontrado!");
        }
        return endereco;
        

    }
}
