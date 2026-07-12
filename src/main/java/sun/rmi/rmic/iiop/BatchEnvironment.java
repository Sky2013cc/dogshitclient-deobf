package sun.rmi.rmic.iiop;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import sun.rmi.rmic.Main;
import sun.tools.java.ClassDefinition;
import sun.tools.java.ClassNotFound;
import sun.tools.java.ClassPath;

/* loaded from: target.jar:sun/rmi/rmic/iiop/BatchEnvironment.class */
public class BatchEnvironment extends sun.rmi.rmic.BatchEnvironment implements Constants {
    private boolean parseNonConforming;
    private boolean standardPackage;
    HashSet alreadyChecked;
    Hashtable allTypes;
    Hashtable invalidTypes;
    DirectoryLoader loader;
    ClassPathLoader classPathLoader;
    Hashtable nameContexts;
    Hashtable namesCache;
    NameContext modulesContext;
    ClassDefinition defRemote;
    ClassDefinition defError;
    ClassDefinition defException;
    ClassDefinition defRemoteException;
    ClassDefinition defCorbaObject;
    ClassDefinition defSerializable;
    ClassDefinition defExternalizable;
    ClassDefinition defThrowable;
    ClassDefinition defRuntimeException;
    ClassDefinition defIDLEntity;
    ClassDefinition defValueBase;
    sun.tools.java.Type typeRemoteException;
    sun.tools.java.Type typeIOException;
    sun.tools.java.Type typeException;
    sun.tools.java.Type typeThrowable;
    ContextStack contextStack;

    public BatchEnvironment(OutputStream outputStream, ClassPath classPath, Main main) {
        super(outputStream, classPath, main);
        this.parseNonConforming = false;
        this.alreadyChecked = new HashSet();
        this.allTypes = new Hashtable(3001, 0.5f);
        this.invalidTypes = new Hashtable(256, 0.5f);
        this.loader = null;
        this.classPathLoader = null;
        this.nameContexts = null;
        this.namesCache = new Hashtable();
        this.modulesContext = new NameContext(false);
        this.defRemote = null;
        this.defError = null;
        this.defException = null;
        this.defRemoteException = null;
        this.defCorbaObject = null;
        this.defSerializable = null;
        this.defExternalizable = null;
        this.defThrowable = null;
        this.defRuntimeException = null;
        this.defIDLEntity = null;
        this.defValueBase = null;
        this.typeRemoteException = null;
        this.typeIOException = null;
        this.typeException = null;
        this.typeThrowable = null;
        this.contextStack = null;
        try {
            this.defRemote = getClassDeclaration(idRemote).getClassDefinition(this);
            this.defError = getClassDeclaration(idJavaLangError).getClassDefinition(this);
            this.defException = getClassDeclaration(idJavaLangException).getClassDefinition(this);
            this.defRemoteException = getClassDeclaration(idRemoteException).getClassDefinition(this);
            this.defCorbaObject = getClassDeclaration(idCorbaObject).getClassDefinition(this);
            this.defSerializable = getClassDeclaration(idJavaIoSerializable).getClassDefinition(this);
            this.defRuntimeException = getClassDeclaration(idJavaLangRuntimeException).getClassDefinition(this);
            this.defExternalizable = getClassDeclaration(idJavaIoExternalizable).getClassDefinition(this);
            this.defThrowable = getClassDeclaration(idJavaLangThrowable).getClassDefinition(this);
            this.defIDLEntity = getClassDeclaration(idIDLEntity).getClassDefinition(this);
            this.defValueBase = getClassDeclaration(idValueBase).getClassDefinition(this);
            this.typeRemoteException = this.defRemoteException.getClassDeclaration().getType();
            this.typeException = this.defException.getClassDeclaration().getType();
            this.typeIOException = getClassDeclaration(idJavaIoIOException).getType();
            this.typeThrowable = getClassDeclaration(idJavaLangThrowable).getType();
            this.classPathLoader = new ClassPathLoader(classPath);
        } catch (ClassNotFound e) {
            error(0L, "rmic.class.not.found", e.name);
            throw new Error();
        }
    }

    public boolean getParseNonConforming() {
        return this.parseNonConforming;
    }

    public void setParseNonConforming(boolean z) {
        if (z && !this.parseNonConforming) {
            reset();
        }
        this.parseNonConforming = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStandardPackage(boolean z) {
        this.standardPackage = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getStandardPackage() {
        return this.standardPackage;
    }

    @Override // sun.rmi.rmic.BatchEnvironment
    public void reset() {
        Enumeration elements = this.allTypes.elements();
        while (elements.hasMoreElements()) {
            ((Type) elements.nextElement()).destroy();
        }
        Enumeration keys = this.invalidTypes.keys();
        while (keys.hasMoreElements()) {
            ((Type) keys.nextElement()).destroy();
        }
        Iterator it = this.alreadyChecked.iterator();
        while (it.hasNext()) {
            ((Type) it.next()).destroy();
        }
        if (this.contextStack != null) {
            this.contextStack.clear();
        }
        if (this.nameContexts != null) {
            Enumeration elements2 = this.nameContexts.elements();
            while (elements2.hasMoreElements()) {
                ((NameContext) elements2.nextElement()).clear();
            }
            this.nameContexts.clear();
        }
        this.allTypes.clear();
        this.invalidTypes.clear();
        this.alreadyChecked.clear();
        this.namesCache.clear();
        this.modulesContext.clear();
        this.loader = null;
        this.parseNonConforming = false;
    }

    @Override // sun.rmi.rmic.BatchEnvironment, sun.tools.javac.BatchEnvironment, sun.tools.java.Environment
    public void shutdown() {
        if (this.alreadyChecked != null) {
            reset();
            this.alreadyChecked = null;
            this.allTypes = null;
            this.invalidTypes = null;
            this.nameContexts = null;
            this.namesCache = null;
            this.modulesContext = null;
            this.defRemote = null;
            this.defError = null;
            this.defException = null;
            this.defRemoteException = null;
            this.defCorbaObject = null;
            this.defSerializable = null;
            this.defExternalizable = null;
            this.defThrowable = null;
            this.defRuntimeException = null;
            this.defIDLEntity = null;
            this.defValueBase = null;
            this.typeRemoteException = null;
            this.typeIOException = null;
            this.typeException = null;
            this.typeThrowable = null;
            super.shutdown();
        }
    }
}
