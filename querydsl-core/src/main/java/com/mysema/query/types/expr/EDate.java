/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types.expr;

import java.util.Date;

import com.mysema.query.types.operation.ODate;
import com.mysema.query.types.operation.ONumber;
import com.mysema.query.types.operation.Ops;

/**
 * EDate represents Date expressions
 * 
 * @author tiwe
 *
 * @param <D>
 */
@SuppressWarnings({"unchecked","serial"})
public abstract class EDate<D extends Comparable> extends EDateOrTime<D> {
    
    private static final EDate<Date> currentDate = currentDate(Date.class);
    
    public static EDate<java.sql.Date> __create(java.sql.Date date){
        return new EDateConst(date);
    }
    
    /**
     * Get an expression representing the current date as a EDate instance
     * 
     * @return
     */
    public static EDate<Date> currentDate() {
        return currentDate;
    }

    /**
     * Get an expression representing the current date as a EDate instance
     * 
     * @return
     */
    public static <T extends Comparable> EDate<T> currentDate(Class<T> cl) {
        return ODate.create(cl, Ops.DateTimeOps.CURRENT_DATE);
    }
    
    private volatile ENumber<Integer> dayOfMonth, dayOfWeek, dayOfYear;
    
    private volatile ENumber<Integer> week, month, year;
    
    public EDate(Class<? extends D> type) {
        super(type);
    }
    
    /**
     * Create a day of month expression (range 1-31)
     * 
     * @return
     */
    public ENumber<Integer> getDayOfMonth(){
        if (dayOfMonth == null){
            dayOfMonth = ONumber.create(Integer.class, Ops.DateTimeOps.DAY_OF_MONTH, this);
        }
        return dayOfMonth;
    }

    /**
     * Create a day of week expression (range 1-7 / SUN-SAT)     
     * <p>NOT supported in JDOQL and not in Derby</p>
     * 
     * @return
     */
    public ENumber<Integer> getDayOfWeek() {
        if (dayOfWeek == null){
            dayOfWeek = ONumber.create(Integer.class, Ops.DateTimeOps.DAY_OF_WEEK, this); 
        }
        return dayOfWeek; 
    }

    /**
     * Create a day of year expression (range 1-356)
     * <p>NOT supported in JDOQL and not in Derby</p>
     * 
     * @return
     */
    public ENumber<Integer> getDayOfYear() {
        if (dayOfYear == null){
            dayOfYear = ONumber.create(Integer.class, Ops.DateTimeOps.DAY_OF_YEAR, this); 
        }
        return dayOfYear;
    }
    
    /**
     * Create a month expression (range 1-12)
     * 
     * @return
     */
    public ENumber<Integer> getMonth(){
        if (month == null){
            month = ONumber.create(Integer.class, Ops.DateTimeOps.MONTH, this);
        }
        return month;
    }
    
    /**
     * Create a week expression
     * 
     * @return
     */
    public ENumber<Integer> getWeek() {
        if (week == null){
            week = ONumber.create(Integer.class, Ops.DateTimeOps.WEEK,  this); 
        }
        return week; 
    }
    
    /**
     * Create a year expression 
     * 
     * @return
     */
    public ENumber<Integer> getYear(){
        if (year == null){
            year = ONumber.create(Integer.class, Ops.DateTimeOps.YEAR, this);
        }
        return year;
    }
}
