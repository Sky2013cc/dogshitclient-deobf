package org.apache.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators.class */
class RelationalOperators {
    private RelationalOperators() {
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$Eq.class */
    static class Eq implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Stack<Object> stack = context.getStack();
            Object op2 = stack.pop();
            Object op1 = stack.pop();
            boolean result = isEqual(op1, op2);
            stack.push(Boolean.valueOf(result));
        }

        protected boolean isEqual(Object op1, Object op2) {
            boolean result;
            if ((op1 instanceof Number) && (op2 instanceof Number)) {
                Number num1 = (Number) op1;
                Number num2 = (Number) op2;
                result = Float.compare(num1.floatValue(), num2.floatValue()) == 0;
            } else {
                result = op1.equals(op2);
            }
            return result;
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$AbstractNumberComparisonOperator.class */
    private static abstract class AbstractNumberComparisonOperator implements Operator {
        protected abstract boolean compare(Number number, Number number2);

        private AbstractNumberComparisonOperator() {
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Stack<Object> stack = context.getStack();
            Object op2 = stack.pop();
            Object op1 = stack.pop();
            Number num1 = (Number) op1;
            Number num2 = (Number) op2;
            boolean result = compare(num1, num2);
            stack.push(Boolean.valueOf(result));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$Ge.class */
    static class Ge extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Ge() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number num1, Number num2) {
            return num1.floatValue() >= num2.floatValue();
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$Gt.class */
    static class Gt extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Gt() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number num1, Number num2) {
            return num1.floatValue() > num2.floatValue();
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$Le.class */
    static class Le extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Le() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number num1, Number num2) {
            return num1.floatValue() <= num2.floatValue();
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$Lt.class */
    static class Lt extends AbstractNumberComparisonOperator {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Lt() {
            super();
        }

        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.AbstractNumberComparisonOperator
        protected boolean compare(Number num1, Number num2) {
            return num1.floatValue() < num2.floatValue();
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/RelationalOperators$Ne.class */
    static class Ne extends Eq {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.RelationalOperators.Eq
        protected boolean isEqual(Object op1, Object op2) {
            boolean result = super.isEqual(op1, op2);
            return !result;
        }
    }
}
