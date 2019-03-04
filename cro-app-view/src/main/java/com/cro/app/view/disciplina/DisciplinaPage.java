package com.cro.app.view.disciplina;


import com.cro.app.model.entidade.Disciplina;
import com.cro.app.view.MainLayout;
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
import com.vaadin.flow.router.Route;


/**
 * Página para mostrar as disciplinas
 * @author Ednilson Brito Lopes
 */
@Route(value = "Disciplina", layout = MainLayout.class)
@SuppressWarnings("serial")
public class DisciplinaPage
  extends HorizontalLayout
  implements HasUrlParameter<String> {

  public static final String PAGE_NAME = "Disciplina";

  private DisciplinaForm form;
  private DisciplinaGrid grid;
  private TextField filter;
  private Button newDisciplina;

  private DisciplinaViewLogic viewLogic = new DisciplinaViewLogic(this);
  private DisciplinaDataProvider dataProvider =
    new DisciplinaDataProvider();

  public DisciplinaPage() {
    setSizeFull();
    HorizontalLayout topLayout = createTopBar();

    grid = new DisciplinaGrid();
    grid.setDataProvider(dataProvider);
    grid.asSingleSelect().addValueChangeListener(
                                                 event -> viewLogic.rowSelected(event.getValue()));

    form = new DisciplinaForm(viewLogic);
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

  public HorizontalLayout createTopBar() {
    filter = new TextField();
    filter.setPlaceholder("Pesquisar");
    // Apply the filter to grid's data provider. TextField value is never null
    filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));

    newDisciplina = new Button("Nova Disciplina");
    newDisciplina.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    newDisciplina.setIcon(VaadinIcon.PLUS_CIRCLE.create());
    newDisciplina.addClickListener(click -> viewLogic.newDisciplina());

    HorizontalLayout topLayout = new HorizontalLayout();
    topLayout.setWidth("100%");
    topLayout.add(filter);
    topLayout.add(newDisciplina);
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

  public void setNewDisciplinaEnabled(boolean enabled) {
    newDisciplina.setEnabled(enabled);
  }

  public void clearSelection() {
    grid.getSelectionModel().deselectAll();
  }

  public void selectRow(Disciplina row) {
    grid.getSelectionModel().select(row);
  }

  public Disciplina getSelectedRow() {
    return grid.getSelectedRow();
  }

  public void updateDisciplina(Disciplina disciplina) {
    boolean newObject = dataProvider.save(disciplina);
    if (newObject) {
      DisciplinaDataProvider newDataProvider =
        (DisciplinaDataProvider) grid.getDataProvider();
      newDataProvider.getItems().add(disciplina);
      grid.setDataProvider(newDataProvider);
      grid.getDataProvider().refreshAll();
    }
    else {
      grid.getDataProvider().refreshItem(disciplina);
    }
  }

  public void removeDisciplina(Disciplina disciplina) {
    dataProvider.delete(disciplina);
    DisciplinaDataProvider newDataProvider =
      (DisciplinaDataProvider) grid.getDataProvider();
    newDataProvider.getItems().remove(disciplina);
    grid.setDataProvider(newDataProvider);
    grid.getDataProvider().refreshAll();
  }

  public void editDisciplina(Disciplina disciplina) {
    showForm(disciplina != null);
    form.editDisciplina(disciplina);
  }

  public void showForm(boolean show) {
    form.setVisible(show);
  }

  @Override
  public void setParameter(BeforeEvent event,
                           @OptionalParameter String parameter) {
    viewLogic.enter(parameter);
  }
}
