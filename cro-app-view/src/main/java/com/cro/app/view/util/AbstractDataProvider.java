package com.cro.app.view.util;


import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.cro.app.model.util.AbstractBasicEntity;
import com.vaadin.flow.data.provider.ListDataProvider;


public abstract class AbstractDataProvider<T extends AbstractBasicEntity<?>>
  extends ListDataProvider<T> {

  /**
   * Serial
   */
  private static final long serialVersionUID = 4727859476824871202L;

  public AbstractDataProvider(List<T> items) {
    super(items);
  }

  /**
   * Salva o novo objeto
   * 
   * @param obj
   *            será persistido
   */
  public boolean save(T obj) {
    boolean isNewObject = obj.isNewObject();
    if (isNewObject) {
      insert(obj);
      refreshAll();
    }
    else {
      update(obj);
      refreshItem(obj);
    }
    return isNewObject;
  }

  public void remove(T obj) {
    delete(obj);
    refreshAll();
  }

  @Override
  public Object getId(T obj) {
    Objects.requireNonNull(obj,
                           "Não foi possível encontrar o identificador.");

    return obj.getId();
  }

  protected boolean passesFilter(Object object, String filterText) {
    return object != null &&
      object.toString().toLowerCase(new Locale("PT",
                                               "BR")).contains(filterText);
  }

  public abstract void insert(T obj);

  public abstract void update(T obj);

  public abstract void delete(T obj);

  public abstract void setFilter(String filterText);
}
