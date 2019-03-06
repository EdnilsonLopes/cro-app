package com.cro.app.authentication;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


/**
 * UI content when the user is not logged in yet.
 */
@Route("Login")
@PageTitle("Login")
@HtmlImport("css/shared-styles.html")
public class LoginScreen
  extends FlexLayout {

  /**
   * Serial
   */
  private static final long serialVersionUID = -4055686491024833774L;
  private TextField username;
  private PasswordField password;
  private Button login;
  private Button forgotPassword;
  private AccessControl accessControl;

  public LoginScreen() {
    accessControl =
      AccessControlFactory.getInstance().createAccessControl();
    buildUI();
    username.focus();
  }

  private void buildUI() {
    setSizeFull();
    setClassName("login-screen");

    // login form, centered in the available part of the screen
    Component loginForm = buildLoginForm();

    // layout to center login form when there is sufficient screen space
    FlexLayout centeringLayout = new FlexLayout();
    centeringLayout.setSizeFull();
    centeringLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    centeringLayout.setAlignItems(Alignment.CENTER);
    centeringLayout.add(loginForm);

    // information text about logging in
    Component loginInformation = buildLoginInformation();

    add(loginInformation);
    add(centeringLayout);
  }

  private Component buildLoginForm() {
    FormLayout loginForm = new FormLayout();

    loginForm.setWidth("310px");

    loginForm.addFormItem(username = new TextField(), "Username");
    username.setWidth("15em");
    username.setValue("admin");
    loginForm.add(new Html("<br/>"));
    loginForm.addFormItem(password = new PasswordField(), "Password");
    password.setWidth("15em");

    HorizontalLayout buttons = new HorizontalLayout();
    loginForm.add(new Html("<br/>"));
    loginForm.add(buttons);

    buttons.add(login = new Button("Login"));
    login.addClickListener(event -> login());
    loginForm.getElement().addEventListener("keypress",
                                            event -> login()).setFilter("event.key == 'Enter'");
    login.addThemeVariants(ButtonVariant.LUMO_SUCCESS,
                           ButtonVariant.LUMO_PRIMARY);

    buttons.add(forgotPassword = new Button("Esqueceu a senha?"));
    forgotPassword.addClickListener(event -> showNotification(new Notification("A ser implementado")));
    forgotPassword.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

    return loginForm;
  }

  private Component buildLoginInformation() {
    VerticalLayout loginInformation = new VerticalLayout();
    loginInformation.setClassName("login-information");

    H1 loginInfoHeader = new H1("Informações de Login");
    Span loginInfoText = new Span(
                                  "Colégio Raphael Oliveira. " +
                                    "Os menus apareceram de acordo " +
                                    "com as funcionalidade disponíveis para o usuário.");
    loginInformation.add(loginInfoHeader);
    loginInformation.add(loginInfoText);

    return loginInformation;
  }

  private void login() {
    login.setEnabled(false);
    try {
      if (accessControl.signIn(username.getValue(), password.getValue())) {
        getUI().get().navigate("");
      }
      else {
        showNotification(new Notification("Nome de usuário e/ou senha errado(s)."));
        username.focus();
      }
    }
    finally {
      login.setEnabled(true);
    }
  }

  private void showNotification(Notification notification) {
    // keep the notification visible a little while after moving the
    // mouse, or until clicked
    notification.setDuration(2000);
    notification.open();
  }
}
