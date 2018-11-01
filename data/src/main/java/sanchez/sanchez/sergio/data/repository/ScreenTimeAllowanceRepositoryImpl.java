package sanchez.sanchez.sergio.data.repository;

import javax.inject.Inject;

import sanchez.sanchez.sergio.data.net.services.IScreenTimeAllowanceService;
import sanchez.sanchez.sergio.domain.repository.IScreenTimeAllowanceRepository;


/**
 * Screen Time Allowance Repository
 */
public final class ScreenTimeAllowanceRepositoryImpl implements IScreenTimeAllowanceRepository {

    private final IScreenTimeAllowanceService screenTimeAllowanceService;

    /**
     *
     * @param screenTimeAllowanceService
     */
    @Inject
    public ScreenTimeAllowanceRepositoryImpl(final IScreenTimeAllowanceService screenTimeAllowanceService) {
        this.screenTimeAllowanceService = screenTimeAllowanceService;
    }

}
