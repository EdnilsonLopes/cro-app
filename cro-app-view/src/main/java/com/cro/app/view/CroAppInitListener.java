package com.cro.app.view;

import com.cro.app.authentication.AccessControl;
import com.cro.app.authentication.AccessControlFactory;
import com.cro.app.authentication.LoginScreen;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

/**
 * Esta classe é usada para iniciar os serviços da aplicação. Deve ser mapeada
 * em META-INF/services
 * 
 * @author Ednilson Brito Lopes
 *
 */
public class CroAppInitListener implements VaadinServiceInitListener {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = -5531511663010207537L;

	@Override
	public void serviceInit(ServiceInitEvent initEvent) {
		final AccessControl accessControl = AccessControlFactory.getInstance().createAccessControl();

		initEvent.getSource().addUIInitListener(uiInitEvent -> {
			uiInitEvent.getUI().addBeforeEnterListener(enterEvent -> {
				if (!accessControl.isUserSignedIn() && !LoginScreen.class.equals(enterEvent.getNavigationTarget()))
					enterEvent.rerouteTo(LoginScreen.class);
			});
		});
	}
}
