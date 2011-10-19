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


/**
 *
 *
 * sample
 * 0000: 84 6d 06 00 00 36 05 00 00 42 4d 36 05 00 00 00    .m...6...BM6....
 * 0010: 00 00 00 36 04 00 00 28 00 00 00 10 00 00 00 10    ...6...(........
 * 0020: 00 00 00 01 00 08 00 00 00 00 00 00 01 00 00 c4    ................
 * 0030: ...
 * 0530: 10 91 00 00 00 60 9b d1 01 e4 6e ee 52 97 dd       .....`....n.R..
 *
 *
 *
 * format   dd x...x
 *
 * @version $Revision: 1.3.2.1.2.4 $ $Date: 2005/03/27 15:29:57 $
 */
public final class PledgeCrest extends L2GameServerPacket
{
	private static final String _S__84_PLEDGECREST = "[S] 6a PledgeCrest";
    private int _crestId;
    private int _crestSize;
    private byte[] _data;

    public PledgeCrest(int crestId,byte[] data)
	{
    	_crestId = crestId;
    	_data = data;
    	_crestSize = _data.length;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x6a);
		writeD(_crestId);
        writeD(_crestSize);
        writeB(_data);
	}
	
	/* (non-Javadoc)
	 * @see l2.brick.gameserver.serverpackets.ServerBasePacket#getType()
	 */
	@Override
	public String getType()
	{
		return _S__84_PLEDGECREST;
	}
}
