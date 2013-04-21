package com.pc.test.cqutil.impl;


import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.wrappers.SlingHttpServletRequestWrapper;

public class FormsHandlingRequest extends SlingHttpServletRequestWrapper {

    public FormsHandlingRequest(SlingHttpServletRequest wrappedRequest) {
        super(wrappedRequest);
    }

    /**
     * Validation includes always assume GET
     */
    @Override
    public String getMethod() {
        return "GET";
    }
}
