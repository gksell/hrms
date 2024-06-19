package org.example.hrms.core.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Aspect
@Component
public class ValidationAspect {

    @Before("execution(* org.example.hrms.api.controllers.*.*(.., @javax.validation.Valid *))")
    public void beforeControllerMethodExecution() throws MethodArgumentNotValidException {
        BindingResult bindingResult = new BeanPropertyBindingResult(null, "");

        bindingResult.addError(new ObjectError("dto", "Validation error"));

        throw new MethodArgumentNotValidException(null, bindingResult);
    }
}
