package com.aw.support.validation;

import com.aw.swing.mvp.Presenter;
import com.aw.swing.mvp.validation.ValidatorManager;
import com.aw.swing.mvp.validation.support.PatternRules;
import com.aw.swing.mvp.validation.support.PropertyResults;
import com.aw.swing.mvp.validation.support.PropertyValidator;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Date: Sep 11, 2007
 */
public class ValidationTest extends TestCase {

    protected final Log logger = LogFactory.getLog(getClass());

    public void testRegisterAction1() throws Throwable {
        Presenter pst = new TestPresenter();
        ValidatorManager validatorResolver = new ValidatorManager(pst);
        validatorResolver.registerValidator(pst, "enterMethod");
    }

    public void testInvoke() {

    }

    /**
     * prefix
     * R: requerid
     * <p/>
     * sufix:
     * N : number
     * L : string
     * <p/>
     * String caso1 = "R6N";
     * String caso2 = "6N";
     * String caso3 = "5";
     * <p/>
     * String PATTERN_CASE_1="[A-Z][0-9][A-Z]";
     * String CASE_2="[0-9][A-Z]";
     * String CASE_3="[0-9]";
     * <p/>
     * Pattern p1 = Pattern.compile(PATTERN_CASE_1);
     * Matcher m = p1.matcher(caso1);
     * System.out.println(m.matches());
     *
     * @param propertyName
     * @param keyWordRule
     * @return
     * @testng.test
     */
    public void testAtrributeRequerid() {
        // rx2y4d
    }

    /**
     * @testng.test
     */
    public void testParserStringRule() {

        PropertyValidator validatorObtenido;
        PropertyValidator validatorDeseado;
        String strValidator;

        strValidator = "RX2Y2D";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, 2, 2, true, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "RX12Y22D";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, 12, 22, true, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "RY12D";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, -1, 12, true, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "RX12D";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, 12, -1, true, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "RX12L";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, 12, -1, false, true);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "RX12";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, 12, -1, false, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "RY12";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(true, -1, 12, false, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "Y30";
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
        validatorDeseado = new PropertyValidator(false, -1, 30, false, false);
        assertEquals("se debe obtener ", validatorObtenido, validatorDeseado);

        strValidator = "X40Y30";
        try{
        validatorObtenido = PatternRules.buildPropertyValidator(strValidator);
            fail("value incorrect");
        }catch(RuntimeException ex){
            assertTrue(true);
        }


        // tengo
        // rulesName ..
        // Param[]..

    }

    /**
     * @testng.test
     */
    public class ObjectTest {

        public ObjectTest() {
        }

        public PropertyResults validMethod1(Object obO) {
            System.out.println("Method 1");
            return new PropertyResults("message 1");
        }

        public PropertyResults validMethod2(Object obO) {
            System.out.println("Method 2");
            return new PropertyResults("message 2");
        }
    }

    public class TestPresenter extends Presenter {
        public String enterMethod(Object obj) {
            System.out.println("Simple Call");
            return "enterMethodReturn";
        }
    }

}


