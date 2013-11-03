/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public interface IAgente extends IPessoa {
    
    /**
     *
     * @return
     */
    double getMontante_Devido_Bruto_Agente();

    /**
     *
     * @param montante_devido_bruto_agente
     */
    void setMontante_Devido_Bruto_Agente(double montante_devido_bruto_agente);

    /**
     *
     * @return
     */
    double getMontante_Devido_Liquido_Agente();

    /**
     *
     * @param montante_devido_liquido_agente
     */
    void setMontante_Devido_Liquido_Agente(double montante_devido_liquido_agente);

}
