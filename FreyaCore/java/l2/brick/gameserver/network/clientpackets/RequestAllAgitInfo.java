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

import l2.brick.gameserver.network.L2GameClient;
import l2.brick.gameserver.network.serverpackets.ExShowAgitInfo;

/**
 *
 * @author  KenM
 */
public class RequestAllAgitInfo extends L2GameClientPacket
{
	
	/**
	 * @see l2.brick.gameserver.network.clientpackets.L2GameClientPacket#getType()
	 */
	@Override
	public String getType()
	{
		return "[C] D0:41 RequestAllAgitInfo";
	}
	
	/**
	 * @see l2.brick.gameserver.network.clientpackets.L2GameClientPacket#readImpl()
	 */
	@Override
	protected void readImpl()
	{
		
	}
	
	/**
	 * @see l2.brick.gameserver.network.clientpackets.L2GameClientPacket#runImpl()
	 */
	@Override
	protected void runImpl()
	{
		L2GameClient client = this.getClient();
		if (client != null)
		{
			client.sendPacket(new ExShowAgitInfo());
		}
	}
	
}
