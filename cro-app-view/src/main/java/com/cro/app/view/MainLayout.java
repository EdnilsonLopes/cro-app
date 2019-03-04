package com.cro.app.view;


import com.cro.app.view.disciplina.DisciplinaPage;
import com.cro.app.view.turma.TurmaPage;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


/**
 * Layout Principal para as paginas da aplicação
 * 
 * @author Ednilson Brito Lopes
 *
 */
@HtmlImport("css/shared-styles.html")
@Theme(value = Lumo.class, variant = Lumo.LIGHT)
@PWA(name = "Colégio Raphael Oliveira", shortName = "CRO")
public class MainLayout
  extends FlexLayout
  implements RouterLayout {

  /**
   * Serial
   */
  private static final long serialVersionUID = 1259529833531816844L;

  private Menu menu;

  /**
   * Contrutor. Onde serão adicionados os links para as páginas
   */
  public MainLayout() {
    setSizeFull();
    setClassName("main-layout");

    menu = new Menu();
    menu.addView(DisciplinaPage.class,
                 DisciplinaPage.PAGE_NAME,
                 VaadinIcon.BOOK.create());
    menu.addView(TurmaPage.class,
                 TurmaPage.PAGE_NAME,
                 VaadinIcon.ACADEMY_CAP.create());

    add(menu);
  }

}
