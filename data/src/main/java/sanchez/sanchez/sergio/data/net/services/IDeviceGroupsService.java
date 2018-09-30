package sanchez.sanchez.sergio.data.net.services;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveDeviceDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.DeviceDTO;

/**
 * Device Groups Service
 */
public interface IDeviceGroupsService {

    /**
     * Save
     * @param saveDevice
     * @return
     */
    @POST("device-groups/devices/save")
    Observable<APIResponse<DeviceDTO>> save(@Body  final SaveDeviceDTO saveDevice);


    /**
     * Delete
     * @param deviceId
     * @return
     */
    @DELETE("device-groups/devices/{id}/delete")
    Observable<APIResponse<String>> delete(@Path("id") final String deviceId);

}
