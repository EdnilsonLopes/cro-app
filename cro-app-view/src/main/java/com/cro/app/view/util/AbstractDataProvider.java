package com.cro.app.view.util;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import com.cro.app.model.util.AbstractBasicEntity;
import com.vaadin.flow.data.provider.ListDataProvider;

public abstract class AbstractDataProvider<T extends AbstractBasicEntity<?>> extends ListDataProvider<T> {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 4727859476824871202L;

	private String filterText = "";

	public AbstractDataProvider(List<T> items) {
		super(items);
	}

	/**
	 * Salva o novo objeto
	 * 
	 * @param obj
	 *            será persistido
	 */
	public void save(T obj) {
		if (obj.isNewObject()) {
			insert(obj);
			refreshAll();
		} else {
			update(obj);
			refreshItem(obj);
		}
	}

	public void remove(T obj) {
		delete(obj);
		refreshAll();
	}

	public void setFilter(String filterText) {
		Objects.requireNonNull(filterText, "Filter text cannot be null.");
		if (Objects.equals(this.filterText, filterText.trim())) {
			return;
		}
		this.filterText = filterText.trim();
	}

	public Object getId(T obj) {
		Objects.requireNonNull(obj, "Cannot provide an id for a null product.");

		return obj.getId();
	}

	public abstract void insert(T obj);

	public abstract void update(T obj);

	public abstract void delete(T obj);
	
	private boolean passesFilter(Object object, String filterText) {
		return object != null && object.toString().toLowerCase(new Locale("PT", "BR")).contains(filterText);
	}

}
