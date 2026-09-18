package com.moduletwo.api;

import com.moduleone.api.TextTransformService;

/**
 * UpperCaseTransformer implements TextTransformService.
 * This demonstrates service provider implementation in JPMS.
 * 
 * Exported API - can be accessed by other modules that require moduletwo.
 */
public class UpperCaseTransformer implements TextTransformService {

    @Override
    public String getProviderName() {
        return "UpperCaseTransformer";
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            return null;
        }
        return text.toUpperCase();
    }

    @Override
    public int getPriority() {
        return 100;
    }

    @Override
    public String toString() {
        return "TextTransformService{" +
                "provider='" + getProviderName() + '\'' +
                ", priority=" + getPriority() +
                '}';
    }
}
