package com.cro.app.view.util.component;


import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;


/**
 * Permite a seleção de páginas através de abas. Um componente {@link Tabs} não
 * possui as páginas subjacentes. Esta versão já inclui as páginas.
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class TabSheet
  extends VerticalLayout {

  /**
   * Mapeia tabs para páginas.
   */
  private Map<Tab, Component> tabsToPages = new HashMap<Tab, Component>();
  /**
   * Mapeia páginas para tabs.
   */
  private Map<Component, Tab> pagesToTabs = new HashMap<Component, Tab>();

  /**
   * Componente para paginação.
   */
  private Tabs tabs;

  /**
   * Construtor.
   */
  public TabSheet() {
    super();
    // Uma altura padrão é definida porque geralmente os componentes inseridos
    // tentarão ocupar toda altura disponível. Se a altura deste componente for
    // indefinida então haverá um comportamento de dimensionamento indesejado
    // nas páginas inseridas com height = 100%. Caso se deseje criar um TabSheet
    // que se dimensione conforme o conteúdo, então o height deve ser definido
    // para null.
    setHeight("100%");
    setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    add(createTabs());
    tabs.addSelectedChangeListener(ev -> {
      tabsToPages.keySet().forEach(t -> {
        tabsToPages.get(t).setVisible(t == tabs.getSelectedTab());
      });
    });
    setPadding(false);
    setSpacing(false);
  }

  /**
   * Cria o componente {@link #tabs}.
   * 
   * @return o componente criado.
   */
  private Tabs createTabs() {
    return tabs = new Tabs();

  }

  /**
   * Adiciona uma tab e o componente a ser controlado pela sua seleção.
   * 
   * @param caption texto
   * @param icon    ícone.
   * @param page    componente a ser apresentado.
   */
  public void addTab(String caption, Icon icon, Component page) {
    Tab tab;
    if (icon == null) {
      tab = new Tab(new Label(caption));
    }
    else {
      tab = new Tab(icon, new Label(caption));
    }
    tabsToPages.put(tab, page);
    pagesToTabs.put(page, tab);
    tabs.add(tab);
    add(page);
    expand(page);
    page.setVisible(tabs.getSelectedTab() == tab);
  }

  /**
   * Obtém a {@link Tab} correspondente a uma página informada.
   * 
   * @param page página para a qual a TAB será retornada.
   * @return a Tab para a página informada.
   */
  public Tab getTab(Component page) {
    return pagesToTabs.get(page);
  }

  /**
   * Obtém a {@link Tab} que ocupa uma posição informada.
   * 
   * @param index posição da aba a ser retornada, começando com 0.
   * @return a Tab para a posição informada.
   */
  public Tab getTab(int index) {
    return (Tab) tabs.getComponentAt(index);
  }

}
