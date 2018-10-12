package sanchez.sanchez.sergio.domain.interactor.comments;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.Arrays;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Comments Interact
 */
public final class GetCommentsInteract extends UseCase<List<CommentEntity>, GetCommentsInteract.IParameterFilter> {

    /**
     * Comments Repository
     */
    private final ICommentsRepository commentsRepository;

    /**
     * Parameter Filter Visitor
     */
    private final IParameterFilterVisitor parameterFilterVisitor;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     */
    public GetCommentsInteract(final IThreadExecutor threadExecutor,
                               final IPostExecutionThread postExecutionThread, final ICommentsRepository commentsRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentsRepository = commentsRepository;
        this.parameterFilterVisitor = new ParameterFilterVisitor();
    }

    /**
     *
     * @param parameterFilter
     * @return
     */
    @Override
    protected Observable<List<CommentEntity>> buildUseCaseObservable(IParameterFilter parameterFilter) {
        Preconditions.checkNotNull(parameterFilter, "Parameter filter can not be null");
        Preconditions.checkNotNull(parameterFilterVisitor, "Parameter filter visitor can not be null");
        return parameterFilter.accept(parameterFilterVisitor);
    }


    /**
     * Types of input parameters
     * ============================
     */

    /**
     * Parameter Filter interface
     */
    public interface IParameterFilter extends ISupportVisitable<IParameterFilterVisitor> {

        /**
         * Accept
         * @param visitor
         * @return
         */
         Observable<List<CommentEntity>> accept(final IParameterFilterVisitor visitor);
    }


    /**
     * Basic Parameter Filter
     */
    public static class KidsFilter implements IParameterFilter  {

        /**
         * Identities
         */
        protected final String[] identities;

        /**
         * Days Ago
         */
        protected final int daysAgo;

        /**
         *
         * @param identities
         * @param daysAgo
         */
        private KidsFilter(String[] identities, int daysAgo) {
            this.identities = identities;
            this.daysAgo = daysAgo;
        }

        public String[] getIdentities() {
            return identities;
        }

        public int getDaysAgo() {
            return daysAgo;
        }

        @Override
        public String toString() {
            return "KidsFilter{" +
                    "identities=" + Arrays.toString(identities) +
                    ", daysAgo=" + daysAgo +
                    '}';
        }

        /**
         * Create
         * @param identities
         * @param daysAgo
         * @return
         */
        public static IParameterFilter create(final String[] identities, final int daysAgo) {
            Preconditions.checkNotNull(identities, "Identities can not be null");
            Preconditions.checkState(identities.length > 0 , "Identities must be greater than 0");
            Preconditions.checkState(daysAgo > 0, "Days Ago must be greater than 0");
            return new KidsFilter(identities, daysAgo);
        }

        /**
         * Accept
         * @param visitor
         * @param data
         * @param <E>
         */
        @Override
        public <E> void accept(IParameterFilterVisitor visitor, E data) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            visitor.visit(this);
        }

        /**
         * Accept
         * @param visitor
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> accept(IParameterFilterVisitor visitor) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            return visitor.visit(this);
        }
    }

    /**
     * Kids And Social Media Filter
      */
    public static class KidsAndSocialMediaFilter extends KidsFilter {

        /**
         * Social Medias
         */
        protected final String[] socialMedias;


        /**
         * @param identities
         * @param daysAgo
         */
        private KidsAndSocialMediaFilter(final String[] identities, final int daysAgo, final String[] socialMedias) {
            super(identities, daysAgo);
            this.socialMedias = socialMedias;
        }

        public String[] getSocialMedias() {
            return socialMedias;
        }

        /**
         * Create
         * @param identities
         * @param daysAgo
         * @param socialMedias
         * @return
         */
        public static IParameterFilter create(final String[] identities, final int daysAgo, final String[] socialMedias) {
            return new KidsAndSocialMediaFilter(identities, daysAgo, socialMedias);
        }

        /**
         *
         * @param visitor
         * @param data
         * @param <E>
         */
        @Override
        public <E> void accept(IParameterFilterVisitor visitor, E data) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            visitor.visit(this);
        }

