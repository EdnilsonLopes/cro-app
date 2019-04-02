package com.cro.app.view.disciplina;


import java.util.Locale;
import java.util.Objects;

import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Disciplina;
import com.cro.app.view.util.AbstractDataProvider;


/**
 * Provedor de dados da disciplina
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class DisciplinaDataProvider
  extends AbstractDataProvider<Disciplina> {

  private String filterText = "";

  public DisciplinaDataProvider() {
    super(DataService.get().getDisciplinaDAO().loadAll());
  }

  //  public boolean save(Disciplina obj) {
  //    boolean isNewObject = obj.isNewObject();
  //
  //    if (isNewObject) {
  //      DataService.get().getDisciplinaDAO().insert(obj);
  //      refreshAll();
  //    }
  //    else {
  //      DataService.get().getDisciplinaDAO().update(obj);
  //      refreshItem(obj);
  //    }
  //    return isNewObject;
  //  }

  //  public void delete(Disciplina obj) {
  //    DataService.get().getDisciplinaDAO().delete(obj);
  //    refreshAll();
  //  }

  public void setFilter(String filterText) {
    Objects.requireNonNull(filterText, "Filter text cannot be null.");
    if (Objects.equals(this.filterText, filterText.trim())) {
      return;
    }
    this.filterText = filterText.trim();

    setFilter(disciplina -> passesFilter(disciplina.getNome(),
                                         filterText) ||
      passesFilter(disciplina.getDescricao(), filterText));
  }

  //  @Override
  //  public Integer getId(Disciplina item) {
  //    Objects.requireNonNull(item,
  //                           "Não foi possivel encontrar o ID da Disciplina.");
  //    return item.getId();
  //  }

  protected boolean passesFilter(Object object, String filterText) {
    return object != null &&
      object.toString().toLowerCase(new Locale("PT",
                                               "BR")).contains(filterText);
  }

  @Override
  public void insert(Disciplina obj) {
    DataService.get().getDisciplinaDAO().insert(obj);
  }

  @Override
  public void update(Disciplina obj) {
    DataService.get().getDisciplinaDAO().update(obj);
  }

  @Override
  public void delete(Disciplina obj) {
    DataService.get().getDisciplinaDAO().update(obj);
  }

}