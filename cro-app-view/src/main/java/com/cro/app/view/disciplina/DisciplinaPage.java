package com.cro.app.view.disciplina;


import com.cro.app.model.entidade.Disciplina;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractDataProvider;
import com.cro.app.view.util.AbstractListView;
import com.cro.app.view.util.AbstractViewLogic;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * Página para mostrar as disciplinas
 * @author Ednilson Brito Lopes
 */
@Route(value = "Disciplina", layout = MainLayout.class)
@PageTitle(DisciplinaPage.PAGE_NAME)
@SuppressWarnings("serial")
public class DisciplinaPage
  extends AbstractListView<Disciplina> {

  public static final String PAGE_NAME = "Disciplina";

  //  private DisciplinaForm form;
  //  private DisciplinaGrid grid;
  //  private TextField filter;
  //  private Button newDisciplina;
  //
  private DisciplinaViewLogic viewLogic;
  private DisciplinaDataProvider dataProvider;

  public DisciplinaPage() {
    //    setSizeFull();
    //    HorizontalLayout topLayout = createTopBar();
    //
    //    grid = new DisciplinaGrid();
    //    grid.setDataProvider(dataProvider);
    //    grid.asSingleSelect().addValueChangeListener(
    //                                                 event -> viewLogic.rowSelected(event.getValue()));
    //
    //    form = new DisciplinaForm(viewLogic);
    //    VerticalLayout barAndGridLayout = new VerticalLayout();
    //    barAndGridLayout.add(topLayout);
    //    barAndGridLayout.add(grid);
    //    barAndGridLayout.setFlexGrow(1, grid);
    //    barAndGridLayout.setFlexGrow(0, topLayout);
    //    barAndGridLayout.setSizeFull();
    //    barAndGridLayout.expand(grid);
    //
    //    add(barAndGridLayout);
    //    add(form);
    //
    //    viewLogic.init();
  }
  //
  //  public HorizontalLayout createTopBar() {
  //    filter = new TextField();
  //    filter.setPlaceholder("Pesquisar");
  //    // Apply the filter to grid's data provider. TextField value is never null
  //    filter.addValueChangeListener(event -> dataProvider.setFilter(event.getValue()));
  //
  //    newDisciplina = new Button("Nova Disciplina");
  //    newDisciplina.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
  //    newDisciplina.setIcon(VaadinIcon.PLUS_CIRCLE.create());
  //    newDisciplina.addClickListener(click -> viewLogic.newDisciplina());
  //
  //    HorizontalLayout topLayout = new HorizontalLayout();
  //    topLayout.setWidth("100%");
  //    topLayout.add(filter);
  //    topLayout.add(newDisciplina);
  //    topLayout.setVerticalComponentAlignment(Alignment.START, filter);
  //    topLayout.expand(filter);
  //    return topLayout;
  //  }
  //
  //  public void showError(String msg) {
  //    Notification.show(msg);
  //  }
  //
  //  public void showSaveNotification(String msg) {
  //    Notification.show(msg);
  //  }
  //
  //  public void setNewDisciplinaEnabled(boolean enabled) {
  //    newDisciplina.setEnabled(enabled);
  //  }
  //
  //  public void clearSelection() {
  //    grid.getSelectionModel().deselectAll();
  //  }
  //
  //  public void selectRow(Disciplina row) {
  //    grid.getSelectionModel().select(row);
  //  }
  //
  //  public Disciplina getSelectedRow() {
  //    return grid.getSelectedRow();
  //  }
  //
  //  public void updateDisciplina(Disciplina disciplina) {
  //    boolean newObject = dataProvider.save(disciplina);
  //    if (newObject) {
  //      DisciplinaDataProvider newDataProvider =
  //        (DisciplinaDataProvider) grid.getDataProvider();
  //      newDataProvider.getItems().add(disciplina);
  //      grid.setDataProvider(newDataProvider);
  //      grid.getDataProvider().refreshAll();
  //    }
  //    else {
  //      grid.getDataProvider().refreshItem(disciplina);
  //    }
  //  }
  //
  //  public void removeDisciplina(Disciplina disciplina) {
  //    dataProvider.delete(disciplina);
  //    DisciplinaDataProvider newDataProvider =
  //      (DisciplinaDataProvider) grid.getDataProvider();
  //    newDataProvider.getItems().remove(disciplina);
  //    grid.setDataProvider(newDataProvider);
  //    grid.getDataProvider().refreshAll();
  //  }
  //
  //  public void editDisciplina(Disciplina disciplina) {
  //    showForm(disciplina != null);
  //    form.editDisciplina(disciplina);
  //  }
  //
  //  public void showForm(boolean show) {
  //    form.setVisible(show);
  //  }
  //
  //  @Override
  //  public void setParameter(BeforeEvent event,
  //                           @OptionalParameter String parameter) {
  //    viewLogic.enter(parameter);
  //  }

  @Override
  public AbstractViewLogic<Disciplina> createViewLogic() {
    if (viewLogic == null) {
      viewLogic = new DisciplinaViewLogic(this);
    }
    return viewLogic;
  }

  @Override
  public AbstractDataProvider<Disciplina> createDataProvider() {
    if (dataProvider == null) {
      dataProvider = new DisciplinaDataProvider();
    }
    return dataProvider;
  }

  @Override
  public BeanGrid<Disciplina> createBeanGrid() {
    return new DisciplinaGrid();
  }

  @Override
  public BeanForm<Disciplina> createBeanForm() {
    return new DisciplinaForm(viewLogic);
  }

  @Override
  public String getArtigoEntidade() {
    return "a";
  }

  @Override
  public String getNomeEntidade() {
    return "Disciplina";
  }
}
