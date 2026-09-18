package com.moduletwo.api;

import com.moduleone.api.TextTransformService;

/**
 * LowerCaseTransformer implements TextTransformService.
 * Another implementation of the service interface.
 */
public class LowerCaseTransformer implements TextTransformService {

    @Override
    public String getProviderName() {
        return "LowerCaseTransformer";
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            return null;
        }
        return text.toLowerCase();
    }

    @Override
    public int getPriority() {
        return 50;
    }

    @Override
    public String toString() {
        return "TextTransformService{" +
                "provider='" + getProviderName() + '\'' +
                ", priority=" + getPriority() +
                '}';
    }
}
