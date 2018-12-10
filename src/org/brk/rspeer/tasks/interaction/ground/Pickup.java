package org.brk.rspeer.tasks.interaction.ground;

import org.brk.rspeer.data.information.PlayerState;
import org.brk.rspeer.data.settings.ScriptSettings;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Pickup extends Task {

    private ScriptSettings s = ScriptSettings.getInstance();
    private Pickable groundBones;

    @Override
    public int execute() {
        groundBones.interact("Take");
        return 1000;
    }

    @Override
    public boolean validate() {
        groundBones = Pickables.getNearest(x -> x.getName().equals("Bones") && x.distance(Players.getLocal()) < 20 && x.distance(PlayerState.PORT_SARIM_DOCKS) < 20);
        return s.isDoBury() && Skills.getLevel(Skill.PRAYER) < s.getTrainPrayer() && groundBones != null;
    }

    @Override
    public String toString() {
        return "Picking";
    }
}
