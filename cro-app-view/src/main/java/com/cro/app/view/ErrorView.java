package com.cro.app.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.ParentLayout;

import javax.servlet.http.HttpServletResponse;

/**
 * View mostrada caso não encontre uma view da aplicação
 */
@ParentLayout(MainLayout.class)
public class ErrorView extends VerticalLayout implements HasErrorParameter<NotFoundException> {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1352394686585688567L;

	private Span explanation;

	public ErrorView() {
		H1 header = new H1("Página não encontrada.");
		add(header);

		explanation = new Span();
		add(explanation);
	}

	@Override
	public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
		explanation.setText("Não pode encontrar a página'" + event.getLocation().getPath() + "'.");
		return HttpServletResponse.SC_NOT_FOUND;
	}
}
