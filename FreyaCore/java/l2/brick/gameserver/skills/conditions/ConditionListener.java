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
package l2.brick.gameserver.skills.conditions;

/**
 * The listener interface for receiving condition events.
 * The class that is interested in processing a condition
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addConditionListener<code> method. When
 * the condition event occurs, that object's appropriate
 * method is invoked.
 *
 * @author mkizub
 */
public interface ConditionListener
{
	
	/**
	 * Notify changed.
	 */
	public void notifyChanged();
}
