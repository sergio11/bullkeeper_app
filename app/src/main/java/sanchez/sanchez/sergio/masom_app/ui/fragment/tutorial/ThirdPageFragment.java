package sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial;

import android.support.annotation.NonNull;

import com.cleveroad.slidingtutorial.Direction;
import com.cleveroad.slidingtutorial.PageSupportFragment;
import com.cleveroad.slidingtutorial.TransformItem;

import sanchez.sanchez.sergio.masom_app.R;

/**
 * Third Page Fragment
 */
public class ThirdPageFragment extends PageSupportFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.third_page_fragment_layout;
    }

    @NonNull
    @Override
    protected TransformItem[] getTransformItems() {
        return new TransformItem[] {
                TransformItem.create(R.id.titleText, Direction.LEFT_TO_RIGHT, 0.2f),
        };
    }
}
