/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import enums.PessoaTipo;
import interfaces.IAgente;
import java.text.DecimalFormat;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public class Agente extends Pessoa implements IAgente {

    private double montante_devido_bruto_agente = 0;
    private double montante_devido_liquido_agente = 0;


    /**
     *
     * @return Montante Bruto Devido Ao Agente
     */
    public double getMontante_Devido_Bruto_Agente() {
        return montante_devido_bruto_agente;
    }

    /**
     *
     * @param montante_devido_bruto_agente Montante Bruto Devido Ao Agente
     */
    public void setMontante_Devido_Bruto_Agente(double montante_devido_bruto_agente) {
        this.montante_devido_bruto_agente = montante_devido_bruto_agente;
    }

    /**
     *
     * @return Montante Liquido Devido Ao Agente
     */
    public double getMontante_Devido_Liquido_Agente() {
        return montante_devido_liquido_agente;
    }

    /**
     *
     * @param montante_devido_liquido_agente Montante Liquido Devido Ao Agente
     */
    public void setMontante_Devido_Liquido_Agente(double montante_devido_liquido_agente) {
        this.montante_devido_liquido_agente = montante_devido_liquido_agente;
    }

    /**
     *
     * @param nome Nome do Agente
     * @param apelido Apelido do Agente
     * @param id ID do Agente
     * @param nif NIF do Agente
     * @param montante_devido_bruto_agente Montante Bruto Devido Ao Agente
     * @param montante_devido_liquido_agente Montante Liquido Devido Ao Agent
     * @param capital Capital do Agente
     */
    public Agente(String nome, String apelido, long id, long nif, double montante_devido_bruto_agente, 
            double montante_devido_liquido_agente, double capital) {
        super(PessoaTipo.Agente, nome, apelido, nif, id, capital);
        this.montante_devido_bruto_agente = montante_devido_bruto_agente;
        this.montante_devido_liquido_agente = montante_devido_liquido_agente;
    }

    @Override
    public String toString() {
        DecimalFormat dinheiro = new DecimalFormat("#.##");
        return ("----ID:" + getID() + "----"
                + "\nNome: " + getNome() + " " + getApelido()
                + "\nNIF: " + getNif()
                + "\nCapital: " + dinheiro.format(getCapital()) + "€"
                + "\nMontante Devido Bruto: " + dinheiro.format(this.montante_devido_bruto_agente) + "€"
                + "\nMontante Devido Liquido: " + dinheiro.format(this.montante_devido_liquido_agente) + "€");
    }
}