        /**
         * Accept
         * @param visitor
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> accept(IParameterFilterVisitor visitor) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            return visitor.visit(this);
        }
    }

    /**
     * Kids And Social Media And Dimension Level Filter
     */
    public static class KidsAndSocialMediaAndDimensionLevelFilter extends KidsAndSocialMediaFilter {


        protected ViolenceLevelEnum violenceLevelEnum = ViolenceLevelEnum.UNKNOWN;
        protected DrugsLevelEnum drugsLevelEnum = DrugsLevelEnum.UNKNOWN;
        protected BullyingLevelEnum bullyingLevelEnum = BullyingLevelEnum.UNKNOWN;
        protected AdultLevelEnum adultLevelEnum = AdultLevelEnum.UNKNOWN;

        /**
         *
         * @param identities
         * @param daysAgo
         * @param socialMedias
         * @param violenceLevelEnum
         * @param drugsLevelEnum
         * @param bullyingLevelEnum
         * @param adultLevelEnum
         */
        private KidsAndSocialMediaAndDimensionLevelFilter(final String[] identities, final int daysAgo,
                                                         final String[] socialMedias, ViolenceLevelEnum violenceLevelEnum,
                                                          DrugsLevelEnum drugsLevelEnum, BullyingLevelEnum bullyingLevelEnum,
                                                          AdultLevelEnum adultLevelEnum) {
            super(identities, daysAgo, socialMedias);
            this.violenceLevelEnum = violenceLevelEnum;
            this.drugsLevelEnum = drugsLevelEnum;
            this.bullyingLevelEnum = bullyingLevelEnum;
            this.adultLevelEnum = adultLevelEnum;
        }

        public ViolenceLevelEnum getViolenceLevelEnum() {
            return violenceLevelEnum;
        }

        public DrugsLevelEnum getDrugsLevelEnum() {
            return drugsLevelEnum;
        }

        public BullyingLevelEnum getBullyingLevelEnum() {
            return bullyingLevelEnum;
        }

        public AdultLevelEnum getAdultLevelEnum() {
            return adultLevelEnum;
        }

        /**
         * Create
         * @param identities
         * @param daysAgo
         * @param socialMedias
         * @param violenceLevelEnum
         * @param drugsLevelEnum
         * @param bullyingLevelEnum
         * @param adultLevelEnum
         * @return
         */
        public static IParameterFilter create(final String[] identities, final int daysAgo,
                                              final String[] socialMedias, final ViolenceLevelEnum violenceLevelEnum,
                                              final DrugsLevelEnum drugsLevelEnum, final BullyingLevelEnum bullyingLevelEnum,
                                              final AdultLevelEnum adultLevelEnum) {

            return new KidsAndSocialMediaAndDimensionLevelFilter(identities, daysAgo,
                    socialMedias, violenceLevelEnum, drugsLevelEnum, bullyingLevelEnum,
                    adultLevelEnum);
        }

        /**
         * Accept
         * @param visitor
         * @param data
         * @param <E>
         */
        @Override
        public <E> void accept(final IParameterFilterVisitor visitor, E data) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            visitor.visit(this);
        }

