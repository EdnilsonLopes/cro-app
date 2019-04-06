package com.cro.app.view.professor;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Disciplina;
import com.cro.app.model.entidade.Professor;
import com.cro.app.view.disciplina.DisciplinaGrid;
import com.cro.app.view.turma.TurmaGrid;
import com.cro.app.view.util.BeanForm;
import com.cro.app.view.util.component.TabSheet;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


/**
 * Formulário para edição de {@link Professor}
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class ProfessorForm
  extends BeanForm<Professor> {

  private VerticalLayout content;

  private TurmaGrid turmaGrid;

  private DisciplinaGrid disciplinaGrid;

  public ProfessorForm() {
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
                VaadinIcon.CLIPBOARD.create(),
                createGeralLayout());
    tabs.addTab("Disciplinas",
                VaadinIcon.BOOK.create(),
                createDisciplinasLayout());
    tabs.addTab("Turmas",
                VaadinIcon.ACADEMY_CAP.create(),
                createTurmasLayout());

    return tabs;
  }

  private Component createGeralLayout() {
    VerticalLayout layout = new VerticalLayout();
    layout.add(createNomeEdit());
    layout.add(createFormacaoEdit());
    layout.add(createDataNascimentoEdit());

    return layout;
  }

  private Component createNomeEdit() {
    TextField nomeField = createTextField("Nome", "nome");
    return nomeField;
  }

  private Component createFormacaoEdit() {
    TextField formacaoField = createTextField("Formação", "formacao");
    return formacaoField;
  }

  private Component createDataNascimentoEdit() {
    DatePicker dateField =
      createDateField("Data de Nascimento", "dataNascimento");
    return dateField;
  }

  private Component createDisciplinasLayout() {
    return createCheckBoxGroupLayout("Selecione as Disciplinas que o professor(a) leciona",
                                     "chkDisc",
                                     "disciplinas",
                                     DataService.get().getDisciplinaDAO().loadAll());
  }

  CheckboxGroup<Disciplina> cbgDisciplinas;

  private Component createDisciplinasCheckBox() {
    cbgDisciplinas =
      createCheckBoxGroup("disciplinas",
                          DataService.get().getDisciplinaDAO().loadAll());
    return cbgDisciplinas;
  }

  private Component createTurmasLayout() {
    VerticalLayout layout = new VerticalLayout();
    turmaGrid = new TurmaGrid();
    turmaGrid.addClassName("grid-in-form");
    layout.add(turmaGrid);
    return layout;
  }

  @Override
  protected void doEdit() {
    if (getObject() != null) {
      //      if (getObject().getDisciplinas() != null) {
      //        cbgDisciplinas.updateSelection(getObject().getDisciplinas(),
      //                                       Collections.emptySet());
      //      }
      if (getObject().getTurmas() != null)
        turmaGrid.setItems(getObject().getTurmas());
    }
  }

}
