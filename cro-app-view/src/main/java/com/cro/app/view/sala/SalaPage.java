package com.cro.app.view.sala;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Sala;
import com.cro.app.model.util.DataAccessObject;
import com.cro.app.view.MainLayout;
import com.cro.app.view.util.AbstractListPage;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.BeanGrid;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * Página da entidade {@link Sala}
 * @author Ednilson Brito Lopes
 */
@Route(value = "sala", layout = MainLayout.class)
@PageTitle(SalaPage.PAGE_NAME)
public class SalaPage
  extends AbstractListPage<Sala> {

  /**
   * Serial
   */
  private static final long serialVersionUID = -1523631726591548784L;

  public static final String PAGE_NAME = "Sala";

  @Override
  protected void navigateTo(String fragmentParameter) {
    UI.getCurrent().navigate(SalaPage.class, fragmentParameter);
  }

  @Override
  public BeanGrid<Sala> createBeanGrid() {
    return new SalaGrid();
  }

  @Override
  public BeanForm<Sala> createBeanForm() {
    return new SalaForm();
  }

  @Override
  public String getArtigoEntidade() {
    return "a";
  }

  @Override
  public String getNomeEntidade() {
    return "Sala";
  }

  @Override
  public DataAccessObject<Sala> getDao() {
    return DataService.get().getSalaDAO();
  }

  @Override
  public void createFilter(String filter) {

  }

}
