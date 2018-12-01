package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;

/**
 * Conversation Module
 */
@Module
public class ConversationModule {

    /**
     * Provide Image Loader
     * @param picasso
     * @return
     */
    @Provides
    @PerActivity
    ImageLoader provideImageLoader(final Picasso picasso){
        return new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                picasso.load(url).into(imageView);
            }
        };
    }

}
