package com.sun.tools.hat.internal.server;

import com.sun.tools.hat.internal.oql.OQLEngine;
import com.sun.tools.hat.internal.oql.OQLException;
import com.sun.tools.hat.internal.oql.ObjectVisitor;

/* loaded from: target.jar:com/sun/tools/hat/internal/server/OQLQuery.class */
class OQLQuery extends QueryHandler {
    private OQLEngine engine;

    public OQLQuery(OQLEngine oQLEngine) {
        this.engine = oQLEngine;
    }

    @Override // com.sun.tools.hat.internal.server.QueryHandler
    public void run() {
        int indexOf;
        startHtml("Object Query Language (OQL) query");
        String str = null;
        if (this.query != null && !this.query.equals("") && (indexOf = this.query.indexOf("?query=")) != -1 && this.query.length() > 7) {
            str = this.query.substring(indexOf + 7);
        }
        this.out.println("<p align='center'><table>");
        this.out.println("<tr><td><b>");
        this.out.println("<a href='/'>All Classes (excluding platform)</a>");
        this.out.println("</b></td>");
        this.out.println("<td><b><a href='/oqlhelp/'>OQL Help</a></b></td></tr>");
        this.out.println("</table></p>");
        this.out.println("<form action='/oql/' method='get'>");
        this.out.println("<p align='center'>");
        this.out.println("<textarea name='query' cols=80 rows=10>");
        if (str != null) {
            println(str);
        }
        this.out.println("</textarea>");
        this.out.println("</p>");
        this.out.println("<p align='center'>");
        this.out.println("<input type='submit' value='Execute'></input>");
        this.out.println("</p>");
        this.out.println("</form>");
        if (str != null) {
            executeQuery(str);
        }
        endHtml();
    }

    private void executeQuery(String str) {
        try {
            this.out.println("<table border='1'>");
            this.engine.executeQuery(str, new ObjectVisitor() { // from class: com.sun.tools.hat.internal.server.OQLQuery.1
                @Override // com.sun.tools.hat.internal.oql.ObjectVisitor
                public boolean visit(Object obj) {
                    OQLQuery.this.out.println("<tr><td>");
                    try {
                        OQLQuery.this.out.println(OQLQuery.this.engine.toHtml(obj));
                    } catch (Exception e) {
                        OQLQuery.this.printException(e);
                    }
                    OQLQuery.this.out.println("</td></tr>");
                    return false;
                }
            });
            this.out.println("</table>");
        } catch (OQLException e) {
            printException(e);
        }
    }
}
