/*
 * Copyright (c) 2007 Agile-Works
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Agile-Works. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Agile-Works.
 */
package com.aw.swing.mvp.binding.component;

import com.aw.core.cache.loader.MetaLoader;
import com.aw.core.cache.support.DropDownFormatter;
import com.aw.core.context.AWBaseContext;
import com.aw.core.format.*;
import com.aw.support.ObjectConverter;
import com.aw.swing.mvp.binding.BindingComponent;
import com.aw.swing.mvp.binding.BndFormattedComponent;
import com.aw.swing.mvp.binding.InputCmpMgr;
import com.aw.swing.mvp.binding.component.support.JTextFieldDocumentDecorator;
import com.aw.swing.mvp.binding.component.support.ValidatorActionListener;
import com.aw.swing.mvp.binding.component.support.ValidatorItemListener;
import com.aw.swing.mvp.binding.component.support.table.function.GridFunction;
import com.aw.swing.mvp.validation.Validator;
import com.aw.swing.mvp.validation.support.AWInputVerifier;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import com.aw.swing.mvp.validation.support.ReflectionValidator;
import com.aw.swing.mvp.validation.support.Rule;
import org.springframework.beans.NotWritablePropertyException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Date: Aug 16, 2007
 */
public class BndIJTextField extends BndFormattedComponent<JTextComponent> {

    protected static final boolean defaultToUpperCase = true;

    protected static final boolean defaultAutoSelectText = true;

    protected static final boolean defaultTrim = true;
    /**
     * Used to provide format and transformations support
     */
    private boolean helperFocusListenerInstalled = false;

    private boolean autoSelectTextOnFocusGain = defaultAutoSelectText;

    protected GridFunction gridFunction;
    protected int gridFunctionIdx;


    public BndIJTextField(InputCmpMgr inputCmpMgr, JTextComponent jInputComponent, String fieldName) {
        super(inputCmpMgr, jInputComponent, fieldName);
        if (autoSelectTextOnFocusGain)
            enableAutoSelectText();
    }

    public BndIJTextField(InputCmpMgr inputCmpMgr, JTextComponent jInputComponent) {
        super(inputCmpMgr, jInputComponent);
        if (autoSelectTextOnFocusGain)
            enableAutoSelectText();
    }

    public BndIJTextField(InputCmpMgr inputCmpMgr, JTextComponent jInputComponent, Object bean, String fieldName) {
        super(inputCmpMgr, jInputComponent, bean, fieldName);
        if (autoSelectTextOnFocusGain)
            enableAutoSelectText();
    }

    /**
     * Initialize the jComponent
     */
    public void initComponent() {
        super.initComponent();
        getJComponent().setInputVerifier(AWInputVerifier.getInstance());
        PropertyValidator propertyValidator = validator.getPropertyValidator();
        if (getJComponent() instanceof JTextArea)
            propertyBinding.setToUpperCase(null);//accept any case

        JTextFieldDocumentDecorator.decorate(getJComponent(), propertyValidator, propertyBinding);

        if (propertyValidator.isDateFormat()) {
            jComponent.setToolTipText(AWBaseContext.instance().getConfigInfoProvider().getDateFormatLabel());
        }


        if (propertyBinding.getFormatter() != null) {
            logger.info("Formatter " + this.getFieldName() + " - " + propertyBinding.getFormatter());
            formatter = propertyBinding.getFormatter();
        }


        if (propertyBinding.getTrim() == null) {
            propertyBinding.setTrim(defaultTrim);
        }

        if (getJComponent() instanceof JTextArea) {
            ((JTextArea) getJComponent()).setLineWrap(true);
            ((JTextArea) getJComponent()).setWrapStyleWord(true);
/*
            if (propertyBinding.isLineWrap()) {
                ((JTextArea) getJComponent()).setLineWrap(true);
            }
            if (propertyBinding.isWrapStyleWord()) {
                ((JTextArea) getJComponent()).setWrapStyleWord(propertyBinding.isLineWrap());
            }
*/
        }

    }


    public void acceptOnlyLetters() {
        propertyBinding.setOnlyLetters(true);
    }

    protected void setFormattedTextToJComponent(String formattedText) {
        JTextComponent textField = jComponent;
        if (dropDownValues.size() > 0) {
            formattedText = (String) getValueDrowList(formattedText);
        }
        textField.setText(formattedText);
    }


