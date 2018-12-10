package org.brk.rspeer.tasks.traversing;

import org.brk.rspeer.data.information.PlayerState;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class MoveToDocks extends Task {

    private Npc seagull;


    @Override
    public int execute() {
        if (Movement.getRunEnergy() > Random.nextInt(7, 13) && !Movement.isRunEnabled()) {
            Movement.toggleRun(true);
            return 1000;
        }

        if (!PlayerState.isTraversing()) {
            Movement.walkTo(PlayerState.PORT_SARIM_DOCKS);
        }
        return 1000;
    }

    @Override
    public boolean validate() {
        seagull = Npcs.getNearest(x -> x.getName().equals("Seagull") && (x.getTargetIndex() == -1 || x.getTarget().equals(Players.getLocal())) && x.getHealthPercent() > 0);
        return seagull == null && Players.getLocal().distance(PlayerState.PORT_SARIM_DOCKS) > 15;
    }

    @Override
    public String toString() {
        return "Traversing";
    }
}
