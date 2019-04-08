package com.cro.app.view.util;


import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import com.cro.app.model.util.AbstractBasicEntity;
import com.cro.app.model.util.webService.WebServiceCep;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.LocalDateToDateConverter;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.data.value.ValueChangeMode;


/**
 * Base para criação de formulários de edição
 * 
 * @author Ednilson Brito Lopes
 *
 * @param <T>
 *            tipo da entidade a ser edidade
 */
public class BeanForm<T extends AbstractBasicEntity>
  extends Div {

  /**
   * Serial
   */
  private static final long serialVersionUID = -7657180476942237003L;

  private VerticalLayout buttonLayout;

  private T currentObject;
  private Binder<T> binder;
  private Button saveButton;
  private Button discardButton;
  private Button cancelButton;
  private Button deleteButton;

  private AbstractViewLogic<T> viewLogic;

  private TextField cepField;
  private TextField ufField;
  private TextField logradouroField;
  private TextField bairroField;
  private TextField cidadeField;

  /**
   * Tipo da classe passada por parâmetro
   */
  private Class<T> type;

  public BeanForm() {
    setClassName("bean-form");
    this.binder = new BeanValidationBinder<>(getType());
  }

  public BeanForm(AbstractViewLogic<T> viewLogic) {
    setClassName("bean-form");
    this.binder = new BeanValidationBinder<>(getType());
    this.viewLogic = viewLogic;

  }

  public void inicializarBinder() {
    this.binder.bindInstanceFields(this);

    binder.addStatusChangeListener(event -> {
      boolean isValid = !event.hasValidationErrors();
      boolean hasChanges = binder.hasChanges();
      saveButton.setEnabled(hasChanges && isValid);
      discardButton.setEnabled(hasChanges);
    });
  }

  /**
   * Cria um campo para edição de números decimais e já faz o bind com a
   * propriedade da classe
   * 
   * @param caption
   *            label do campo
   * @param propertyName
   *            nome da propriedada da entidade que será feito o bind
   * @return um campo para editar valores decimais
   */
  protected TextField createDecimalField(String caption,
                                         String propertyName) {
    TextField field = new TextField(caption);
    field.setValueChangeMode(ValueChangeMode.EAGER);
    binder.forField(field).withConverter(new DecimalConverter()).bind(propertyName);
    return field;
  }

  /**
   * Cria um campo de texto com altura maior que a de um simples {@link TextField}
   * @param caption
   *            label do campo
   * @param propertyName
   *            nome da propriedada da entidade que será feito o bind
   * @return um campo para editar valores decimais
   */
  protected <U> CheckboxGroup<U> createCheckBoxGroup(String propertyName,
                                                     Collection<U> items) {
    CheckboxGroup<U> checkboxGroup = new CheckboxGroup<>();
    checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
    checkboxGroup.setItems(items);
    binder.forField(checkboxGroup).bind(propertyName);
    return checkboxGroup;
  }

  protected <U> Component createCheckBoxGroupLayout(String caption,
                                                    String idField,
                                                    String propertyName,
                                                    Collection<U> items) {
    VerticalLayout layout = new VerticalLayout();
    CheckboxGroup<U> checkboxGroup =
      createCheckBoxGroup(propertyName, items);
    checkboxGroup.setId(idField);
    Label label = new Label(caption);
    label.setClassName("vaadin-label");
    label.setFor(checkboxGroup);
    layout.add(label, checkboxGroup);
    return layout;
  }

  protected TextArea createTextArea(String caption,
                                    String propertyName) {
    TextArea field = new TextArea(caption);
    field.setValueChangeMode(ValueChangeMode.EAGER);
    binder.forField(field).bind(propertyName);
    return field;
  }

  /**
   * Cria um campo para edição de textos e já faz o bind com a propriedade da
   * classe
   * 
   * @param caption
   *            label do campo
   * @param propertyName
   *            nome da propriedada da entidade que será feito o bind
   * @return um campo para editar textos
   */
  protected TextField createTextField(String caption, String propertyName) {
    TextField field = new TextField(caption);
    field.setValueChangeMode(ValueChangeMode.EAGER);
    binder.forField(field).bind(propertyName);
    return field;
  }

  /**
   * Cria um campo para edição de valores inteiros e já faz o bind com a
   * propriedade da classe
   * 
   * @param caption
   *            label do campo
   * @param propertyName
   *            nome da propriedada da entidade que será feito o bind
   * @return um campo para editar valores inteiros
   */
  protected TextField createIntegerField(String caption,
                                         String propertyName) {
    TextField field = new TextField(caption);
    field.setValueChangeMode(ValueChangeMode.EAGER);
    binder.forField(field).withConverter(new IntegerConverter()).bind(propertyName);
    return field;
  }

  /**
   * Cria uma ComboBox e já faz o bind com a propriedade da classe
   * 
   * @param caption
   *            label do campo
   * @param propertyName
   *            nome da propriedada da entidade que será feito o bind
   * @return uma ComboBox
   */
  protected <U> ComboBox<U> createComboBox(String caption,
                                           String propertyName,
                                           List<U> items) {
    ComboBox<U> combo = new ComboBox<>(caption, items);
    binder.forField(combo).bind(propertyName);
    return combo;
  }

  /**
   * Cria um componente para data e faz o bind com a propriedade da classe
   * @param caption
   *            label do campo
   * @param propertyName
   *            nome da propriedada da entidade que será feito o bind
   * @return um {@link DatePicker}
   */
  protected DatePicker createDateField(String caption,
                                       String propertyName) {
    DatePicker field = new DatePicker(caption);
    binder.forField(field).withConverter(new LocalDateToDateConverter()).bind(propertyName);
    return field;
  }

  protected Component createEnderecoEdit() {
    FormLayout layout = new FormLayout();
    layout.setResponsiveSteps(new FormLayout.ResponsiveStep("50px", 1),
                              new FormLayout.ResponsiveStep("150px", 3),
                              new FormLayout.ResponsiveStep("250px", 5));
    layout.add(createCepField());
    layout.add(createUfField());
    layout.add(createCidadeField());
    layout.add(createBairroField());
    layout.add(createLogradouroField());
    layout.add(createNumeroField());
    layout.add(createComplementoField());
    return layout;
  }

  private TextField createCepField() {
    cepField = new TextField("CEP");
    cepField.setPattern("[0-9]*");
    cepField.setMaxLength(8);
    cepField.setMinLength(8);
    binder.forField(cepField).bind("cep");
    cepField.getElement().setAttribute("colspan", "1");
    cepField.addValueChangeListener(e -> {
      if (e.getValue() != null && e.getValue().length() == 8) {
        WebServiceCep cep = WebServiceCep.searchCep(e.getValue());
        ufField.setValue(cep.getUf());
        cidadeField.setValue(cep.getCidade());
        logradouroField.setValue(cep.getLogradouroFull());
        bairroField.setValue(cep.getBairro());
      }
    });
    return cepField;
  }

  private TextField createCidadeField() {
    cidadeField = new TextField("Cidade");
    cidadeField.setReadOnly(true);
    cidadeField.getElement().setAttribute("colspan", "3");
    binder.forField(cidadeField).bind("cidade");
    return cidadeField;
  }

  private TextField createUfField() {
    ufField = new TextField("UF");
    ufField.setReadOnly(true);
    ufField.getElement().setAttribute("colspan", "1");
    binder.forField(ufField).bind("uf");
    return ufField;
  }

  private TextField createLogradouroField() {
    logradouroField = new TextField("Logradouro");
    logradouroField.setMaxLength(200);
    logradouroField.getElement().setAttribute("colspan", "3");
    binder.forField(logradouroField).bind("logradouro");
    return logradouroField;
  }

  private TextField createBairroField() {
    bairroField = new TextField("Bairro");
    bairroField.setMaxLength(200);
    bairroField.getElement().setAttribute("colspan", "2");
    binder.forField(bairroField).bind("bairro");
    return bairroField;
  }

  private TextField createNumeroField() {
    TextField field = new TextField("Número");
    field.setMaxLength(10);
    field.getElement().setAttribute("colspan", "1");
    binder.forField(field).bind("numeroEndereco");
    return field;
  }

  private TextField createComplementoField() {
    TextField field = new TextField("Complemento");
    field.getElement().setAttribute("colspan", "4s");
    binder.forField(field).bind("complemento");
    return field;
  }

  protected Component createButtonBar() {
    buttonLayout = new VerticalLayout();
    buttonLayout.setSizeUndefined();
    buttonLayout.add(createSaveButton(),
                     createDiscartChagesButton(),
                     createCancelButton(),
                     createDeleteButton());
    buttonLayout.setWidth("100%");
    return buttonLayout;
  }

  /**
   * @return o botão para salvar
   */
  protected Button createSaveButton() {
    saveButton = new Button("Salvar");
    saveButton.setWidth("100%");
    saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    saveButton.addClickListener(e -> {
      if (currentObject != null && binder.writeBeanIfValid(currentObject)) {
        viewLogic.saveObject(currentObject);
      }
    });
    saveButton.setWidth("100%");
    return saveButton;
  }

  protected Button createDiscartChagesButton() {
    discardButton = new Button("Descartar Alterações");
    discardButton.setWidth("100%");
    discardButton.addClickListener(e -> viewLogic.editObject(currentObject));
    discardButton.setWidth("100%");
    return discardButton;
  }

  protected Button createCancelButton() {
    cancelButton = new Button("Cancelar");
    cancelButton.setWidth("100%");
    cancelButton.addClickListener(e -> viewLogic.cancelObject());
    getElement().addEventListener("keydown",
                                  event -> viewLogic.cancelObject()).setFilter("event.key == 'Escape'");
    cancelButton.setWidth("100%");
    return cancelButton;
  }

  protected Button createDeleteButton() {
    deleteButton = new Button("Deletar");
    deleteButton.setWidth("100%");
    deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR,
                                  ButtonVariant.LUMO_PRIMARY);
    deleteButton.addClickListener(event -> {
      if (currentObject != null) {
        Dialog dialog = new Dialog();
        dialog.setCloseOnEsc(false);
        dialog.setCloseOnOutsideClick(false);
        Label messageLabel = new Label("Deseja realmente excluir?");
        VerticalLayout vl = new VerticalLayout(messageLabel);
        Button confirmButton = new Button("Sim", e -> {
          viewLogic.deleteObject(currentObject);
          dialog.close();
        });
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancelButton = new Button("Não", e -> {
          dialog.close();
        });
        HorizontalLayout buttonBar =
          new HorizontalLayout(confirmButton, cancelButton);
        vl.add(buttonBar);
        dialog.add(vl);
        dialog.open();
      }
    });
    deleteButton.setWidth("100%");
    return deleteButton;
  }

  /**
   * @return o tipo {@link Class} da entidade usada como parâmetro na classe
   */
  @SuppressWarnings("unchecked")
  private Class<T> getType() {
    if (this.type == null) {
      this.type =
        (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    return type;
  }

  public void editObject(T obj) {
    try {
      if (obj == null) {
        obj = getType().newInstance();
      }
      deleteButton.setVisible(!obj.isNewObject());
      currentObject = obj;
      binder.readBean(obj);
      doEdit();
    }
    catch (InstantiationException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  protected void doEdit() {
    //Opcional
  }

  /**
   * Retorna o valor da propriedade object.
   * 
   * @return {@link #currentObject}
   */
  public T getObject() {
    return currentObject;
  }

  /**
   * Configura o valor da propriedade object.
   * 
   * @param object
   *            atualiza {@link #currentObject}
   */
  public void setObject(T object) {
    this.currentObject = object;
  }

  private static class DecimalConverter
    extends StringToBigDecimalConverter {

    /**
     * Serial
     */
    private static final long serialVersionUID = -3291469744436486489L;

    public DecimalConverter() {
      super(BigDecimal.ZERO, "Valor inválido.");
    }

    @Override
    protected NumberFormat getFormat(Locale locale) {
      NumberFormat format = super.getFormat(locale);
      if (format instanceof DecimalFormat) {
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(2);
      }
      return format;
    }
  }

  private static class IntegerConverter
    extends StringToIntegerConverter {

    /**
     * Serial
     */
    private static final long serialVersionUID = 7042445253621503717L;

    public IntegerConverter() {
      super(0, "Valor inválido. Deve ser um número inteiro!");
    }

    @Override
    protected NumberFormat getFormat(Locale locale) {
      DecimalFormat format = new DecimalFormat();
      format.setMaximumFractionDigits(0);
      format.setDecimalSeparatorAlwaysShown(false);
      format.setParseIntegerOnly(true);
      format.setGroupingUsed(false);
      return format;
    }
  }

  public T getCurrentObject() {
    return currentObject;
  }

  public void setCurrentObject(T currentObject) {
    this.currentObject = currentObject;
  }

  public AbstractViewLogic<T> getViewLogic() {
    return viewLogic;
  }

  public void setViewLogic(AbstractViewLogic<T> viewLogic) {
    this.viewLogic = viewLogic;
  }

  /**
   * Retorna o valor da propriedade binder.
   * @return {@link #binder}
   */
  public Binder<T> getBinder() {
    return binder;
  }

}
