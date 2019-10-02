package calculator;

/**
 * Created by Michal Szarek
 **/
public class Calculator {

    private String currentOperation;
    private String value = "0";
    private Double tempValue = 0.0;
    private Double secondValue;
    private Boolean canSetSecond = false;

    Calculator() {}

    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public String getValue() {
        return value;
    }

    public String setValue(String value) {

        if(!canSetSecond) {
            if(this.value == "0") {
                this.value = value;
                currentOperation = null;
                tempValue = 0.0;
                secondValue = 0.0;
            } else if(currentOperation != null) {
                this.value = value;
                currentOperation = null;
                tempValue = 0.0;
                secondValue = 0.0;
            } else
                this.value += value;
        } else {
            if(this.value == "0" ) {
                this.value = value;
                secondValue = Double.parseDouble(value);
            } else
                this.value += value;
                secondValue = Double.parseDouble(this.value);
        }

        return getValue();
    }

    public String setComma() {
        if(!value.contains(".")) {
            value += ".";
        }else {}
        return value;
    }

    public String clear() {
        value = "0";
        currentOperation = null;
        canSetSecond = false;
        tempValue = 0.0;
        return value;
    }

    public String remove() {
        int lenght = value.length();

        if(currentOperation == null) {
            if(lenght > 1) {
                value = value.substring(0, lenght-1);
            } else value = "0";
        }
        return value;
    }

    public String sqrt() throws ArithmeticException {
        Double valuedouble = Double.parseDouble(getValue());
        try {
            valuedouble = Math.sqrt(valuedouble);
        } catch (ArithmeticException ex) {
            System.out.println(ex);
        }

        value = "" + valuedouble;
        return "" + valuedouble;
    }

    public String x2() {
        Double valuedouble = Double.parseDouble(getValue());
        value = "" + Math.pow(valuedouble, 2);
        return value;
    }

    public String x3() {
        Double valuedouble = Double.parseDouble(getValue());
        value = "" + Math.pow(valuedouble, 3);
        return value;
    }

    public void divide() {
        currentOperation = "divide";
        canSetSecond = true;
        tempValue = Double.parseDouble(value);
        value = "0";
    }

    public void multiply() {
        currentOperation = "multiply";
        canSetSecond = true;
        tempValue = Double.parseDouble(value);
        value = "0";
    }

    public void sum() {
        currentOperation = "sum";
        canSetSecond = true;
        tempValue = Double.parseDouble(value);
        value = "0";
    }

    public void minus() {
        currentOperation = "minus";
        canSetSecond = true;
        tempValue = Double.parseDouble(value);
        value = "0";
    }

    public String makeCalculation() throws ArithmeticException {
        Double result;
        switch (currentOperation) {
            case "sum":
                result = secondValue + tempValue;
                canSetSecond = false;
                tempValue = result;
                value = "" + result;
                break;
            case "minus":
                result = tempValue - secondValue;
                canSetSecond = false;
                tempValue = result;
                value = "" + result;
                break;
            case "multiply":
                result = tempValue * secondValue;
                canSetSecond = false;
                tempValue = result;
                value = "" + result;
                break;
            case "divide":
                result = 0.0;
                try {
                    result = tempValue / secondValue;
                } catch (ArithmeticException ex) {
                    value = "0";
                } finally {
                    canSetSecond = false;
                    tempValue = result;
                    value = "" + result;
                }
                break;
        }
        return value;
    }

}
