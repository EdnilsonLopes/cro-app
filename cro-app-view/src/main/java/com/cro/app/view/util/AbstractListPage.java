package com.cro.app.view.util;


import java.lang.reflect.ParameterizedType;

import com.cro.app.model.util.AbstractBasicEntity;
import com.cro.app.model.util.DataAccessObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;


/**
 * Classe abstrata para páginas
 * @author Ednilson Brito Lopes
 *
 * @param <T> Tipo da entidade que será mostrada na páginas
 */
public abstract class AbstractListPage<T extends AbstractBasicEntity>
  extends HorizontalLayout
  implements HasUrlParameter<String> {

  /**
   * Serial
   */
  private static final long serialVersionUID = 3443396101708350317L;

  private Button insertButton;
  private TextField filter;
  private BeanForm<T> form;
  private BeanGrid<T> grid;
  private AbstractDataProvider<T> dataProvider = createDataProvider();
  private AbstractViewLogic<T> viewLogic;

  private Class<T> typeT;

  public AbstractListPage() {
    setSizeFull();
    HorizontalLayout topLayout = createTopBar();
    grid = createBeanGrid();
    grid.setDataProvider(dataProvider);
    viewLogic = createViewLogic();
    grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));
    form = createBeanForm();
    form.setViewLogic(getViewLogic());
    VerticalLayout barAndGridLayout = new VerticalLayout();
    barAndGridLayout.add(topLayout);
    barAndGridLayout.add(grid);
    barAndGridLayout.setFlexGrow(1, grid);
    barAndGridLayout.setFlexGrow(0, topLayout);
    barAndGridLayout.setSizeFull();
    barAndGridLayout.expand(grid);
    add(barAndGridLayout);
    add(form);

    viewLogic.init();
  }

  private HorizontalLayout createTopBar() {
    filter = new TextField();
    filter.setPlaceholder("Pesquisar " + getNomeEntidade());
    // Apply the filter to grid's data provider. TextField value is never null
    filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));

    insertButton = new Button();
    //      new Button("Nov" + getArtigoEntidade() + " " + getNomeEntidade());
    insertButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    insertButton.addThemeVariants(ButtonVariant.LUMO_ICON);
    insertButton.setIcon(VaadinIcon.PLUS_CIRCLE.create());
    insertButton.addClickListener(click -> viewLogic.newObject());

    HorizontalLayout topLayout = new HorizontalLayout();
    topLayout.setWidth("100%");
    topLayout.add(filter);
    topLayout.add(insertButton);
    topLayout.setVerticalComponentAlignment(Alignment.START, filter);
    topLayout.expand(filter);
    return topLayout;
  }

  public void showError(String msg) {
    Notification.show(msg);
  }

  public void showSaveNotification(String msg) {
    Notification.show(msg);
  }

  public void setNewObjectEnabled(boolean enabled) {
    insertButton.setEnabled(enabled);
  }

  public void clearSelection() {
    grid.getSelectionModel().deselectAll();
  }

  public void selectRow(T row) {
    grid.getSelectionModel().select(row);
  }

  public T getSelectedRow() {
    return grid.getSelectedRow();
  }

  @SuppressWarnings("unchecked")
  public void saveObject(T object) {
    boolean newObject = dataProvider.save(object);
    if (newObject) {
      AbstractDataProvider<T> newDataProvider =
        (AbstractDataProvider<T>) grid.getDataProvider();
      newDataProvider.getItems().add(object);
      grid.setDataProvider(newDataProvider);
      grid.getDataProvider().refreshAll();
    }
    else {
      grid.getDataProvider().refreshItem(object);
    }
  }

  @SuppressWarnings("unchecked")
  public void removeObject(T object) {
    dataProvider.delete(object);
    AbstractDataProvider<T> newDataProvider =
      (AbstractDataProvider<T>) grid.getDataProvider();
    newDataProvider.getItems().remove(object);
    grid.setDataProvider(newDataProvider);
    grid.getDataProvider().refreshAll();
  }

  public void editObject(T obj) {
    showForm(obj != null);
    form.editObject(obj);
  }

  public void showForm(boolean show) {
    form.setVisible(show);
  }

  @Override
  public void setParameter(BeforeEvent event,
                           @OptionalParameter String parameter) {
    viewLogic.enter(parameter);
  }

  public AbstractViewLogic<T> createViewLogic() {
    AbstractViewLogic<T> viewLogic =
      new AbstractViewLogic<T>(this) {

        /**
         * Serial
         */
        private static final long serialVersionUID = 8217920226629625094L;

        @Override
        public void navigate(String fragmentParameter) {
          navigateTo(fragmentParameter);
        }

        @Override
        public T createNewInstanceObject() {
          try {
            return getTypeT().newInstance();
          }
          catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
          }
          return null;
        }

        @Override
        public T loadObject(T obj) {
          return getDao().load(obj);
        }
      };
    return viewLogic;
  }

  protected abstract void navigateTo(String fragmentParameter);

  public AbstractDataProvider<T> createDataProvider() {
    AbstractDataProvider<T> dataProvider =
      new AbstractDataProvider<T>(getDao().loadAll()) {

        /**
         * Serial
         */
        private static final long serialVersionUID = 8550872573645229430L;

        @Override
        public void insert(T obj) {
          getDao().insert(obj);
        }

        @Override
        public void update(T obj) {
          getDao().update(obj);
        }

        @Override
        public void delete(T obj) {
          getDao().delete(obj);
        }

        @Override
        public void setFilter(String filterText) {
          createFilter(filterText);
        }
      };
    return dataProvider;
  }

  public abstract BeanGrid<T> createBeanGrid();

  public abstract BeanForm<T> createBeanForm();

  public abstract String getArtigoEntidade();

  public abstract String getNomeEntidade();

  public abstract DataAccessObject<T> getDao();

  public abstract void createFilter(String filter);

  public BeanForm<T> getForm() {
    return form;
  }

  public BeanGrid<T> getGrid() {
    return grid;
  }

  public AbstractDataProvider<T> getDataProvider() {
    return dataProvider;
  }

  public AbstractViewLogic<T> getViewLogic() {
    return viewLogic;
  }

  @SuppressWarnings("unchecked")
  public Class<T> getTypeT() {
    if (this.typeT == null) {
      this.typeT =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return typeT;
  }

}
