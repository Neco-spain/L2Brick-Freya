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

import l2.brick.gameserver.model.actor.instance.L2PcInstance;

/**
 * This class ...
 *
 * @version $Revision: 1.2.2.1.2.3 $ $Date: 2005/03/27 15:29:30 $
 */
public class RequestPrivateStoreQuitBuy extends L2GameClientPacket
{
	private static final String _C__93_REQUESTPRIVATESTOREQUITBUY = "[C] 93 RequestPrivateStoreQuitBuy";
	
	//private static Logger _log = Logger.getLogger(RequestPrivateStoreQuitBuy.class.getName());
	
	@Override
	protected void readImpl()
	{
		// trigger
	}
	
	@Override
	protected void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		if (player == null)
			return;
		
		player.setPrivateStoreType(L2PcInstance.STORE_PRIVATE_NONE);
		player.standUp();
		player.broadcastUserInfo();
	}
	
	/* (non-Javadoc)
	 * @see l2.brick.gameserver.clientpackets.ClientBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _C__93_REQUESTPRIVATESTOREQUITBUY;
	}
	
	@Override
	protected boolean triggersOnActionRequest()
	{
		return false;
	}
}
