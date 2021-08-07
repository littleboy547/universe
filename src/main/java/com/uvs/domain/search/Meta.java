package com.uvs.domain.search;

public class Meta {

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
