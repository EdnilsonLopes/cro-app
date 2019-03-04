package com.cro.app.view.disciplina;


import com.cro.app.model.entidade.Disciplina;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;


/**
 * Formulário para edição de uma Disciplina
 * @author Ednilson Brito Lopes
 */
@SuppressWarnings("serial")
public class DisciplinaForm
  extends Div {

  private VerticalLayout content;

  private TextField nomeField;
  private TextArea descricaoField;

  private Button save;
  private Button discard;
  private Button cancel;
  private Button delete;

  private DisciplinaViewLogic viewLogic;

  private Disciplina currentDisciplina;

  private Binder<Disciplina> binder;

  public DisciplinaForm(DisciplinaViewLogic disciplinaViewLogic) {
    setClassName("product-form");

    content = new VerticalLayout();
    content.setSizeUndefined();
    add(content);
    this.viewLogic = disciplinaViewLogic;

    nomeField = new TextField("Nome");
    nomeField.setWidth("100%");
    nomeField.setRequired(true);
    nomeField.setValueChangeMode(ValueChangeMode.EAGER);
    content.add(nomeField);

    descricaoField = new TextArea("Descrição");
    descricaoField.setWidth("100%");
    descricaoField.setRequired(true);
    descricaoField.setValueChangeMode(ValueChangeMode.EAGER);
    content.add(descricaoField);

    binder = new BeanValidationBinder<>(Disciplina.class);
    binder.forField(nomeField).bind("nome");
    binder.forField(descricaoField).bind("descricao");
    binder.bindInstanceFields(this);

    binder.addStatusChangeListener(event -> {
      boolean isValid = !event.hasValidationErrors();
      boolean hasChanges = binder.hasChanges();
      save.setEnabled(hasChanges && isValid);
      discard.setEnabled(hasChanges);
    });

    save = new Button("Salvar");
    save.setWidth("100%");
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    save.addClickListener(event -> {
      if (currentDisciplina != null &&
        binder.writeBeanIfValid(currentDisciplina)) {
        viewLogic.saveDisciplina(currentDisciplina);
      }
    });

    discard = new Button("Descartar Alterações");
    discard.setWidth("100%");
    discard.addClickListener(event -> viewLogic.editDisciplina(currentDisciplina));

    cancel = new Button("Cancelar");
    cancel.setWidth("100%");
    cancel.addClickListener(event -> viewLogic.cancelDisciplina());
    getElement().addEventListener("keydown",
                                  event -> viewLogic.cancelDisciplina()).setFilter("event.key == 'Escape'");

    delete = new Button("Deletar");
    delete.setWidth("100%");
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_PRIMARY);
    delete.addClickListener(event -> {
      if (currentDisciplina != null) {
        viewLogic.deleteDisciplina(currentDisciplina);
      }
    });

    content.add(save, discard, delete, cancel);
  }

  public void editDisciplina(Disciplina disciplina) {
    if (disciplina == null) {
      disciplina = new Disciplina();
    }
    delete.setVisible(!disciplina.isNewObject());
    currentDisciplina = disciplina;
    binder.readBean(disciplina);
  }

}
