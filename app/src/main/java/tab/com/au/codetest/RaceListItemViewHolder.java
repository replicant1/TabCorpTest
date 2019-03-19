package tab.com.au.codetest;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tab.com.au.codetest.data.Race;

/**
 * Created by rodbailey on 19/3/19.
 */

public class RaceListItemViewHolder extends RecyclerView.ViewHolder {

	private static final String LOG_TAG = RaceListItemViewHolder.class.getSimpleName();

	@BindView(R.id.tv_race_name)
	TextView raceNameTextView;

	@BindView(R.id.tv_race_time)
	TextView raceTimeTextView;

	@BindView(R.id.iv_race_thumbnail)
	ImageView raceImageView;

	public RaceListItemViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this,itemView);
	}

	public void bind(Race race) {
		Log.d(LOG_TAG, "bind: race=" + race);
//		raceNameTextView = itemView.findViewById(R.id.tv_race_name);
//		 raceTimeTextView = itemView.findViewById(R.id.tv_race_time);
//		ImageView raceImageView = itemView.findViewById(R.id.iv_race_thumbnail);

		// TODO: Get from formatted string
		raceNameTextView.setText(race.getRaceName() + " (" + race.getNumber() + ")");

		// TODO: Ignore date, show time in am.pm, change font color per current time.
		raceTimeTextView.setText(race.getRaceStartTime().toString());
		switch (race.getMeeting().getRaceType()) {
			case HARNESS:
				raceImageView.setImageResource(R.mipmap.ic_harness);
				break;

			case GREYHOUND:
				raceImageView.setImageResource(R.mipmap.ic_greyhound);
				break;

			case THOROUGHBRED:
				raceImageView.setImageResource(R.mipmap.ic_horse);
				break;

			case UNKNOWN:
				break;
		}
	}

	/**
	 * Note this view holder class must not be an "inner" class. It must not have the implicit enclosing
	 * instance that an "inner" class has. Omitting "inner" makes this Kotlin class the same as a nested
	 * static class in Java.
	 */
//	class NewsAssetListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//		@BindView(R.id.tv_news_asset_headline)
//		lateinit var headlineTextView: TextView
//
//		@BindView(R.id.tv_news_asset_byline)
//		lateinit var bylineTextView: TextView
//
//		@BindView(R.id.tv_news_asset_abstract)
//		lateinit var abstractTextView: TextView
//
//		@BindView(R.id.iv_news_asset_thumbnail)
//		lateinit var thumbnailImageView: ImageView
//
//		init {
//			ButterKnife.bind(this, itemView)
//		}
//
//		fun bind(item: NewsAssetDAO, clickListener: INewsAssetListItemClickListener) {
//			// Copy item data into text fields for headline, byline and abstract
//			headlineTextView.text = item.headline
//			bylineTextView.text = item.byline
//			abstractTextView.text = item.abstract
//
//			// Set click listener on this news asset - when clicked the user is transitioned to another
//					// screen that shows the full news article inside a web view.
//					itemView.setOnClickListener { clickListener.onNewsAssetClick(item) }
//
//			// TODO: Clear any current loading even if there is no replacement thumbnail
//			val potentialThumbnails = item.relatedImages
//			if (potentialThumbnails != null) {
//				val thumbnail = SimpleThumbnailSelectionStrategy().selectThumbnail(potentialThumbnails)
//
//				// Cancel any outstanding Glide loads into the image view, then start a load of the thumbnail image
//				// for the newly bound item. 60dp to pixels.
//				val thumbnailHeightPx = itemView.context.resources.getDimension(R.dimen.asset_list_thumbnail_image_height).toInt()
//				val thumbnailWidthPx = itemView.context.resources.getDimension(R.dimen.asset_list_thumbnail_image_width).toInt()
//
//				Timber.d("Loading thumbnail for item ${item.headline} which is at url ${thumbnail?.url}, " +
//						"width=${thumbnail?.width}, height=${thumbnail?.height}")
//
//				GlideApp.with(itemView).clear(thumbnailImageView)
//				GlideApp.with(itemView)
//						.load(thumbnail?.url)
//						.centerCrop()
//						.override(thumbnailWidthPx, thumbnailHeightPx)
//						.diskCacheStrategy(DiskCacheStrategy.ALL)
//						.into(thumbnailImageView)
//
//			}
//		}
}
