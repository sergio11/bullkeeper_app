package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.JwtAuthenticationResponseDTO;
import sanchez.sanchez.sergio.domain.models.AuthenticationResponseEntity;

/**
 * Authentication Response Entity Data Mapper
 */
public final class AuthenticationResponseEntityDataMapper
    extends AbstractDataMapper<JwtAuthenticationResponseDTO, AuthenticationResponseEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AuthenticationResponseEntity transform(final JwtAuthenticationResponseDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final AuthenticationResponseEntity authenticationResponseEntity =
                new AuthenticationResponseEntity();
        authenticationResponseEntity.setIdentity(originModel.getIdentity());
        authenticationResponseEntity.setToken(originModel.getToken());
        return authenticationResponseEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public JwtAuthenticationResponseDTO transformInverse(final AuthenticationResponseEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final JwtAuthenticationResponseDTO jwtAuthenticationResponseDTO =
                new JwtAuthenticationResponseDTO();
        jwtAuthenticationResponseDTO.setIdentity(originModel.getIdentity());
        jwtAuthenticationResponseDTO.setToken(originModel.getToken());
        return jwtAuthenticationResponseDTO;
    }
}
