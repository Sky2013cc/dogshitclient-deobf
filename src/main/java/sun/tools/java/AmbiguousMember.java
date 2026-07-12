package sun.tools.java;

/* loaded from: target.jar:sun/tools/java/AmbiguousMember.class */
public class AmbiguousMember extends Exception {
    public MemberDefinition field1;
    public MemberDefinition field2;

    public AmbiguousMember(MemberDefinition memberDefinition, MemberDefinition memberDefinition2) {
        super(memberDefinition.getName() + " + " + memberDefinition2.getName());
        this.field1 = memberDefinition;
        this.field2 = memberDefinition2;
    }
}
