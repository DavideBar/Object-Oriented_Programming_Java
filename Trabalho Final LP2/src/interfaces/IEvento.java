/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Date;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public interface IEvento {

    /**
     *
     * @return
     */
    long getEvento_ID();

    /**
     *
     * @param evento_id
     */
    void setEvento_ID(long evento_id);

    /**
     *
     * @return
     */
    long[] getArtistas();

    /**
     *
     * @param artistas
     */
    void setArtistas(long[] artistas);
    
    /**
     *
     * @return
     */
    long[] getAgentes();
    
    /**
     *
     * @param agentes
     */
    void setAgentes(long[] agentes);
    
    /**
     *
     * @return
     */
    String getNome_evento();

    /**
     *
     * @param nome_evento
     */
    void setNome_evento(String nome_evento);

    /**
     *
     * @return
     */
    Date getData();

    /**
     *
     * @param data
     */
    void setData(Date data);

    /**
     *
     * @return
     */
    double getReceita();

    /**
     *
     * @param receita
     */
    void setReceita(double receita);
}
