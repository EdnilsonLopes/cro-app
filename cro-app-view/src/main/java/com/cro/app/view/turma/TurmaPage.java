package com.cro.app.view.turma;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Turma;
import com.cro.app.model.util.DataAccessObject;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractListPage;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * @author Ednilson Brito Lopes
 */
@Route(value = "turma", layout = MainLayout.class)
@PageTitle(TurmaPage.PAGE_NAME)
@SuppressWarnings("serial")
public class TurmaPage
  extends AbstractListPage<Turma> {

  public static final String PAGE_NAME = "Turmas";

  @Override
  protected void navigateTo(String fragmentParameter) {
    UI.getCurrent().navigate(TurmaPage.class, fragmentParameter);
  }

  @Override
  public BeanGrid<Turma> createBeanGrid() {
    return new TurmaGrid();
  }

  @Override
  public BeanForm<Turma> createBeanForm() {
    return new TurmaForm();
  }

  @Override
  public String getArtigoEntidade() {
    return "a";
  }

  @Override
  public String getNomeEntidade() {
    return "Turma";
  }

  @Override
  public DataAccessObject<Turma> getDao() {
    return DataService.get().getTurmaDAO();
  }

  @Override
  public void createFilter(String filter) {

  }

}
