package com.cro.app.view.util;


import java.lang.reflect.ParameterizedType;

import com.cro.app.model.util.AbstractBasicEntity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public abstract class AbstractListView<T extends AbstractBasicEntity<?>>
  extends HorizontalLayout{

  /**
   * Serial
   */
  private static final long serialVersionUID = 6790346902631256999L;

  private BeanForm<T> form;
  private BeanGrid<T> grid;
  private TextField filter;

  private AbstractViewLogic<T> viewLogic;
  private Button newObjButton;

  private AbstractDataProvider<T> dataProvider = createDataProvider();

  private Class<T> type;

  public AbstractListView() {
    setSizeFull();
    HorizontalLayout topLayout = createTopBar();
    grid = createBeanGrid();
    grid.setDataProvider(dataProvider);
    viewLogic = createViewLogic();
    grid.asSingleSelect().addValueChangeListener(event -> viewLogic.rowSelected(event.getValue()));
    form = createBeanForm();
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
    filter.setPlaceholder("Filter name, availability or category");
    // Apply the filter to grid's data provider. TextField value is never null
    filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));

    newObjButton =
      new Button("Nov" + getArtigoEntidade() + " " + getNomeEntidade());
    newObjButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    newObjButton.setIcon(VaadinIcon.PLUS_CIRCLE.create());
    newObjButton.addClickListener(click -> viewLogic.newObject());

    HorizontalLayout topLayout = new HorizontalLayout();
    topLayout.setWidth("100%");
    topLayout.add(filter);
    topLayout.add(newObjButton);
    topLayout.setVerticalComponentAlignment(Alignment.START, filter);
    topLayout.expand(filter);
    return topLayout;
  }

  /**
   * @return o tipo {@link Class} da entidade usada como parâmetro na classe
   */
  @SuppressWarnings("unchecked")
  private Class<T> getType() {
    if (this.type == null) {
      this.type =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return type;
  }

  public void showError(String msg) {
    Notification.show(msg);
  }

  public void showSaveNotification(String msg) {
    Notification.show(msg);
  }

  public void setNewObjectEnabled(boolean enabled) {
    newObjButton.setEnabled(enabled);
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

  public void saveObject(T object) {
    dataProvider.save(object);
  }

  public void removeObject(T object) {
    dataProvider.delete(object);
  }

  public void editObject(T obj) {
    showForm(obj != null);
    form.editObject(obj);
  }

  public void showForm(boolean show) {
    form.setVisible(show);
  }

  public abstract AbstractViewLogic<T> createViewLogic();

  public abstract AbstractDataProvider<T> createDataProvider();

  public abstract BeanGrid<T> createBeanGrid();

  public abstract BeanForm<T> createBeanForm();

  public abstract String getArtigoEntidade();

  public abstract String getNomeEntidade();
}
