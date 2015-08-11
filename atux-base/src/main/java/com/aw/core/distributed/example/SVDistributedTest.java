package com.aw.core.distributed.example;

import com.aw.core.format.DateFormatter;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Julio C. Macavilca
 * Date: 01/07/2009
 * Time: 06:18:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class SVDistributedTest {
    public Object test1(){
        return DateFormatter.DATE_TIME_FORMATTER.format(new Date());
    }
}
