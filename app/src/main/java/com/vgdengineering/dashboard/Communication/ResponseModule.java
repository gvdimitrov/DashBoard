package com.vgdengineering.dashboard.communication;


public class ResponseModule {

    private String moduleName;
    private Object value;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getValueAsString() {
        return value.toString();
    }
}
