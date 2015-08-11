package com.aw.swing.mvp.action;

/**
 * User: gmc
 * Date: 11-sep-2007
 */
public class TestActionResolver {// extends TestCase {
//
//    public void testRegisterAction() throws Throwable {
//        Presenter pst = new TestPresenter();
//        SwingContext.setCurrentPst(pst);
//        ActionResolver actionResolver = new ActionResolver(pst);
////        actionResolver.registerAction(ActionDialog.KEY_ENTER,"enterMethod");
//
////        assertTrue(actionResolver.containsActionFor(ActionIdentifier.getActionIdentifier(ActionDialog.KEY_ENTER)));
////        Action action = actionResolver.getAction(ActionIdentifier.getActionIdentifier(ActionDialog.KEY_ENTER));
////        String returnValue = (String) action.execute();
////        assertTrue(returnValue.equals("enterMethodReturn"));
//
//    }
//    public void testRegisterAction1() throws Throwable {
//        Presenter pst = new Presenter();
//        SwingContext.setCurrentPst(pst);
//        Object obj = new ObjectTest();
//        ActionResolver actionResolver = new ActionResolver(pst);
////        actionResolver.registerAction(ActionDialog.KEY_ENTER,obj,"enterMethod");
////
////        assertTrue(actionResolver.containsActionFor(ActionIdentifier.getActionIdentifier(ActionDialog.KEY_ENTER)));
////        Action action = actionResolver.getAction(ActionIdentifier.getActionIdentifier(ActionDialog.KEY_ENTER));
////        String returnValue = (String) action.execute();
////        assertTrue(returnValue.equals("enterMethodReturn"));
//
//    }
//    public void testRegisterAction2() throws Throwable {
//        Presenter pst = new Presenter(){
//            public void beforeActionForTest(ActionForTest action){
//                System.out.println("Before -->");
//            }
//        };
//        SwingContext.setCurrentPst(pst);
//        ActionResolver actionResolver = new ActionResolver(pst);
//        Action action = new ActionForTest();
////        actionResolver.registerAction(ActionDialog.KEY_ENTER,action);
//
////        assertTrue(actionResolver.containsActionFor(ActionIdentifier.getActionIdentifier(ActionDialog.KEY_ENTER)));
////        action = actionResolver.getAction(ActionIdentifier.getActionIdentifier(ActionDialog.KEY_ENTER));
////        String returnValue = (String) action.execute();
////        assertTrue(returnValue.equals("enterMethodReturn"));
//    }
//
//
//    public class ObjectTest {
//        Object vsr;
//        Object pst;
//        Object dmn;
//
//        public ObjectTest() {
//        }
//
//        public void beforeEnterMethod(ReflectionAction action){
//            System.out.println("Before action");
//        }
//        public String enterMethod(){
//            return "enterMethodReturn";
//        }
//    }
//
//    public class TestPresenter extends Presenter{
//        public void beforeEnterMethod(ReflectionAction reflectionAction){
//            System.out.println("test---->");
//
//        }
//        public String enterMethod(){
//            return "enterMethodReturn";
//        }
//    }

}
