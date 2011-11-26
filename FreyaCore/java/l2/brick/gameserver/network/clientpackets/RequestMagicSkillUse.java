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
package l2.brick.gameserver.network.clientpackets;

import java.util.logging.Logger;

import l2.brick.Config;
import l2.brick.gameserver.ai.CtrlIntention;
import l2.brick.gameserver.datatables.SkillTable;
import l2.brick.gameserver.model.L2CharPosition;
import l2.brick.gameserver.model.L2Skill;
import l2.brick.gameserver.model.actor.instance.L2PcInstance;
import l2.brick.gameserver.model.actor.position.PcPosition;
import l2.brick.gameserver.network.serverpackets.ActionFailed;
import l2.brick.gameserver.templates.L2SkillType;
import l2.brick.gameserver.model.L2Skill.SkillTargetType;

/**
 * This class ...
 *
 * @version $Revision: 1.7.2.1.2.3 $ $Date: 2005/03/27 15:29:30 $
 */
public final class RequestMagicSkillUse extends L2GameClientPacket
{
	private static final String _C__2F_REQUESTMAGICSKILLUSE = "[C] 2F RequestMagicSkillUse";
	private static Logger _log = Logger.getLogger(RequestMagicSkillUse.class.getName());
	
	private int _magicId;
	private boolean _ctrlPressed;
	private boolean _shiftPressed;
	
	@Override
	protected void readImpl()
	{
		_magicId      = readD();              // Identifier of the used skill
		_ctrlPressed  = readD() != 0;         // True if it's a ForceAttack : Ctrl pressed
		_shiftPressed = readC() != 0;         // True if Shift pressed
	}
	
