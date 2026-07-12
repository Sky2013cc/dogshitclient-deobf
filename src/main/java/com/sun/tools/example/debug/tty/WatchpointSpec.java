package com.sun.tools.example.debug.tty;

/* loaded from: target.jar:com/sun/tools/example/debug/tty/WatchpointSpec.class */
abstract class WatchpointSpec extends EventRequestSpec {
    final String fieldId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WatchpointSpec(ReferenceTypeSpec referenceTypeSpec, String str) throws MalformedMemberNameException {
        super(referenceTypeSpec);
        this.fieldId = str;
        if (!isJavaIdentifier(str)) {
            throw new MalformedMemberNameException(str);
        }
    }

    public int hashCode() {
        return this.refSpec.hashCode() + this.fieldId.hashCode() + getClass().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof WatchpointSpec) {
            WatchpointSpec watchpointSpec = (WatchpointSpec) obj;
            return this.fieldId.equals(watchpointSpec.fieldId) && this.refSpec.equals(watchpointSpec.refSpec) && getClass().equals(watchpointSpec.getClass());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.sun.tools.example.debug.tty.EventRequestSpec
    public String errorMessageFor(Exception exc) {
        if (exc instanceof NoSuchFieldException) {
            return MessageOutput.format("No field in", new Object[]{this.fieldId, this.refSpec.toString()});
        }
        return super.errorMessageFor(exc);
    }
}
