package sanchez.sanchez.sergio.bullkeeper.ui.models;

import com.stfalcon.chatkit.commons.models.IUser;

/**
 * Conversation Message User
 */
public final class ConversationMessageUser implements IUser {

    private String id;
    private String name;
    private String avatar;
    private boolean online;

    public ConversationMessageUser(final String id, final String name,
                                   final String avatar, final boolean online) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public boolean isOnline() {
        return online;
    }
}