    /**
     * Set the value of the JInputComponent to the bean
     */
    public void setValueToBean() {
        Object value = getValue();
        if (logger.isDebugEnabled())
            logger.debug("[" + this.fieldName + "] Cmpt-->Bean:" + value);
        try {

            if (!(value instanceof String)) {
                if (value instanceof Date)
                    value = ObjectConverter.convert(value, Date.class);
                else {
                    value = ObjectConverter.convert(value, String.class);
                }
            }
            if (propertyBinding.getTrim() != null && propertyBinding.getTrim().booleanValue() && value instanceof String)
                value = ((String) value).trim();
            getBeanWrapper().setPropertyValue(fieldName, value);
        } catch (NotWritablePropertyException e) {
            throw e;
        } catch (TypeMismatchException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method cleans the value actully set in the JComponent
     * For TextFields it cleans his value
     * For ComboBox, it shows no element selected, but the list remains there
     */
    public void cleanJComponentValues() {
        JTextComponent textField = jComponent;
        textField.setText(null);
    }


    /**
     * Get the value that contains the input jComponent
     *
     * @return this iu jComponent value
     */
    public Object getValue() {
        String text = jComponent.getText();
        Object value = null;
        if (dropDownValues.size() == 0) {
            value = text;
            if (formatter != null) {
                try {
                    value = formatter.parseObject(text);
                } catch (Exception e) {
                    logger.debug("Problems at formatting:" + text + " the value will be set to null");
                    value = null;
                }
            }
        } else {
            value = getKeyFromDrowList(text);
        }
        return value;
    }

    /////////////////////////   Input Edition Restriction Methods  /////////////////////////
    /**
     * Used to limit the text size that this text box can accept
     *
     * @param maxTextSize
     */
    /**
     * Used to limit the text size that this text box can accept
     *
     * @param maxTextSize
     */
    public void setLimitTextSize(int maxTextSize) {
        setLimitTextSizeIntern(maxTextSize);
    }

    protected void setLimitTextSizeIntern(int maxTextSize) {
        propertyBinding.setLimitTextSize(maxTextSize);
    }

    public void acceptOnlyDigitsAndMinusSymbol(){
        acceptOnlyDigits();
        propertyBinding.setMinusSymbolAllowed(true);
    }

    public void acceptOnlyDigits() {
        propertyBinding.setOnlyDigits(true);
        propertyBinding.setMillarSymbolAllowed(false);
        propertyBinding.setDecimalSymbolAllowed(false);
    }

    /**
     * Used to limit size of the number  that this text box can accept
     *
     * @param maxNumberSize
     */
    public BndIJTextField setLimitNumberSize(int maxNumberSize) {
        propertyBinding.setLimitTextSize(maxNumberSize);
        propertyBinding.setOnlyDigits(true);
        return this;
    }


    /**
     * Used to transform the case of the text entered
     */
    public BndIJTextField setAsUpperCase() {
        propertyBinding.setToUpperCase(true);
        return this;
    }

    /**
     * @return this instance
     */
    public BndIJTextField setAsLowerCase() {
        propertyBinding.setToUpperCase(false);
        return this;
    }

    public BndIJTextField setAsNotChangeCase() {
        propertyBinding.setToUpperCase(null);
//        document.doNotChangeCase();   todo ojo con esto
        return this;
    }

    /**
     * todo dar soporte a esto.....
     *
     * @return
     */
    public BndIJTextField enableAutoSelectText() {
        addFocusListener();
        autoSelectTextOnFocusGain = true;
        return this;
    }

    protected void selectText() {
        try {
            JTextComponent textField = jComponent;
            textField.setCaretPosition(textField.getText().length());
            textField.moveCaretPosition(0);
        } catch (Exception e){
            if(logger.isDebugEnabled()){
                logger.debug("Error seleccionando contenido del text field...");
            }
        }//Inocuo
    }


    /////////////////////////   Format & Transformation Methods  /////////////////////////
    /**
     * Indicate that this control must accept only date values (and format in this fashion)
     *
     * @return this instance
     */
    public BndIJTextField formatAsDate() {
        final JTextField jTextField = (JTextField) jComponent;
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isDigit(keyChar) && (jTextField.getText().trim().length() == 2 || jTextField.getText().trim().length() == 5)) {
                    jTextField.setText(jTextField.getText().trim() + "/");
                }
            }

            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (KeyEvent.VK_ENTER == e.getKeyCode()) {
                    fullYear(jTextField);
                }
            }
        });
        jTextField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                fullYear(jTextField);
            }
        });
        super.formatAsDateIntern();
        if (validator != null && validator.getPropertyValidator() != null) {
            validator.getPropertyValidator().setDateFormat(true);
//            updateComponentRules();
        }
        return this;
    }

    private void fullYear(JTextField jTextField) {
        String input1 = jTextField.getText();
        if (StringUtils.hasText(input1)) {
            String input2 = getValidDate(input1);
            if (input1 != input2)
                jTextField.setText(input2);
        }
    }

    public static String getValidDate(String input) {
        if (!StringUtils.hasText(input)) {
            return "";
        }
        String result = input;
        if (StringUtils.countOccurrencesOf(input, "/") == 2 && input.length() > 6 && input.length() < 10) {
            int inputYear = Integer.parseInt(input.substring(input.lastIndexOf("/") + 1, input.length()));
            boolean parse = (inputYear < 1000);
            if (input.lastIndexOf("/") == 5 && parse) {
                //String numMilenios = Integer.parseInt( new SimpleDateFormat("yyyy").format(new Date()) );
                //numMilenios = numMilenios.substring(0, 1);
                int YEAR_BREAK = 30;
                int numMilenios = inputYear > 30 ? 1900 : 2000;
                String currentYear = String.valueOf(numMilenios + inputYear);
                result = input.substring(0, input.lastIndexOf("/") + 1) + currentYear;
            }
        }
        if (!isValidDate(result)) {
            result = "";
        }
        return result;
    }

    private static boolean isValidDate(String input) {
        try {
            DateFormatter.DATE_FORMATTER.parse(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public BndIJTextField formatAsTime() {
        return (BndIJTextField) super.formatAsTimeIntern();
    }

    public BndIJTextField formatAsDateTime() {
        return (BndIJTextField) super.formatAsDateTimeIntern();
    }

/*
    public BndIJTextField enableAutomaticSlash() {
//        propertyBinding.set
        document.setAddDateSlash(true);
        return this;
    }
*/

    /**
     * Indicate that this control must accept only percentaje values (and format in this fashion)
     *
     * @return this instance
     */
    public BndFormattedComponent formatAsPercentage() {
        return formatAsPercentage(BigDecimal.class, AWBaseContext.instance().getConfigInfoProvider().getPercentFormat());
    }

    public BndIJTextField setAsEmail() {
        validator.getPropertyValidator().add(Rule.VALIDATE_EMAIL);
        return this;
    }

    public BndIJTextField setAsRuc() {
        validator.getPropertyValidator().add(Rule.VALIDATE_RUC);
        return this;
    }

    public BndIJTextField setRangeRule(Object from, Object to) {
        validator.getPropertyValidator().add(Rule.VALIDATE_RANGE);
        validator.getPropertyValidator().setMinValue(from);
        validator.getPropertyValidator().setMaxValue(to);
        return this;
    }

    public BndIJTextField setGreaterThanOrEqualRule(Object minValue) {
        validator.getPropertyValidator().add(Rule.VALIDATE_GREATER_THAN_OR_EQUAL);
        validator.getPropertyValidator().setMinValue(minValue);
        return this;
    }

    public BndIJTextField setGreaterThanRule(Object minValue) {
        validator.getPropertyValidator().add(Rule.VALIDATE_GREATER_THAN);
        validator.getPropertyValidator().setMinValue(minValue);
        return this;
    }

    public BndIJTextField setLessThanRule(Object maxValue) {
        validator.getPropertyValidator().add(Rule.VALIDATE_LESS_THAN);
        validator.getPropertyValidator().setMaxValue(maxValue);
        return this;
    }


    /**
     * Indicate that this control must accept only percentaje values (and format in this fashion)
     *
     * @return this instance
     */
    public BndFormattedComponent formatAsPercentage(Class modelType, String format) {
        propertyBinding.setConfigureForNumbers(format);
        propertyBinding.setLimitTextSize(format.length());
        return super.registerAsPercentageFormattedIntern(modelType, format);
    }

    /**
     * Indicate that this control must accept money values
     *
     * @return this instance
     */
    public BndIJTextField formatAsMoney(Number maximun) {
        String format = AWBaseContext.instance().getConfigInfoProvider().getMoneyFormat();
        propertyBinding.setConfigureForNumbers(format);
//        document.configureForNumbers(format);
        return (BndIJTextField) super.registerAsNumberFormattedIntern(maximun.getClass(), null, (Comparable) maximun, format);
    }

    /**
     * Indicate that this control must accept only percentaje values (and format in this fashion)
     *
     * @return this instance
     */
    public BndIJTextField formatAsFilled(char fillerCharacter, int lenght) {
        Formatter fillerFormatter = new FillerFormat(fillerCharacter, lenght);
        formatUsingCustomFormatter(fillerFormatter);
        setLimitTextSize(lenght);
        return this;
    }

    /**
     * Indicate that this control will trim any spaces
     *
     * @return this instance
     */
    public BndIJTextField formatUsingTrim() {
        Formatter trimFormat = new TrimFormat();
        formatUsingCustomFormatter(trimFormat);
        return this;
    }


    public void registerValidator(Validator validator) {
        this.validator = validator;
        validator.registerBindingComponent(this);
        addItemListenerTo(validator);
    }

    private void addItemListenerTo(Validator validator) {
        ReflectionValidator reflectionValidator = (ReflectionValidator) validator;
        if (reflectionValidator.getJComponentDiscriminator() instanceof JComboBox) {
            ((JComboBox) reflectionValidator.getJComponentDiscriminator()).addItemListener(new ValidatorItemListener(reflectionValidator));
            ((JComboBox) reflectionValidator.getJComponentDiscriminator()).addActionListener(new ValidatorActionListener(reflectionValidator));
        }
    }

    /**
     * Indicate that this control must accept only percentaje values (and format in this fashion)
     *
     * @return this instance
     */
    public BndIJTextField formatAsFilledExceptEmptyString(char fillerCharacter, int lenght) {
        Formatter fillerFormatter = new FillerFormat(fillerCharacter, lenght);
        ((FillerFormat) fillerFormatter).setFillInclusiveEmptyString(false);
        formatUsingCustomFormatter(fillerFormatter);
        this.setLimitTextSize(lenght);
        return this;
    }

    public BndIJTextField formatAsNumber(Comparable minimun, Comparable maximun) {
        return formatAsNumber(minimun, maximun, "0");
    }

    public BndIJTextField formatAsNumber(Comparable minimun, Comparable maximun, String format) {
        propertyBinding.setConfigureForNumbers(format);
        if (minimun != null && ((Number) minimun).doubleValue() < 0)
            propertyBinding.setMinusSymbolAllowed(true);
//        document.configureForNumbers(format);
        return (BndIJTextField) super.registerAsNumberFormattedIntern(null, minimun, maximun, format);
    }

    /**
     * Register the specified formatter fo this control
     *
     * @param customformatter
     */
    protected BndFormattedComponent formatUsingCustomFormatterIntern(Formatter customformatter) {
        addFocusListener();
        return super.formatUsingCustomFormatterIntern(customformatter);
    }


    /**
     * Register the specified formatter fo this control
     *
     * @param customformatter Custom formatter
     * @return esta instancia
     */
    public BndIJTextField formatUsingCustomFormatter(Formatter customformatter) {
        return (BndIJTextField) formatUsingCustomFormatterIntern(customformatter);
    }

    public BndIJTextField formatAsDropDown(MetaLoader metaLoader) {
        return (BndIJTextField) formatUsingCustomFormatterIntern(new DropDownFormatter(metaLoader));
    }

    public BndIJTextField formatAsNumber() {
        return (BndIJTextField) formatUsingCustomFormatterIntern(NumberFormatter.NUMBER_FORMATTER_WOUT_GRP_SYM);
    }

    public BndIJTextField formatAsMoney() {
        formatUsingCustomFormatterIntern(NumberFormatter.MONEY_FORMATTER);
        if (validator != null && validator.getPropertyValidator() != null) {
            validator.getPropertyValidator().setMoneyFormat(true);
//            updateComponentRules();
        }
        return this;
    }

    public BindingComponent formatAsMoneyExchange() {
        formatUsingCustomFormatterIntern(NumberFormatter.MONEYEXCHANGE_FORMATTER);
        if (validator != null && validator.getPropertyValidator() != null) {
            validator.getPropertyValidator().setMoneyFormat(true);
//            updateComponentRules();
        }
        return this;
    }

/*

    public Formatter getFormatter() {
        return formatter;
    }
*/

    /**
     * Install the  focus listener, to provide lost focus event
     */
    private void addFocusListener() {
        logger.info(" addFocusListener " + this.fieldName + " formatter " + formatter);
        if (helperFocusListenerInstalled) return;

        // flag this to avoid multiple listener installation
        helperFocusListenerInstalled = true;
        // install the listener
        jComponent.addFocusListener(
                new FocusListener() {
                    /**
                     * Invoked when a jComponent gains the keyboard focus.
                     */
                    public void focusGained(FocusEvent e) {
                        if (e.isTemporary()) return;
                        if (autoSelectTextOnFocusGain)
                            selectText();
                    }

                    /**
                     * Invoked when a jComponent loses the keyboard focus.
                     * Here the text will be validated and formatted
                     */
                    public void focusLost(FocusEvent e) {
                        if (e.isTemporary()) return;
                        formatComponentText();
                    }
                }
        );
    }

    public PropertyValidator getPropertyValidator() {
        return getValidator().getPropertyValidator();
    }

    /**
     * Only for text Areas
     *
     * @param lineWrap
     * @return
     */
    public BndIJTextField setLineWrap(boolean lineWrap) {
        if (!(jComponent instanceof JTextArea)) {
            throw new IllegalStateException("The component must be JTextArea to apply this option");
        }
        propertyBinding.setLineWrap(lineWrap);
//        ((JTextArea)jComponent).setLineWrap(lineWrap);
        return this;
    }

    /*
    * Only for text Areas
    */
    public BndIJTextField setWrapStyleWord(boolean wrapStyleWord) {
        if (!(jComponent instanceof JTextArea)) {
            throw new IllegalStateException("The component must be JTextArea to apply this option");
        }
        propertyBinding.setWrapStyleWord(wrapStyleWord);
//        ((JTextArea)jComponent).setWrapStyleWord(wrapStyleWord);
        return this;
    }

    public void disablePick() {
        (getJComponent()).putClientProperty(BindingComponent.ATTR_EXECUTE_PICK, Boolean.FALSE);
    }

    public void enabledPick() {
        (getJComponent()).putClientProperty(BindingComponent.ATTR_EXECUTE_PICK, Boolean.TRUE);
    }

    public void addChangeListener(final OnChangeListener onChangeListener) {
        final JTextComponent jTextField = jComponent;
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                onChangeListener.onChange(jTextField, jTextField.getText());
            }
        });
    }

    public static interface OnChangeListener {
        void onChange(JTextComponent jTextField, String text);
    }

    public BndIJTextField setFunction(GridFunction gridFunction) {
        this.gridFunction = gridFunction;
        this.gridFunctionIdx = 0;
        return this;
    }

    public BndIJTextField setFunction(GridFunction gridFunction, int gridIndex) {
        this.gridFunction = gridFunction;
        this.gridFunctionIdx = gridIndex;
        return this;
    }

    public GridFunction getGridFunction() {
        return gridFunction;
    }

    public int getGridFunctionIdx() {
        return gridFunctionIdx;
    }

    /**
     * Set the value of the bean to the JInputComponent
     */
    protected void setValueToJComponentInternal(){
        Object beanValue = "";
        if ((gridFunction == null) || ((gridFunction != null) && StringUtils.hasText(fieldName))) {
            beanValue = getValueToBeSetToJComponent();
        }
        String text = getFormattedValue(beanValue);
        setFormattedTextToJComponent(text);

        jComponent.updateUI();
        if (!getComponentManager().getPresenter().isInitializing()){
            InputVerifier inputVerifier = jComponent.getInputVerifier();
            if (inputVerifier!=null){
                inputVerifier.verify(jComponent);
            }
        }
    }


    public void setValue(Object value) {
        if ((gridFunction == null) || ((gridFunction != null) && StringUtils.hasText(fieldName))) {
            if (logger.isDebugEnabled())
                logger.debug("[" + this.fieldName + "] Bean-->Cmpt:" + value);
            getBeanWrapper().setPropertyValue(fieldName, value);
        }
        String text = getFormattedValue(value);
        setFormattedTextToJComponent(text);
    }

}
