package com.sun.xml.internal.xsom.impl.parser.state;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/* loaded from: target.jar:com/sun/xml/internal/xsom/impl/parser/state/NGCCHandler.class */
public abstract class NGCCHandler implements NGCCEventReceiver {
    protected final NGCCHandler _parent;
    protected final NGCCEventSource _source;
    protected final int _cookie;

    protected abstract NGCCRuntime getRuntime();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void onChildCompleted(Object obj, int i, boolean z) throws SAXException;

    /* JADX INFO: Access modifiers changed from: protected */
    public NGCCHandler(NGCCEventSource source, NGCCHandler parent, int parentCookie) {
        this._parent = parent;
        this._source = source;
        this._cookie = parentCookie;
    }

    public void spawnChildFromEnterElement(NGCCEventReceiver child, String uri, String localname, String qname, Attributes atts) throws SAXException {
        int id = this._source.replace(this, child);
        this._source.sendEnterElement(id, uri, localname, qname, atts);
    }

    public void spawnChildFromEnterAttribute(NGCCEventReceiver child, String uri, String localname, String qname) throws SAXException {
        int id = this._source.replace(this, child);
        this._source.sendEnterAttribute(id, uri, localname, qname);
    }

    public void spawnChildFromLeaveElement(NGCCEventReceiver child, String uri, String localname, String qname) throws SAXException {
        int id = this._source.replace(this, child);
        this._source.sendLeaveElement(id, uri, localname, qname);
    }

    public void spawnChildFromLeaveAttribute(NGCCEventReceiver child, String uri, String localname, String qname) throws SAXException {
        int id = this._source.replace(this, child);
        this._source.sendLeaveAttribute(id, uri, localname, qname);
    }

    public void spawnChildFromText(NGCCEventReceiver child, String value) throws SAXException {
        int id = this._source.replace(this, child);
        this._source.sendText(id, value);
    }

    public void revertToParentFromEnterElement(Object result, int cookie, String uri, String local, String qname, Attributes atts) throws SAXException {
        int id = this._source.replace(this, this._parent);
        this._parent.onChildCompleted(result, cookie, true);
        this._source.sendEnterElement(id, uri, local, qname, atts);
    }

    public void revertToParentFromLeaveElement(Object result, int cookie, String uri, String local, String qname) throws SAXException {
        if (uri == "��" && uri == local && uri == qname && this._parent == null) {
            return;
        }
        int id = this._source.replace(this, this._parent);
        this._parent.onChildCompleted(result, cookie, true);
        this._source.sendLeaveElement(id, uri, local, qname);
    }

    public void revertToParentFromEnterAttribute(Object result, int cookie, String uri, String local, String qname) throws SAXException {
        int id = this._source.replace(this, this._parent);
        this._parent.onChildCompleted(result, cookie, true);
        this._source.sendEnterAttribute(id, uri, local, qname);
    }

    public void revertToParentFromLeaveAttribute(Object result, int cookie, String uri, String local, String qname) throws SAXException {
        int id = this._source.replace(this, this._parent);
        this._parent.onChildCompleted(result, cookie, true);
        this._source.sendLeaveAttribute(id, uri, local, qname);
    }

    public void revertToParentFromText(Object result, int cookie, String text) throws SAXException {
        int id = this._source.replace(this, this._parent);
        this._parent.onChildCompleted(result, cookie, true);
        this._source.sendText(id, text);
    }

    public void unexpectedEnterElement(String qname) throws SAXException {
        getRuntime().unexpectedX('<' + qname + '>');
    }

    public void unexpectedLeaveElement(String qname) throws SAXException {
        getRuntime().unexpectedX("</" + qname + '>');
    }

    public void unexpectedEnterAttribute(String qname) throws SAXException {
        getRuntime().unexpectedX('@' + qname);
    }

    public void unexpectedLeaveAttribute(String qname) throws SAXException {
        getRuntime().unexpectedX("/@" + qname);
    }
}
