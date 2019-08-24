package com.cro.app.view.aluno;


import com.cro.app.model.DataService;
import com.cro.app.model.entidade.Aluno;
import com.cro.app.model.entidade.Turma;
import com.cro.app.view.util.BeanForm;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route("preMatricula")
@PageTitle(AlunoPreMatriculaPage.PAGE_NAME)
public class AlunoPreMatriculaPage
  extends BeanForm<Aluno> {

  /**
   * Serial
   */
  private static final long serialVersionUID = 1L;

  public static final String PAGE_NAME = "Pré Matrícula";

  /**
   * Aluno que será pré matriculado  
   */
  private Aluno aluno = new Aluno();

  /**
   * Campo para editar o nome
   */
  private TextField nomeField;

  private TextField cpfField;

  private TextField nomePaiField;

  private TextField nomeMaeField;

  private TextField telefoneField;

  private TextField telefone2Field;

  private ComboBox<Turma> turmaCbx;

  private DatePicker dataNascimento;

  public AlunoPreMatriculaPage() {
    setObject(aluno);
    Div divContent = new Div();
    divContent.addClassName("pre-matricula-form");
    divContent.setTitle("Pré-Matricula de Alunos para o Colégio Raphael Oliveira.");
    VerticalLayout content = new VerticalLayout();
//    content.setSizeFull();
    content.setPadding(true);
    FormLayout layout = new FormLayout();
    layout.setSizeFull();
    layout.setResponsiveSteps(new ResponsiveStep("300px", 1),
                          new ResponsiveStep("600px", 2),
                          new ResponsiveStep("900px", 4),
                          new ResponsiveStep("1200px", 6));
    layout.add(createNomeEdit());
    layout.add(createCpfEdit());
    layout.add(createDataNascimentoEdit());
    layout.add(createTurmaCbx());
    layout.add(createNomeMae());
    layout.add(createNomePai());
    layout.add(createCepField());
	layout.add(createUfField());
	layout.add(createCidadeField());
	layout.add(createBairroField());
	layout.add(createLogradouroField());
	layout.add(createNumeroField());
	layout.add(createComplementoField());
    content.add(layout);
    add(content);
    getBinder().bindInstanceFields(this);
  }

  private Component createNomeEdit() {
    nomeField = createTextField("Nome Completo", "nome");
    return nomeField;
  }

  private Component createCpfEdit() {
    cpfField = createTextField("CPF", "cpf");
    cpfField.setPattern("[0-9]*");
    cpfField.setMaxLength(11);
    return cpfField;
  }

  private Component createNomeMae() {
    nomeMaeField = createTextField("Nome da Mãe", "nomeMae");
    return nomeMaeField;
  }

  private Component createNomePai() {
    nomePaiField = createTextField("Nome do Pai", "nomeMae");
    return nomePaiField;
  }

  /**
   * @return um componente para selecionar a turma
   */
  private Component createTurmaCbx() {
    turmaCbx = createComboBox("Turma",
                              "turma",
                              DataService.get().getTurmaDAO().loadAll());
    return turmaCbx;
  }

  private Component createDataNascimentoEdit() {
    dataNascimento =
      createDateField("Data de Nascimento", "dataNascimento");
    return dataNascimento;
  }

}
