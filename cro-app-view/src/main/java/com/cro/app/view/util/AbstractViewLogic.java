package com.cro.app.view.util;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import com.cro.app.authentication.AccessControl;
import com.cro.app.authentication.AccessControlFactory;
import com.cro.app.model.util.AbstractBasicEntity;


/**
 * Classe abstrata onde contém a lógica da visualizção
 * 
 * @author Ednilson Brito Lopes
 *
 * @param <T>
 */
@SuppressWarnings("unchecked")
public abstract class AbstractViewLogic<T extends AbstractBasicEntity<?>>
  implements Serializable {

  /**
   * Serial
   */
  private static final long serialVersionUID = 865713311914120999L;

  private AbstractListView<T> view;

  /**
   * Tipo da classe passada por parâmetro
   */
  private Class<T> type;

  public AbstractViewLogic(AbstractListView<T> view) {
    this.view = view;
  }

  public void init() {
    editObject(null);
    if (!AccessControlFactory.getInstance().createAccessControl().isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
      view.setNewObjectEnabled(false);
    }
  }

  public void cancelObject() {
    setFragmentParameter("");
    view.clearSelection();
  }

  public void editObject(T obj) {
    if (obj == null) {
      setFragmentParameter("");
    }
    else {
      setFragmentParameter(obj.getId() + "");
    }
    view.editObject(obj);
  }

  public void newObject() {
    try {
      view.clearSelection();
      setFragmentParameter("new");
      view.editObject(getType().newInstance());
    }
    catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public void saveObject(T obj) {
    boolean newObject = obj.isNewObject();
    view.clearSelection();
    view.saveObject(obj);
    setFragmentParameter("");
    view.showSaveNotification(
                              obj + (newObject
                                ? " adicionad" + view.getArtigoEntidade()
                                : " atualizado" +
                                  view.getArtigoEntidade()));
  }

  public void deleteObject(T obj) {
    view.clearSelection();
    view.removeObject(obj);
    setFragmentParameter("");
    view.showSaveNotification(obj + " removid" + view.getArtigoEntidade());
  }

  public void rowSelected(T obj) {
    if (AccessControlFactory.getInstance().createAccessControl().isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
      editObject(obj);
    }
  }

  /**
   * Atualize o fragmento sem fazer com que o navegador altere a visualização
   */
  private void setFragmentParameter(String id) {
    String fragmentParameter;
    if (id == null || id.isEmpty()) {
      fragmentParameter = "";
    }
    else {
      fragmentParameter = id;
    }
    navigate(fragmentParameter);
  }

  public abstract void navigate(String fragmentParameter);

  public void enter(String id) {
    if (id != null && !id.isEmpty()) {
      if (id.equals("new")) {
        newObject();
      }
      else {
        try {
          int pid = Integer.parseInt(id);
          T obj = getType().newInstance();
          obj.setId(pid);
          T product = loadObject(obj);
          view.selectRow(product);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    else {
      view.showForm(false);
    }
  }

  /**
   * @return o tipo {@link Class} da entidade usada como parâmetro na classe
   */
  private Class<T> getType() {
    if (this.type == null) {
      this.type =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return type;
  }

  public abstract T loadObject(T obj);

}
