package com.sun.tools.internal.jxc.gen.config;

import com.sun.tools.internal.jxc.NGCCRuntimeEx;
import com.sun.tools.internal.ws.wsdl.parser.Constants;
import java.io.File;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/tools/internal/jxc/gen/config/Schema.class */
public class Schema extends NGCCHandler {
    private File baseDir;
    private String loc;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;
    private File location;
    private String namespace;

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCHandler
    public final NGCCRuntime getRuntime() {
        return this.$runtime;
    }

    public Schema(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie, File _baseDir) {
        super(source, parent, cookie);
        this.$runtime = runtime;
        this.baseDir = _baseDir;
        this.$_ngcc_current_state = 10;
    }

    public Schema(NGCCRuntimeEx runtime, File _baseDir) {
        this(null, runtime, runtime, -1, _baseDir);
    }

    private void action0() throws SAXException {
        this.location = new File(this.baseDir, this.loc);
    }

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCEventReceiver
    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        this.$uri = $__uri;
        this.$localName = $__local;
        this.$qname = $__qname;
        switch (this.$_ngcc_current_state) {
            case 0:
                revertToParentFromEnterElement(this, this._cookie, $__uri, $__local, $__qname, $attrs);
                return;
            case 2:
                int $ai = this.$runtime.getAttributeIndex("", Constants.ATTR_LOCATION);
                if ($ai >= 0) {
                    this.$runtime.consumeAttribute($ai);
                    this.$runtime.sendEnterElement(this._cookie, $__uri, $__local, $__qname, $attrs);
                    return;
                } else {
                    this.$_ngcc_current_state = 1;
                    this.$runtime.sendEnterElement(this._cookie, $__uri, $__local, $__qname, $attrs);
                    return;
                }
            case 6:
                int $ai2 = this.$runtime.getAttributeIndex("", Constants.ATTR_NAMESPACE);
                if ($ai2 >= 0) {
                    this.$runtime.consumeAttribute($ai2);
                    this.$runtime.sendEnterElement(this._cookie, $__uri, $__local, $__qname, $attrs);
                    return;
                } else {
                    this.$_ngcc_current_state = 2;
                    this.$runtime.sendEnterElement(this._cookie, $__uri, $__local, $__qname, $attrs);
                    return;
                }
            case 10:
                if ($__uri.equals("") && $__local.equals("schema")) {
                    this.$runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    this.$_ngcc_current_state = 6;
                    return;
                } else {
                    unexpectedEnterElement($__qname);
                    return;
                }
            default:
                unexpectedEnterElement($__qname);
                return;
        }
    }

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCEventReceiver
    public void leaveElement(String $__uri, String $__local, String $__qname) throws SAXException {
        this.$uri = $__uri;
        this.$localName = $__local;
        this.$qname = $__qname;
        switch (this.$_ngcc_current_state) {
            case 0:
                revertToParentFromLeaveElement(this, this._cookie, $__uri, $__local, $__qname);
                return;
            case 1:
                if ($__uri.equals("") && $__local.equals("schema")) {
                    this.$runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    this.$_ngcc_current_state = 0;
                    return;
                } else {
                    unexpectedLeaveElement($__qname);
                    return;
                }
            case 2:
                int $ai = this.$runtime.getAttributeIndex("", Constants.ATTR_LOCATION);
                if ($ai >= 0) {
                    this.$runtime.consumeAttribute($ai);
                    this.$runtime.sendLeaveElement(this._cookie, $__uri, $__local, $__qname);
                    return;
                } else {
                    this.$_ngcc_current_state = 1;
                    this.$runtime.sendLeaveElement(this._cookie, $__uri, $__local, $__qname);
                    return;
                }
            case 3:
            case 4:
            case 5:
            default:
                unexpectedLeaveElement($__qname);
                return;
            case 6:
                int $ai2 = this.$runtime.getAttributeIndex("", Constants.ATTR_NAMESPACE);
                if ($ai2 >= 0) {
                    this.$runtime.consumeAttribute($ai2);
                    this.$runtime.sendLeaveElement(this._cookie, $__uri, $__local, $__qname);
                    return;
                } else {
                    this.$_ngcc_current_state = 2;
                    this.$runtime.sendLeaveElement(this._cookie, $__uri, $__local, $__qname);
                    return;
                }
        }
    }

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCEventReceiver
    public void enterAttribute(String $__uri, String $__local, String $__qname) throws SAXException {
        this.$uri = $__uri;
        this.$localName = $__local;
        this.$qname = $__qname;
        switch (this.$_ngcc_current_state) {
            case 0:
                revertToParentFromEnterAttribute(this, this._cookie, $__uri, $__local, $__qname);
                return;
            case 2:
                if ($__uri.equals("") && $__local.equals(Constants.ATTR_LOCATION)) {
                    this.$_ngcc_current_state = 4;
                    return;
                } else {
                    this.$_ngcc_current_state = 1;
                    this.$runtime.sendEnterAttribute(this._cookie, $__uri, $__local, $__qname);
                    return;
                }
            case 6:
                if ($__uri.equals("") && $__local.equals(Constants.ATTR_NAMESPACE)) {
                    this.$_ngcc_current_state = 8;
                    return;
                } else {
                    this.$_ngcc_current_state = 2;
                    this.$runtime.sendEnterAttribute(this._cookie, $__uri, $__local, $__qname);
                    return;
                }
            default:
                unexpectedEnterAttribute($__qname);
                return;
        }
    }

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCEventReceiver
    public void leaveAttribute(String $__uri, String $__local, String $__qname) throws SAXException {
        this.$uri = $__uri;
        this.$localName = $__local;
        this.$qname = $__qname;
        switch (this.$_ngcc_current_state) {
            case 0:
                revertToParentFromLeaveAttribute(this, this._cookie, $__uri, $__local, $__qname);
                return;
            case 1:
            case 4:
            case 5:
            default:
                unexpectedLeaveAttribute($__qname);
                return;
            case 2:
                this.$_ngcc_current_state = 1;
                this.$runtime.sendLeaveAttribute(this._cookie, $__uri, $__local, $__qname);
                return;
            case 3:
                if ($__uri.equals("") && $__local.equals(Constants.ATTR_LOCATION)) {
                    this.$_ngcc_current_state = 1;
                    return;
                } else {
                    unexpectedLeaveAttribute($__qname);
                    return;
                }
            case 6:
                this.$_ngcc_current_state = 2;
                this.$runtime.sendLeaveAttribute(this._cookie, $__uri, $__local, $__qname);
                return;
            case 7:
                if ($__uri.equals("") && $__local.equals(Constants.ATTR_NAMESPACE)) {
                    this.$_ngcc_current_state = 2;
                    return;
                } else {
                    unexpectedLeaveAttribute($__qname);
                    return;
                }
        }
    }

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCEventReceiver
    public void text(String $value) throws SAXException {
        switch (this.$_ngcc_current_state) {
            case 0:
                revertToParentFromText(this, this._cookie, $value);
                return;
            case 1:
            case 3:
            case 5:
            case 7:
            default:
                return;
            case 2:
                int $ai = this.$runtime.getAttributeIndex("", Constants.ATTR_LOCATION);
                if ($ai >= 0) {
                    this.$runtime.consumeAttribute($ai);
                    this.$runtime.sendText(this._cookie, $value);
                    return;
                } else {
                    this.$_ngcc_current_state = 1;
                    this.$runtime.sendText(this._cookie, $value);
                    return;
                }
            case 4:
                this.loc = $value;
                this.$_ngcc_current_state = 3;
                action0();
                return;
            case 6:
                int $ai2 = this.$runtime.getAttributeIndex("", Constants.ATTR_NAMESPACE);
                if ($ai2 >= 0) {
                    this.$runtime.consumeAttribute($ai2);
                    this.$runtime.sendText(this._cookie, $value);
                    return;
                } else {
                    this.$_ngcc_current_state = 2;
                    this.$runtime.sendText(this._cookie, $value);
                    return;
                }
            case 8:
                this.namespace = $value;
                this.$_ngcc_current_state = 7;
                return;
        }
    }

    @Override // com.sun.tools.internal.jxc.gen.config.NGCCHandler
    public void onChildCompleted(Object result, int cookie, boolean needAttCheck) throws SAXException {
    }

    public boolean accepted() {
        return this.$_ngcc_current_state == 0;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public File getLocation() {
        return this.location;
    }
}
