package sanchez.sanchez.sergio.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import sanchez.sanchez.sergio.utils.exceptions.FailedParsePermissionInformation;

public final class ResourceUtils {

    /**
     * Get String Resource By Name
     * @param context
     * @param identifier
     * @return
     */
    public static String getStringResourceByName(final Context context, final String identifier) {
        String packageName = context.getPackageName();
        int resId = context.getResources()
                .getIdentifier(identifier, "string", packageName);
        return context.getString(resId);
    }


    /**
     * Get Permissions Resource
     * @param c
     * @param hashMapResId
     * @return
     * @throws FailedParsePermissionInformation
     */
    public static Map<String, Map<String, String>> getPermissionsResource(Context c, int hashMapResId)
            throws FailedParsePermissionInformation {

        final XmlResourceParser parser = c.getResources().getXml(hashMapResId);

        Map<String, Map<String, String>> permissionRes = null;
        String permissionsKey = null, valueKey = null;
        Map<String, String> permissionValues = null;
        String value = null;

        try {
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    if (parser.getName().equals("map")) {
                        boolean isLinked = parser.getAttributeBooleanValue(null, "linked", false);
                        String name = parser.getAttributeValue(null, "name");
                        if(name.equals("permissions")) {
                            permissionRes = isLinked ? new LinkedHashMap<String, Map<String, String>>() : new HashMap<String, Map<String, String>>();
                        } else if(name.equals("permissions_text")) {
                            permissionValues = isLinked ? new LinkedHashMap<String, String>() : new HashMap<String, String>();
                        }
                    } else if (parser.getName().equals("entry")) {
                        String key = parser.getAttributeValue(null, "key");
                        if (null == key) {
                            parser.close();
                            return null;
                        }
                        String typeKey = parser.getAttributeValue(null, "type");
                        if(typeKey != null &&  typeKey.equals("permissions_key"))
                            permissionsKey = key;
                        else
                            valueKey = key;

                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    if (parser.getName().equals("entry")) {
                        if(valueKey != null) {
                            permissionValues.put(valueKey, value);
                            valueKey = null;
                            value = null;
                        } else {
                            permissionRes.put(permissionsKey, permissionValues);
                            permissionsKey = null;
                            permissionValues = null;
                        }
                    }
                } else if (eventType == XmlPullParser.TEXT) {
                    if (null != permissionValues) {
                        value = getStringResourceByName(c, parser.getText());
                    }
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new FailedParsePermissionInformation();
        }

        return permissionRes;
    }
}
