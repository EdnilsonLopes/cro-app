package com.cro.app.view.turma;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Turma;
import com.cro.app.model.enumerator.SerieEnum;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.component.TabSheet;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


/**
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class TurmaForm
  extends BeanForm<Turma> {

  public TurmaForm() {
    VerticalLayout content = new VerticalLayout();
    content.setHeight("100%");
    content = new VerticalLayout();
    content.setSizeUndefined();
    add(content);

    content.add(createTabsLayout());

    content.add(createButtonBar());
    inicializarBinder();
  }

  private Component createTabsLayout() {
    TabSheet tabs = new TabSheet();
    tabs.addTab("Geral",
                VaadinIcon.CLIPBOARD_TEXT.create(),
                createTabGeral());
    tabs.addTab("Alunos", VaadinIcon.CHILD.create(), createTabAlunos());
    return tabs;
  }

  private Component createTabGeral() {
    VerticalLayout layout = new VerticalLayout();
    layout.setSpacing(false);
    layout.setPadding(false);
    layout.setMargin(false);
    layout.setSizeUndefined();
    FormLayout fl = new FormLayout();
    fl.setResponsiveSteps(new ResponsiveStep("0", 1),
                          new ResponsiveStep("21em", 2));
    fl.setWidth("100%");
    fl.add(createSerieCbx(), createNomeEdit());
    layout.add(fl,
               createProfessorCbx(),
               createSalaCbx(),
               createDescricaoEdit());
    return layout;
  }

  private Component createTabAlunos() {
    VerticalLayout tabAlunos = new VerticalLayout();
    tabAlunos.setSpacing(false);
    tabAlunos.setPadding(false);
    tabAlunos.setMargin(false);
    AlunoTurmaGrid grid = new AlunoTurmaGrid();
    grid.addClassName("grid-in-form");
    grid.setItems(DataService.get().getAlunoDAO().loadAll());
    tabAlunos.add(grid);
    tabAlunos.expand(grid);
    return grid;
  }

  private Component createSerieCbx() {
    ComboBox<SerieEnum> cbxSerie =
      createComboBox("Serie", "serie", SerieEnum.getValues());
    cbxSerie.setWidth("100%");
    return cbxSerie;
  }

  private Component createSalaCbx() {
    return createComboBox("Sala",
                          "sala",
                          DataService.get().getSalaDAO().loadAll());
  }

  private Component createProfessorCbx() {
    return createComboBox("Conselheiro",
                          "professor",
                          DataService.get().getProfessorDAO().loadAll());
  }

  private Component createNomeEdit() {
    TextField field = createTextField("Nome", "nome");
    field.setMaxLength(2);
    return field;
  }

  private Component createDescricaoEdit() {
    TextArea field = createTextArea("Descrição", "descricao");
    field.setWidth("100%");
    return field;
  }

}
