/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package intelligence.Monsters;

import l2.brick.gameserver.ai.CtrlIntention;
import l2.brick.gameserver.model.actor.L2Npc;
import l2.brick.gameserver.model.actor.instance.L2PcInstance;
import l2.brick.util.Rnd;
import l2.brick.bflmpsvz.a.L2AttackableAIScript;


public class KarulBugbear extends L2AttackableAIScript
{
	private static final int KARUL_BUGBEAR = 20600;
	
	public KarulBugbear(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addAttackId(KARUL_BUGBEAR);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet)
	{
		if (npc.getNpcId() == KARUL_BUGBEAR)
		{
			if (npc.getAI().getIntention() != CtrlIntention.AI_INTENTION_ATTACK)
			{
				if (Rnd.get(100) < 70)
					npc.broadcastNpcSay("Watch your back!");
			}
			else if (Rnd.get(100) < 10)
				npc.broadcastNpcSay("Your rear is practically unguarded!");
		}
		
		return super.onAttack(npc, player, damage, isPet);
	}
	
	public static void main(String[] args)
	{
		new KarulBugbear(-1, "KarulBugbear", "ai");
	}
}
