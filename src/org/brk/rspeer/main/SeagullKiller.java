package org.brk.rspeer.main;

import org.brk.rspeer.rendering.paint.PaintRenderer;
import org.brk.rspeer.tasks.interaction.combat.Attack;
import org.brk.rspeer.tasks.interaction.ground.Pickup;
import org.brk.rspeer.tasks.interaction.interfaces.ContinueDialog;
import org.brk.rspeer.tasks.interaction.inventory.Bury;
import org.brk.rspeer.tasks.randoms.DismissRandom;
import org.brk.rspeer.tasks.traversing.MoveToDocks;
import org.brk.rspeer.userinterface.Gui;
import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.Game;
import org.rspeer.runetek.api.commons.StopWatch;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Dialog;
import org.rspeer.runetek.api.component.tab.Combat;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.component.tab.Skill;
import org.rspeer.runetek.api.component.tab.Skills;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.event.EventDispatcher;
import org.rspeer.runetek.event.listeners.RenderListener;
import org.rspeer.runetek.event.types.RenderEvent;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptCategory;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;
import org.rspeer.ui.Log;

import javax.swing.*;
import java.awt.*;

@ScriptMeta(version = 0.02, name = "SeagullKiller", category = ScriptCategory.COMBAT, developer = "buracc", desc = "Kills seagulls")
public class SeagullKiller extends TaskScript {

    public static StopWatch time = null;
    public PaintRenderer paintRenderer;
    public EventDispatcher eventDispatcher;
    Task[] tasks = {
            new DismissRandom(),
            new ContinueDialog(),
            new MoveToDocks(),
            new Pickup(),
            new Bury(),
            new Attack()
    };

    @Override
    public void onStart() {
        this.setPaused(true);
        SwingUtilities.invokeLater(() -> new Gui(this));
        submit(tasks);
    }

    @Override
    public void onStop() {
        eventDispatcher.deregister(paintRenderer);
    }
}