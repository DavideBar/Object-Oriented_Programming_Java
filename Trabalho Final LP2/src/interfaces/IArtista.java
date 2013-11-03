/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public interface IArtista extends IPessoa {

    /**
     *
     * @return
     */
    String getNome_Artistico();

    /**
     *
     * @param nome_artistico
     */
    void setNome_Artistico(String nome_artistico);

    /**
     *
     * @return
     */
    double getMontante_devido_liquido_artista();

    /**
     *
     * @param montante_devido_liquido_artista
     */
    void setMontante_devido_liquido_artista(double montante_devido_liquido_artista);

    /**
     *
     * @return
     */
    double getMontante_devido_bruto_artista();

    /**
     *
     * @param montante_devido_bruto_artista
     */
    void setMontante_devido_bruto_artista(double montante_devido_bruto_artista);
}