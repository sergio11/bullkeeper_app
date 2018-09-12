package sanchez.sanchez.sergio.bullkeeper.ui.fragment.tutorial;

import android.support.annotation.NonNull;
import com.cleveroad.slidingtutorial.Direction;
import com.cleveroad.slidingtutorial.PageSupportFragment;
import com.cleveroad.slidingtutorial.TransformItem;
import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * First Page Fragment
 */
public final class FirstPageFragment extends PageSupportFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.first_page_fragment_layout;
    }

    @NonNull
    @Override
    protected TransformItem[] getTransformItems() {
        return new TransformItem[] {
                TransformItem.create(R.id.titleText,
                        Direction.LEFT_TO_RIGHT, 0.2f),
                TransformItem.create(R.id.contentText,
                        Direction.RIGHT_TO_LEFT, 0.07f)
        };
    }
}
