package sanchez.sanchez.sergio.masom_app.ui.activity.tutorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cleveroad.slidingtutorial.TutorialOptions;
import com.cleveroad.slidingtutorial.TutorialPageProvider;
import com.cleveroad.slidingtutorial.TutorialSupportFragment;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.FifthPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.FirstPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.FourthPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.SecondPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.ThirdPageFragment;

/**
 * App Tutorial Activity
 */
public class AppTutorialActivity extends AppCompatActivity {

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, AppTutorialActivity.class);
    }

    /**
     * Add Fragment
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_tutorial);

        if (savedInstanceState == null) {

            final TutorialOptions tutorialOptions = TutorialSupportFragment
                    .newTutorialOptionsBuilder(this)
                    .setUseAutoRemoveTutorialFragment(true)
                    .setUseInfiniteScroll(true)
                    .setTutorialPageProvider(new TutorialPageProvider<Fragment>() {
                        @NonNull
                        @Override
                        public Fragment providePage(int position) {
                            switch (position) {
                                case 0:
                                    return new FirstPageFragment();
                                case 1:
                                    return new SecondPageFragment();
                                case 2:
                                    return new ThirdPageFragment();
                                case 3:
                                    return new FourthPageFragment();
                                case 4:
                                    return new FifthPageFragment();
                                default:
                                    throw new IllegalArgumentException("Unknown position: " + position);
                            }
                        }
                    })
                    .setOnSkipClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    })
                    .setPagesCount(5)
                    .build();

            final TutorialSupportFragment tutorialFragment = TutorialSupportFragment
                    .newInstance(tutorialOptions);

            addFragment(R.id.fragmentContainer, tutorialFragment);

        }
    }

}
