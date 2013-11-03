/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import interfaces.IReparticao;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class Reparticao implements IReparticao, Serializable {

    private static long id_aux_reparticao = 0;
    private long reparticao_id = 0;
    private long evento_id = 0;
    private double[] comissoes = null;
    private double percentagem_artistas = 0;
    private double receita_artistas = 0;
    private double[] montante_artista = null;

    /**
     *
     * @return
     */
    public static long getId_aux_reparticao() {
        return id_aux_reparticao;
    }

    /**
     *
     * @param aId_aux_reparticao
     */
    public static void setId_aux_reparticao(long aId_aux_reparticao) {
        Reparticao.id_aux_reparticao = aId_aux_reparticao;
    }

    /**
     *
     * @return
     */
    public long getReparticao_id() {
        return reparticao_id;
    }

    /**
     *
     * @param reparticao_id
     */
    public void setReparticao_id(long reparticao_id) {
        this.reparticao_id = reparticao_id;
    }

    /**
     *
     * @return
     */
    public long getEvento_id() {
        return evento_id;
    }

    /**
     *
     * @param evento_id
     */
    public void setEvento_id(long evento_id) {
        this.evento_id = evento_id;
    }

    /**
     *
     * @return
     */
    public double[] getComissoes() {
        return comissoes;
    }

    /**
     *
     * @param comissoes
     */
    public void setComissoes(double[] comissoes) {
        this.comissoes = comissoes;
    }

    /**
     *
     * @return
     */
    public double getPercentagem_artistas() {
        return percentagem_artistas;
    }

    /**
     *
     * @param percentagem_artistas
     */
    public void setPercentagem_artistas(double percentagem_artistas) {
        this.percentagem_artistas = percentagem_artistas;
    }

    /**
     *
     * @return
     */
    public double getReceita_artistas() {
        return receita_artistas;
    }

    /**
     *
     * @param receita_artistas
     */
    public void setReceita_artistas(double receita_artistas) {
        this.receita_artistas = receita_artistas;
    }

    /**
     *
     * @return
     */
    public double[] getMontante_artista() {
        return montante_artista;
    }

    /**
     *
     * @param montante_artista
     */
    public void setMontante_artista(double[] montante_artista) {
        this.montante_artista = montante_artista;
    }

    /**
     *
     * @param reparticao_id
     * @param evento_id
     * @param receita_artistas
     * @param percentagem_artistas
     * @param comissoes
     * @param montante_artista
     */
    public Reparticao(long reparticao_id, long evento_id, double receita_artistas, double percentagem_artistas, double[] comissoes, double[] montante_artista) {
        this.reparticao_id = reparticao_id;
        this.evento_id = evento_id;
        this.receita_artistas = receita_artistas;
        this.percentagem_artistas = percentagem_artistas;
        this.comissoes = comissoes;
        this.montante_artista = montante_artista;
    }

    @Override
    public String toString() {
        DecimalFormat dinheiro = new DecimalFormat("#.##");
        return "Reparticao{"
                + "reparticao_id=" + this.reparticao_id
                + ", evento_id=" + this.evento_id
                + ", comissoes=" + Arrays.toString(this.comissoes)
                + ", percentagem_artistas=" + dinheiro.format(this.percentagem_artistas) + "%"
                + ", receita_artistas=" + dinheiro.format(this.receita_artistas) + "€"
                + ", montante_artista=" + Arrays.toString(this.montante_artista) + '}';
    }
}
