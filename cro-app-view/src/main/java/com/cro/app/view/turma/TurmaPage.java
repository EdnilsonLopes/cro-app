package com.cro.app.view.turma;


import com.cro.app.model.entidade.Turma;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractDataProvider;
import com.cro.app.view.util.AbstractListView;
import com.cro.app.view.util.AbstractViewLogic;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.router.Route;


/**
 * @author Ednilson Brito Lopes
 */
@Route(value = "turma", layout = MainLayout.class)
@SuppressWarnings("serial")
public class TurmaPage
  extends AbstractListView<Turma> {

  public static final String PAGE_NAME = "Turmas";
  private TurmaViewLogic viewLogic;
  private TurmaDataProvider dataProvider;

  @Override
  public AbstractViewLogic<Turma> createViewLogic() {
    if (viewLogic == null) {
      viewLogic = new TurmaViewLogic(this);
    }
    return viewLogic;
  }

  @Override
  public AbstractDataProvider<Turma> createDataProvider() {
    if (dataProvider == null) {
      dataProvider = new TurmaDataProvider();
    }
    return dataProvider;
  }

  @Override
  public BeanGrid<Turma> createBeanGrid() {
    return new TurmaGrid();
  }

  @Override
  public BeanForm<Turma> createBeanForm() {
    return new TurmaForm(viewLogic);
  }

  @Override
  public String getArtigoEntidade() {
    return "a";
  }

  @Override
  public String getNomeEntidade() {
    return "Turma";
  }

}
