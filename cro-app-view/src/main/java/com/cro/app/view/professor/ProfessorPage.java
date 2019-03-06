package com.cro.app.view.professor;


import com.cro.app.model.entidade.Professor;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractDataProvider;
import com.cro.app.view.util.AbstractListView;
import com.cro.app.view.util.AbstractViewLogic;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
@Route(value = ProfessorPage.PAGE_NAME, layout = MainLayout.class)
@PageTitle(ProfessorPage.PAGE_NAME)
public class ProfessorPage
  extends AbstractListView<Professor> {

  public static final String PAGE_NAME = "Professores";

  private ProfessorViewLogic viewLogic;

  private ProfessorDataProvider dataProvider;

  @Override
  public AbstractViewLogic<Professor> createViewLogic() {
    if (viewLogic == null) {
      viewLogic = new ProfessorViewLogic(this);
    }
    return viewLogic;
  }

  @Override
  public AbstractDataProvider<Professor> createDataProvider() {
    if (dataProvider == null) {
      dataProvider = new ProfessorDataProvider();
    }
    return dataProvider;
  }

  @Override
  public BeanGrid<Professor> createBeanGrid() {
    return new ProfessorGrid();
  }

  @Override
  public BeanForm<Professor> createBeanForm() {
    return new ProfessorForm(createViewLogic());
  }

  @Override
  public String getArtigoEntidade() {
    return "o(a)";
  }

  @Override
  public String getNomeEntidade() {
    return "Professor(a)";
  }

}