	@Override
	protected void runImpl()
	{
		// Get the current L2PcInstance of the player
		L2PcInstance activeChar = getClient().getActiveChar();
		
		if (activeChar == null)
			return;
		
		// Get the level of the used skill
		int level = activeChar.getSkillLevel(_magicId);
		if (level <= 0)
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		// Get the L2Skill template corresponding to the skillID received from the client
		L2Skill skill = SkillTable.getInstance().getInfo(_magicId, level);
		
		// Check the validity of the skill
		if (skill != null)
		{
			if ((activeChar.isTransformed() || activeChar.isInStance())
					&& !activeChar.containsAllowedTransformSkill(skill.getId()))
			{
				activeChar.sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			// _log.fine("	skill:"+skill.getName() + " level:"+skill.getLevel() + " passive:"+skill.isPassive());
			// _log.fine("	range:"+skill.getCastRange()+" targettype:"+skill.getTargetType()+" optype:"+skill.getOperateType()+" power:"+skill.getPower());
			// _log.fine("	reusedelay:"+skill.getReuseDelay()+" hittime:"+skill.getHitTime());
			// _log.fine("	currentState:"+activeChar.getCurrentState());	//for debug
			
			// If Alternate rule Karma punishment is set to true, forbid skill Return to player with Karma
			if (skill.getSkillType() == L2SkillType.RECALL && !Config.ALT_GAME_KARMA_PLAYER_CAN_TELEPORT && activeChar.getKarma() > 0)
				return;
			
			// players mounted on pets cannot use any toggle skills
			if (skill.isToggle() && activeChar.isMounted())
				return;
			
			activeChar.useMagic(skill, _ctrlPressed, _shiftPressed);
		     
            // Stop if use self-buff. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use heal-buffs.
            if(skill.getSkillType() == L2SkillType.HEAL && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use heal percent self buffs. 
            if(skill.getSkillType() == L2SkillType.HEAL_PERCENT && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use general buffs etc. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use chant buffs. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_PARTY) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use servitor buffs. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_PET) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		 
		    // Stop if use servitor heal. 
            if(skill.getSkillType() == L2SkillType.HEAL && skill.getTargetType() == SkillTargetType.TARGET_PET) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use servitor recharge. 
            if(skill.getSkillType() == L2SkillType.MANARECHARGE && skill.getTargetType() == SkillTargetType.TARGET_PET) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use recharge. 
            if(skill.getSkillType() == L2SkillType.MANARECHARGE && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use cure poison etc. 
            if(skill.getSkillType() == L2SkillType.NEGATE && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		  
		    // Stop if use blessing of eva. 
            if(skill.getSkillType() == L2SkillType.HPMPHEAL_PERCENT && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use restoration. 
            if(skill.getSkillType() == L2SkillType.HOT && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use restoration impact. 
            if(skill.getSkillType() == L2SkillType.HEAL_PERCENT && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use group heal's. 
            if(skill.getSkillType() == L2SkillType.HEAL && skill.getTargetType() == SkillTargetType.TARGET_PARTY) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use pow/pof/pow. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_PARTY_MEMBER) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		 
		    // Stop if use summon servitor. 
            if(skill.getSkillType() == L2SkillType.SUMMON && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use Self Heal. 
            if(skill.getSkillType() == L2SkillType.HEAL && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use chant of life etc. 
            if(skill.getSkillType() == L2SkillType.HOT && skill.getTargetType() == SkillTargetType.TARGET_PARTY) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use braveheart etc. 
            if(skill.getSkillType() == L2SkillType.COMBATPOINTHEAL && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use bandage etc. 
            if(skill.getSkillType() == L2SkillType.NEGATE && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use body to mind. 
            if(skill.getSkillType() == L2SkillType.MANAHEAL && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use cubic party etc. 
            if(skill.getSkillType() == L2SkillType.SUMMON && skill.getTargetType() == SkillTargetType.TARGET_PARTY) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use summon cp potion. 
            if(skill.getSkillType() == L2SkillType.CREATE_ITEM && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use symbol of energy etc. 
            if(skill.getSkillType() == L2SkillType.SIGNET && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use simple cubic etc. 
            if(skill.getSkillType() == L2SkillType.SUMMON && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use flames of invincibility. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_PARTY_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use mass recharge. 
            if(skill.getSkillType() == L2SkillType.MANARECHARGE && skill.getTargetType() == SkillTargetType.TARGET_PARTY) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use common craft. 
            if(skill.getSkillType() == L2SkillType.COMMON_CRAFT && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use dwarven craft. 
            if(skill.getSkillType() == L2SkillType.DWARVEN_CRAFT && skill.getTargetType() == SkillTargetType.TARGET_SELF) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use heroic valor. 
            if(skill.getSkillType() == L2SkillType.BUFF && skill.getTargetType() == SkillTargetType.TARGET_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use the heart of paagrio etc. 
            if(skill.getSkillType() == L2SkillType.HEAL && skill.getTargetType() == SkillTargetType.TARGET_PARTY_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use the honor of pa'agrio. 
            if(skill.getSkillType() == L2SkillType.COMBATPOINTHEAL && skill.getTargetType() == SkillTargetType.TARGET_PARTY_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use ritual of life. 
            if(skill.getSkillType() == L2SkillType.COMBATPOINTHEAL && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use miracle. 
            if(skill.getSkillType() == L2SkillType.HEAL_PERCENT && skill.getTargetType() == SkillTargetType.TARGET_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use purification field. 
            if(skill.getSkillType() == L2SkillType.CANCEL_DEBUFF && skill.getTargetType() == SkillTargetType.TARGET_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use cleanse - soul cleanse. 
            if(skill.getSkillType() == L2SkillType.CANCEL_DEBUFF && skill.getTargetType() == SkillTargetType.TARGET_ONE) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use benediction. 
            if(skill.getSkillType() == L2SkillType.HEAL_PERCENT && skill.getTargetType() == SkillTargetType.TARGET_PARTY) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use resurrection. 
            if(skill.getSkillType() == L2SkillType.RESURRECT && skill.getTargetType() == SkillTargetType.TARGET_CORPSE_PLAYER) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use mass resurrection. 
            if(skill.getSkillType() == L2SkillType.RESURRECT && skill.getTargetType() == SkillTargetType.TARGET_CORPSE_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		
		    // Stop if use victory of pa'agrio. 
            if(skill.getSkillType() == L2SkillType.CPHEAL_PERCENT && skill.getTargetType() == SkillTargetType.TARGET_PARTY_CLAN) 
            { 
                final PcPosition charPos = activeChar.getPosition(); 
                final L2CharPosition stopPos = new L2CharPosition(charPos.getX(), charPos.getY(), charPos.getZ(), charPos.getHeading()); 
                activeChar.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, stopPos); 
            }
		}
		else
		{
			activeChar.sendPacket(ActionFailed.STATIC_PACKET);
			_log.warning("No skill found with id " + _magicId + " and level " + level + " !!");
		}
	}
	
	/* (non-Javadoc)
	 * @see l2.brick.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__2F_REQUESTMAGICSKILLUSE;
	}
}