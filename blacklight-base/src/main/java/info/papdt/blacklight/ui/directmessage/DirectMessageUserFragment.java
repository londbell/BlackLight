/* 
 * Copyright (C) 2015 Peter Cai
 *
 * This file is part of BlackLight
 *
 * BlackLight is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * BlackLight is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with BlackLight.  If not, see <http://www.gnu.org/licenses/>.
 */

package info.papdt.blacklight.ui.directmessage;

import info.papdt.blacklight.api.remind.RemindApi;
import info.papdt.blacklight.api.remind.RemindApi.Type;
import info.papdt.blacklight.cache.directmessages.DirectMessagesUserApiCache;
import info.papdt.blacklight.support.adapter.DirectMessageUserAdapter;
import info.papdt.blacklight.ui.statuses.AbsTimeLineFragment;

public class DirectMessageUserFragment extends AbsTimeLineFragment<DirectMessageUserAdapter>
{
	private DirectMessagesUserApiCache mApiCache;

	@Override
	protected DirectMessageUserAdapter buildAdapter() {
		return new DirectMessageUserAdapter(getActivity(), mApiCache.mUsers, mList);
	}

	@Override
	protected void onCreateCache() {
		mApiCache = new DirectMessagesUserApiCache(getActivity());
	}

	@Override
	protected void loadFromCache() {
		mApiCache.loadFromCache();
	}

	@Override
	protected int getCacheSize() {
		return mApiCache.mUsers.getSize();
	}

	@Override
	protected void cacheLoadNew(boolean param) {
		if (param) {
			mApiCache.mUsers.getList().clear();
		}

		mApiCache.load(param);
		mApiCache.cache();

		if (param) {
			RemindApi.clearUnread(Type.Dm.str);
		}
	}

	@Override
	protected int getCurrentItemCount() {
		return mAdapter.getCount();
	}

	@Override
	protected void initTitle() {
		// Do nothing
	}
}
