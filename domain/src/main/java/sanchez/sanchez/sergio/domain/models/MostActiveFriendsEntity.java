package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

public final class MostActiveFriendsEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Friends
     */
    private List<SocialMediaFriendEntity> friends;

    public MostActiveFriendsEntity(){}

    public MostActiveFriendsEntity(String title, List<SocialMediaFriendEntity> friends) {
        this.title = title;
        this.friends = friends;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SocialMediaFriendEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<SocialMediaFriendEntity> friends) {
        this.friends = friends;
    }
}
