package com.starfoxKiosk.admin.model.dto;

public class Option {
    private int id;
    private String name;
    private String detail;
    private String optionType;
    private Integer maxQuantity;
    private String allowedValues;
    private boolean isRequired;

    public Option() {
    }

    public Option(int id, String name, String detail, String optionType, Integer maxQuantity, String allowedValues) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.optionType = optionType;
        this.maxQuantity = maxQuantity;
        this.allowedValues = allowedValues;
    }

    public int getId() { return id; }

    public String getName() {
        return name;
    }

    public String getDetail() {
        return detail;
    }

    public String getOptionType() {
        return optionType;
    }

    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public String getAllowedValues() {
        return allowedValues;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public void setAllowedValues(String allowedValues) {
        this.allowedValues = allowedValues;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OptionS{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", detail='").append(detail).append('\'');
        sb.append(", optionType='").append(optionType).append('\'');
        if (maxQuantity != null) {
            sb.append(", maxQuantity=").append(maxQuantity);
        }
        if (allowedValues != null) {
            sb.append(", allowedValues='").append(allowedValues).append('\'');
        }
        sb.append(", isRequired=").append(isRequired);
        sb.append('}');
        return sb.toString();
    }
}
