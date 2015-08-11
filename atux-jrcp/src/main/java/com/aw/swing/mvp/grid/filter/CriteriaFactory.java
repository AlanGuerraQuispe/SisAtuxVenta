package com.aw.swing.mvp.grid.filter;

import com.aw.support.collection.ListUtils;
import org.apache.commons.functor.UnaryPredicate;
import org.apache.commons.functor.core.composite.UnaryAnd;
import org.apache.commons.functor.core.composite.UnaryOr;
import org.springframework.beans.BeanWrapper;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * User: gmc
 * Date: 26-nov-2007
 */
public class CriteriaFactory {
    public final static String AND = "and";
    public final static String OR = "or";
    public final static String WILDCARD = "*";

    public UnaryPredicate createCriteria(List attributes, String operator) {
        return createCriteria(attributes, ListUtils.create(operator, attributes.size() - 1));
    }

    public UnaryPredicate createCriteria(List attributes, List operators) {
        CriteriaAttribute firstAttr = (CriteriaAttribute) attributes.get(0);
        UnaryPredicate predicate = createPredicateFor(firstAttr);
        for (int i = 1, opIndex = 0; i < attributes.size(); i++, opIndex++) {
            String operator = (String) operators.get(opIndex);
            CriteriaAttribute criteriaAttribute = (CriteriaAttribute) attributes.get(i);
            if (criteriaAttribute.getValue() == null || (criteriaAttribute.getValue() instanceof String && !StringUtils.hasText((String) criteriaAttribute.getValue())))
                continue;
            UnaryPredicate currentPred = createPredicateFor(criteriaAttribute);
            if (AND.equals(operator)) {
                predicate = new UnaryAnd(predicate, currentPred);
            } else {
                predicate = new UnaryOr(predicate, currentPred);
            }
        }
        return predicate;
    }

    private UnaryPredicate createPredicateFor(final CriteriaAttribute attr) {
        return new UnaryPredicate() {
            public boolean test(Object o) {
                BeanWrapper beanWrapper = (BeanWrapper) o;
                Object bwValue = beanWrapper.getPropertyValue(attr.getName());
                Object attrValue = attr.getValue();
                if ((attrValue instanceof String) && ((String) attrValue).endsWith(WILDCARD)) {
                    String bwValueStr = (String) bwValue;
                    String attrValueStr = (String) attrValue;
                    return bwValueStr.startsWith(attrValueStr.substring(0, attrValueStr.length() - 1));
                }
                return bwValue.equals(attr.getValue());
            }
        };
    }
}
