package com.dnt.cloud.validate;

import com.dnt.cloud.validate.exception.ValidationException;

/**
 * <p>校验器</p>
 * @author sean won
 * @version $Id: Validator.java, v 0.1 2010-1-28 下午02:46:08 fuyangbiao Exp $
 */
public interface Validator {
    /**
     * 校验信息
     * @param model
     * @throws ValidationException
     */
    public void validate(Object model) throws ValidationException;
}
