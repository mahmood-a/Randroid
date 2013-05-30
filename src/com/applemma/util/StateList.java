/*
 * Copyright (C) 2013 Mahmood Abdulla
 * 
 * This file is part of Randroid.
 * 
 * Randroid is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Randroid is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * Randroid. If not, see <http://www.gnu.org/licenses/>.
 */

package com.applemma.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Mahmood
 * 
 */
public class StateList<T>
{
	ArrayList<StateNode> mList;
	
	/**
	 * Adds the specified item at the end of this StateList.
	 * @param element The item to add.
	 */
	public void addNewItem(T element)
	{
		StateNode node = new StateNode(element, State.ADDED);
		mList.add(node);
	}
	
	public StateList(Collection<T> collection)
	{
		mList = new ArrayList<StateNode>();
		StateNode node;
		for(T item: collection)
		{
			node = new StateNode(item, State.UNCHANGED);
			mList.add(node);
		}
	}
	
	/**
	 * Returns the element at the specified location in this list.
	 * @param index
	 * @return
	 */
	public T get(int index)
	{
		StateNode node = mList.get(index);
		return null;
	}

	public enum State
	{
		UNCHANGED, MODIFIED, ADDED, DELETED
	}

	private class StateNode
	{
		T mElement;
		State mState;

		public StateNode(T item, State state)
		{
			mElement = item;
			mState = state;
		}

		@Override
		public String toString()
		{
			return mElement.toString();
		}

		public T getItem()
		{
			return mElement;
		}

		public void setElement(T element)
		{
			T tempItem = mElement;
			mElement = element;
			if (!tempItem.equals(element))
			{
				mState = State.MODIFIED;
			}
		}

		public void delete()
		{
			mElement = null;
			mState = State.DELETED;
		}

		public State getState()
		{
			return mState;
		}
	}
}
