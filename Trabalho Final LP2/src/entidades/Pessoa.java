/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import enums.PessoaTipo;
import interfaces.IPessoa;
import java.io.Serializable;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class Pessoa implements IPessoa, Serializable {

    private static long id_aux_pessoa = 0;
    private String nome = null;
    private String apelido = null;
    private long nif = 0;
    private long id = 0;
    private double capital = 0;
    private PessoaTipo tipo = PessoaTipo.Artista;

    /**
     *
     * @return
     */
    public static long getId_aux_pessoa() {
        return id_aux_pessoa;
    }

    /**
     *
     * @param id_aux_pessoa
     */
    public static void setId_aux_pessoa(long id_aux_pessoa) {
        Pessoa.id_aux_pessoa = id_aux_pessoa;
    }

    /**
     *
     * @return
     */
    @Override
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    @Override
    public String getApelido() {
        return apelido;
    }

    /**
     *
     * @param apelido
     */
    @Override
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    /**
     *
     * @return
     */
    @Override
    public long getNif() {
        return nif;
    }

    /**
     *
     * @param nif
     */
    @Override
    public void setNif(long nif) {
        this.nif = nif;
    }

    /**
     *
     * @return
     */
    @Override
    public long getID() {
        return id;
    }

    /**
     *
     * @param id
     */
    @Override
    public void setID(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public double getCapital() {
        return capital;
    }

    /**
     *
     * @param capital
     */
    public void setCapital(double capital) {
        this.capital = capital;
    }

    /**
     *
     * @return
     */
    @Override
    public PessoaTipo getPessoaTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     * @param nome
     * @param apelido
     * @param nif
     * @param id
     * @param capital
     */
    public Pessoa(PessoaTipo tipo, String nome, String apelido, long nif, long id, double capital) {
        this.tipo = tipo;
        this.nome = nome;
        this.apelido = apelido;
        this.nif = nif;
        this.id = id;
        this.capital = capital;
    }
}
