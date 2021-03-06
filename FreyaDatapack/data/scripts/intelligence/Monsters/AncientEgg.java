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

import l2.brick.gameserver.datatables.SkillTable;
import l2.brick.gameserver.model.L2Skill;
import l2.brick.gameserver.model.actor.L2Npc;
import l2.brick.gameserver.model.actor.instance.L2PcInstance;
import l2.brick.bflmpsvz.a.L2AttackableAIScript;

public class AncientEgg extends L2AttackableAIScript
{
	private static final int EGG = 18344;
	
	public AncientEgg(int questId, String name, String descr)
	{
		super(questId, name, descr);
		addAttackId(EGG);
	}
	
	@Override
	public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet, L2Skill skill)
	{
		player.setTarget(player);
		player.doCast(SkillTable.getInstance().getInfo(5088, 1));
		
		return "";
	}
	
	public static void main(String[] args)
	{
		new AncientEgg(-1, "AncientEgg", "ai");
	}
}
