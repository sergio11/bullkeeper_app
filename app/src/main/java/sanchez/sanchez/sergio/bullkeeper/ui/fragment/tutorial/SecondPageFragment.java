package sanchez.sanchez.sergio.bullkeeper.ui.fragment.tutorial;

import android.support.annotation.NonNull;

import com.cleveroad.slidingtutorial.Direction;
import com.cleveroad.slidingtutorial.PageSupportFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Second Page Fragment
 */
public final class SecondPageFragment extends PageSupportFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.second_page_fragment_layout;
    }

    @NonNull
    @Override
    protected TransformItem[] getTransformItems() {
        return new TransformItem[] {
                TransformItem.create(R.id.titleText, Direction.LEFT_TO_RIGHT, 0.2f),
        };
    }
}
