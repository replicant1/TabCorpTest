package tab.com.au.codetest.races;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tab.com.au.codetest.R;
import tab.com.au.codetest.data.Races;

/**
 * Adapts {@link Races} to a {@link RecyclerView} in the form of a list.
 * Each list item is a single Race.
 */
public class RaceListAdapter extends RecyclerView.Adapter<RaceListItemViewHolder> {

	private static final String LOG_TAG = RaceListAdapter.class.getSimpleName();

	private final Races races;

	public RaceListAdapter(Races races) {
		this.races = races;
	}

	@Override
	public int getItemCount() {
		return races.getRaces().size();
	}

	@Override
	public void onBindViewHolder(RaceListItemViewHolder holder, int position) {
		holder.bind(races.getRaces().get(position));
	}

	@Override
	public RaceListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_race, parent, false);
		return new RaceListItemViewHolder(view);
	}
}
