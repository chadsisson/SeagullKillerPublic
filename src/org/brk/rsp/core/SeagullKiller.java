package org.brk.rsp.core;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;

import java.awt.*;

@ScriptMeta(version = 0.01, name = "SeagullKiller", category = ScriptCategory.COMBAT, developer = "buracc", desc = "Kills seagulls")
public class SeagullKiller extends Script implements RenderListener {

    final Position docks = new Position(3028, 3236, 0);
    String action = "Idle";
    StopWatch time;

    @Override
    public void onStart() {
        time = StopWatch.start();
    }

    @Override
    public int loop() {
        Player me = Players.getLocal();
        Npc seagull = Npcs.getNearest(x -> x.getName().equals("Seagull") && (x.getTargetIndex() == -1 || x.getTarget().equals(me)) && x.getHealthPercent() > 0);
        Pickable groundBones = Pickables.getNearest(x -> x.getName().equals("Bones") && x.distance(me) < 20 && x.distance(docks) < 20);
        Item invBones = Inventory.getFirst("Bones");

        if (!Movement.isRunEnabled() && Movement.getRunEnergy() > Random.nextInt(5, 15))
            Movement.toggleRun(true);

        if (Dialog.canContinue())
            Dialog.processContinue();

        if (invBones != null)
            invBones.interact("Bury");

        if (groundBones != null) {
            groundBones.interact("Take");
        } else {
            if (me.getTargetIndex() == -1) {
                if (seagull != null) {
                    action = "Attacking";
                    if (!me.isMoving()) {
                        seagull.interact("Attack");
                        Time.sleepUntil(me::isAnimating, Random.nextInt(4000, 5000));
                    }
                } else {
                    if (me.getPosition().distance(docks) > 5) {
                        action = "Walking";
                        Movement.walkToRandomized(docks);
                    } else {
                        action = "Waiting for spawn";
                    }
                }
            } else {
                action = "Waiting/In combat";
            }
        }
        return Random.nextInt(500, 800);
    }

    @Override
    public void notify(RenderEvent renderEvent) {
        Player me = Players.getLocal();
        Graphics g = renderEvent.getSource();

        if (me.getTargetIndex() != -1)
            me.getTarget().getPosition().outline(g);

        int x = 372;
        int y = 19;

        g.setColor(new Color(0, 0, 0, 0.5f));
        g.fillRect(x - 5, y - 15, 150, 100);
        g.setColor(Color.red);

        g.setColor(Color.decode("#B63EA3"));
        g.drawString("SeagullKiller", x, y);
        g.setColor(Color.GREEN);

        g.drawString("Runtime: " + time.toElapsedString(), x, y += 11);
        g.drawString("Status: " + action, x, y += 11);
    }
}