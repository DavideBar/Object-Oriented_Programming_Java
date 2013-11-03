/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public interface IContracto {
    
    /**
     *
     * @return
     */
    long getContracto_Id();
    
    /**
     *
     * @param contracto_id
     */
    void setContracto_Id(long contracto_id);
    
    /**
     *
     * @return
     */
    long getArtista_Id();
    
    /**
     *
     * @param artista_id
     */
    void setArtista_Id(long artista_id);
    
    /**
     *
     * @return
     */
    long getAgente_Id();
    
    /**
     *
     * @param agente_id
     */
    void setAgente_Id(long agente_id);
    
    /**
     *
     * @return
     */
    Date getData_Inicial();
    
    /**
     *
     * @param data_inicial
     */
    void setData_Inicial(Date data_inicial);
    
    /**
     *
     * @return
     */
    Date getData_Final();
    
    /**
     *
     * @param data_final
     */
    void setData_Final(Date data_final);
    
    /**
     *
     * @return
     */
    String getObs();
    
    /**
     *
     * @param obs
     */
    void setObs(String obs);
}
