/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Work;

import entidades.Agente;
import entidades.Artista;
import entidades.Pessoa;
import enums.PessoaTipo;
import interfaces.IArtista;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class WPessoa {

    /**
     *
     */
    public static ArrayList<Pessoa> list = new ArrayList<Pessoa>();
    /**
     *
     * @param id id da pessoa
     * @param nif nif
     * @param opcao
     *
     */
    public static long id = 0, nif = 0, opcao = -1;
    /**
     *
     * @param nome nome da pessoa
     * @param apelido apelido da pessoa
     * @param nome_artistico nome artistico do artista
     *
     */
    public static String nome, apelido, nome_artistico;
    /**
     *
     * @param montante_devido_bruto_agente dinheiro bruto devido ao agente
     * @param montante_devido_liquido_agente dinheiro com descontos ao agente
     * @param montante_devido_bruto_artista dinheiro bruto devido ao artista
     * @param montante_devido_liquido_artista dinheiro com descontos ao artista
     * @param capital capital da pessoa
     *
     */
    public static double montante_devido_bruto_agente = 0, montante_devido_liquido_agente = 0, capital = 0, montante_devido_bruto_artista = 0, montante_devido_liquido_artista = 0;
    /**
     *
     */
    public static Scanner leitor = new Scanner(System.in).useDelimiter("\n");
    /**
     *
     */
    public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     *
     * Cria um artista/agente
     *
     * @param PessoaTipo Artista ou Agente
     */
    public static void criar_Pessoa(PessoaTipo PessoaTipo) {
        System.out.println(Pessoa.getId_aux_pessoa());
        Pessoa.setId_aux_pessoa(Pessoa.getId_aux_pessoa() + 1);
        id = Pessoa.getId_aux_pessoa();

        nome();
        apelido();
        nif();

        if (PessoaTipo == PessoaTipo.Agente) {
            list.add(new Agente(nome, apelido, id, nif, 0, 0, 0));
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Agente criado com sucesso!");
        } else {
            System.out.println("Insira nome artistico: ");
            nome_artistico = leitor.next();
            list.add(new Artista(nome, apelido, id, nome_artistico, nif, 0, 0, 0));
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Artista criado com sucesso!");
        }

        listar_individual(id);

        escrever_Pessoa();
    }

    /**
     *
     * altera um artista/agente
     *
     * @param PessoaTipo
     */
    public static void alterar_Pessoa(PessoaTipo PessoaTipo) {
        listar_Pessoa(PessoaTipo);

        pedir_confirmar_Pessoa(PessoaTipo);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == opcao) {
                list.remove(i);
            }
        }

        nome();
        apelido();
        nif();

        if (PessoaTipo == PessoaTipo.Agente) {
            list.add(new Agente(nome, apelido, opcao, nif, montante_devido_bruto_agente, montante_devido_liquido_agente, capital));
        } else {
            nome_artistico();
            list.add(new Artista(nome, apelido, opcao, nome_artistico, nif, montante_devido_bruto_artista, montante_devido_liquido_artista, capital));
        }

        escrever_Pessoa();
    }

    /**
     *
     * altera um campo individual do artista/agente
     *
     * @param PessoaTipo
     * @param opcao2
     */
    public static void alterar_individual(PessoaTipo PessoaTipo, int opcao2) {
        listar_Pessoa(PessoaTipo);

        pedir_confirmar_Pessoa(PessoaTipo);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == opcao) {

                if (opcao2 == 1) {
                    System.out.println("Nome: " + list.get(i).getNome());
                    nome();
                    list.get(i).setNome(nome);
                }
                if (opcao2 == 2) {
                    System.out.println("Apelido: " + list.get(i).getApelido());
                    apelido();
                    list.get(i).setApelido(apelido);
                }
                if (opcao2 == 3) {
                    System.out.println("NIF: " + list.get(i).getNif());
                    nif();
                    list.get(i).setNif(nif);
                }
                if (opcao2 == 4 && PessoaTipo == PessoaTipo.Artista) {
                    IArtista Artista = (IArtista) list.get(i);
                    System.out.println("Nome Artistico: " + Artista.getNome_Artistico());
                    nome_artistico();
                    Artista.setNome_Artistico(nome_artistico);
                }
            }
        }
        escrever_Pessoa();
    }

    /**
     *
     * remove artista/agente
     *
     * @param PessoaTipo
     */
    public static void remover(PessoaTipo PessoaTipo) {
        listar_Pessoa(PessoaTipo);
        String frase;
        boolean erro = false;
        do {
            try {
                System.out.println("Escolha o ID que quer remover: ");
                frase = leitor.next();
                opcao = Long.parseLong(frase);
            } catch (NumberFormatException e) {
                System.out.println("Numero Incorrecto!");
                erro = true;
            }
        } while (erro);

        for (int i = 0; i < list.size(); i++) {//PERCORRE LISTA PESSOA
            if (list.get(i).getID() == opcao) {//VE SE O ID E IGUAL A OPCAO ESCOLHIDA
                if (PessoaTipo == PessoaTipo.Artista) {//VE SE O PESSOATIPO E UM ARTISTA
                    for (int j = 0; j < WEvento.list.size(); j++) {//PERCORRE A LISTA EVENTO
                        long[] artistas = WEvento.list.get(j).getArtistas();//PASSA PARA UM ARRAY TODOS OS IDS DOS ARTISTAS DE DETERMINADO EVENTO
                        for (int k = 0; k < artistas.length; k++) {//PERCORRE O ARRAY DOS IDS DOS ARTISTAS
                            if (list.get(i).getID() == artistas[k]) {//VE SE O ID E IGUAL A ALGUNS DOS IDS DOS ARTISTASS DO EVENTO
                                if (WEvento.list.get(j).getData().after(new Date())) {
                                    for (int h = 0; h < WContracto.list.size(); h++) {//PERCORRE LISTA CONTRACTOS
                                        if (list.get(i).getID() == WContracto.list.get(h).getArtista_Id()) {
                                            System.out.println("ERRO!");
                                            System.out.println("Este Artista está tem um contracto e esta no Evento: " + WEvento.list.get(j).getEvento_ID() + ".Que acaba em " + df.format(WContracto.list.get(h).getData_Final()));
                                            escrever_Pessoa();
                                            break;
                                        }
                                    }
                                    System.out.println("ERRO!");
                                    System.out.println("Este Artista está num evento!");
                                    escrever_Pessoa();
                                    break;
                                }
                            }
                        }
                    }
                    for (int h = 0; h < WContracto.list.size(); h++) {//PERCORRE LISTA CONTRACTOS
                        if (list.get(i).getID() == WContracto.list.get(h).getArtista_Id()) {
                            System.out.println("ERRO!");
                            System.out.println("Este Artista está tem um contracto! Que acaba em " + df.format(WContracto.list.get(h).getData_Final()));
                            escrever_Pessoa();
                            break;
                        }
                    }
                    list.remove(i);
                    escrever_Pessoa();
                    break;
                } else if (PessoaTipo == PessoaTipo.Agente) {
                    if (WContracto.list.isEmpty()) {
                        list.remove(i);
                        escrever_Pessoa();
                        break;
                    } else {
                        for (int h = 0; h < WContracto.list.size(); h++) {//PERCORRE LISTA CONTRACTOS
                            if (list.get(i).getID() == WContracto.list.get(h).getAgente_Id()) {
                                System.out.println("ERRO!");
                                System.out.println("Este Agente tem um contracto! Que acaba em " + df.format(WContracto.list.get(h).getData_Final()));
                                escrever_Pessoa();
                                break;
                            } else {
                                list.remove(i);
                                escrever_Pessoa();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     *
     * listar individual de um artista/agente
     *
     * @param opcao
     */
    public static void listar_individual(long opcao) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == opcao) {
                System.out.println(list.get(i));
            }
        }
    }

    /**
     *
     * Listar pessoatipo
     *
     * @param PessoaTipo
     */
    public static void listar_Pessoa(PessoaTipo PessoaTipo) {
        ordenar();
        for (int i = 0; i < list.size(); i++) {
            if (PessoaTipo == list.get(i).getPessoaTipo()) {
                System.out.println(list.get(i));
            }
        }
    }

    /**
     *
     * listar todas as pessoas
     *
     */
    public static void listar() {
        ordenar();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /**
     *
     * pesquisar pessoas por nome
     *
     */
    public static void pesquisar_nome(PessoaTipo PessoaTipo) {
        ArrayList<Pessoa> nome = new ArrayList<Pessoa>();

        System.out.println("Insira nome a pesquisar: ");
        String procura = leitor.next();

        for (int x = 0; x < list.size(); x++) {
            Pessoa y = list.get(x);
            if (y.getPessoaTipo() == PessoaTipo) {
                if (y.getNome().toUpperCase().contains(procura.toUpperCase())) {
                    nome.add(y);
                }
            }
        }

        System.out.println("\n\n\n\n\n\n\n");

        System.out.println(nome.size());//numero de ocurrencias

        for (int k = 0; k < nome.size(); k++) {
            System.out.println(nome.get(k));
        }

        if (nome.isEmpty()) {
            System.out.println("Não existe nome corresponde ao procurado!");
        }
    }

    /**
     *
     * pesquisar pessoas por apelido
     *
     */
    public static void pesquisar_apelido(PessoaTipo PessoaTipo) {
        ArrayList<Pessoa> apelido = new ArrayList<Pessoa>();

        System.out.println("Insira um apelido a pesquisar: ");
        String procura = leitor.next();

        for (int x = 0; x < list.size(); x++) {
            Pessoa y = list.get(x);
            if (y.getPessoaTipo() == PessoaTipo) {
                if (y.getApelido().toUpperCase().contains(procura.toUpperCase())) {
                    apelido.add(y);
                }
            }
        }

        System.out.println("\n\n\n\n\n\n\n");

        System.out.println(apelido.size());//numero de ocurrencias

        for (int k = 0; k < apelido.size(); k++) {
            System.out.println(apelido.get(k));
        }

        if (apelido.isEmpty()) {
            System.out.println("Não existe apelido corresponde ao procurado!");
        }
    }

    /**
     *
     * pesquisar pessoas por nif
     *
     */
    public static void pesquisar_nif() {
        ArrayList<Pessoa> Lista_nif = new ArrayList<Pessoa>();
        boolean erro = false;
        String procura = null;

        do {
            try {
                System.out.println("Insira NIF: ");
                procura = leitor.next();
                nif = Long.parseLong(procura);
            } catch (NumberFormatException e) {
                System.out.println("NIF Inexistente");
                erro = true;
            }
        } while (erro);

        for (int x = 0; x < list.size(); x++) {
            Pessoa y = list.get(x);
            String nif_string = String.valueOf(y.getNif());
            if (nif_string.contains(procura)) {
                Lista_nif.add(y);
            }
        }

        System.out.println("\n\n\n\n\n\n\n");

        System.out.println(Lista_nif.size());//numero de ocurrencias

        for (int k = 0; k < Lista_nif.size(); k++) {
            System.out.println(Lista_nif.get(k));
        }

        if (Lista_nif.isEmpty()) {
            System.out.println("Não existe NIF corresponde ao procurado!");
        }
    }

    /**
     *
     * ordenar pessoas por id
     *
     */
    public static void ordenar() {
        Collections.sort(list, new Comparator<Pessoa>() {
            @Override
            public int compare(Pessoa o1, Pessoa o2) {
                if (o1.getID() < o2.getID()) {
                    return -1;
                } else if (o1.getID() > o2.getID()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     *
     * verificar se a pessoa existe para remover
     *
     * @param PessoaTipo
     */
    public static void confirmar_Id(PessoaTipo PessoaTipo) {
        boolean erro = false;
        boolean confirmacao = false;
        String numero;
        do {
            do {
                try {
                    System.out.println("Escolha o ID que quer remover: ");
                    numero = leitor.next();
                    opcao = Long.parseLong(numero);
                } catch (NumberFormatException e) {
                    System.out.println("Numero Incorrecto!");
                    erro = true;
                }
            } while (erro);

            for (int a = 0; a < list.size(); a++) {
                if (opcao == list.get(a).getID()) {
                    confirmacao = false;
                } else {
                    if (PessoaTipo == PessoaTipo.Agente) {
                        System.out.println("Agente inexistente!");
                    } else {
                        System.out.println("Artista inexistente!");
                    }
                    erro = true;
                }
            }

        } while (confirmacao);
    }

    /**
     *
     * pede nome
     *
     */
    public static void nome() {
        System.out.println("Insira nome: ");
        nome = leitor.next();
    }

    /**
     *
     * pede apelido
     *
     */
    public static void apelido() {
        System.out.println("Insira apelido: ");
        apelido = leitor.next();
    }

    /**
     *
     * pede o nif
     *
     */
    public static void nif() {
        boolean erro = false;
        String frase = null;

        do {
            try {
                System.out.println("Insira NIF: ");
                frase = leitor.next();
                validar_Nif(frase);

                if (validar_Nif(frase) == true) {
                    erro = false;
                } else {
                    erro = true;
                    System.out.println("NIF Inexistente");
                }
                nif = Long.parseLong(frase);
            } catch (NumberFormatException e) {
                System.out.println("NIF Inexistente");
                erro = true;
            }

            if (erro == false) {
                for (int i = 0; i < list.size(); i++) {
                    if (nif == list.get(i).getNif()) {
                        System.out.println("NIF ja utilizado!");
                        erro = true;
                    }
                }
            }
        } while (erro);
    }

    /**
     *
     * pede o nome artistico
     *
     */
    public static void nome_artistico() {
        System.out.println("Insira nome artistico: ");
        nome_artistico = leitor.next();
    }

    /**
     *
     * valida o nif
     *
     * @param number
     * @return boolean
     */
    public static boolean validar_Nif(String number) {
        // Nove números obrigatórios...
        if (number.length() != 9) {
            return false;
        } /*
         * else if (number.equals("123456789")) { return false; }
         */

        // Tem que começar com 1, 2, 5, 6, 8 ou 9
        if (!"125689".contains(number.charAt(0) + "")) {
            return false;
        }

        // Ciclo que vai construir o array de inteiros
        int[] numbers = new int[9];
        for (int i = 0; i < 9; i++) {
            numbers[i] = Integer.parseInt(number.charAt(i) + "");
        }

        // A fazer as contas
        float resultado = 0.0f;
        for (int i = 0, j = 9; i < 9; i++, j--) {
            resultado += (j * numbers[i]);
            //System.out.println(resultado + " = " + j + " x " + numbers[i]);
        }

        if ((resultado % 11) != 0.0f) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * confirmar se a pessoa existe
     *
     * @param PessoaTipo
     * @return
     */
    public static long pedir_confirmar_Pessoa(PessoaTipo PessoaTipo) {
        boolean erro = false;
        String frase;

        do {
            do {
                try {
                    if (PessoaTipo.Artista == PessoaTipo) {
                        System.out.println("Insira ID artista: ");
                    } else {
                        System.out.println("Insira ID agente: ");
                    }
                    frase = leitor.next();
                    opcao = Long.parseLong(frase);
                    erro = false;
                } catch (NumberFormatException e) {
                    System.out.println("Insira um numero Inteiro!");
                    erro = true;
                }
            } while (erro);

            erro = true;
            for (int a = 0; a < list.size(); a++) {
                if (PessoaTipo.Artista == PessoaTipo) {
                    if (opcao == WPessoa.list.get(a).getID() && PessoaTipo.Artista == WPessoa.list.get(a).getPessoaTipo()) {
                        erro = false;
                        return opcao;
                    }
                } else {
                    if (opcao == WPessoa.list.get(a).getID() && PessoaTipo.Agente == WPessoa.list.get(a).getPessoaTipo()) {
                        erro = false;
                        return opcao;
                    }
                }

            }
            if (erro == true) {
                if (PessoaTipo.Artista == PessoaTipo) {
                    System.out.println("Artista inexistente!");
                } else {
                    System.out.println("Agente inexistente!");
                }
            }
        } while (erro);
        return opcao;
    }

    /**
     *
     * escreve o ficheiro
     *
     */
    public static void escrever_Pessoa() {
        try {  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.dat.
            FileOutputStream saveFile = new FileOutputStream("Pessoa.dat");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(list);
            save.writeLong(Pessoa.getId_aux_pessoa());

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    /**
     *
     * le o ficheiro
     *
     */
    public static void ler_Pessoa() {
        boolean encontrado = false;

        // Wrap all in a try/catch block to trap I/O errors.
        try {
            // Open file to read from, named SavedObj.dat.
            FileInputStream saveFile = new FileInputStream("Pessoa.dat");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back
            // into their original class type.
            // For primitive types, use the corresponding reference class.
            list = (ArrayList<Pessoa>) save.readObject();
            long x = save.readLong();
            Pessoa.setId_aux_pessoa(x);

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro Pessoa.dat nao encontrado!");
            encontrado = false;
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }
}
