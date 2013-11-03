/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import enums.PessoaTipo;
import interfaces.IArtista;
import java.text.DecimalFormat;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class Artista extends Pessoa implements IArtista {

    private String nome_artistico = null;
    private double montante_devido_liquido_artista = 0;
    private double montante_devido_bruto_artista = 0;

    public String getNome_Artistico() {
        return nome_artistico;
    }

    public void setNome_Artistico(String nome_artistico) {
        this.nome_artistico = nome_artistico;
    }

    /**
     *
     * @return
     */
    public double getMontante_devido_liquido_artista() {
        return montante_devido_liquido_artista;
    }

    /**
     *
     * @param montante_devido_liquido_artista
     */
    public void setMontante_devido_liquido_artista(double montante_devido_liquido_artista) {
        this.montante_devido_liquido_artista = montante_devido_liquido_artista;
    }

    /**
     *
     * @return
     */
    public double getMontante_devido_bruto_artista() {
        return montante_devido_bruto_artista;
    }

    /**
     *
     * @param montante_devido_bruto_artista
     */
    public void setMontante_devido_bruto_artista(double montante_devido_bruto_artista) {
        this.montante_devido_bruto_artista = montante_devido_bruto_artista;
    }

    /**
     *
     * @param nome
     * @param apelido
     * @param id
     * @param nome_artistico
     * @param nif
     * @param montante_devido_liquido_artista
     * @param capital
     * @param montante_devido_bruto_artista
     */
    public Artista(String nome, String apelido, long id, String nome_artistico, long nif, double montante_devido_liquido_artista, double montante_devido_bruto_artista, double capital) {
        super(PessoaTipo.Artista, nome, apelido, nif, id, capital);
        this.nome_artistico = nome_artistico;
        this.montante_devido_bruto_artista = montante_devido_bruto_artista;
        this.montante_devido_liquido_artista = montante_devido_liquido_artista;
    }

    @Override
    public String toString() {
        DecimalFormat dinheiro = new DecimalFormat("#.##");
        return ("----ID:" + getID() + "----"
                + "\nNome: " + getNome() + " " + getApelido()
                + "\nNome Artistico: " + this.nome_artistico
                + "\nNIF: " + getNif()
                + "\nCapital: " + dinheiro.format(getCapital()) + "€"
                + "\nMontante Devido Bruto: " + dinheiro.format(this.montante_devido_bruto_artista)
                + "\nMontante Devido Liquido: " + dinheiro.format(this.montante_devido_liquido_artista));
    }
}