        /**
         * Accept
         * @param visitor
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> accept(final IParameterFilterVisitor visitor) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            return visitor.visit(this);
        }
    }

    /**
     * Kids And Social Media And Dimension Level And Author Filter
     */
    public static class KidsAndSocialMediaAndDimensionLevelAndAuthorFilter
        extends KidsAndSocialMediaAndDimensionLevelFilter {

        /**
         * Author identity
         */
        protected final String author;


        /**
         * @param identities
         * @param daysAgo
         * @param socialMedias
         * @param violenceLevelEnum
         * @param drugsLevelEnum
         * @param bullyingLevelEnum
         * @param adultLevelEnum
         * @param author
         */
        private KidsAndSocialMediaAndDimensionLevelAndAuthorFilter(final String[] identities, final int daysAgo,
                                                                   final String[] socialMedias, final ViolenceLevelEnum violenceLevelEnum,
                                                                   final DrugsLevelEnum drugsLevelEnum, final BullyingLevelEnum bullyingLevelEnum,
                                                                   final AdultLevelEnum adultLevelEnum, final String author) {
            super(identities, daysAgo, socialMedias, violenceLevelEnum, drugsLevelEnum, bullyingLevelEnum, adultLevelEnum);
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        /**
         *
         * @param identities
         * @param daysAgo
         * @param socialMedias
         * @param violenceLevelEnum
         * @param drugsLevelEnum
         * @param bullyingLevelEnum
         * @param adultLevelEnum
         * @param author
         * @return
         */
        public static IParameterFilter create(final String[] identities, final int daysAgo,
                                              final String[] socialMedias, final ViolenceLevelEnum violenceLevelEnum,
                                              final DrugsLevelEnum drugsLevelEnum, final BullyingLevelEnum bullyingLevelEnum,
                                              final AdultLevelEnum adultLevelEnum, final String author){
            return new KidsAndSocialMediaAndDimensionLevelAndAuthorFilter(identities, daysAgo, socialMedias, violenceLevelEnum, drugsLevelEnum, bullyingLevelEnum, adultLevelEnum, author);
        }

        /**
         * Accept
         * @param visitor
         * @param data
         * @param <E>
         */
        @Override
        public <E> void accept(IParameterFilterVisitor visitor, E data) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            visitor.visit(this);
        }

        /**
         *
         * @param visitor
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> accept(IParameterFilterVisitor visitor) {
            Preconditions.checkNotNull(visitor, "Visitor can not be null");
            return visitor.visit(this);
        }
    }


    /**
     * Parameter Filter Visitor
     */
    interface IParameterFilterVisitor extends ISupportVisitor {

        /**
         * Visit Kids Filter
         * @param kidsFilter
         * @return
         */
        Observable<List<CommentEntity>> visit(final KidsFilter kidsFilter);

        /**
         * Visit Kids And Social Media Filter
         * @param kidsAndSocialMediaFilter
         * @return
         */
        Observable<List<CommentEntity>> visit(final KidsAndSocialMediaFilter kidsAndSocialMediaFilter);

        /**
         * Visit Kids And Social Media And Dimensions Level Filter
         * @param kidsAndSocialMediaAndDimensionLevelFilter
         * @return
         */
        Observable<List<CommentEntity>> visit(final KidsAndSocialMediaAndDimensionLevelFilter kidsAndSocialMediaAndDimensionLevelFilter);


        /**
         * Visit Kids And Social Media And Dimension Level And Author Filter
         * @param kidsAndSocialMediaAndDimensionLevelAndAuthorFilter
         * @return
         */
        Observable<List<CommentEntity>> visit(final KidsAndSocialMediaAndDimensionLevelAndAuthorFilter kidsAndSocialMediaAndDimensionLevelAndAuthorFilter);
    }

    /**
     * Parameter Filter Visitor
     */
    class ParameterFilterVisitor implements IParameterFilterVisitor {

        /**
         * Visit Kids Filter
         * @param kidsFilter
         */
        @Override
        public Observable<List<CommentEntity>> visit(KidsFilter kidsFilter) {
            Preconditions.checkNotNull(kidsFilter, "Basic parameter filter can not be null");
            Preconditions.checkNotNull(kidsFilter.getIdentities(), "Identities can not be null");
            Preconditions.checkState(kidsFilter.getIdentities().length > 0 , "Identities must be greater than 0");
            Preconditions.checkState(kidsFilter.getDaysAgo() > 0, "Days Ago must be greater than 0");

            return commentsRepository.getComments(kidsFilter.getIdentities(), kidsFilter.getDaysAgo());
        }

        /**
         * Visit Kids And Social Media Filter
         * @param kidsAndSocialMediaFilter
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> visit(KidsAndSocialMediaFilter kidsAndSocialMediaFilter) {
            Preconditions.checkNotNull(kidsAndSocialMediaFilter, "Filter can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaFilter.getIdentities(), "Identities can not be null");
            Preconditions.checkState(kidsAndSocialMediaFilter.getIdentities().length > 0, "Identities can not be empty");
            Preconditions.checkNotNull(kidsAndSocialMediaFilter.getSocialMedias(), "Social Media can not be null");
            Preconditions.checkState(kidsAndSocialMediaFilter.getSocialMedias().length > 0, "Social Media can not be empty");

            return commentsRepository.getComments(kidsAndSocialMediaFilter.getIdentities(),
                    kidsAndSocialMediaFilter.getDaysAgo(), kidsAndSocialMediaFilter.getSocialMedias());
        }

        /**
         * Visit Kids And Social Media And Dimension Level Filter
         * @param kidsAndSocialMediaAndDimensionLevelFilter
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> visit(KidsAndSocialMediaAndDimensionLevelFilter kidsAndSocialMediaAndDimensionLevelFilter) {
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter, "Filter can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter.getIdentities(), "Identities can not be null");
            Preconditions.checkState(kidsAndSocialMediaAndDimensionLevelFilter.getIdentities().length > 0, "Identities can not be empty");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter.getSocialMedias(), "Social Media can not be null");
            Preconditions.checkState(kidsAndSocialMediaAndDimensionLevelFilter.getSocialMedias().length > 0, "Social Media can not be empty");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter.getViolenceLevelEnum(), "Violence level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter.getBullyingLevelEnum(), "Bullying level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter.getAdultLevelEnum(), "Adult level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelFilter.getDrugsLevelEnum(), "Drugs level can not be null");

            return commentsRepository.getComments(kidsAndSocialMediaAndDimensionLevelFilter.getIdentities(),
                    kidsAndSocialMediaAndDimensionLevelFilter.getDaysAgo(), kidsAndSocialMediaAndDimensionLevelFilter.getSocialMedias(),
                    kidsAndSocialMediaAndDimensionLevelFilter.getViolenceLevelEnum(), kidsAndSocialMediaAndDimensionLevelFilter.getDrugsLevelEnum(),
                    kidsAndSocialMediaAndDimensionLevelFilter.getBullyingLevelEnum(), kidsAndSocialMediaAndDimensionLevelFilter.getAdultLevelEnum());
        }

        /**
         * Visit Kids And Social Media And Dimension Level And Author Filter
         * @param kidsAndSocialMediaAndDimensionLevelAndAuthorFilter
         * @return
         */
        @Override
        public Observable<List<CommentEntity>> visit(KidsAndSocialMediaAndDimensionLevelAndAuthorFilter kidsAndSocialMediaAndDimensionLevelAndAuthorFilter) {
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter, "Filter can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getIdentities(), "Identities can not be null");
            Preconditions.checkState(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getIdentities().length > 0, "Identities can not be empty");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getSocialMedias(), "Social Media can not be null");
            Preconditions.checkState(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getSocialMedias().length > 0, "Social Media can not be empty");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getViolenceLevelEnum(), "Violence level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getBullyingLevelEnum(), "Bullying level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getAdultLevelEnum(), "Adult level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getDrugsLevelEnum(), "Drugs level can not be null");
            Preconditions.checkNotNull(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getAuthor(), "Author can not be null");
            Preconditions.checkState(!kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getAuthor().isEmpty(), "Author can not be empty");

            return commentsRepository.getComments(kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getIdentities(),
                    kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getDaysAgo(), kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getSocialMedias(),
                    kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getAuthor(), kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getViolenceLevelEnum(),
                    kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getDrugsLevelEnum(), kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getBullyingLevelEnum(),
                    kidsAndSocialMediaAndDimensionLevelAndAuthorFilter.getAdultLevelEnum());
        }
    }

    /**
     * Error Handling
     * =======================
     */

    /**
     * Get Comments Api Error
     */
    public enum GetCommentsApiErrors implements ISupportVisitable<GetCommentsApiErrors.IGetCommentsApiErrorsVisitor> {

        /**
         * Comments Not Found
         */
        COMMENTS_NOT_FOUND(){
            @Override
            public <E> void accept(IGetCommentsApiErrorsVisitor visitor, E data) {
                visitor.visitCommentsNotFound(this);
            }
        };

        /**
         * Get Comments Api Errors Visitor
         */
        public interface IGetCommentsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit Comments Not Found
             * @param apiErrors
             */
            void visitCommentsNotFound(final GetCommentsApiErrors apiErrors);
        }
    }

}
