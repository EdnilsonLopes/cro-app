package com.cro.app.view.professor;


import com.cro.app.model.entidade.Professor;
import com.cro.app.view.util.BeanGrid;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class ProfessorGrid
  extends BeanGrid<Professor> {

  public ProfessorGrid() {
    addColumn(Professor::getNome).setFlexGrow(1).setHeader("Nome").setSortable(true);
    addColumn(Professor::getFormacao).setFlexGrow(1).setHeader("Formação").setSortable(true);
    addColumn(p -> getDateInFormat(p.getDataNascimento())).setFlexGrow(1).setHeader("Data de Nascimento").setSortable(true);
    addColumn(Professor::getTelefone).setFlexGrow(1).setHeader("Telefone").setSortable(true);
  }
}
