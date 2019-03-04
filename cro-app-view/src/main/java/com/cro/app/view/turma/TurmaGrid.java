package com.cro.app.view.turma;


import com.cro.app.model.entidade.Turma;
import com.cro.app.view.util.BeanGrid;


/**
 * Grid para listagem das turmas
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class TurmaGrid
  extends BeanGrid<Turma> {

  public TurmaGrid() {
    addColumn(Turma::getNomeCompleto).setFlexGrow(1).setHeader("Turma").setSortable(true);
    addColumn(Turma::getProfessor).setFlexGrow(1).setHeader("Conselheiro").setSortable(true);
    addColumn(Turma::getDescricao).setFlexGrow(1).setHeader("Descrição").setSortable(true);
  }
}
