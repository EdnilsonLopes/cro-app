package com.cro.app.view.disciplina;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Disciplina;
import com.cro.app.model.util.DataAccessObject;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractListPage;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.component.UI;
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
  extends AbstractListPage<Disciplina> {

  public static final String PAGE_NAME = "Disciplina";

  @Override
  public BeanGrid<Disciplina> createBeanGrid() {
    return new DisciplinaGrid();
  }

  @Override
  public BeanForm<Disciplina> createBeanForm() {
    return new DisciplinaForm();
  }

  @Override
  public String getArtigoEntidade() {
    return "a";
  }

  @Override
  public String getNomeEntidade() {
    return "Disciplina";
  }

  @Override
  public DataAccessObject<Disciplina> getDao() {
    return DataService.get().getDisciplinaDAO();
  }

  @Override
  public void createFilter(String filter) {

  }

  @Override
  protected void navigateTo(String fragmentParameter) {
    UI.getCurrent().navigate(DisciplinaPage.class, fragmentParameter);
  }
}
