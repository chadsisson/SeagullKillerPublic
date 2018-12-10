package org.brk.rspeer.tasks.interaction.inventory;

import org.brk.rspeer.data.information.PlayerState;
import org.brk.rspeer.data.settings.ScriptSettings;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.script.task.Task;

public class Bury extends Task {

    private ScriptSettings s = ScriptSettings.getInstance();
    private Item invBones;

    @Override
    public int execute() {
        invBones.interact("Bury");
        return 1000;
    }

    @Override
    public boolean validate() {
        invBones = Inventory.getFirst("Bones");
        return s.isDoBury() && Skills.getLevel(Skill.PRAYER) < s.getTrainPrayer() && invBones != null;
    }

    @Override
    public String toString() {
        return "Burying";
    }
}
