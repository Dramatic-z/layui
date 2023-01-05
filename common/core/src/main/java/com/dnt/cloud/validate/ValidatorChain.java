package com.dnt.cloud.validate;

import java.util.List;

import com.dnt.cloud.validate.exception.ValidationException;

/**
 * <p>校验器链</p>
 * @author sean won
 * @version $Id: ValidatorChain.java, v 0.1 2010-1-28 下午02:49:11 fuyangbiao Exp $
 */
public class ValidatorChain implements Validator {
    private List<Validator> validators;

    /*
     * (non-Javadoc)
     * @see com.netfinworks.counter.core.model.template.Validator#validate(java.lang.Object)
     */
    public void validate(Object model) throws ValidationException {
        if (validators != null && validators.size() > 0) {
            for (Validator validator : validators) {
                validator.validate(model);
            }
        }
    }

    public void setValidators(List<Validator> validators) {
        this.validators = validators;
    }
}
