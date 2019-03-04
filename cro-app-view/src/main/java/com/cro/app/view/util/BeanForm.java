package com.cro.app.view.util;

import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.cro.app.model.util.AbstractBasicEntity;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;

/**
 * Base para criação de formulários de edição
 * 
 * @author Ednilson Brito Lopes
 *
 * @param <T>
 *            tipo da entidade a ser edidade
 */
public class BeanForm<T extends AbstractBasicEntity<?>> extends Div {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -7657180476942237003L;

	private VerticalLayout buttonLayout;

	private T object;
	private Binder<T> binder;
	private Button saveButton;
	private Button discardButton;
	private Button cancelButton;
	private Button deleteButton;

	private AbstractViewLogic<T> viewLogic;

	/**
	 * Tipo da classe passada por parâmetro
	 */
	private Class<T> type;

	public BeanForm(AbstractViewLogic<T> viewLogic) {
		setClassName("default-form");

		this.binder = new BeanValidationBinder<>(getType());
		this.binder.bindInstanceFields(this);
		this.viewLogic = viewLogic;

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
	protected TextField createDecimalField(String caption, String propertyName) {
		TextField field = new TextField(caption);
		binder.forField(field).withConverter(new DecimalConverter()).bind(propertyName);
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
	protected TextField createIntegerField(String caption, String propertyName) {
		TextField field = new TextField(caption);
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
	protected ComboBox<T> createComboBox(String caption, String propertyName, List<T> items) {
		ComboBox<T> combo = new ComboBox<>(caption, items);
		binder.forField(combo).bind(propertyName);
		return combo;
	}

	protected Component createButtonBar() {
		buttonLayout = new VerticalLayout();
		buttonLayout.setSizeUndefined();
		buttonLayout.add(createSaveButton(), createDiscartChagesButton(), createCancelButton(), createDeleteButton());

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
			if (object != null && binder.writeBeanIfValid(object)) {
				viewLogic.saveObject(object);
			}
		});
		return saveButton;
	}

	protected Button createDiscartChagesButton() {
		discardButton = new Button("Descartar Alterações");
		discardButton.setWidth("100%");
		discardButton.addClickListener(e -> viewLogic.editObject(object));
		return discardButton;
	}

	protected Button createCancelButton() {
		cancelButton = new Button("Cancelar");
		cancelButton.setWidth("100%");
		cancelButton.addClickListener(e -> viewLogic.cancelObject());
		getElement().addEventListener("keydown", event -> viewLogic.cancelObject()).setFilter("event.key == 'Escape'");
		return cancelButton;
	}

	protected Button createDeleteButton() {
		deleteButton = new Button("Deletar");
		deleteButton.setWidth("100%");
		deleteButton.addThemeVariants(ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_PRIMARY);
		deleteButton.addClickListener(event -> {
			if (object != null) {
				viewLogic.deleteObject(object);
			}
		});
		return deleteButton;
	}

	/**
	 * @return o tipo {@link Class} da entidade usada como parâmetro na classe
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getType() {
		if (this.type == null) {
			this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return type;
	}

	public void editObject(T obj) {
		try {
			if (obj == null) {
				obj = getType().newInstance();
			}
			deleteButton.setVisible(!obj.isNewObject());
			object = obj;
			binder.readBean(obj);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna o valor da propriedade object.
	 * 
	 * @return {@link #object}
	 */
	public T getObject() {
		return object;
	}

	/**
	 * Configura o valor da propriedade object.
	 * 
	 * @param object
	 *            atualiza {@link #object}
	 */
	public void setObject(T object) {
		this.object = object;
	}

	private static class DecimalConverter extends StringToBigDecimalConverter {

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

	private static class IntegerConverter extends StringToIntegerConverter {

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

}
