package net.veroxuniverse.what_lurks_between.client;

/**
 * Stores the sanity value locally on the client for HUD rendering and effects.
 */
public class ClientSanityData {
    // We start at 100 (healthy) by default
    private static float clientSanity = 100f;

    /**
     * Updates the local sanity value. Called by the network packet handler.
     */
    public static void setClientSanity(float value) {
        clientSanity = value;
    }

    /**
     * @return The current sanity value for the local player.
     */
    public static float getSanity() {
        return clientSanity;
    }
}
