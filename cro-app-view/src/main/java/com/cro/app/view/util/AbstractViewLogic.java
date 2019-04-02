package com.cro.app.view.util;


import java.io.Serializable;

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
public abstract class AbstractViewLogic<T extends AbstractBasicEntity>
  implements Serializable {

  /**
   * Serial
   */
  private static final long serialVersionUID = 865713311914120999L;

  private AbstractListView<T> page;

  /**
   * Tipo da classe passada por parâmetro
   */
  //  private Class<T> type;

  public AbstractViewLogic(AbstractListView<T> view) {
    this.page = view;
  }

  public void init() {
    editObject(null);
    if (!AccessControlFactory.getInstance().createAccessControl().isUserInRole(AccessControl.ADMIN_ROLE_NAME)) {
      page.setNewObjectEnabled(false);
    }
  }

  public void cancelObject() {
    setFragmentParameter("");
    page.clearSelection();
  }

  public void editObject(T obj) {
    if (obj == null) {
      setFragmentParameter("");
    }
    else {
      setFragmentParameter(obj.getId() + "");
    }
    page.editObject(obj);
  }

  public void newObject() {
    page.clearSelection();
    setFragmentParameter("new");
    page.editObject(createNewInstanceObject());
  }

  public void saveObject(T obj) {
    boolean newObject = obj.isNewObject();
    page.clearSelection();
    page.saveObject(obj);
    setFragmentParameter("");
    page.showSaveNotification(page.getNomeEntidade() + " " +
      obj + (newObject
        ? " adicionad" + page.getArtigoEntidade()
        : " atualizad" +
          page.getArtigoEntidade()));
  }

  public void deleteObject(T obj) {
    page.clearSelection();
    page.removeObject(obj);
    setFragmentParameter("");
    page.showSaveNotification(page.getNomeEntidade() + " " + obj +
      " removid" +
      page.getArtigoEntidade());
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

  public void enter(String objectId) {
    if (objectId != null && !objectId.isEmpty()) {
      if (objectId.equals("new")) {
        newObject();
      }
      else {
        try {
          int pid = Integer.parseInt(objectId);
          T product = findObject(pid);
          page.selectRow(product);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    else {
      page.showForm(false);
    }
  }

  public abstract T createNewInstanceObject();

  private T findObject(int objectId) {
    T obj = createNewInstanceObject();
    obj.setId(objectId);
    return loadObject(obj);
  }

  /**
   * @return o tipo {@link Class} da entidade usada como parâmetro na classe
   */
  //  private Class<T> getType() {
  //    if (this.type == null) {
  //      this.type =
  //        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  //    }
  //    return type;
  //  }

  public abstract T loadObject(T obj);

}
