package intelligence.NPCs.Other;

import java.util.Collection;

import l2.brick.Config;
import l2.brick.bflmpsvz.a.L2AttackableAIScript;
import l2.brick.gameserver.datatables.SpawnTable;
import l2.brick.gameserver.model.L2Spawn;
import l2.brick.gameserver.model.actor.L2Npc;

public class NoRandomAnimation extends L2AttackableAIScript
{
	private final static int[] NO_ANIMATION_MOBS_LIST = 
	{ 
		13148, 18635, 18636, 18638, 18639, 18640, 18641, 18642, 18644, 18645, 18646, 18648,
		18649, 18650, 18652, 18653, 18654, 18655, 18656, 18657, 18658, 18659, 18660, 18704, 
		18705, 18706, 18708, 18709, 18710, 18711, 18805, 18806, 18811, 22136, 29045, 29046,
		29047, 29048, 29049, 29050, 29051, 29099, 29103, 29150, 29151, 29152, 29161, 29163, 
		29173, 29174, 29175, 30675, 30761, 30762, 30763, 30980, 31074, 31665, 32746, 31752, 
		32015, 32568, 32556, 32568 
	};

	public NoRandomAnimation(int questId, String name, String descr)
	{
		super(questId, name, descr);

		final Collection<L2Spawn> spawns =  SpawnTable.getInstance().getSpawnTable();
		for (L2Spawn npc : spawns)
		{
			if (contains(NO_ANIMATION_MOBS_LIST, npc.getTemplate().npcId))
			{
				if (npc.getLastSpawn() != null)
					npc.getLastSpawn().setIsNoAnimation(true);
			}
		}

		for (int npcid : NO_ANIMATION_MOBS_LIST)
			addSpawnId(npcid);
	}

	@Override
	public String onSpawn(L2Npc npc)
	{
		if (contains(NO_ANIMATION_MOBS_LIST, npc.getNpcId()))
			npc.setIsNoAnimation(true);

		return super.onSpawn(npc);
	}

	public static void main(String[] args)
	{
		new NoRandomAnimation(-1, "NoRandomAnimation", "ai");
		if (Config.ENABLE_LOADING_INFO_FOR_SCRIPTS)
			_log.info("Loaded NPC: No Random Animation");
	}
}
