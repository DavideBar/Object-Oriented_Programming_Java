/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Work;

import entidades.Contracto;
import entidades.Pessoa;
import enums.PessoaTipo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class WContracto {

    /**
     * ArrayList com todos os contractos criados
     */
    public static ArrayList<Contracto> list = new ArrayList<Contracto>();
    /**
     * Copia do Arraylist list mas sem os contractos apagados
     */
    public static ArrayList<Contracto> historico = new ArrayList<Contracto>();
    /**
     *
     * @param contracto_id ID do contracto
     * @param artista_id ID do artista do contracto
     * @param agente_id ID do agente do contracto
     * @param opcao Long com as opcoes escolhidas pelo utilizador
     *
     */
    public static long contracto_id = 0, artista_id = 0, agente_id = 0, opcao = -1;
    /**
     * @param data_inicial Data Inicial dos contractos(Data Actual)
     */
    public static Date data_inicial = new Date();
    /**
     * @param data_final Data final do contracto
     */
    public static Date data_final;
    /**
     * @param obs Observoes introduzidas pelo utilizador
     */
    public static String obs = null;
    /**
     * @param df Formato da data
     */
    public static SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    /**
     *
     */
    public static Scanner leitor = new Scanner(System.in).useDelimiter("\n");

    /**
     *
     * Cria um Contracto
     *
     */
    public static void criar_Contracto() {
        System.out.println(Contracto.getId_aux_contracto());
        Contracto.setId_aux_contracto(Contracto.getId_aux_contracto() + 1);//Vai incrementar 1 o Id Aux
        contracto_id = Contracto.getId_aux_contracto();//Actualiza o contracto Id

        Date data_actual = new Date();// Data Actual

        WPessoa.listar_Pessoa(PessoaTipo.Artista);

        artista_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Artista);//Pede um artista ao utilizador e confirma se existe

        WPessoa.listar_Pessoa(PessoaTipo.Agente);

        if (list.isEmpty()) {//Verifica se o ArrayList Contracto esta vazio
            agente_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Agente);//Pede um agente ao utilizador e confirma se existe
            data_actual();//Carrega a data actual
            data_final();//Pede ao utilizador a data final do contracto

            System.out.println("Insira uma Observacao: ");
            obs = leitor.next();

            list.add(new Contracto(contracto_id, artista_id, agente_id, data_inicial, data_final, obs));//Adiciona a list um novo contracto
            historico.add(new Contracto(contracto_id, artista_id, agente_id, data_inicial, data_final, obs));//Adiciona a historico um novo contracto

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Contracto criado com sucesso!");
            listar_individual(contracto_id);//Lista o contracto que se acabou de criar
            escrever_Contracto();//escreve para o ficheiro
        } else {
            for (int i = 0; i < list.size(); i++) {//percorre lista contracto
                if (list.get(i).getArtista_Id() == artista_id) {//verifica se o id do artista que escolhemos já existe em algum contracto
                    if (list.get(i).getData_Final().before(data_actual)) {//verifica se a data final do contracto é antes da data actual
                        continue;
                    } else {
                        System.out.println("O Artista " + list.get(i).getArtista_Id() + " ja tem um contracto pendente!");
                        break;
                    }
                }
                agente_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Agente);//Pede um agente ao utilizador e confirma se existe
                data_actual();//Carrega a data actual
                data_final();//Pede ao utilizador a data final do contracto

                System.out.println("Insira uma Observacao: ");
                obs = leitor.next();

                list.add(new Contracto(contracto_id, artista_id, agente_id, data_inicial, data_final, obs));//Adiciona a list um novo contracto
                historico.add(new Contracto(contracto_id, artista_id, agente_id, data_inicial, data_final, obs));//Adiciona a historico um novo contracto

                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                System.out.println("Contracto criado com sucesso!");
                listar_individual(contracto_id);//Lista o contracto que se acabou de criar
                escrever_Contracto();//escreve para o ficheiro


            }
        }
    }

    /**
     * Altera um Contracto
     */
    public static void alterar_Contracto() {
        listar();//Lista todos os contractos
        confirmar_Id();//verifica se esse contracto existe

        for (int i = 0; i < list.size(); i++) {//percorre a lista dos contractos
            if (list.get(i).getContracto_Id() == opcao) {//verifica se existe algum contracto com essa opcao
                list.remove(i);//remove um contracto
                for (int j = 0; j < historico.size(); j++) {
                    if (historico.get(j).getContracto_Id() == opcao) {
                        historico.remove(j);//remove um historico
                    }
                }
            }
        }

        Date data_actual = new Date();
        artista_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Artista);//Pede um artista ao utilizador e confirma se existe
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).getArtista_Id() == artista_id) {//verifica se o id do artista que escolhemos já existe em algum contracto
                if (list.get(k).getData_Final().before(data_actual)) {//verifica se a data final do contracto é antes da data actual
                    continue;
                } else {
                    System.out.println("O Artista " + list.get(k).getArtista_Id() + " ja tem um contracto pendente!");
                    break;
                }
            }
        }
        agente_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Agente);//Pede um agente ao utilizador e confirma se existe
        data_actual();//Carrega a data actual
        data_final();//Pede ao utilizador a data final do contracto
        obs();

        list.add(new Contracto(opcao, artista_id, agente_id, data_inicial, data_final, obs));//Adiciona a list um novo contracto
        historico.add(new Contracto(opcao, artista_id, agente_id, data_inicial, data_final, obs));//Adiciona a historico um novo contracto

        escrever_Contracto();//escreve para o ficheiro
    }

    /**
     * Altera um campo do Contracto
     *
     * @param opcao2
     */
    public static void alterar_Contracto_individual(int opcao2) {
        listar();//Lista todos os contractos
        confirmar_Id();//verifica se esse contracto existe

        for (int i = 0; i < list.size(); i++) {//percorre lista contractos
            if (list.get(i).getContracto_Id() == opcao) {//verifica se existe algum contracto id igual a opcao

                if (opcao2 == 1) {
                    Date data_actual = new Date();
                    System.out.println("ID Artista: " + list.get(i).getArtista_Id() + "Nome Artista: " + WPessoa.list.get(i).getNome());
                    artista_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Artista);//Pede um artista ao utilizador e confirma se existe
                    if (list.get(i).getArtista_Id() == artista_id) {//verifica se o id do artista que escolhemos já existe em algum contracto
                        if (list.get(i).getData_Final().before(data_actual)) {//verifica se a data final do contracto é antes da data actual
                            continue;
                        } else {
                            System.out.println("O Artista " + list.get(i).getArtista_Id() + " ja tem um contracto pendente!");
                            break;
                        }
                    }

                    list.get(i).setArtista_Id(artista_id);//faz set do id do artista
                    for (int h = 0; h < historico.size(); h++) {//percorre a lista historico
                        if (historico.get(h).getContracto_Id() == opcao) {
                            historico.get(h).setArtista_Id(artista_id);//faz set do id do artista
                        }
                    }
                }
                if (opcao2 == 2) {
                    System.out.println("ID Agente: " + list.get(i).getAgente_Id() + "Nome Agente: " + WPessoa.list.get(i).getNome());
                    agente_id = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Agente);//Pede um agente ao utilizador e confirma se existe
                    list.get(i).setAgente_Id(agente_id);//faz set do id do agente
                    for (int h = 0; h < historico.size(); h++) {//percorre a lista historico
                        if (historico.get(h).getContracto_Id() == opcao) {
                            historico.get(h).setAgente_Id(agente_id);//faz set do id do agente
                        }
                    }
                }
                if (opcao2 == 3) {
                    System.out.println("Data do Contracto: " + list.get(i).getData_Final());
                    data_final();//Pede ao utilizador a data final do contracto
                    list.get(i).setData_Final(data_final);//faz set da data
                    for (int h = 0; h < historico.size(); h++) {//percorre a lista historico
                        if (historico.get(h).getContracto_Id() == opcao) {
                            historico.get(h).setData_Final(data_final);//faz set da data
                        }
                    }
                }
                if (opcao2 == 4) {
                    System.out.println("Observacoes Antigas: " + list.get(i).getObs());
                    obs();
                    list.get(i).setObs(obs);//faz set da obs
                    for (int h = 0; h < historico.size(); h++) {//percorre a lista historico
                        if (historico.get(h).getContracto_Id() == opcao) {
                            historico.get(h).setObs(obs);//faz set da obs
                        }
                    }
                }
            }
        }
    }

    /**
     * Remove Contracto
     */
    public static void remover() {
        listar();//Lista todos os contractos
        confirmar_Id();//verifica se esse contracto existe

        for (int i = 0; i < list.size(); i++) {//percorre a list dos contractos
            if (list.get(i).getContracto_Id() == opcao) {//verifica se a opcao corresponde a algum contracto
                if (list.get(i).getData_Final().after(data_inicial)) {//verfica se a data final do contracto e depois da actual
                    System.out.println("Este contracto ainda não acabou!");
                } else {
                    list.remove(i);//remove o contracto da lista
                    System.out.println("Contracto removido com sucesso!");
                    break;
                }
            }
        }
    }

    /**
     * Lista Contractos
     */
    public static void listar() {
        ordenar();//ordena a lista
        for (int a = 0; a < list.size(); a++) {//percorre a lista contractos
            System.out.println(list.get(a));//imprime todos os contractos
        }
    }

    /**
     * Lista todos os artistas agenciados por uma agente numa data
     */
    public static void listar_artista_agente_data() {
        boolean erro = false;
        Date data_pesquisar = null;

        long agente = WPessoa.pedir_confirmar_Pessoa(PessoaTipo.Agente);//Pede um agente ao utilizador e confirma se existe

        do {
            try {
                System.out.println("Insira a data no formato dd/mm/aaaa: ");
                String frase = leitor.next();
                data_pesquisar = df.parse(frase);//Converte String em data
            } catch (ParseException e) {
                e.printStackTrace();
                erro = true;
            }
        } while (erro);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAgente_Id() == agente) {
                if (data_pesquisar.after(list.get(i).getData_Inicial()) && data_pesquisar.before(list.get(i).getData_Final())) {
                    for (int j = 0; j < WPessoa.list.size(); j++) {
                        if (WPessoa.list.get(j).getID() == list.get(i).getArtista_Id()) {
                            System.out.println(WPessoa.list.get(j));
                        }
                    }
                }
            }
        }
    }

    /**
     * Listar Individual
     *
     * @param opcao
     */
    public static void listar_individual(long opcao) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getContracto_Id() == opcao) {
                System.out.println(list.get(i));
            }
        }
    }

    /**
     * Pesquisar contractos por nome
     */
    public static void pesquisar_nome_contracto(PessoaTipo PessoaTipo) {
        ler_Contracto();
        ArrayList<Pessoa> nome = new ArrayList<Pessoa>();
        ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
        ArrayList<Contracto> total = new ArrayList<Contracto>();

        for (int x = 0; x < historico.size(); x++) {
            for (int i = 0; i < WPessoa.list.size(); i++) {
                if (historico.get(x).getArtista_Id() == WPessoa.list.get(i).getID() && PessoaTipo.Artista == WPessoa.list.get(i).getPessoaTipo()) {
                    nome.add(WPessoa.list.get(i));//adiciona a lista nome artistas com contracto
                }
                if (historico.get(x).getAgente_Id() == WPessoa.list.get(i).getID() && PessoaTipo.Agente == WPessoa.list.get(i).getPessoaTipo()) {
                    nome.add(WPessoa.list.get(i));//adiciona a lista nome agentes com contracto
                }
            }
        }
        System.out.println(nome.size());//numero de ocurrencias

        if (PessoaTipo.Artista == PessoaTipo) {
            System.out.println("Insira nome de Artista a pesquisa: ");
        } else {
            System.out.println("Insira nome de Agente a pesquisa: ");
        }

        String procura = leitor.next();//le o nome do teclado

        for (int j = 0; j < nome.size(); j++) {
            Pessoa y = nome.get(j);
            if (y.getNome().toUpperCase().contains(procura.toUpperCase())) {
                lista.add(y);
            }
        }
        System.out.println(lista.size());//numero de ocurrencias

        String pessoa_id;

        for (int x = 0; x < historico.size(); x++) {
            Contracto y = historico.get(x);
            if (PessoaTipo.Artista == PessoaTipo) {
                pessoa_id = String.valueOf(y.getArtista_Id());
            } else {
                pessoa_id = String.valueOf(y.getAgente_Id());
            }
            for (int k = 0; k < lista.size(); k++) {
                String id = String.valueOf(lista.get(k).getID());
                if (pessoa_id.toUpperCase().contains(id)) {
                    total.add(y);
                }
            }
        }
        System.out.println("\n\n\n\n\n\n\n");

        System.out.println(total.size());//numero de ocurrencias

        for (int k = 0; k < total.size(); k++) {
            System.out.println(total.get(k));
        }

        if (total.isEmpty()) {
            System.out.println("Não artista corresponde ao procurado!");
        }
    }

    /**
     * Ordenar a lista por id de contracto
     */
    public static void ordenar() {
        Collections.sort(list, new Comparator<Contracto>() {
            @Override
            public int compare(Contracto o1, Contracto o2) {
                if (o1.getContracto_Id() < o2.getContracto_Id()) {
                    return -1;
                } else if (o1.getContracto_Id() > o2.getContracto_Id()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * confirma se o contracto existe
     */
    public static void confirmar_Id() {
        boolean erro = false;
        String numero;
        do {
            do {
                try {
                    System.out.println("Insira ID Contracto: ");
                    numero = leitor.next();
                    opcao = Long.parseLong(numero);
                } catch (NumberFormatException e) {
                    System.out.println("Numero Incorrecto!");
                    erro = true;
                }
            } while (erro);


            for (int a = 0; a < list.size(); a++) {
                if (opcao == list.get(a).getContracto_Id()) {
                    erro = false;
                } else {
                    System.out.println("Contracto inexistente!");
                    erro = true;
                }
            }
        } while (erro);
    }

    /**
     * Data Actual
     */
    public static void data_actual() {
        data_inicial = new Date();
        String data_actual = df.format(data_inicial);
    }

    /**
     * Pede quantos anos tem o contracto
     */
    public static void data_final() {
        boolean erro = false;
        String frase;
        int ano = 0;
        do {
            try {
                System.out.println("Quantos anos de contracto ?");
                frase = leitor.next();
                ano = Integer.parseInt(frase);
                if (ano < 0) {
                    System.out.println("Ano inferior a zero!");
                    erro = true;
                }
                if (ano == 0) {
                    System.out.println("Ano igual a zero!");
                    erro = true;
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Insira um numero Inteiro!");
                erro = true;
            }
        } while (erro);

        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(data_inicial);
        gc.add(Calendar.YEAR, ano);
        data_final = gc.getTime();

        System.out.println("Data final do contratcto: " + df.format(data_final));
    }

    /**
     * Insere observacoes
     */
    public static void obs() {
        System.out.println("Insira uma Observacao: ");
        obs = leitor.next();
    }

    /**
     * Verifica se ha contractos expirados
     */
    public static void verificar_contractos() {
        Date hoje = new Date();
        if (!WContracto.list.isEmpty()) {
            for (int x = 0; x < WContracto.list.size(); x++) {
                if (hoje.after(WContracto.list.get(x).getData_Final())) {
                    System.out.println("Contracto eliminado");
                    System.out.println("Contracto numero: " + WContracto.list.get(x).getContracto_Id() + " expirou em " + WEvento.df.format(WContracto.list.get(x).getData_Final()));
                    WContracto.list.remove(x);
                    WContracto.escrever_Contracto();
                }
            }
        }
    }

    /**
     * Escreve no ficheiro
     */
    public static void escrever_Contracto() {
        try {  // Catch errors in I/O if necessary.
            // Open a file to write to, named SavedObj.dat.
            FileOutputStream saveFile = new FileOutputStream("Contracto.dat");

            FileOutputStream saveFileH = new FileOutputStream("Contracto_Hist.dat");

            // Create an ObjectOutputStream to put objects into save file.
            ObjectOutputStream save = new ObjectOutputStream(saveFile);

            ObjectOutputStream saveH = new ObjectOutputStream(saveFileH);

            // Now we do the save.
            save.writeObject(list);
            save.writeLong(Contracto.getId_aux_contracto());

            saveH.writeObject(historico);

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }

    /**
     * le do ficheiro
     */
    public static void ler_Contracto() {
        // Wrap all in a try/catch block to trap I/O errors.
        try {
            // Open file to read from, named SavedObj.dat.
            FileInputStream saveFile = new FileInputStream("Contracto.dat");

            FileInputStream saveFileH = new FileInputStream("Contracto_Hist.dat");

            // Create an ObjectInputStream to get objects from save file.
            ObjectInputStream save = new ObjectInputStream(saveFile);

            ObjectInputStream saveH = new ObjectInputStream(saveFileH);

            // Now we do the restore.
            // readObject() returns a generic Object, we cast those back
            // into their original class type.
            // For primitive types, use the corresponding reference class.
            list = (ArrayList<Contracto>) save.readObject();
            long x = save.readLong();
            Contracto.setId_aux_contracto(x);

            historico = (ArrayList<Contracto>) saveH.readObject();

            // Close the file.
            save.close(); // This also closes saveFile.
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro Contracto.dat nao encontrado!");
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }
    }
}
