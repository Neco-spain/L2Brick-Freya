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
import l2.brick.gameserver.model.quest.Quest;
import l2.brick.util.Rnd;
import javolution.util.FastMap;

public class Splendor extends Quest
{
    private final FastMap<Integer,int[]> SplendorId = new FastMap<Integer,int[]>();
    private boolean AlwaysSpawn = false;

    public Splendor(int id, String name, String descr)
    {
        super(id,name,descr);
        // NpcId, {NewNpcId, % for chance by shot, ModeSpawn}
        // ModeSpawn 1 => delete and spawn the news npc
        // ModeSpawn 2 => just add 1 spawn the news npc
        // if Quest_Drop = 5 => 25% by shot to change mob
        SplendorId.put(21521, new int[]{21522, 5, 1}); // Claw of Splendor
        SplendorId.put(21524, new int[]{21525, 5, 1}); // Blade of Splendor
        SplendorId.put(21527, new int[]{21528, 5, 1}); // Anger of Splendor
        SplendorId.put(21537, new int[]{21538, 5, 1}); // Fang of Splendor
        SplendorId.put(21539, new int[]{21540, 100, 2}); // Wailing of Splendor
        for (int i : SplendorId.keySet())
        {
            addAttackId(i);
            addKillId(i);
        }
        // finally, don't forget to call the parent constructor to prepare the event triggering
        // mechanisms etc.
    }

    public String onAttack(L2Npc npc, L2PcInstance player, int damage, boolean isPet, L2Skill skill)
    {
        int[] tmp = SplendorId.get(npc.getNpcId());
        if (Rnd.get(100) <= tmp[1])
        {
            if (SplendorId.containsKey(npc.getNpcId()))
            {
                if (tmp[2] == 1)
                {
                    npc.deleteMe();
                    L2Npc newNpc = addSpawn(tmp[0], npc);
                    ((L2Attackable)newNpc).addDamageHate(player, 0, 999);
                    newNpc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
                }
                else if (AlwaysSpawn)
                     return super.onAttack(npc,player,damage,isPet,skill);
                else if (tmp[2] == 2)
                {
                    AlwaysSpawn = true;
                    L2Npc newNpc = addSpawn(tmp[0], npc);
                    ((L2Attackable)newNpc).addDamageHate(player, 0, 999);
                    newNpc.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, player);
                }
            }
        }
        return super.onAttack(npc,player,damage,isPet,skill);
    }

    public String onKill(L2Npc npc, L2PcInstance player, boolean isPet)
    {
        int[] tmp = SplendorId.get(npc.getNpcId());
        if (SplendorId.containsKey(npc.getNpcId()))
           if (tmp[2] == 2)
              AlwaysSpawn = false;
        return super.onKill(npc,player,isPet);
    }

    public static void main(String[] args)
	{
		new Splendor(-1, "Splendor", "ai");
	}
}
