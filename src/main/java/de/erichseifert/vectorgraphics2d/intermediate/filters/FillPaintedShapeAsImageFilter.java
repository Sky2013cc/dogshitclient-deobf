package de.erichseifert.vectorgraphics2d.intermediate.filters;

import de.erichseifert.vectorgraphics2d.intermediate.CommandSequence;
import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import de.erichseifert.vectorgraphics2d.intermediate.commands.DisposeCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.DrawImageCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.FillShapeCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.SetPaintCommand;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;

/* loaded from: target.jar:de/erichseifert/vectorgraphics2d/intermediate/filters/FillPaintedShapeAsImageFilter.class */
public class FillPaintedShapeAsImageFilter extends StreamingFilter {
    private SetPaintCommand a;

    public FillPaintedShapeAsImageFilter(CommandSequence commandSequence) {
        super(commandSequence);
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter, java.util.Iterator
    public Command<?> next() {
        Command<?> next = super.next();
        if (next instanceof SetPaintCommand) {
            this.a = (SetPaintCommand) next;
        } else if (next instanceof DisposeCommand) {
            this.a = null;
        }
        return next;
    }

    @Override // de.erichseifert.vectorgraphics2d.intermediate.filters.StreamingFilter
    protected List<Command<?>> filter(Command<?> command) {
        if (this.a != null && (command instanceof FillShapeCommand)) {
            SetPaintCommand setPaintCommand = this.a;
            Shape value = ((FillShapeCommand) command).getValue();
            Rectangle2D bounds2D = value.getBounds2D();
            double x = bounds2D.getX();
            double y = bounds2D.getY();
            double width = bounds2D.getWidth();
            double height = bounds2D.getHeight();
            int round = (int) Math.round(width);
            int round2 = (int) Math.round(height);
            BufferedImage bufferedImage = new BufferedImage(round, round2, 2);
            Graphics2D graphics = bufferedImage.getGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.scale(round / width, round2 / height);
            graphics.translate(-bounds2D.getX(), -bounds2D.getY());
            graphics.setPaint(setPaintCommand.getValue());
            graphics.fill(value);
            graphics.dispose();
            return Collections.singletonList(new DrawImageCommand(bufferedImage, round, round2, x, y, width, height));
        }
        return Collections.singletonList(command);
    }
}
