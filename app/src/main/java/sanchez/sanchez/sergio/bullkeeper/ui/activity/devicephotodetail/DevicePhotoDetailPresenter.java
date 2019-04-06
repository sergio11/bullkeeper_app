package sanchez.sanchez.sergio.bullkeeper.ui.activity.devicephotodetail;

import android.os.Bundle;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;

/**
 * Device Photo Detail Presenter
 */
public final class DevicePhotoDetailPresenter extends SupportPresenter<IDevicePhotoDetailView> {

    @Inject
    public DevicePhotoDetailPresenter() {
        super();
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

    }

}
