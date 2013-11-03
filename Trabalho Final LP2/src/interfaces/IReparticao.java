/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 * @author Davide Barruncho
 * @author Yasmine Chede
 */
public interface IReparticao {

    /**
     *
     * @return
     */
    long getReparticao_id();

    /**
     *
     * @param reparticao_id
     */
    void setReparticao_id(long reparticao_id);

    /**
     *
     * @return
     */
    long getEvento_id();

    /**
     *
     * @param evento_id
     */
    void setEvento_id(long evento_id);

    /**
     *
     * @return
     */
    double[] getComissoes();

    /**
     *
     * @param comissoes
     */
    void setComissoes(double[] comissoes);

    /**
     *
     * @return
     */
    double getPercentagem_artistas();

    /**
     *
     * @param percentagem_artistas
     */
    void setPercentagem_artistas(double percentagem_artistas);

    /**
     *
     * @return
     */
    double getReceita_artistas();

    /**
     *
     * @param receita_artistas
     */
    void setReceita_artistas(double receita_artistas);

    /**
     *
     * @return
     */
    double[] getMontante_artista();

    /**
     *
     * @param montante_artista
     */
    void setMontante_artista(double[] montante_artista);
}
