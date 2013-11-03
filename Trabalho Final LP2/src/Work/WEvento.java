/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Work;

import entidades.Evento;
import enums.PessoaTipo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
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
public class WEvento {

    /**
     * 
     */
    public static ArrayList<Evento> list = new ArrayList<Evento>();
    /**
     * 
     * @param artistas ids dos artistas
     * @param agentes ids dos agentes
     *
     */
    public static long[] artistas = null, agentes = null;
    /**
     *
     * @param evento_id id do evento
     * @param artista_id id do artista
     * @param agente_id id do agente
     * @param opcao
     * @param numero
     * 
     */
    public static long evento_id = 0, artista_id = 0, agente_id = 0, opcao, numero = 0;
    /**
     *
     * @param receita receita do evento
     * @param comissao_agente 50%
     * 
     */
    public static double receita = 0, comissao_agente = 50;
    /**
     *
     * @param nome_evento nome do evento
     * @param frase
     */
    public static String nome_evento = null, frase = null;
    /**
     *
     * @param data_evento data do evento
     * @param data_actual data de hoje
     * 
     */
    public static Date data_evento = null, data_actual = new Date();
    /**
     *
     */
    public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    /**
     *
     */
    public static Scanner leitor = new Scanner(System.in).useDelimiter("\n");

