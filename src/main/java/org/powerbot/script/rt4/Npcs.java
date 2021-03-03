package org.powerbot.script.rt4;

import org.powbot.stream.locatable.interactive.NpcStream;
import org.powbot.stream.Streamable;
import org.powerbot.bot.rt4.client.internal.IClient;
import org.powerbot.bot.rt4.client.internal.INpc;

import java.util.ArrayList;
import java.util.List;

/**
 * Npcs
 */
public class Npcs extends BasicQuery<Npc> implements Streamable<NpcStream> {
	public Npcs(final ClientContext ctx) {
		super(ctx);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Npc> get() {
		final List<Npc> r = new ArrayList<>();
		final IClient client = ctx.client();
		if (client == null) {
			return r;
		}
		final int[] indices = client.getNpcIndices();
		final INpc[] npcs = client.getNpcs();
		if (indices == null || npcs == null) {
			return r;
		}
		for (int index = 0; index < Math.min(client.getNpcCount(), indices.length); ++index) {
			final INpc n = npcs[indices[index]];
			if (n != null) {
				r.add(new Npc(ctx, n));
			}
		}
		return r;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NpcStream toStream() {
		return new NpcStream(ctx, get().stream());
	}

	@Override
	public Npc nil() {
		return Npc.NIL;
	}
}
