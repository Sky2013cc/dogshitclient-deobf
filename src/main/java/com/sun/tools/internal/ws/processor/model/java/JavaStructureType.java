package com.sun.tools.internal.ws.processor.model.java;

import com.sun.tools.internal.ws.processor.model.ModelException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: target.jar:com/sun/tools/internal/ws/processor/model/java/JavaStructureType.class */
public class JavaStructureType extends JavaType {
    private List<JavaStructureMember> members;
    private Map<String, JavaStructureMember> membersByName;
    private Set subclasses;
    private JavaStructureType superclass;
    private Object owner;
    private boolean isAbstract;

    public JavaStructureType() {
        this.members = new ArrayList();
        this.membersByName = new HashMap();
        this.subclasses = new HashSet();
        this.isAbstract = false;
    }

    public JavaStructureType(String name, boolean present, Object owner) {
        super(name, present, "null");
        this.members = new ArrayList();
        this.membersByName = new HashMap();
        this.subclasses = new HashSet();
        this.isAbstract = false;
        this.owner = owner;
    }

    public void add(JavaStructureMember m) {
        if (this.membersByName.containsKey(m.getName())) {
            throw new ModelException("model.uniqueness.javastructuretype", m.getName(), getRealName());
        }
        this.members.add(m);
        this.membersByName.put(m.getName(), m);
    }

    public JavaStructureMember getMemberByName(String name) {
        if (this.membersByName.size() != this.members.size()) {
            initializeMembersByName();
        }
        return this.membersByName.get(name);
    }

    public Iterator getMembers() {
        return this.members.iterator();
    }

    public int getMembersCount() {
        return this.members.size();
    }

    public List<JavaStructureMember> getMembersList() {
        return this.members;
    }

    public void setMembersList(List<JavaStructureMember> l) {
        this.members = l;
    }

    private void initializeMembersByName() {
        this.membersByName = new HashMap();
        if (this.members != null) {
            for (JavaStructureMember m : this.members) {
                if (m.getName() != null && this.membersByName.containsKey(m.getName())) {
                    throw new ModelException("model.uniqueness", new Object[0]);
                }
                this.membersByName.put(m.getName(), m);
            }
        }
    }

    public boolean isAbstract() {
        return this.isAbstract;
    }

    public void setAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    public JavaStructureType getSuperclass() {
        return this.superclass;
    }

    public void setSuperclass(JavaStructureType superclassType) {
        this.superclass = superclassType;
    }

    public void addSubclass(JavaStructureType subclassType) {
        this.subclasses.add(subclassType);
        subclassType.setSuperclass(this);
    }

    public Iterator getSubclasses() {
        if (this.subclasses == null || this.subclasses.size() == 0) {
            return null;
        }
        return this.subclasses.iterator();
    }

    public Set getSubclassesSet() {
        return this.subclasses;
    }

    public void setSubclassesSet(Set s) {
        this.subclasses = s;
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            ((JavaStructureType) iter.next()).setSuperclass(this);
        }
    }

    public Iterator getAllSubclasses() {
        Set subs = getAllSubclassesSet();
        if (subs.size() == 0) {
            return null;
        }
        return subs.iterator();
    }

    public Set getAllSubclassesSet() {
        Set transitiveSet = new HashSet();
        Iterator subs = this.subclasses.iterator();
        while (subs.hasNext()) {
            transitiveSet.addAll(((JavaStructureType) subs.next()).getAllSubclassesSet());
        }
        transitiveSet.addAll(this.subclasses);
        return transitiveSet;
    }

    public Object getOwner() {
        return this.owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