    /**
     * 
     * cria um evente e de seguida uma reparticao
     *
     */
    public static void criar_Evento() {
        boolean erro = false;
        System.out.println(Evento.getId_aux_evento());
        Evento.setId_aux_evento(Evento.getId_aux_evento() + 1);
        evento_id = Evento.getId_aux_evento();

        nome_evento();
        WPessoa.listar_Pessoa(PessoaTipo.Artista);

        do {
            try {
                System.out.println("Quantos artistas vao ao Evento " + nome_evento + " ?");
                frase = leitor.next();
                numero = Long.parseLong(frase);
                erro = false;
            } catch (NumberFormatException e) {
                System.out.println("Insira uma numero!");
                erro = true;
            }

            int count = 0;
            for (int i = 0; i < WPessoa.list.size(); i++) {
                if (PessoaTipo.Artista == WPessoa.list.get(i).getPessoaTipo()) {
                    count++;
                }
            }
            if (numero > count) {
                erro = true;
            }

        } while (erro);

        artistas = new long[Integer.parseInt(frase)];
        agentes = new long[Integer.parseInt(frase)];


        if (verificar_lista() == true) {
            for (int i = 0; i < numero; i++) {
                opcao = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Artista);
                artistas[i] = opcao;
                for (int j = 0; j < WContracto.list.size(); j++) {
                    if (artistas[i] == WContracto.list.get(j).getArtista_Id()) {
                        agentes[i] = WContracto.list.get(j).getAgente_Id();
                    }
                }
            }
            data_evento();

            list.add(new Evento(evento_id, nome_evento, artistas, agentes, data_evento, receita, false));

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Evento criado com sucesso!");

            int evento_id_real = list.size() - 1;
            WReparticao.criar_Reparticao(evento_id_real);

            for (int j = 0; j < list.size(); j++) {
                for (int i = 0; i < WReparticao.list.size(); i++) {
                    if (list.get(j).getEvento_ID() == WReparticao.list.get(i).getEvento_id()) {
                        list.get(j).setReceita(WReparticao.receita);
                    }
                }
            }

            escrever_Evento();
            listar_individual(evento_id);
        }
    }

    /**
     * 
     * altera um evento
     *
     */
    public static void alterar_Evento() {
        boolean erro = false;
        listar();
        confirmar_Id();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEvento_ID() == opcao) {
                list.remove(i);
                for (int j = 0; j < WReparticao.list.size(); j++) {
                    if (opcao == WReparticao.list.get(j).getEvento_id()) {
                        WReparticao.list.remove(j);
                        WReparticao.escrever_Reparticao();
                    }
                }

                nome_evento();

                do {
                    try {
                        System.out.println("Quantos artistas vao ao Evento " + nome_evento);
                        frase = leitor.next();
                        numero = Long.parseLong(frase);
                        erro = false;
                    } catch (NumberFormatException e) {
                        System.out.println("Insira uma numero!");
                        erro = true;
                    }
                } while (erro);

                artistas = new long[Integer.parseInt(frase)];
                agentes = new long[Integer.parseInt(frase)];

                if (verificar_lista() == true) {
                    for (int k = 0; k < numero; k++) {
                        long opcao2 = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Artista);
                        artistas[k] = opcao2;
                        for (int j = 0; j < WContracto.list.size(); j++) {
                            if (artistas[k] == WContracto.list.get(j).getArtista_Id()) {
                                agentes[k] = WContracto.list.get(j).getAgente_Id();
                            }
                        }
                    }

                    data_evento();

                    list.add(new Evento(opcao, nome_evento, artistas, agentes, data_evento, receita, false));

                    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.out.println("Evento alterado com sucesso!");


                    WReparticao.criar_Reparticao(i);

                    for (int j = 0; j < list.size(); j++) {
                        for (int h = 0; h < WReparticao.list.size(); h++) {
                            if (list.get(j).getEvento_ID() == WReparticao.list.get(h).getEvento_id()) {
                                list.get(h).setReceita(WReparticao.receita);
                            }
                        }
                    }

                    escrever_Evento();
                    listar_individual(evento_id);
                }
            }
        }
    }

    /**
     * 
     * altera campo individual de um evento
     *
     * @param opcao2
     */
    public static void alterar_Evento_individual(int opcao2) {
        boolean erro = false;
        listar();
        confirmar_Id();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEvento_ID() == opcao) {
                if (opcao2 == 1) {
                    System.out.println("Evento ID: " + list.get(i).getEvento_ID() + "Nome Evento: " + list.get(i).getNome_evento());
                    nome_evento();
                    list.get(i).setNome_evento(nome_evento);
                }
                if (opcao2 == 2) {
                    System.out.println("Numero de Artistas: " + list.get(i).getArtistas().length);
                    do {
                        try {
                            System.out.println("Quantos artistas vao ao Evento " + list.get(i).getNome_evento());
                            frase = leitor.next();
                            numero = Long.parseLong(frase);
                            erro = false;
                        } catch (NumberFormatException e) {
                            System.out.println("Insira uma numero!");
                            erro = true;
                        }
                    } while (erro);

                    long[] artistas = new long[Integer.parseInt(frase)];
                    long[] agentes = new long[Integer.parseInt(frase)];

                    if (verificar_lista() == true) {
                        for (int k = 0; k < numero; k++) {
                            long opcao = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Artista);
                            artistas[k] = opcao;
                            for (int j = 0; j < WContracto.list.size(); j++) {
                                if (artistas[k] == WContracto.list.get(j).getArtista_Id()) {
                                    agentes[k] = WContracto.list.get(j).getAgente_Id();
                                }
                            }
                        }
                        list.get(i).setArtistas(artistas);
                        list.get(i).setAgentes(agentes);

                        for (int h = 0; h < WReparticao.list.size(); h++) {
                            if (list.get(i).getEvento_ID() == WReparticao.list.get(h).getEvento_id()) {
                                WReparticao.list.remove(h);
                                WReparticao.escrever_Reparticao();
                            }
                        }
                        WReparticao.ler_Reparticao();
                        WReparticao.criar_Reparticao(i);
                    }
                }
                if (opcao2 == 3) {
                    System.out.println("Data do Evento: " + df.format(list.get(i).getData()));
                    data_evento();;
                    list.get(i).setData(data_evento);
                }
            }
        }
    }

    /**
     * 
     * remove um evento e uma reparticao
     *
     */
    public static void remover() {
        listar();
        confirmar_Id();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEvento_ID() == opcao) {
                for (int j = 0; j < WReparticao.list.size(); j++) {
                    if (list.get(i).getEvento_ID() == WReparticao.list.get(j).getEvento_id()) {
                        WReparticao.list.remove(j);
                    }
                }
                list.remove(i);
                System.out.println("Evento numero: " + opcao + " e respectiva reparticao, removidos com sucesso!");
            }
        }
    }

    /**
     * 
     * lista uma evento com uma reparticao
     *
     */
    public static void listar() {
        ordenar();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("EVENTO:");
            System.out.println(list.get(i));
            for (int j = 0; j < WReparticao.list.size(); j++) {
                if (!WReparticao.list.isEmpty()) {
                    if (list.get(i).getEvento_ID() == WReparticao.list.get(j).getEvento_id()) {
                        System.out.println("REPARTICAO:");
                        System.out.println(WReparticao.list.get(j));
                    }
                }
            }
        }
    }

    /**
     * 
     * lista evento individual
     *
     * @param opcao
     */
    public static void listar_individual(long opcao) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEvento_ID() == opcao) {
                System.out.println("EVENTO:");
                System.out.println(list.get(i));
                for (int j = 0; j < WReparticao.list.size(); j++) {
                    if (!WReparticao.list.isEmpty()) {
                        if (list.get(i).getEvento_ID() == WReparticao.list.get(j).getEvento_id()) {
                            System.out.println("REPARTICAO:");
                            System.out.println(WReparticao.list.get(j));
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 
     * ordena os eventos por ordem do id
     *
     */
    public static void ordenar() {
        Collections.sort(list, new Comparator<Evento>() {

            @Override
            public int compare(Evento o1, Evento o2) {
                if (o1.getEvento_ID() < o2.getEvento_ID()) {
                    return -1;
                } else if (o1.getEvento_ID() > o2.getEvento_ID()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * 
     * confirma se o evento existe
     *
     */
    public static void confirmar_Id() {
        boolean erro = false;
        String numero;
        do {
            do {
                try {
                    System.out.println("Insira ID Evento: ");
                    numero = leitor.next();
                    opcao = Long.parseLong(numero);
                    erro = false;
                } catch (NumberFormatException e) {
                    System.out.println("Numero Incorrecto!");
                    erro = true;
                }
            } while (erro);

            for (int a = 0; a < list.size(); a++) {
                if (opcao != list.get(a).getEvento_ID()) {
                    erro = true;
                } else {
                    erro = false;
                }
            }

            if (erro == true) {
                System.out.println("Evento inexistente!");
            }
        } while (erro);
    }

    /**
     * 
     * nome do evento
     *
     */
    public static void nome_evento() {
        System.out.println("Insira o Nome do evento: ");
        nome_evento = leitor.next();
    }

    /**
     * 
     * verifica se a lista pessoa esta vazia
     *
     * @return
     */
    public static boolean verificar_lista() {
        boolean verificar = false;

        if (!WPessoa.list.isEmpty()) {
            for (int a = 0; a < WPessoa.list.size(); a++) {
                if (PessoaTipo.Artista == WPessoa.list.get(a).getPessoaTipo()) {
                    verificar = true;
                    return verificar;
                }
            }
        } else {
            System.out.println("A lista Pessoa esta fazia!");
            verificar = false;
            return verificar;
        }

        System.out.println("Não existe nenhum artista!");

        return verificar;
    }

    /**
     * 
     * data do evento
     *
     */
    public static void data_evento() {
        boolean erro = false;

        do {
            System.out.println("Insira a data do Evento no formato dd/mm/aaaa: ");
            frase = leitor.next();

            try {
                data_evento = df.parse(frase);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (data_actual.after(data_evento) == true && df.format(data_evento).equals(df.format(data_actual)) == false) {
                System.out.println("Data Inserida e antes da Data Actual!");
                erro = true;
            }
            if (df.format(data_evento).equals(df.format(data_actual)) == true) {
                System.out.println("Data Inserida e igual a Data Actual!");
                erro = true;
            }
            if (data_actual.before(data_evento) == true) {
                System.out.println("Data Inserida e depois da Data Actual");
                erro = false;
            }
        } while (erro);
    }

    /**
     * 
     * escreve no ficheiro
     *
     */
    public static void escrever_Evento() {
        try {  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.dat.
            FileOutputStream saveFile = new FileOutputStream("Evento.dat");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(list);
            save.writeLong(Evento.getId_aux_evento());

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    /**
     * 
     * le do ficheiro
     *
     */
    public static void ler_Evento() {

        // Wrap all in a try/catch block to trap I/O errors.
        try {
            // Open file to read from, named SavedObj.dat.
            FileInputStream saveFile = new FileInputStream("Evento.dat");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back
            // into their original class type.
            // For primitive types, use the corresponding reference class.
            list = (ArrayList<Evento>) save.readObject();
            long x = save.readLong();
            Evento.setId_aux_evento(x);

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro Evento.dat nao encontrado!");
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }
}
