package jdk.nashorn.internal.runtime;

import java.lang.invoke.SwitchPoint;

/* loaded from: target.jar:jdk/nashorn/internal/runtime/SharedPropertyMap.class */
public final class SharedPropertyMap extends PropertyMap {
    private SwitchPoint switchPoint;
    private static final long serialVersionUID = 2166297719721778876L;
    static final /* synthetic */ boolean $assertionsDisabled;

    static {
        $assertionsDisabled = !SharedPropertyMap.class.desiredAssertionStatus();
    }

    public SharedPropertyMap(PropertyMap map) {
        super(map);
        this.switchPoint = new SwitchPoint();
    }

    @Override // jdk.nashorn.internal.runtime.PropertyMap
    public void propertyAdded(Property property, boolean isSelf) {
        if (isSelf) {
            invalidateSwitchPoint();
        }
        super.propertyAdded(property, isSelf);
    }

    @Override // jdk.nashorn.internal.runtime.PropertyMap
    public void propertyDeleted(Property property, boolean isSelf) {
        if (isSelf) {
            invalidateSwitchPoint();
        }
        super.propertyDeleted(property, isSelf);
    }

    @Override // jdk.nashorn.internal.runtime.PropertyMap
    public void propertyModified(Property oldProperty, Property newProperty, boolean isSelf) {
        if (isSelf) {
            invalidateSwitchPoint();
        }
        super.propertyModified(oldProperty, newProperty, isSelf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.nashorn.internal.runtime.PropertyMap
    public synchronized boolean isValidSharedProtoMap() {
        return this.switchPoint != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // jdk.nashorn.internal.runtime.PropertyMap
    public synchronized SwitchPoint getSharedProtoSwitchPoint() {
        return this.switchPoint;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void invalidateSwitchPoint() {
        if (this.switchPoint != null) {
            if (!$assertionsDisabled && this.switchPoint.hasBeenInvalidated()) {
                throw new AssertionError();
            }
            SwitchPoint.invalidateAll(new SwitchPoint[]{this.switchPoint});
            this.switchPoint = null;
        }
    }
}
