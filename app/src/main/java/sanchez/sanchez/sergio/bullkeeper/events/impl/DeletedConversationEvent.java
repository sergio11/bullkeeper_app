package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IMessageEventVisitor;

/**
 * Deleted Conversation Event
 */
public final class DeletedConversationEvent extends SupportEvent<IMessageEventVisitor> {

    /**
     * Args
     */
    private static final String CONVERSATION_ARG = "CONVERSATION_ARG";
    private static final String MEMBER_ONE_ARG = "MEMBER_ONE_ARG";
    private static final String MEMBER_TWO_ARG = "MEMBER_TWO_ARG";


    private String conversation;
    private String memberOne;
    private String memberTwo;

    /**
     *
     * @param conversation
     * @param memberOne
     * @param memberTwo
     */
    public DeletedConversationEvent(final String conversation, final String memberOne, final String memberTwo) {
        this.conversation = conversation;
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
    }


    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getMemberOne() {
        return memberOne;
    }

    public void setMemberOne(String memberOne) {
        this.memberOne = memberOne;
    }

    public String getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(String memberTwo) {
        this.memberTwo = memberTwo;
    }

    /**
     * To Intent
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent intent = new Intent(getClass().getCanonicalName());
        final Bundle bundle = new Bundle();
        bundle.putString(CONVERSATION_ARG, conversation);
        bundle.putString(MEMBER_ONE_ARG, memberOne);
        bundle.putString(MEMBER_TWO_ARG, memberTwo);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(IMessageEventVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * From Bundle
     * @param bundle
     * @return
     */
    public static DeletedConversationEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String conversation = bundle.getString(CONVERSATION_ARG, "");
        final String memberOne = bundle.getString(MEMBER_ONE_ARG, "");
        final String memberTwo = bundle.getString(MEMBER_TWO_ARG, "");
        return new DeletedConversationEvent(conversation, memberOne, memberTwo);
    }

}
