package quests.Q637_ThroughOnceMore;

import l2.brick.Config;
import l2.brick.gameserver.model.actor.L2Npc;
import l2.brick.gameserver.model.actor.instance.L2PcInstance;
import l2.brick.gameserver.model.quest.Quest;
import l2.brick.gameserver.model.quest.QuestState;
import l2.brick.gameserver.model.quest.State;

public final class Q637_ThroughOnceMore extends Quest
{
	private static final String QN = "637_ThroughOnceMore";
	
	private static final int FLAURON = 32010;
	private static final int[] MOBS = { 21565, 21566, 21567 };
	private static final int VISITOR_MARK = 8064;
	private static final int FADED_MARK = 8065;
	private static final int NECRO_HEART = 8066;
	private static final int MARK = 8067;
	
	private static final double DROP_CHANCE = 90;
	
	public Q637_ThroughOnceMore(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		addStartNpc(FLAURON);
		addTalkId(FLAURON);
		for (int id : MOBS)
			addKillId(id);
		
		questItemIds = new int[]{NECRO_HEART};
	}
	
	@Override
	public final String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		final QuestState st = player.getQuestState(QN);
		if (st == null)
			return null;
		
		if ("32010-03.htm".equalsIgnoreCase(event))
		{
			st.set("cond", "1");
			st.setState(State.STARTED);
			st.playSound("ItemSound.quest_accept");
		}
		else if ("32010-10.htm".equalsIgnoreCase(event))
			st.exitQuest(true);
		
		return event;
	}
	
	@Override
	public final String onTalk(L2Npc npc, L2PcInstance player)
	{
		final QuestState st = player.getQuestState(QN);
		if (st == null)
			return getNoQuestMsg(player);
		
		final byte id = st.getState();
		if (id == State.CREATED)
		{
			if (player.getLevel() > 72)
			{
				if (st.getQuestItemsCount(FADED_MARK) > 0)
					return "32010-02.htm";
				if (st.getQuestItemsCount(VISITOR_MARK) > 0)
				{
					st.exitQuest(true);
					return "32010-01a.htm";
				}
				if (st.getQuestItemsCount(MARK) > 0)
				{
					st.exitQuest(true);
					return "32010-0.htm";
				}
			}
			st.exitQuest(true);
			return "32010-01.htm";
		}
		else if (id == State.STARTED)
		{
			if (Integer.parseInt(st.get("cond")) == 2
					&& st.getQuestItemsCount(NECRO_HEART) == 10)
			{
				st.takeItems(NECRO_HEART, 10);
				st.takeItems(FADED_MARK, 1);
				st.giveItems(MARK, 1);
				st.giveItems(8273,10);
				st.exitQuest(true);
				st.playSound("ItemSound.quest_finish");
				return "32010-05.htm";
			}
			else
				return "32010-04.htm";
		}
		
		return getNoQuestMsg(player);
	}
	
	@Override
	public final String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
	{
		final QuestState st = player.getQuestState(QN);
		if (st != null && st.getState() == State.STARTED)
		{
			final long count = st.getQuestItemsCount(NECRO_HEART);
			if (count < 10)
			{
				int chance = (int)(Config.RATE_QUEST_DROP * DROP_CHANCE);
				int numItems = chance / 100;
				chance = chance % 100;
				if (st.getRandom(100) < chance)
					numItems++;
				if (numItems > 0)
				{
					if (count + numItems >= 10)
					{
						numItems = 10 - (int)count;
						st.playSound("ItemSound.quest_middle");
						st.set("cond", "2");
					}
					else
						st.playSound("ItemSound.quest_itemget");
					
					st.giveItems(NECRO_HEART, numItems);
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		new Q637_ThroughOnceMore(637, QN, "Through the Gate Once More");
		if (Config.ENABLE_LOADING_INFO_FOR_SCRIPTS)
			_log.info("Loaded Quest: Through the Gate Once More");
	}
}