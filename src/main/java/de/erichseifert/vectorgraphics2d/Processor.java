package de.erichseifert.vectorgraphics2d;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.util.PageSize;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/Processor.class */
public interface Processor {
    Document getDocument(CommandSequence commandSequence, PageSize pageSize);
}
