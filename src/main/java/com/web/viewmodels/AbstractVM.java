package com.web.viewmodels;

import com.web.viewmodels.binding.Param;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class AbstractVM {

    protected Map<String, String> errors;
    private HttpServletRequest request;
    private boolean isValidate;

    protected AbstractVM() {
        errors = new HashMap<>();
    }

    public AbstractVM(HttpServletRequest request) {
        this.request = request;
        errors = new HashMap<>();
        try {
            bindParameters(request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setError(String type, String error) {
        errors.put(type, error);
    }

    public boolean isValidate() {
        return isValidate;
    }

    private void bindParameters(Map<String, String[]> data) throws IllegalAccessException {
        isValidate = true;
        for (Field field : getClass().getDeclaredFields()) {
            Param param = field.getAnnotation(Param.class);
            if (param != null) {
                String name = param.value();
                if (name.length() == 0) {
                    name = field.getName();
                }
                Object value = request.getParameter(name);
                Locale locale = (Locale) request.getSession().getAttribute("locale");
                ResourceBundle resourceBundle = ResourceBundle.getBundle("global", locale);
                if (!param.nullable()) {
                    if (value == null || value.toString().trim().isEmpty()) {
                        isValidate = false;
                        errors.put(field.getName(), resourceBundle.getString("global.error.nullOrEmpty"));
                        continue;
                    }
                }
                try {
                    value = marshal((String) value, field.getType());
                } catch (NumberFormatException e) {
                    errors.put(field.getName(), resourceBundle.getString("global.error.illegalFormat"));
                }
                field.setAccessible(true);
                field.set(this, value);
            }
        }
    }

    private Object marshal(String value, Class cls) throws NumberFormatException {
        if (cls.equals(String.class)) {
            return value;
        } else if (cls.equals(int.class)) {
            return Integer.valueOf(value);
        } else if (cls.equals(double.class)) {
            return Double.valueOf(value);
        } else if (cls.equals(boolean.class)) {
            return Boolean.valueOf(value);
        }
        throw new UnsupportedOperationException();
    }
}
