package com.cro.app.view.professor;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Professor;
import com.cro.app.model.util.DataAccessObject;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractListPage;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * Página para {@link ProfessorF}
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
@Route(value = ProfessorPage.PAGE_NAME, layout = MainLayout.class)
@PageTitle(ProfessorPage.PAGE_NAME)
public class ProfessorPage
  extends AbstractListPage<Professor> {

  public static final String PAGE_NAME = "Professores";

  @Override
  protected void navigateTo(String fragmentParameter) {
    UI.getCurrent().navigate(ProfessorPage.class, fragmentParameter);
  }

  @Override
  public BeanGrid<Professor> createBeanGrid() {
    return new ProfessorGrid();
  }

  @Override
  public BeanForm<Professor> createBeanForm() {
    return new ProfessorForm();
  }

  @Override
  public String getArtigoEntidade() {
    return "o(a)";
  }

  @Override
  public String getNomeEntidade() {
    return "Professor";
  }

  @Override
  public DataAccessObject<Professor> getDao() {
    return DataService.get().getProfessorDAO();
  }

  @Override
  public void createFilter(String filter) {

  }

}
