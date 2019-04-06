package com.cro.app.view.sala;


import com.cro.app.model.entidade.Sala;
import com.cro.app.view.util.BeanGrid;


/**
 * Grid para listagem de {@link Sala}
 * @author Ednilson Brito Lopes
 */
public class SalaGrid
  extends BeanGrid<Sala> {

  /**
   * Serial
   */
  private static final long serialVersionUID = 3821188707383455868L;

  public SalaGrid() {
    addColumn(Sala::getNumero).setFlexGrow(1).setHeader("Número").setSortable(true);
    addColumn(Sala::getDescricao).setFlexGrow(4).setHeader("Descriçao").setSortable(true);
  }

}
