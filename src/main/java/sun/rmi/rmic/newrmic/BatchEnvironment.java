package sun.rmi.rmic.newrmic;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: target.jar:sun/rmi/rmic/newrmic/BatchEnvironment.class */
public class BatchEnvironment {
    private final RootDoc rootDoc;
    private boolean verbose = false;
    private final List<File> generatedFiles = new ArrayList();
    private final ClassDoc docRemote = rootDoc().classNamed(Constants.REMOTE);
    private final ClassDoc docException = rootDoc().classNamed(Constants.EXCEPTION);
    private final ClassDoc docRemoteException = rootDoc().classNamed(Constants.REMOTE_EXCEPTION);
    private final ClassDoc docRuntimeException = rootDoc().classNamed(Constants.RUNTIME_EXCEPTION);

    public BatchEnvironment(RootDoc rootDoc) {
        this.rootDoc = rootDoc;
    }

    public RootDoc rootDoc() {
        return this.rootDoc;
    }

    public ClassDoc docRemote() {
        return this.docRemote;
    }

    public ClassDoc docException() {
        return this.docException;
    }

    public ClassDoc docRemoteException() {
        return this.docRemoteException;
    }

    public ClassDoc docRuntimeException() {
        return this.docRuntimeException;
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    public boolean verbose() {
        return this.verbose;
    }

    public void addGeneratedFile(File file) {
        this.generatedFiles.add(file);
    }

    public List<File> generatedFiles() {
        return Collections.unmodifiableList(this.generatedFiles);
    }

    public void output(String str) {
        this.rootDoc.printNotice(str);
    }

    public void error(String str, String... strArr) {
        this.rootDoc.printError(Resources.getText(str, strArr));
    }
}
