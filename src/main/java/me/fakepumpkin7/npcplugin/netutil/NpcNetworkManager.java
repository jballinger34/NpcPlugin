package me.fakepumpkin7.npcplugin.netutil;

import net.minecraft.server.v1_8_R3.EnumProtocolDirection;
import net.minecraft.server.v1_8_R3.NetworkManager;

public class NpcNetworkManager extends NetworkManager {
    public NpcNetworkManager(EnumProtocolDirection enumprotocoldirection) {
        super(enumprotocoldirection);
    }
}
