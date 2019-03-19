package tab.com.au.codetest;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tab.com.au.codetest.data.Races;

/**
 * Created by rodbailey on 19/3/19.
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
		Log.d(LOG_TAG, "onBindViewHolder: position=" +position);
		holder.bind(races.getRaces().get(position));
	}

	@Override
	public RaceListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_race, parent, false);
		return new RaceListItemViewHolder(view);
	}

	//	class NewsAssetListAdapter(private val newsAssets: List<NewsAssetDAO>,
//	private val clickListener: INewsAssetListItemClickListener) :
//			RecyclerView.Adapter<NewsAssetListAdapter.NewsAssetListItemViewHolder>() {
//
//		override fun getItemCount(): Int {
//			return newsAssets.size
//		}
//
//		override fun onBindViewHolder(holder: NewsAssetListItemViewHolder, position: Int) {
//			holder.bind(newsAssets[position], clickListener)
//		}
//
//		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAssetListItemViewHolder {
//			val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_news_asset, parent,
//					false) as View
//			return NewsAssetListItemViewHolder(v)
//		}
}
