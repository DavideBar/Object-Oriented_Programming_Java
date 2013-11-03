/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Work;

import entidades.Evento;
import entidades.Reparticao;
import interfaces.IAgente;
import interfaces.IArtista;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class WReparticao {

    /**
     * ArrayList com todos os contractos criados
     */
    public static ArrayList<Reparticao> list = new ArrayList<Reparticao>();
    /**
     *
     * @param reparticao_id ID Reparticao
     * @param opcao
     *
     */
    public static long reparticao_id = 0, opcao;
    /**
     *
     * @param tamanho_artistas tamanho do array com os artistista de determinado
     * evento
     *
     */
    public static int tamanho_artistas = 0;
    /**
     *
     * @param receita receita do evento
     * @param percentagem_artistas percentagem da receita para os artistas
     * @param receita_artistas receita dos artistas
     * @param percentagem_por_artista percentagem por artista
     * @param comissao_agente comissao em percentagem
     *
     */
    public static double receita = 0, percentagem_artistas = 0, receita_artistas = 0, percentagem_por_artista = 0, comissao_agente = 50;
    /**
     *
     * @param comissoes array com todas as comissoes
     * @param montante_artista array com os montantes dos artistas de um evento
     *
     */
    public static double[] comissoes, montante_artista;
    /**
     *
     * @param artistas_ids array com os ids dos artistas de um evento
     *
     */
    public static long[] artistas_ids;
    /**
     *
     */
    public static String frase = null;
    /**
     *
     */
    public static Scanner leitor = new Scanner(System.in).useDelimiter("\n");

    /**
     *
     * -pede para colocar uma receita do evento -pergunta qual e a percentagem
     * da receita para os artistas -verifica quantos artistas vão ao evento
     * -dependendo de quantos forem pede a percentagem para cada um
     *
     * @param evento_int
     */
    public static void criar_Reparticao(int evento_int) {
        ler_Reparticao();
        boolean erro = false;
        System.out.println(Reparticao.getId_aux_reparticao());
        Reparticao.setId_aux_reparticao(Reparticao.getId_aux_reparticao() + 1);
        System.out.println(Reparticao.getId_aux_reparticao());
        reparticao_id = Reparticao.getId_aux_reparticao();

        do {
            tamanho_artistas = WEvento.list.get(evento_int).getArtistas().length;//variavel int com o numero de artista do evento
            artistas_ids = WEvento.list.get(evento_int).getArtistas();//ids dos artistas do evento

            do {
                try {
                    System.out.println("Qual e a percentagem da receita total para os Artistas ? ");
                    frase = leitor.next();
                    percentagem_artistas = Double.parseDouble(frase);
                    int a = Integer.parseInt(frase);
                } catch (NumberFormatException e) {
                    System.out.println("Insira um numero!");
                }
                if (percentagem_artistas < 0 || percentagem_artistas > 100) {
                    System.out.println("Insira um numero entre 0 e 100!");
                    erro = true;
                }
            } while (erro);

            receita_artistas = (percentagem_artistas * 0.01) * WEvento.list.get(evento_int).getReceita();//faz as contas em que vai dar o montante para os artistas
            System.out.println("A receita do Evento " + WEvento.list.get(evento_int).getNome_evento() + " e de " + WEvento.list.get(evento_int).getReceita() + "€. O montante para os Artistas e de " + receita_artistas + "€");


            if (tamanho_artistas == 1) {
                System.out.println(tamanho_artistas + " Artista vai ao Evento " + WEvento.list.get(evento_int).getNome_evento());
                comissoes = new double[tamanho_artistas];
            } else if (tamanho_artistas > 1) {
                System.out.println(tamanho_artistas + " Artistas vao ao Evento " + WEvento.list.get(evento_int).getNome_evento());
                comissoes = new double[tamanho_artistas];
            }
            montante_artista = new double[tamanho_artistas];//vai limitar o array ao numero de artistas

            do {
                double percentagem_restante = 100;
                for (int i = 0; i < tamanho_artistas; i++) {
                    if (i == (tamanho_artistas - 1)) {
                        if (percentagem_restante == 0) {
                            erro = true;
                            System.out.println("ERRO!!!");
                            System.out.println("A PERCENTAGEM RESTANTE NAO CHEGA PARA OS RESTANTES ARTISTAS!!!");
                        } else {
                            comissoes[i] = percentagem_restante;
                            System.out.println("Artista numero: " + artistas_ids[i]);
                            System.out.println("Insira a percentagem da receita: \n" + percentagem_restante);
                            percentagem_por_artista = percentagem_restante;
                            percentagem_restante -= percentagem_restante;//para meter a percentagem restante a zeros
                        }
                    } else {
                        try {
                            System.out.println("Percentagem Restante: " + percentagem_restante);
                            System.out.println("Artista numero: " + artistas_ids[i]);
                            System.out.println("Insira a percentagem da receita: ");
                            frase = leitor.next();
                            percentagem_por_artista = Integer.parseInt(frase);
                            percentagem_restante -= percentagem_por_artista;//retira a percentagem restante a percentagem para o artista
                            comissoes[i] = percentagem_por_artista;//adiciona ao array a percentagem escolhida para o artista
                        } catch (NumberFormatException e) {
                            System.out.println("Insira um numero!");
                        }
                    }

                    montante_artista[i] = (percentagem_por_artista * 0.01) * receita_artistas;//dinheiro para o artista

                    divida(evento_int, montante_artista);

                }

                if (percentagem_restante < 0 && percentagem_restante != 0) {
                    System.out.println("\n\n\n\n\n\n\n\nErro!");
                    System.out.println("PERCENTAGEM NEGATIVA!!!");
                    erro = true;
                }
            } while (erro);
        } while (erro);
        list.add(new Reparticao(reparticao_id, WEvento.list.get(evento_int).getEvento_ID(), receita_artistas, percentagem_artistas, comissoes, montante_artista));

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println("Reparticao criada com sucesso!");
        listar_individual(reparticao_id);

        escrever_Reparticao();
    }

    /**
     *
     * -altera uma reparticao
     *
     */
    public static void alterar() {
        WEvento.listar();
        WEvento.confirmar_Id();

        for (int i = 0; i < WEvento.list.size(); i++) {
            if (WEvento.list.get(i).getEvento_ID() == opcao) {
                for (int j = 0; j < list.size(); j++) {
                    if (opcao == list.get(j).getEvento_id()) {
                        list.remove(j);
                        criar_Reparticao(j);
                        break;
                    }
                }
            }
        }
    }

    /**
     *
     * -remove a reparticao e pede para criar uma nova logo de seguida
     *
     */
    public static void remover() {
        WEvento.listar();
        WEvento.confirmar_Id();

        for (int i = 0; i < WEvento.list.size(); i++) {
            if (WEvento.list.get(i).getEvento_ID() == opcao) {
                for (int j = 0; j < list.size(); j++) {
                    if (opcao == list.get(j).getEvento_id()) {
                        list.remove(j);
                        criar_Reparticao(j);
                        break;
                    }
                }
            }
        }
    }

    /**
     *
     * -lista reparticao a partir da opcao
     *
     * @param opcao
     */
    public static void listar_individual(long opcao) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEvento_id() == opcao) {
                System.out.println("REPARTICAO:");
                System.out.println(WReparticao.list.get(i));
            }
        }
    }

    /**
     *
     * -vê se o evento esta pago -se não estiver pago, vê quantos artistas e
     * quantos agentes estão no evento -vai buscar o dinheiro dos artistas que
     * esta na repartição -paga aos artistas e aos agentes -retira divida das
     * pessoas
     *
     * Faz o pagamento as pessoas
     */
    public static void pagamento_Main(int k) {
        double[] montante_artista = null;
        for (int i = 0; i < WEvento.list.get(k).getArtistas().length; i++) {
            for (int h = 0; h < list.size(); h++) {
                if (WEvento.list.get(k).getEvento_ID() == list.get(h).getEvento_id()) {
                    for (int y = 0; y < list.get(h).getComissoes().length; y++) {
                        double[] percentagem = list.get(h).getComissoes();
                        receita_artistas = (list.get(h).getPercentagem_artistas() * 0.01) * WEvento.list.get(k).getReceita();
                        montante_artista = new double[list.get(h).getComissoes().length];
                        for (int p = 0; p < percentagem.length; p++) {
                            montante_artista[y] += (percentagem[p] * 0.01) * receita_artistas;
                        }
                    }
                    break;
                }
            }
            divida(k, montante_artista);

            long[] artistas = WEvento.list.get(k).getArtistas();
            long[] agentes = WEvento.list.get(k).getAgentes();

            double pagar_agente = montante_artista[i] * (comissao_agente * 0.01);
            double pagar_artista = montante_artista[i] - pagar_agente;

            for (int j = 0; j < WPessoa.list.size(); j++) {
                if (artistas[i] == WPessoa.list.get(j).getID()) {
                    IArtista Artista = (IArtista) WPessoa.list.get(j);
                    double bruto = Artista.getMontante_devido_bruto_artista() - pagar_artista;
                    double liquido = Artista.getMontante_devido_liquido_artista() - (pagar_artista * 0.77);
                    Artista.setMontante_devido_bruto_artista(bruto);
                    Artista.setMontante_devido_liquido_artista(liquido);
                    if (agentes[i] == 0) {
                        WPessoa.list.get(j).setCapital(WPessoa.list.get(j).getCapital() + pagar_artista + pagar_agente);
                    } else {
                        WPessoa.list.get(j).setCapital(WPessoa.list.get(j).getCapital() + pagar_artista);
                    }
                }
                if (agentes[i] == WPessoa.list.get(j).getID()) {
                    IAgente Agente = (IAgente) WPessoa.list.get(j);
                    double bruto = Agente.getMontante_Devido_Bruto_Agente() - pagar_agente;
                    double liquido = Agente.getMontante_Devido_Liquido_Agente() - (pagar_agente * 0.77);
                    Agente.setMontante_Devido_Bruto_Agente(bruto);
                    Agente.setMontante_Devido_Liquido_Agente(liquido);
                    WPessoa.list.get(j).setCapital(WPessoa.list.get(j).getCapital() + pagar_agente);
                }
            }
        }
        WEvento.list.get(k).setPago(true);
        WEvento.escrever_Evento();
        WReparticao.escrever_Reparticao();
        WPessoa.escrever_Pessoa();
    }

    /**
     *
     * -mete divida nas pessoas
     *
     */
    public static void divida(int i, double[] montante_artista) {
        for (int x = 0; x < WEvento.list.get(i).getArtistas().length; x++) {

            long[] artistas = WEvento.list.get(i).getArtistas();
            long[] agentes = WEvento.list.get(i).getAgentes();

            double pagar_agente = montante_artista[x] * (comissao_agente * 0.01);
            double pagar_artista = montante_artista[x] - pagar_agente;

            for (int j = 0; j < WPessoa.list.size(); j++) {
                if (artistas[x] == WPessoa.list.get(j).getID()) {
                    IArtista Artista = (IArtista) WPessoa.list.get(j);
                    double bruto = Artista.getMontante_devido_bruto_artista() + pagar_artista;
                    double liquido = Artista.getMontante_devido_liquido_artista() + (pagar_artista * 0.77);
                    Artista.setMontante_devido_bruto_artista(bruto);
                    Artista.setMontante_devido_liquido_artista(liquido);
                }
                if (agentes[x] == WPessoa.list.get(j).getID()) {
                    IAgente Agente = (IAgente) WPessoa.list.get(j);
                    double bruto = Agente.getMontante_Devido_Bruto_Agente() + pagar_agente;
                    double liquido = Agente.getMontante_Devido_Liquido_Agente() + (pagar_agente * 0.77);
                    Agente.setMontante_Devido_Bruto_Agente(bruto);
                    Agente.setMontante_Devido_Liquido_Agente(liquido);
                }
            }
        }
        WEvento.escrever_Evento();
        WReparticao.escrever_Reparticao();
        WPessoa.escrever_Pessoa();
    }

    public static void verificar_pagamentos() {
        ArrayList<Evento> pagar = new ArrayList<Evento>();
        Date hoje = new Date();
        long evento_id = 0;
        double receita = 0;
        int z = -1;
        int count = 0;
        for (int i = 0; i < WEvento.list.size(); i++) {
            if (WEvento.list.get(i).getPago() == false) {
                if (hoje.after(WEvento.list.get(i).getData())) {
                    pagar.add(WEvento.list.get(i));
                    count++;
                }
            }
        }
        do {
            if (pagar.isEmpty()) {
                System.out.println("Nao existe nenhum evento por pagar!");
            } else {
                for (int i = 0; i < WEvento.list.size(); i++) {
                    if (WEvento.list.get(i).getPago() == false) {
                        if (hoje.after(WEvento.list.get(i).getData())) {
                            System.out.println(WEvento.list.get(i));
                        }
                    }
                }
                System.out.println("Deseja pagar algum evento ?");
                String frase = leitor.next();
                if (frase.equalsIgnoreCase("sim")) {
                    try {
                        System.out.println("Qual o ID do evento ?");
                        frase = leitor.next();
                        evento_id = Long.parseLong(frase);
                    } catch (NumberFormatException e) {
                        System.out.println("Insira um numero!");
                    }
                    for (z = 0; z < WEvento.list.size(); z++) {
                        if (WEvento.list.get(z).getEvento_ID() == evento_id) {
                            break;
                        }
                    }
                    if (z != -1) {
                        try {
                            System.out.println("Insira a receita do Evento " + WEvento.list.get(z).getNome_evento() + ": ");
                            frase = leitor.next();
                            receita = Double.parseDouble(frase);
                        } catch (NumberFormatException e) {
                            System.out.println("Insira um numero!");
                        }
                        WEvento.list.get(z).setReceita(receita);
                        WEvento.escrever_Evento();
                        WReparticao.ler_Reparticao();
                        WReparticao.pagamento_Main(z);
                        WReparticao.escrever_Reparticao();
                        WEvento.escrever_Evento();
                        pagar.remove(z);
                    }
                }
                count--;
            }
            if (count != 0) {
                if (count == 1) {
                    System.out.println("Ainda falta pagar mais " + count + " evento!");
                } else {
                    System.out.println("Ainda faltam pagar mais " + count + " eventos!");
                }
            }
        } while (count != 0);
    }

    /**
     *
     * -escreve para o ficheiro
     *
     */
    public static void escrever_Reparticao() {
        try {  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.dat.
            FileOutputStream saveFile = new FileOutputStream("Reparticao.dat");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            // Now we do the save.
            save.writeObject(list);
            save.writeLong(Reparticao.getId_aux_reparticao());

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    /**
     *
     * -ler do ficheiro
     *
     */
    public static void ler_Reparticao() {

        // Wrap all in a try/catch block to trap I/O errors.
        try {
            // Open file to read from, named SavedObj.dat.
            FileInputStream saveFile = new FileInputStream("Reparticao.dat");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back
            // into their original class type.
            // For primitive types, use the corresponding reference class.
            list = (ArrayList<Reparticao>) save.readObject();
            long x = save.readLong();
            Reparticao.setId_aux_reparticao(x);

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro Reparticao.dat nao encontrado!");
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }
}