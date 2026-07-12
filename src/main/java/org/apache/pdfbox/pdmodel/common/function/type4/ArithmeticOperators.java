package org.apache.pdfbox.pdmodel.common.function.type4;

import java.util.Stack;

/* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators.class */
class ArithmeticOperators {
    private ArithmeticOperators() {
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Abs.class */
    static class Abs implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            if (num instanceof Integer) {
                context.getStack().push(Integer.valueOf(Math.abs(num.intValue())));
            } else {
                context.getStack().push(Float.valueOf(Math.abs(num.floatValue())));
            }
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Add.class */
    static class Add implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num2 = context.popNumber();
            Number num1 = context.popNumber();
            if ((num1 instanceof Integer) && (num2 instanceof Integer)) {
                long sum = num1.longValue() + num2.longValue();
                if (sum < -2147483648L || sum > 2147483647L) {
                    context.getStack().push(Float.valueOf((float) sum));
                    return;
                } else {
                    context.getStack().push(Integer.valueOf((int) sum));
                    return;
                }
            }
            context.getStack().push(Float.valueOf(num1.floatValue() + num2.floatValue()));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Atan.class */
    static class Atan implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            float den = context.popReal();
            float num = context.popReal();
            float atan = ((float) Math.toDegrees((float) Math.atan2(num, den))) % 360.0f;
            if (atan < 0.0f) {
                atan += 360.0f;
            }
            context.getStack().push(Float.valueOf(atan));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Ceiling.class */
    static class Ceiling implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            if (num instanceof Integer) {
                context.getStack().push(num);
            } else {
                context.getStack().push(Float.valueOf((float) Math.ceil(num.doubleValue())));
            }
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Cos.class */
    static class Cos implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            float angle = context.popReal();
            float cos = (float) Math.cos(Math.toRadians(angle));
            context.getStack().push(Float.valueOf(cos));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Cvi.class */
    static class Cvi implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            context.getStack().push(Integer.valueOf(num.intValue()));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Cvr.class */
    static class Cvr implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            context.getStack().push(Float.valueOf(num.floatValue()));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Div.class */
    static class Div implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num2 = context.popNumber();
            Number num1 = context.popNumber();
            context.getStack().push(Float.valueOf(num1.floatValue() / num2.floatValue()));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Exp.class */
    static class Exp implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number exp = context.popNumber();
            Number base = context.popNumber();
            double value = Math.pow(base.doubleValue(), exp.doubleValue());
            context.getStack().push(Float.valueOf((float) value));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Floor.class */
    static class Floor implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            if (num instanceof Integer) {
                context.getStack().push(num);
            } else {
                context.getStack().push(Float.valueOf((float) Math.floor(num.doubleValue())));
            }
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$IDiv.class */
    static class IDiv implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            int num2 = context.popInt();
            int num1 = context.popInt();
            context.getStack().push(Integer.valueOf(num1 / num2));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Ln.class */
    static class Ln implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            context.getStack().push(Float.valueOf((float) Math.log(num.doubleValue())));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Log.class */
    static class Log implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            context.getStack().push(Float.valueOf((float) Math.log10(num.doubleValue())));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Mod.class */
    static class Mod implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            int int2 = context.popInt();
            int int1 = context.popInt();
            context.getStack().push(Integer.valueOf(int1 % int2));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Mul.class */
    static class Mul implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num2 = context.popNumber();
            Number num1 = context.popNumber();
            if ((num1 instanceof Integer) && (num2 instanceof Integer)) {
                long result = num1.longValue() * num2.longValue();
                if (result >= -2147483648L && result <= 2147483647L) {
                    context.getStack().push(Integer.valueOf((int) result));
                    return;
                } else {
                    context.getStack().push(Float.valueOf((float) result));
                    return;
                }
            }
            context.getStack().push(Float.valueOf((float) (num1.doubleValue() * num2.doubleValue())));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Neg.class */
    static class Neg implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            if (num instanceof Integer) {
                int v = num.intValue();
                if (v == Integer.MIN_VALUE) {
                    context.getStack().push(Float.valueOf(-num.floatValue()));
                    return;
                } else {
                    context.getStack().push(Integer.valueOf(-num.intValue()));
                    return;
                }
            }
            context.getStack().push(Float.valueOf(-num.floatValue()));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Round.class */
    static class Round implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            if (num instanceof Integer) {
                context.getStack().push(Integer.valueOf(num.intValue()));
            } else {
                context.getStack().push(Float.valueOf((float) Math.round(num.doubleValue())));
            }
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Sin.class */
    static class Sin implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            float angle = context.popReal();
            float sin = (float) Math.sin(Math.toRadians(angle));
            context.getStack().push(Float.valueOf(sin));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Sqrt.class */
    static class Sqrt implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            float num = context.popReal();
            if (num < 0.0f) {
                throw new IllegalArgumentException("argument must be nonnegative");
            }
            context.getStack().push(Float.valueOf((float) Math.sqrt(num)));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Sub.class */
    static class Sub implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Stack<Object> stack = context.getStack();
            Number num2 = context.popNumber();
            Number num1 = context.popNumber();
            if ((num1 instanceof Integer) && (num2 instanceof Integer)) {
                long result = num1.longValue() - num2.longValue();
                if (result < -2147483648L || result > 2147483647L) {
                    stack.push(Float.valueOf((float) result));
                    return;
                } else {
                    stack.push(Integer.valueOf((int) result));
                    return;
                }
            }
            stack.push(Float.valueOf(num1.floatValue() - num2.floatValue()));
        }
    }

    /* loaded from: target.jar:org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators$Truncate.class */
    static class Truncate implements Operator {
        @Override // org.apache.pdfbox.pdmodel.common.function.type4.Operator
        public void execute(ExecutionContext context) {
            Number num = context.popNumber();
            if (num instanceof Integer) {
                context.getStack().push(Integer.valueOf(num.intValue()));
            } else {
                context.getStack().push(Float.valueOf((int) num.floatValue()));
            }
        }
    }
}
