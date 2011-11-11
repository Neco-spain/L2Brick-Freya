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
import l2.brick.gameserver.model.L2Skill;
import l2.brick.gameserver.model.actor.L2Attackable;
import l2.brick.gameserver.model.actor.L2Npc;
import l2.brick.gameserver.model.actor.instance.L2PcInstance;
import l2.brick.gameserver.network.serverpackets.NpcSay;
import l2.brick.bflmpsvz.a.L2AttackableAIScript;
import l2.brick.util.Rnd;


public class WarriorMonk extends L2AttackableAIScript
{
    private boolean FirstAttacked = false;

    public WarriorMonk(int questId, String name, String descr)
	{
		super(questId, name, descr);
		this.registerMobs(new int[] {22129});
	}

    public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet, L2Skill skill)
    {
        if (FirstAttacked)
        {
            if (Rnd.getChance(50)) return super.onAttack(npc,player,damage,isPet,skill);
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), "Brother " + player.getName() + ", move your weapon away!!"));
        }
        else
            FirstAttacked = true;
        return super.onAttack(npc,player,damage,isPet,skill);
    }

    public String onSpawn(L2Npc npc)
    {
        for (L2PcInstance player : npc.getKnownList().getKnownPlayers().values())
        {
            if (player.isInsideRadius(npc, 500, false, false))
            {
                if (player.getActiveWeaponItem() != null)
                {
                  npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), "Brother " + player.getName() + ", move your weapon away!!"));
                  ((L2Attackable)npc).addDamageHate(player, 0, 999);
                  npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
                }
                else
                {
                  ((L2Attackable)npc).getAggroList().remove(player);
                  npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
                }
            }
        }
        return super.onSpawn(npc);
    }

    public String onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isPet)
    {
        if (player.getActiveWeaponItem() != null)
        {
            npc.broadcastPacket(new NpcSay(npc.getObjectId(), 0, npc.getNpcId(), "Brother " + player.getName() + ", move your weapon away!!"));
            ((L2Attackable)npc).addDamageHate(player, 0, 999);
            npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
        }
        else
        {
            ((L2Attackable)npc).getAggroList().remove(player);
            npc.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE, null, null);
        }
        return super.onAggroRangeEnter(npc, player, isPet);
    }

    public static void main(String[] args)
	{
		new WarriorMonk(-1, "WarriorMonk", "ai");
	}
}
