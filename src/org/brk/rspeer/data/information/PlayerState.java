package org.brk.rspeer.data.information;

import org.rspeer.runetek.adapter.component.Item;
import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.adapter.scene.Pickable;
import org.rspeer.runetek.adapter.scene.Player;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Position;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Pickables;
import org.rspeer.runetek.api.scene.Players;

public class PlayerState {

    public static final Position PORT_SARIM_DOCKS = new Position(3028, 3236, 0);

    public static boolean isTraversing() {
        return Movement.getDestination() != null && Movement.getDestination().distance() >= Random.nextInt(4, 6) && Players.getLocal().isMoving();
    }
}
