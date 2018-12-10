package org.brk.rspeer.rendering.paint;

import org.brk.rspeer.main.SeagullKiller;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.EventDispatcher;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;

import java.awt.*;

public class PaintRenderer implements RenderListener {

    public static String action;
    private SeagullKiller main;
    private EventDispatcher eventDispatcher;

    public PaintRenderer(SeagullKiller main) {
        this.main = main;
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Player me = Players.getLocal();
        Graphics g = renderEvent.getSource();

        if (me.getTargetIndex() != -1)
            me.getTarget().getPosition().outline(g);

        int x = 372;
        int y = 19;

        if (main.getCurrent() != null) {
            action = main.getCurrent().toString();
        } else {
            action = "Idle";
        }

        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(x - 5, y - 15, 150, 100);
        g.setColor(Color.red);

        g.setColor(Color.decode("#B63EA3"));
        g.drawString("SeagullKiller", x, y);
        g.setColor(Color.GREEN);

        g.drawString("Runtime: " + SeagullKiller.time.toElapsedString(), x, y += 11);
        g.drawString("Status: " + action, x, y += 11);
    }
}
