package com.zn.tdd;

import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * @description:
 * @author:zn
 * @company:万息科技
 * @projectName:tdd_learn
 * @page:com.zn.tdd
 * @date:2022/10/11-10:55:58
 */
public class MillimeterTest {
    @Test
    public void should_1_mm_equal_1_mm(){
        Length millimeter = new Length(1, Unit.MILLIMETER);
        assertThat(millimeter, Is.is(new Length(1, Unit.MILLIMETER)));
    }

    @Test
    public void should_1_cm_equal_10_mm() {
        Length centimeter = new Length(1,Unit.CENTIMETER);
        assertThat(centimeter,Is.is(new Length(10, Unit.MILLIMETER)));
    }

    @Test
    public void should_1_dm_equal_100_mm() {
        Length decimetre = new Length(1,Unit.DECIMETER);
        assertThat(decimetre,Is.is(new Length(100, Unit.MILLIMETER)));
    }

    @Test
    public void should_1_dm_equal_10_cm() {
        Length decimetre = new Length(1,Unit.DECIMETER);
        assertThat(decimetre,Is.is(new Length(10, Unit.CENTIMETER)));

    }

    @Test
    public void should_1_m_equal_10_dm(){
        Length meter = new Length(1,Unit.METER);
        assertThat(meter,Is.is(new Length(10,Unit.DECIMETER)));
    }
}
