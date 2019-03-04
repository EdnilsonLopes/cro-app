package com.cro.app.view.disciplina;


import com.cro.app.model.entidade.Disciplina;
import com.cro.app.view.util.BeanGrid;


/**
 * Grid para listagem de disciplinas
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class DisciplinaGrid
  extends BeanGrid<Disciplina> {

  public DisciplinaGrid() {
    addColumn(Disciplina::getNome).setFlexGrow(1).setHeader("Nome").setSortable(true);
    addColumn(Disciplina::getDescricao).setFlexGrow(1).setHeader("Descrição").setSortable(true);
  }
}
