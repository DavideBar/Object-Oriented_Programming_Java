/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import Work.WPessoa;
import interfaces.IContracto;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import sun.awt.windows.WPageDialog;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class Contracto implements IContracto, Serializable {

    private static long id_aux_contracto = 0;
    private long contracto_id = 0;
    private long artista_id = 0;
    private long agente_id = 0;
    private Date data_inicial;
    private Date data_final;
    private String obs = null;
    private SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * @return the id_aux_contracto
     */
    public static long getId_aux_contracto() {
        return id_aux_contracto;
    }

    /**
     * @param id_aux_contracto the id_aux to set
     */
    public static void setId_aux_contracto(long id_aux_contracto) {
        Contracto.id_aux_contracto = id_aux_contracto;
    }

    /**
     *
     * @return
     */
    public long getContracto_Id() {
        return contracto_id;
    }

    /**
     *
     * @param contracto_id
     */
    public void setContracto_Id(long contracto_id) {
        this.contracto_id = contracto_id;
    }

    /**
     *
     * @return
     */
    public long getArtista_Id() {
        return artista_id;
    }

    /**
     *
     * @param artista_id
     */
    public void setArtista_Id(long artista_id) {
        this.artista_id = artista_id;
    }

    /**
     *
     * @return
     */
    public long getAgente_Id() {
        return agente_id;
    }

    /**
     *
     * @param agente_id
     */
    public void setAgente_Id(long agente_id) {
        this.agente_id = agente_id;
    }

    /**
     *
     * @return
     */
    public Date getData_Inicial() {
        return data_inicial;
    }

    /**
     *
     * @param data_inicial
     */
    public void setData_Inicial(Date data_inicial) {
        this.data_inicial = data_inicial;
    }

    /**
     *
     * @return
     */
    public Date getData_Final() {
        return data_final;
    }

    /**
     *
     * @param data_final
     */
    public void setData_Final(Date data_final) {
        this.data_final = data_final;
    }

    /**
     *
     * @return
     */
    public String getObs() {
        return obs;
    }

    /**
     *
     * @param obs
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     *
     * @param contracto_id
     * @param artista_id
     * @param agente_id
     * @param data_inicial
     * @param data_final
     * @param obs
     */
    public Contracto(long contracto_id, long artista_id, long agente_id, Date data_inicial, Date data_final, String obs) {
        this.contracto_id = contracto_id;
        this.artista_id = artista_id;
        this.agente_id = agente_id;
        this.data_inicial = data_inicial;
        this.data_final = data_final;
        this.obs = obs;
    }

    public String toString() {
        String artista = null;
        String agente = null;
        for (int i = 0; i < WPessoa.list.size(); i++) {
            if (this.artista_id == WPessoa.list.get(i).getID()) {
                artista = WPessoa.list.get(i).getNome();
            }
            if (this.agente_id == WPessoa.list.get(i).getID()) {
                agente = WPessoa.list.get(i).getNome();
            }
        }
        return ("----ID:" + this.contracto_id + "----"
                + "\nNome Artista: " + artista + " ID: " + this.artista_id
                + "\nNome Agente: " + agente + " ID: " + this.agente_id
                + "\nData Incial: " + df.format(this.data_inicial)
                + "\nData Final: " + df.format(this.data_final)
                + "\nObservacoes: " + this.obs);
    }
}
