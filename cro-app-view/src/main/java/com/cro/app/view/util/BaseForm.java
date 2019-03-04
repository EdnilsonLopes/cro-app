package com.cro.app.view.util;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.data.converter.StringToBigDecimalConverter;
import com.vaadin.flow.data.converter.StringToIntegerConverter;


@SuppressWarnings("serial")
public class BaseForm
  extends Div {

  protected static class DecimalConverter
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

  protected static class IntegerConverter
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

}
