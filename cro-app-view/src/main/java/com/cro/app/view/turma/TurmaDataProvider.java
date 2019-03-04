package com.cro.app.view.turma;


import java.util.Objects;

import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Turma;
import com.cro.app.view.util.AbstractDataProvider;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class TurmaDataProvider
  extends AbstractDataProvider<Turma> {

  public TurmaDataProvider() {
    super(DataService.get().getTurmaDAO().loadAll());
  }

  private String filterText = "";

  @Override
  public void insert(Turma obj) {
    DataService.get().getTurmaDAO().insert(obj);
  }

  @Override
  public void update(Turma obj) {
    DataService.get().getTurmaDAO().update(obj);
  }

  @Override
  public void delete(Turma obj) {
    DataService.get().getTurmaDAO().delete(obj);
  }

  @Override
  public void setFilter(String filterText) {
    Objects.requireNonNull(filterText, "Filter text cannot be null.");
    if (Objects.equals(this.filterText, filterText.trim())) {
      return;
    }
    this.filterText = filterText.trim();

    setFilter(turma -> passesFilter(turma.getNome(),
                                    filterText) ||
      passesFilter(turma.getDescricao(), filterText) ||
      passesFilter(turma.getSerie(), filterText) ||
      passesFilter(turma.getProfessor(), filterText));
  }

}
