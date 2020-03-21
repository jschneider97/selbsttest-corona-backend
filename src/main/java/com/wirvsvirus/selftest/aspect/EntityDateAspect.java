package com.wirvsvirus.selftest.aspect;

import com.wirvsvirus.selftest.api.BaseDto;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Justus Schneider
 */

@EnableAspectJAutoProxy
@Aspect
@Component
public class EntityDateAspect {

    @Before(value =
            "(execution(* com.wirvsvirus.selftest.service..*(..)) && args(dto))")
    public void setCreateDate(BaseDto dto) {
        dto.setCreateDate(new Date());
        dto.setUpdateDate(null);
    }

    @Before(value =
            "(execution(* com.wirvsvirus.selftest.service..*(..)) && args(id, dto))")
    public void setUpdateDate(Long id, BaseDto dto) {
        dto.setUpdateDate(new Date());
    }
}
