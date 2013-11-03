/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import interfaces.IEvento;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class Evento implements IEvento, Serializable {

    private static long id_aux_evento = 0;
    private long evento_id = 0;
    private long[] artistas = null;
    private long[] agentes = null;
    private String nome_evento = null;
    private Date data_evento = null;
    private double receita = 0;
    private boolean pago = false;
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @return the id_aux_evento
     */
    public static long getId_aux_evento() {
        return id_aux_evento;
    }

    /**
     * @param id_aux_evento the id_aux_evento to set
     */
    public static void setId_aux_evento(long id_aux_evento) {
        Evento.id_aux_evento = id_aux_evento;
    }

    /**
     *
     * @return
     */
    public long getEvento_ID() {
        return evento_id;
    }

    /**
     *
     * @param evento_id
     */
    public void setEvento_ID(long evento_id) {
        this.evento_id = evento_id;
    }

    /**
     *
     * @return
     */
    public long[] getArtistas() {
        return artistas;
    }

    /**
     *
     * @param artistas
     */
    public void setArtistas(long[] artistas) {
        this.artistas = artistas;
    }

    /**
     *
     * @return
     */
    public long[] getAgentes() {
        return agentes;
    }

    /**
     *
     * @param agentes
     */
    public void setAgentes(long[] agentes) {
        this.agentes = agentes;
    }

    /**
     *
     * @return
     */
    public String getNome_evento() {
        return nome_evento;
    }

    /**
     *
     * @param nome_evento
     */
    public void setNome_evento(String nome_evento) {
        this.nome_evento = nome_evento;
    }

    /**
     *
     * @return
     */
    public Date getData() {
        return data_evento;
    }

    /**
     *
     * @param data_evento
     */
    public void setData(Date data_evento) {
        this.data_evento = data_evento;
    }

    /**
     *
     * @return
     */
    public double getReceita() {
        return receita;
    }

    /**
     *
     * @param receita
     */
    public void setReceita(double receita) {
        this.receita = receita;
    }

    /**
     *
     * @return
     */
    public boolean getPago() {
        return pago;
    }

    /**
     *
     * @param pago
     */
    public void setPago(boolean pago) {
        this.pago = pago;
    }

    /**
     *
     * @param evento_id
     * @param nome_evento
     * @param artistas
     * @param agentes
     * @param data_evento
     * @param receita
     * @param pago
     */
    public Evento(long evento_id, String nome_evento, long[] artistas, long[] agentes, Date data_evento, double receita, boolean pago) {
        this.evento_id = evento_id;
        this.nome_evento = nome_evento;
        this.artistas = artistas;
        this.agentes = agentes;
        this.data_evento = data_evento;
        this.receita = receita;
        this.pago = pago;
    }

    public String toString() {
        DecimalFormat dinheiro = new DecimalFormat("#.##");
        return ("----ID:" + this.evento_id + "----"
                + "\nNome Evento: " + this.nome_evento
                + "\nID Artistas: " + Arrays.toString(this.artistas)
                + "\nID Agentes: " + Arrays.toString(this.agentes)
                + "\nData do Evento: " + df.format(this.data_evento)
                + "\nReceitas: " + dinheiro.format(this.receita) + "€"
                + "\nEstado do Pagamento: " + this.pago);
    }
}
