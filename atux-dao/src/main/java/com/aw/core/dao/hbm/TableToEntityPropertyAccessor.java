package com.aw.core.dao.hbm;

import com.aw.core.dao.meta.EntityPropertyMapper;
import com.aw.core.dao.meta.HbmUtilFactory;
import com.aw.support.ObjectConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.property.BasicPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.Setter;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.util.Collection;

//import org.hibernate.engine.SessionFactoryImplementor;

/**
 * User: Julio C. Macavilca
 * Date: 08/01/2008
 */
public class TableToEntityPropertyAccessor extends BasicPropertyAccessor {
    private Log logger = LogFactory.getLog(getClass());

    EntityPropertyMapper mapper = null;
    public Setter getSetter(Class theClass, String propertyName) throws PropertyNotFoundException {
        propertyName = determineEntityProperty(theClass, propertyName);
        return new PropertySetter(propertyName);
    }

    public Getter getGetter(Class theClass, String propertyName) throws PropertyNotFoundException {
        propertyName = determineEntityProperty(theClass, propertyName);
        return super.getGetter(theClass, propertyName);
    }

    private String determineEntityProperty(Class theClass, String columnName) {
        if (mapper==null)
            mapper = HbmUtilFactory.newInstance().buildPropertyMapper(theClass);

        return mapper.columnToProperty(columnName);
    }
    public final class PropertySetter implements Setter {
        String propertyName;
        public PropertySetter(String propertyName) {
            this.propertyName =propertyName;
        }

        public Method getMethod() {
			return null;
		}

		public String getMethodName() {
			return null;
		}

		public void set(Object target, Object value, SessionFactoryImplementor factory) throws HibernateException {
            logger.debug("set property["+propertyName+"] --> "+value);
            BeanWrapper wrapper  = new BeanWrapperImpl(target);
            Class propertyType = wrapper.getPropertyType(propertyName);
            if (Collection.class.isAssignableFrom(propertyType) && !(value instanceof Collection)){
                logger.debug("set property["+propertyName+"] --> invalid (collection):"+value);
            }else{
                Object valueConverted = ObjectConverter.convert(value, propertyType);
                wrapper.setPropertyValue(propertyName, valueConverted);
            }
        }
	}
}
