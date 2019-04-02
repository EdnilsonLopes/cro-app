package com.cro.app.view.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cro.app.model.util.AbstractBasicEntity;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;

public class BeanGrid<T extends AbstractBasicEntity> extends Grid<T> {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6801616796253209084L;

	/**
	 * Construtor
	 */
	public BeanGrid() {
		setSizeFull();
		addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
	}

	/**
	 * @return o objeto selecionado no grid
	 */
	public T getSelectedRow() {
		return asSingleSelect().getValue();
	}

	public void refresh(T obj) {
		getDataCommunicator().refresh(obj);
	}

	protected String getDateInFormat(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}
}