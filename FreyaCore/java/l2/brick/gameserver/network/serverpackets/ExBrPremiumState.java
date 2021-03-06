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
package l2.brick.gameserver.network.serverpackets;
import l2.brick.gameserver.model.actor.instance.L2PcInstance;

/**
 * format: dc
 * @author GodKratos
 */

 public class ExBrPremiumState extends L2GameServerPacket
{
	private static final String _S__FE_BC_EXBRPREMIUMSTATE = "[S] FE:CD ExBrPremiumState";
	private L2PcInstance _activeChar;
	private int _state;
	
	public ExBrPremiumState(L2PcInstance activeChar, int state)
	{
		_activeChar = activeChar;
		_state = state;
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xfe);
		writeH(0xcd);
		writeD(_activeChar.getObjectId());
		writeC(_state);
	}
	
	@Override
	public String getType()
	{
		return _S__FE_BC_EXBRPREMIUMSTATE;
	}
}