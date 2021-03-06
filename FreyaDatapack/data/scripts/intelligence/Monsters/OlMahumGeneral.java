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

public class OlMahumGeneral extends L2AttackableAIScript
{
	private static final int OLMAHUM_GENERAL = 20438;
	
	public OlMahumGeneral(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addAttackId(OLMAHUM_GENERAL);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet)
	{
		if (npc.getNpcId() == OLMAHUM_GENERAL)
		{
			if (npc.getAI().getIntention() != CtrlIntention.AI_INTENTION_ATTACK)
			{
				if (Rnd.get(100) < 70)
					npc.broadcastNpcSay("I will definitely repay this humiliation!");
			}
			else if (Rnd.get(100) < 10)
				npc.broadcastNpcSay("We shall see about that!");
		}
		
		return super.onAttack(npc, player, damage, isPet);
	}
	
	public static void main(String[] args)
	{
		new OlMahumGeneral(-1, "OlMahumGeneral", "ai");
	}
}
