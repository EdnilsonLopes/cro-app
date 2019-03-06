package com.cro.app.view.professor;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Professor;
import com.cro.app.view.util.AbstractDataProvider;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class ProfessorDataProvider
  extends AbstractDataProvider<Professor> {

  public ProfessorDataProvider() {
    super(DataService.get().getProfessorDAO().loadAll());
  }

  @Override
  public void insert(Professor obj) {
    DataService.get().getProfessorDAO().insert(obj);
  }

  @Override
  public void update(Professor obj) {
    DataService.get().getProfessorDAO().update(obj);
  }

  @Override
  public void delete(Professor obj) {
    DataService.get().getProfessorDAO().delete(obj);
  }

  @Override
  public void setFilter(String filterText) {

  }

}
