package com.cro.app.view.aluno;


import com.cro.app.model.entidade.Aluno;
import com.cro.app.view.util.BeanGrid;


/**
 * Grid para listagem de Alunos
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class AlunoGrid
  extends BeanGrid<Aluno> {

  public AlunoGrid() {
    addColumn(Aluno::getNome).setFlexGrow(3).setHeader("Nome").setSortable(true);
  }
}
